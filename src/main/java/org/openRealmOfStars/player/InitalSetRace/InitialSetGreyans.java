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
* implement greyans Initial setting
*
*/

public class InitialSetGreyans extends InitialSetRaces {
  
  /**
   * tech
   */
  Tech tech;
  
  /**
   * Set combat tech to techList
   */
  protected void setCombat(TechList techList) {
    tech = TechFactory.createRandomTech(TechType.Combat, 1,
        techList.getListForTypeAndLevel(TechType.Combat, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
  }
  
  /**
   * Set defence tech to techList
   */
  protected void setDefence(TechList techList) {
    tech = TechFactory.createRandomTech(TechType.Defense, 1,
      techList.getListForTypeAndLevel(TechType.Defense, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
  }
  
  /**
   * Set hull tech to techList
   */
  protected void setHull(TechList techList) {
    tech = TechFactory.createHullTech("Colony", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    tech = TechFactory.createHullTech("Scout Mk1", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
  }
  
  /**
   * Set propusion tech to techList
   */
  protected void setPropusion(TechList techList) {
    tech = TechFactory.createPropulsionTech("Ion drive Mk1", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    tech = TechFactory.createPropulsionTech("Fission source Mk1", 1);
    if (tech != null) {
      techList.addTech(tech);
    }
    tech = TechFactory.createRandomTech(TechType.Propulsion, 1,
        techList.getListForTypeAndLevel(TechType.Propulsion, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
  }
  
  /**
   * Set electrics tech to techList
   */
  protected void setElectrics(TechList techList) {
    tech = TechFactory.createRandomTech(TechType.Electrics, 1,
        techList.getListForTypeAndLevel(TechType.Electrics, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
  }
  
  /**
   * add ship
   */
  protected void addShip(PlayerInfo playerInfo) {
    ShipDesign design = ShipGenerator.createScout(playerInfo);
    ShipStat stat = new ShipStat(design);
    playerInfo.addShipStat(stat);
    design = ShipGenerator.createColony(playerInfo, false);
    stat = new ShipStat(design);
    playerInfo.addShipStat(stat);
  }
}
