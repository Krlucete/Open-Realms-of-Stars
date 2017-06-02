package org.openRealmOfStars.player.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openRealmOfStars.player.SpaceRace.SpaceRace;
import org.openRealmOfStars.player.ship.shiphull.product.HullScoutMK1;
import org.openRealmOfStars.player.ship.shiphull.product.ShipHull;

/**
 * 
 * Open Realm of Stars game project
 * Copyright (C) 2016  Tuomo Untinen
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
 * Test for Ship hull class
 */
public class ShipHullTest {

  @Test
  @Category(org.openRealmOfStars.UnitTest.class)
  public void testShipHullScoutMK1() {
    ShipHull hull = new HullScoutMK1(SpaceRace.GREYANS);
    assertEquals(5, hull.getCost());
    assertEquals(5, hull.getMetalCost());
    assertEquals(0, hull.getIndex());
    assertEquals("Scout Mk1", hull.getName());
    assertEquals(SpaceRace.GREYANS, hull.getRace());
    assertEquals(1, hull.getSlotHull());
    assertEquals(ShipSize.SMALL, hull.getSize());
    assertEquals(4, hull.getMaxSlot());
    String tmp = hull.toString();
    assertTrue("ToString does not contain ship hull name",
        tmp.contains("Scout Mk1"));
    assertTrue("ToString does not contain ship hull free slots",
        tmp.contains("Slots:4"));
  }

  @Test
  @Category(org.openRealmOfStars.UnitTest.class)
  public void testShipHullHullScoutMK1ByName() {
    ShipHull hull = ShipHullFactory.createByName("Scout Mk1", SpaceRace.GREYANS);
    assertEquals(5, hull.getCost());
    assertEquals(5, hull.getMetalCost());
    assertEquals(0, hull.getIndex());
    assertEquals("Scout Mk1", hull.getName());
    assertEquals(SpaceRace.GREYANS, hull.getRace());
    assertEquals(1, hull.getSlotHull());
    assertEquals(ShipSize.SMALL, hull.getSize());
    assertEquals(4, hull.getMaxSlot());
  }


}
