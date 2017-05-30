package org.openRealmOfStars.player.InitalSetRace;

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
*  Greyans additional setting
*
*/

public class InitialSetGreyans extends InitialSetRaces {

  /**
   * tech
   */
  private Tech tech;

  /**
   * Set propusion tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalPropusion(final TechList  techList) {
    tech = TechFactory.createRandomTech(TechType.Propulsion, 1,
        techList.getListForTypeAndLevel(TechType.Propulsion, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
  }

  /**
   * Set electrics tech to techList
   * @param techList is PlayerInfo's techList
   */
  protected void additionalElectrics(final TechList techList) {
    tech = TechFactory.createRandomTech(TechType.Electrics, 1,
        techList.getListForTypeAndLevel(TechType.Electrics, 1));
    if (tech != null) {
      techList.addTech(tech);
    }
  }

}
