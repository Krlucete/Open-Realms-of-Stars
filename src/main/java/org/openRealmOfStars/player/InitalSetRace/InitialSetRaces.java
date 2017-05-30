package org.openRealmOfStars.player.InitalSetRace;

import org.openRealmOfStars.player.PlayerInfo;

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

import org.openRealmOfStars.player.ship.ShipStat;
import org.openRealmOfStars.player.ship.generator.ShipGenerator;
import org.openRealmOfStars.player.ship.shipdesign.ShipDesign;
import org.openRealmOfStars.player.tech.Tech;
import org.openRealmOfStars.player.tech.TechFactory;
import org.openRealmOfStars.player.tech.TechList;
import org.openRealmOfStars.player.tech.TechType;

public abstract class InitialSetRaces {
  
  /**
   * Set combat tech to techList
   */
  protected void setCombat(TechList techList){}
  
  /**
   * Set defence tech to techList
   */
  protected void setDefence(TechList techList){}
  
  /**
   * Set hull tech to techList
   */
  protected void setHull(TechList techList){}
  
  /**
   * Set propusion tech to techList
   */
  protected void setPropusion(TechList techList){}
  
  /**
   * Set Electrics tech to techList
   */
  protected void setElectrics(TechList techList){}
  
  /**
   * add ship
   */
  protected void addShip(PlayerInfo playerInfo){}
  
  /**
   * template method
   */
  public void setRacesInfo(TechList techList, PlayerInfo playerInfo) {
    setCombat(techList);
    setDefence(techList);
    setHull(techList);
    setPropusion(techList);
    setElectrics(techList);
    addShip(playerInfo);
  }
}
