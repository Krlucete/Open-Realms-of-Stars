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
* HullMediumStarbase (Concrete Product)
*
*/
public class HullMediumStarbase extends ShipHull {
    /**
     * Hull for Medium starbase
     */
    public static final int HULL_MEDIUM_STARBASE = 10;

    /**
     * Constructor for Ship hull
     * @param race whom builds the ship hull
     */
    public HullMediumStarbase(final SpaceRace race) {
        super(HULL_MEDIUM_STARBASE, "Medium starbase", 6, 2,
                ShipHullType.STARBASE,
                ShipSize.MEDIUM, 8, 10, race);
        super.setImageIndex(ShipImage.MEDIUM_STARBASE);
    }
}
