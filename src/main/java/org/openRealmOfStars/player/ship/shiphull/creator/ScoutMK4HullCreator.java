package org.openRealmOfStars.player.ship.shiphull.creator;

import org.openRealmOfStars.player.SpaceRace.SpaceRace;
import org.openRealmOfStars.player.ship.shiphull.product.HullScoutMK4;
import org.openRealmOfStars.player.ship.shiphull.product.ShipHull;

/**
*
* Open Realm of Stars game project
* Copyright (C) 2017 Lucas Lee
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
* ScoutMK4HullCreator (Concrete Creator)
*
*/
public class ScoutMK4HullCreator extends ShipHullCreator {

    /**
     * Constructor for ScoutMK2HullCreator
     */
    public ScoutMK4HullCreator() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param race whom builds the ship hull
     * @return HullScoutMK4
     */
    @Override
    public ShipHull create(final SpaceRace race) {
        return new HullScoutMK4(race);
    }

}
