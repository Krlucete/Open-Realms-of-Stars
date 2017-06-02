package org.openRealmOfStars.player.ship.shiphull.product;

import org.openRealmOfStars.player.SpaceRace.SpaceRace;
import org.openRealmOfStars.player.ship.ShipHullType;
import org.openRealmOfStars.player.ship.ShipImage;
import org.openRealmOfStars.player.ship.ShipSize;

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
* HullBattleshipMK2 (Concrete Product)
*
*/
public class HullBattleshipMK2 extends ShipHull {
    /**
     * Hull for Battleship Mk2
     */
    public static final int HULL_BATTLESHIP_MK2 = 25;

    /**
     * Constructor for Ship hull
     * @param race whom builds the ship hull
     */
    public HullBattleshipMK2(final SpaceRace race) {
        super(HULL_BATTLESHIP_MK2, "Battleship Mk2", 10, 3, ShipHullType.NORMAL,
                ShipSize.HUGE, 20, 25, race);
            super.setImageIndex(ShipImage.BATTLESHIP);
    }
}
