package org.openRealmOfStars.player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.openRealmOfStars.AI.Mission.MissionList;
import org.openRealmOfStars.AI.PathFinding.PathPoint;
import org.openRealmOfStars.player.InitalSetRace.InitialSetDefault;
import org.openRealmOfStars.player.InitalSetRace.InitialSetGreyans;
import org.openRealmOfStars.player.InitalSetRace.InitialSetRaces;
import org.openRealmOfStars.player.InitalSetRace.InitialSetSporks;
import org.openRealmOfStars.player.SpaceRace.SpaceRace;
import org.openRealmOfStars.player.SpaceRace.SpaceRaceUtility;
import org.openRealmOfStars.player.diplomacy.Diplomacy;
import org.openRealmOfStars.player.fleet.Fleet;
import org.openRealmOfStars.player.fleet.FleetList;
import org.openRealmOfStars.player.message.MessageList;
import org.openRealmOfStars.player.ship.ShipStat;
import org.openRealmOfStars.player.tech.TechList;
import org.openRealmOfStars.starMap.Coordinate;
import org.openRealmOfStars.starMap.StarMap;
import org.openRealmOfStars.starMap.Sun;
import org.openRealmOfStars.utilities.ErrorLogger;
import org.openRealmOfStars.utilities.IOUtilities;

/**
 *
 * Open Realm of Stars game project
 * Copyright (C) 2016, 2017  Tuomo Untinen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/
 *
 *
 * Player info contains which race player has, planet etc
 * Player here means both Human and AI players
 *
 */

public class PlayerInfo {

  /**
   * Player's space race
   */
  private SpaceRace race;

  /**
   * Player's empire name
   */
  private String empireName;

  /**
   * Total credits for player, these should not go negative
   */
  private int totalCredits;

  /**
   * Technology list that player has studied or gained
   */
  private TechList techList;

  /**
   * Message for player for one turn
   */
  private MessageList msgList;

  /**
   * Space ship stat and design list
   */
  private ArrayList<ShipStat> shipStatList;

  /**
   * Player fleets
   */
  private FleetList fleets;

  /**
   * Map Data
   * 0: Uncharted only suns are drawn
   * 1: Fog of war, no fleets are drawn
   * 2: Visible everything is drawn
   */
  private byte[][] mapData;

  /**
   * Cloaking detection per sector
   */
  private int[][] mapCloakDetection;

  /**
   * Map size
   */
  private Coordinate maxCoordinate;

  /**
   * Human player if true
   */
  private boolean human;

  /**
   * Missions list
   */
  private MissionList missions;

  /**
   * Player's diplomacy relations to other players
   */
  private Diplomacy diplomacy;

  /**
   * Uncharted map sector, only suns are visible
   */
  public static final byte UNCHARTED = 0;
  /**
   * Fog of war, no fleets are drawn
   */
  public static final byte FOG_OF_WAR = 1;
  /**
   * Every thing are drawn
   */
  public static final byte VISIBLE = 2;

  /**
   * Constructor player info.
   * @param race Space Race for player
   * @param maxPlayers Maximum number of players when game is created
   * @param index Player's index in list when creating the player
   */
  public PlayerInfo(final SpaceRace race, final int maxPlayers,
      final int index) {
    setTechList(new TechList());
    this.msgList = new MessageList();
    shipStatList = new ArrayList<>();
    fleets = new FleetList();
    setHuman(false);
    setRace(race);
    InitialSetRaces initialSet = null;
    diplomacy = new Diplomacy(maxPlayers, index);
    switch (getRace()) {
      case HUMAN:
      case MECHIONS:
      case CENTAURS: {
      /*
       * Humans, Mechions and Centaurs get 1 Combat, 1 Defense, Scout and Colony
       */
        initialSet = new InitialSetDefault();
        break;
      }
      case SPORKS: {
        /*
         * Sporks get 2 Combat, 1 Defense, Scout and Colony
         */
        initialSet = new InitialSetSporks();
        break;
      }
      case GREYANS: {
      /*
       * Greyans get 1 Combat, 1 Defense, Scout and Colony, 1 propulsion, 1
       * electronics
       */
        initialSet = new InitialSetGreyans();
        break;
      }
      default:
        ErrorLogger.log("Unexpected race:" + getRace());
    }
    initialSet.setRacesInfo(techList, this);
  }

  /**
   * Constructor player info.
   * This constructor should be used only when
   * creating temporary playerInfo without real
   * diplomacy information. For example in JUnits.
   * @param race Space Race for player
   */
  public PlayerInfo(final SpaceRace race) {
    this(race, 4, 0);
  }

  /**
   * Read PlayerInfo from DataInputStream
   * @param dis DataInputStream
   * @param maxPlayers Maximum players in list when game was created.
   * @param index Current player index
   * @throws IOException if there is any problem with DataInputStream
   */
  public PlayerInfo(final DataInputStream dis, final int maxPlayers,
      final int index) throws IOException {
    empireName = IOUtilities.readString(dis);
    race = SpaceRaceUtility.getRaceByIndex(dis.readInt());
    totalCredits = dis.readInt();
    techList = new TechList(dis);
    msgList = new MessageList(dis);
    int count = dis.readInt();
    shipStatList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ShipStat ship = new ShipStat(dis);
      shipStatList.add(ship);
    }
    fleets = new FleetList(dis);
    int xSize = dis.readInt();
    int ySize = dis.readInt();
    initMapData(xSize, ySize);
    int mapOffset = 0;
    try {
      for (int y = 0; y < maxCoordinate.getY(); y++) {
        for (int x = 0; x < maxCoordinate.getX(); x++) {
          mapData[x][y] = dis.readByte();
          mapOffset++;
        }
      }
    } catch (IOException e) {
      throw new IOException("Reading failed at player mapdata! MapOffset:"
          + mapOffset + " " + e.getMessage());
    }
    human = dis.readBoolean();
    if (!human) {
      missions = new MissionList(dis);
    }
    // Still just creating the diplomacy not actually loading it
    diplomacy = new Diplomacy(maxPlayers, index);
  }

  /**
   * Save Player Info to DataOutputStream
   * @param dos DataOutputStream
   * @throws IOException if there is any problem with DataOutputStream
   */
  public void savePlayerInfo(final DataOutputStream dos) throws IOException {
    IOUtilities.writeString(dos, empireName);
    dos.writeInt(race.getIndex());
    dos.writeInt(totalCredits);
    techList.saveTechList(dos);
    msgList.saveMessageList(dos);
    dos.writeInt(shipStatList.size());
    for (int i = 0; i < shipStatList.size(); i++) {
      shipStatList.get(i).saveShipStat(dos);
    }
    fleets.saveFleetList(dos);
    dos.writeInt(maxCoordinate.getX());
    dos.writeInt(maxCoordinate.getY());
    if (mapData == null) {
      throw new IOException("Map data is not initialized yet!");
    }
    for (int y = 0; y < maxCoordinate.getY(); y++) {
      for (int x = 0; x < maxCoordinate.getX(); x++) {
        dos.writeByte(mapData[x][y]);
      }
    }
    dos.writeBoolean(human);
    if (!human) {
      missions.saveMissionList(dos);
    }
  }

  /**
   * Get Player diplomatic relations to other players
   * @return Diplomacy
   */
  public Diplomacy getDiplomacy() {
    return diplomacy;
  }
  /**
   * Calculate how many uncharted sectors is between start and end
   * @param sx Start X coordinate
   * @param sy Start Y coordinate
   * @param ex End X coordinate
   * @param ey End Y coordinate
   * @return Number of uncharted sector
   */
  private int calculateUnchartedLine(final int sx, final int sy, final int ex,
      final int ey) {
    double startX = sx;
    double startY = sy;
    double dx = Math.abs(startX - ex);
    double dy = Math.abs(startY - ey);
    // Calculate distance to end
    int distance = (int) dy;
    if (dx > dy) {
      distance = (int) dx;
    }
    int result = 0;
    double mx;
    double my;
    // Calculate how much move each round
    if (distance > 0) {
      mx = (ex - startX) / distance;
      my = (ey - startY) / distance;
    } else {
      mx = 0;
      my = 0;
    }
    // Moving loop
    for (int i = 0; i < distance; i++) {
      startX = startX + mx;
      startY = startY + my;
      int nx = (int) Math.round(startX);
      int ny = (int) Math.round(startY);
      if (new Coordinate(nx, ny).isValidCoordinate(maxCoordinate)
          && mapData[nx][ny] == UNCHARTED) {
        result++;
      }
    }
    return result;
  }

  /**
  * Get best sector to explore in this Solar system
  * @param sun Solar System
  * @param fleet Fleet doing the exploring
  * @return How many percentage is uncharted
  */
  public int getUnchartedValueSystem(final Sun sun, final Fleet fleet) {
    int unCharted = 0;
    int charted = 0;
    for (int x = -StarMap.SOLAR_SYSTEM_WIDTH - 2;
        x < StarMap.SOLAR_SYSTEM_WIDTH + 3; x++) {
      for (int y = -StarMap.SOLAR_SYSTEM_WIDTH - 2;
          y < StarMap.SOLAR_SYSTEM_WIDTH + 3; y++) {
        Coordinate coordinate = new Coordinate(sun.getCenterX() + x,
            sun.getCenterY() + y);
        if (coordinate.isValidCoordinate(maxCoordinate)
            && (x > 1 || x < -1 || y > 1 || y < -1)) {
          if (mapData[sun.getCenterX() + x][sun.getCenterY()
              + y] == UNCHARTED) {
            unCharted++;
          } else {
            charted++;
          }
        }
      }
    }
    unCharted = 100 * unCharted / (charted + unCharted);
    return unCharted;
  }

  /**
   * Get best sector to explore in this Solar system
   * @param sun Solar System
   * @param fleet Fleet doing the exploring
   * @return PathPoint where to go next or null if no more exploring
   */
  public PathPoint getUnchartedSector(final Sun sun, final Fleet fleet) {
    PathPoint result = null;
    int scan = fleet.getFleetScannerLvl();
    int[] unCharted = new int[4];
    int[] charted = new int[4];
    int[] sectors = new int[4];
    PathPoint[] points = new PathPoint[4];
    int[] bestPoint = new int[4];
    for (int i = 0; i < points.length; i++) {
      points[i] = null;
    }
    int sector = 0;
    for (int x = -StarMap.SOLAR_SYSTEM_WIDTH
        - 2; x < StarMap.SOLAR_SYSTEM_WIDTH + 3; x++) {
      for (int y = -StarMap.SOLAR_SYSTEM_WIDTH
          - 2; y < StarMap.SOLAR_SYSTEM_WIDTH + 3; y++) {
        if (x <= 0 && y <= 0) {
          sector = 0;
        } else if (x > 0 && y <= 0) {
          sector = 1;
        } else if (x <= 0 && y > 0) {
          sector = 2;
        } else if (x > 0 && y > 0) {
          sector = 3;
        }
        Coordinate sectorCoordinate =
            new Coordinate(sun.getCenterX() + x, sun.getCenterY() + y);
        if (sectorCoordinate.isValidCoordinate(maxCoordinate)
            && (x > 1 || x < -1 || y > 1 || y < -1)) {
          if (mapData[sun.getCenterX() + x][sun.getCenterY()
              + y] == UNCHARTED) {
            unCharted[sector]++;
            Coordinate fleetCoordinate =
                new Coordinate(fleet.getX(), fleet.getY());
            PathPoint tempPoint = new PathPoint(sun.getCenterX() + x,
                sun.getCenterY() + y,
                fleetCoordinate.calculateDistance(sectorCoordinate));
            int value = calculateUnchartedLine(fleet.getX(), fleet.getY(),
                sun.getCenterX() + x, sun.getCenterY() + y);
            if (points[sector] == null) {
              points[sector] = tempPoint;
              bestPoint[sector] = value;
            } else if (value > bestPoint[sector]) {
              points[sector] = tempPoint;
              bestPoint[sector] = value;
            }
          } else {
            charted[sector]++;
          }
        }
      }
    }
    for (int i = 0; i < sectors.length; i++) {
      sectors[i] = 100 * unCharted[i] / (charted[i] + unCharted[i]);
    }
    int unChartedValue = (sectors[0] + sectors[1] + sectors[2] + sectors[3])
        / 4;
    if (unChartedValue < 20) {
      return null;
    }
    int pathValue = 0;
    int resultValue = 0;
    for (sector = 0; sector < 4; sector++) {
      int mx = 0;
      int my = 0;
      switch (sector) {
      case 0: {
        mx = -1;
        my = -1;
        break;
      }
      case 1: {
        mx = 1;
        my = -1;
        break;
      }
      case 2: {
        mx = -1;
        my = 1;
        break;
      }
      case 3: {
        mx = 1;
        my = 1;
        break;
      }
      default: {
        mx = -1;
        my = -1;
      }
      }
      PathPoint temp = null;
      if (sectors[sector] > 60) {
        int nx = sun.getCenterX();
        int ny = sun.getCenterY();
        nx = nx + mx;
        ny = ny + my;
        for (int i = 0; i < StarMap.SOLAR_SYSTEM_WIDTH + 2; i++) {
          nx = nx + mx;
          ny = ny + my;
          Coordinate fleetCoordinate = fleet.getCoordinate();
          Coordinate coordinate = new Coordinate(nx, ny);
          double distance = fleetCoordinate.calculateDistance(coordinate);
          if (coordinate.isValidCoordinate(maxCoordinate) && i >= scan
              && distance > 1 && mapData[nx][ny] == UNCHARTED) {
            temp = new PathPoint(nx, ny, distance);
            pathValue = calculateUnchartedLine(fleet.getX(), fleet.getY(), nx,
                ny);
            break;
          }
          coordinate = new Coordinate(sun.getCenterX(), ny);
          distance = fleetCoordinate.calculateDistance(coordinate);
          if (temp == null && coordinate.isValidCoordinate(maxCoordinate)
              && i >= scan && distance > 1
              && mapData[sun.getCenterX()][ny] == UNCHARTED) {
            temp = new PathPoint(sun.getCenterX(), ny, distance);
            pathValue = calculateUnchartedLine(fleet.getX(), fleet.getY(),
                sun.getCenterX(), ny);
            break;
          }
          coordinate = new Coordinate(nx, sun.getCenterY());
          distance = fleetCoordinate.calculateDistance(coordinate);
          if (temp == null && coordinate.isValidCoordinate(maxCoordinate)
              && i >= scan && distance > 1
              && mapData[nx][sun.getCenterY()] == UNCHARTED) {
            temp = new PathPoint(nx, sun.getCenterY(), distance);
            pathValue = calculateUnchartedLine(fleet.getX(), fleet.getY(), nx,
                sun.getCenterY());
            break;
          }
        }
      }
      if (temp == null && points[sector] != null
          && points[sector].getDistance() > 1) {
        temp = points[sector];
        pathValue = bestPoint[sector];
      }
      if (result == null && temp != null) {
        result = temp;
        resultValue = pathValue;
      }
      if (temp != null && result != null && pathValue > resultValue) {
        result = temp;
        resultValue = pathValue;
      }
    }
    return result;
  }

  /**
   * Init map visibility and cloaking detection maps
   * @param maximumX Map size in X axel
   * @param maximumY Map size in Y axel
   */
  public void initMapData(final int maximumX, final int maximumY) {
    maxCoordinate = new Coordinate(maximumX, maximumY);
    mapData = new byte[maximumX][maximumY];
    mapCloakDetection = new int[maximumX][maximumY];
  }

  /**
   * Get sector visibility
   * @param coordinate coordinate
   * @return UNCHARTED, FOG_OF_WAR or VISIBLE
   */
  public byte getSectorVisibility(final Coordinate coordinate) {
    byte result = UNCHARTED;
    try {
      result = mapData[coordinate.getX()][coordinate.getY()];
    } catch (ArrayIndexOutOfBoundsException e) {
      ErrorLogger.log(e);
    }
    return result;
  }

  /**
   * Set sector visibility
   * @param x X coordinate
   * @param y Y coordinate
   * @param visibility UNCHARTED, FOG_OF_WAR or VISIBLE
   */
  public void setSectorVisibility(final int x, final int y,
      final byte visibility) {
    if (visibility >= 0 && visibility <= VISIBLE) {
      try {
        mapData[x][y] = visibility;
      } catch (ArrayIndexOutOfBoundsException e) {
        ErrorLogger.log(e);
      }
    }
  }

  /**
   * Get sector cloaking detection
   * @param x X coordinate
   * @param y Y coordinate
   * @return cloaking detection value
   */
  public int getSectorCloakDetection(final int x, final int y) {
    int result = 0;
    try {
      result = mapCloakDetection[x][y];

    } catch (ArrayIndexOutOfBoundsException e) {
      ErrorLogger.log(e);
    }
    return result;
  }

  /**
   * Set sector cloaking detection value
   * @param x X coordinate
   * @param y Y coordinate
   * @param value cloaking detection value
   */
  public void setSectorCloakingDetection(final int x, final int y,
      final int value) {
    try {
      mapCloakDetection[x][y] = value;
    } catch (ArrayIndexOutOfBoundsException e) {
      ErrorLogger.log(e);
    }
  }

  /**
   * Clear visibility data after turn. These needs to be recalculated for
   * each turn.
   */
  public void resetVisibilityDataAfterTurn() {
    for (int y = 0; y < maxCoordinate.getY(); y++) {
      for (int x = 0; x < maxCoordinate.getX(); x++) {
        mapCloakDetection[x][y] = 0;
        if (mapData[x][y] == VISIBLE) {
          mapData[x][y] = FOG_OF_WAR;
        }
      }
    }
  }

  /**
   * Number of Ship stats player has
   * @return Number of ship stats in list
   */
  public int getNumberOfShipStats() {
    return shipStatList.size();
  }

  /**
   * Get ship stat by index. May return null if index invalid
   * @param index ShipStat index
   * @return ShipStat or null
   */
  public ShipStat getShipStat(final int index) {
    if (shipStatList.size() > 0 && index >= 0 && index < shipStatList.size()) {
      return shipStatList.get(index);
    }
    return null;
  }

  /**
   * Get Ship stat by name.
   * @param name for search
   * @return ShipStat if found otherwise null
   */
  public ShipStat getShipStatByName(final String name) {
    for (ShipStat stat : shipStatList) {
      if (stat.getDesign().getName().equals(name)) {
        return stat;
      }
    }
    return null;
  }

  /**
   * Get count of Ship stats name starts with
   * @param name for search
   * @return Number of ship stats which start with that
   */
  public int getShipStatStartsWith(final String name) {
    int result = 0;
    for (ShipStat stat : shipStatList) {
      if (stat.getDesign().getName().startsWith(name)) {
        result++;
      }
    }
    return result;
  }

  /**
  * Get Ship Stat list as a fixed array
  * @return Ship Stat array
  */
  public ShipStat[] getShipStatList() {
    return shipStatList.toArray(new ShipStat[shipStatList.size()]);
  }

  /**
  * Get Ship Stat list as a fixed array but in alphabetical order
  * @return Ship Stat array
  */
  public ShipStat[] getShipStatListInOrder() {
    @SuppressWarnings("unchecked")
    ArrayList<ShipStat> orderList = (ArrayList<ShipStat>) shipStatList.clone();
    Collections.sort(orderList);
    return orderList.toArray(new ShipStat[orderList.size()]);
  }

  /**
   * Add Ship Stat to list
   * @param stat ShipStat to add
   */
  public void addShipStat(final ShipStat stat) {
    if (stat != null) {
      shipStatList.add(stat);
    }
  }

  /**
   * remove Ship Stat from list
   * @param index Index to remove
   */
  public void removeShipStat(final int index) {
    if (shipStatList.size() > 0 && index >= 0 && index < shipStatList.size()) {
      shipStatList.remove(index);
    }
  }

  /**
   * Get space race for player
   * @return Space Race
   */
  public SpaceRace getRace() {
    return race;
  }

  /**
   * Set space race for player
   * @param race Space Race
   */
  public void setRace(final SpaceRace race) {
    this.race = race;
  }

  /**
   * Get empire name for player
   * @return Empire name as a String
   */
  public String getEmpireName() {
    return empireName;
  }

  /**
   * Set empire name for player
   * @param empireName as a String
   */
  public void setEmpireName(final String empireName) {
    this.empireName = empireName;
  }

  /**
   * Get total amount of credits player has
   * @return Number of credits
   */
  public int getTotalCredits() {
    return totalCredits;
  }

  /**
   * Set total amount of credits for player
   * @param totalCredits Number of credits
   */
  public void setTotalCredits(final int totalCredits) {
    this.totalCredits = totalCredits;
  }

  /**
   * Get tech list for player
   * @return Tech list
   */
  public TechList getTechList() {
    return techList;
  }

  /**
   * Set tech list for player
   * @param techList Tech List
   */
  public void setTechList(final TechList techList) {
    this.techList = techList;
  }

  /**
   * Get the player fleets
   * @return fleetList which is never null
   */
  public FleetList getFleets() {
    return fleets;
  }

  /**
   * Get message list for one turn
   *
   * @return Message list for one turn
   */
  public MessageList getMsgList() {
    return msgList;
  }

  /**
   * Is player human controlled or AI
   * @return True for human controlled
   */
  public boolean isHuman() {
    return human;
  }

  /**
   * Set if player is human controlled or AI
   * @param human True for human controlled
   */
  public void setHuman(final boolean human) {
    this.human = human;
    if (this.human) {
      missions = null;
    } else {
      missions = new MissionList();
    }
  }

  /**
   * Get missions list. This is non null only for AI players
   * @return Mission list
   */
  public MissionList getMissions() {
    return missions;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(empireName);
    sb.append(" - ");
    if (isHuman()) {
      sb.append("Human");
    } else {
      sb.append("AI");
    }
    sb.append("\n");
    sb.append(race.toString());
    return sb.toString();
  }

}
