package org.openRealmOfStars.player.InitalSetRace;

import org.openRealmOfStars.player.PlayerInfo;
import org.openRealmOfStars.player.ship.ShipStat;
import org.openRealmOfStars.player.ship.generator.ShipGenerator;
import org.openRealmOfStars.player.ship.shipdesign.ShipDesign;
import org.openRealmOfStars.player.tech.Tech;
import org.openRealmOfStars.player.tech.TechFactory;
import org.openRealmOfStars.player.tech.TechList;
import org.openRealmOfStars.player.tech.TechType;


/**
*
* Open Realm of Stars game project
* Copyright (C) 2017  God Beom
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
* InitialSetRace class is used in PlayerInfo construct
* This class set initial information of races
*
*/

public abstract class InitialSetRaces {
  /**
   * tech
   */
  private Tech tech;

  /**
   * Add combat tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalCombat(final TechList techList) { }

  /**
   * Add defence tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalDefence(final TechList techList) { }

  /**
   * Add hull tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalHull(final TechList techList) { }

  /**
   * Add propusion tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalPropusion(final TechList techList) { }

  /**
   * Add Electrics tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalElectrics(final TechList techList) { }

  /**
   * Add ship
   * @param playerInfo is object of this class call
   */
  protected void additionalShip(final PlayerInfo playerInfo) { }

  /**
   * template method of initial setting
   * @param techList is PlayerInfo's techList
   * @param playerInfo is object of this class call
   */
  public void setRacesInfo(final TechList techList,
                            final PlayerInfo playerInfo) {
    tech = TechFactory.createRandomTech(TechType.Combat, 1,
        techList.getListForTypeAndLevel(TechType.Combat, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
    additionalCombat(techList);

    tech = TechFactory.createRandomTech(TechType.Defense, 1,
        techList.getListForTypeAndLevel(TechType.Defense, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
    additionalDefence(techList);

    tech = TechFactory.createHullTech("Colony", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    tech = TechFactory.createHullTech("Scout Mk1", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    additionalHull(techList);

    tech = TechFactory.createPropulsionTech("Ion drive Mk1", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    tech = TechFactory.createPropulsionTech("Fission source Mk1", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    additionalElectrics(techList);

    ShipDesign design = ShipGenerator.createScout(playerInfo);
    ShipStat stat = new ShipStat(design);
    playerInfo.addShipStat(stat);
    design = ShipGenerator.createColony(playerInfo, false);
    stat = new ShipStat(design);
    playerInfo.addShipStat(stat);
    additionalShip(playerInfo);
  }
}
