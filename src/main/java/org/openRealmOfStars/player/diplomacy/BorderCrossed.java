package org.openRealmOfStars.player.diplomacy;

import org.openRealmOfStars.player.SpaceRace.SpaceRace;
/**
*
* Open Realm of Stars game project
* Copyright (C) 2017  Tuomo Untinen
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
* BorderCrossed
*/

public  class BorderCrossed implements Bonus{

    @Override
    public int BonusValue(SpaceRace race) {
        if (race == SpaceRace.SPORKS) 
            return -1;
        else if(race == SpaceRace.MECHIONS) 
            return  10;
        else
            return -2;
    }

    @Override
    public int BonusLasting(SpaceRace race) {
        if (race == SpaceRace.SPORKS) 
            return 10;
        else if(race == SpaceRace.MECHIONS) 
            return  10;
        else
            return 20;
    }
}
