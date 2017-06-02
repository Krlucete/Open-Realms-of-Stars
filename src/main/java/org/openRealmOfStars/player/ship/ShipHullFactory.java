package org.openRealmOfStars.player.ship;

import org.openRealmOfStars.player.SpaceRace.SpaceRace;

import org.openRealmOfStars.player.ship.shiphull.creator.BattleCruiserMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.BattleCruiserMK2HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.BattleshipMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.BattleshipMK2HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.CapitalShipHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.ColonyHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.CorvetteMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.CorvetteMK2HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.CorvetteMK3HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.CruiserHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.DestroyerMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.DestroyerMK2HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.DestroyerMK3HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.DestroyerMK4HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.LargeFreighterHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.LargeStarbaseHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.MassiveFreighterHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.MassiveStarbaseHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.MediumFreighterHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.MediumStarbaseHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.PrivateerMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.PrivateerMK2HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.PrivateerMK3HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.ProbeHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.ScoutMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.ScoutMK2HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.ScoutMK3HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.ScoutMK4HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.SmallFreighterHullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.SmallStarbaseMK1HullCreator;
import org.openRealmOfStars.player.ship.shiphull.creator.SmallStarbaseMK2HullCreator;

import org.openRealmOfStars.player.ship.shiphull.product.ShipHull;

/**
 *
 * Open Realm of Stars game project
 * Copyright (C) 2016  Tuomo Untinen
 * Copyright (C) 2017 Lucas
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
 * Ship hull factory
 *
 */
public final class ShipHullFactory {

  /**
   * Hiding the constructor
   */
  private ShipHullFactory() {
    // Nothing to do
  }

  /**
   * Create ShipHull with matching name
   * @param name Ship hull name
   * @param race Space race whom built the ship
   * @return ShipHull or null if not found
   */
  public static ShipHull createByName(final String name, final SpaceRace race) {
    switch (name.toLowerCase()) {
    case "scout mk1" :
        return new ScoutMK1HullCreator().create(race);
    case "destroyer mk1" :
        return new DestroyerMK1HullCreator().create(race);
    case "colony" :
        return new ColonyHullCreator().create(race);
    case "probe" :
        return new ProbeHullCreator().create(race);
    case "small freighter" :
        return new SmallFreighterHullCreator().create(race);
    case "small starbase mk1" :
        return new SmallStarbaseMK1HullCreator().create(race);
    case "destroyer mk2" :
        return new DestroyerMK2HullCreator().create(race);
    case "small starbase mk2" :
        return new SmallStarbaseMK2HullCreator().create(race);
    case "medium freighter" :
        return new MediumFreighterHullCreator().create(race);
    case "medium starbase" :
        return new MediumStarbaseHullCreator().create(race);
    case "scout mk2" :
        return new ScoutMK2HullCreator().create(race);
    case "cruiser" :
        return new CruiserHullCreator().create(race);
    case "battleship mk1" :
        return new BattleshipMK1HullCreator().create(race);
    case "privateer mk1" :
        return new PrivateerMK1HullCreator().create(race);
    case "large freighter" :
        return new LargeFreighterHullCreator().create(race);
    case "large starbase" :
        return new LargeStarbaseHullCreator().create(race);
    case "corvette mk1" :
        return new CorvetteMK1HullCreator().create(race);
    case "corvette mk2" :
        return new CorvetteMK2HullCreator().create(race);
    case "battle cruiser mk1" :
        return new BattleCruiserMK1HullCreator().create(race);
    case "privateer mk2" :
        return new PrivateerMK2HullCreator().create(race);
    case "scout mk3" :
        return new ScoutMK3HullCreator().create(race);
    case "massive freighter" :
        return new MassiveFreighterHullCreator().create(race);
    case "massive starbase" :
        return new MassiveStarbaseHullCreator().create(race);
    case "corvette mk3" :
        return new CorvetteMK3HullCreator().create(race);
    case "destroyer mk3" :
        return new DestroyerMK3HullCreator().create(race);
    case "battleship mk2" :
        return new BattleshipMK2HullCreator().create(race);
    case "privateer mk3" :
        return new PrivateerMK3HullCreator().create(race);
    case "battle cruiser mk2" :
        return new BattleCruiserMK2HullCreator().create(race);
    case "scout mk4" :
        return new ScoutMK4HullCreator().create(race);
    case "destroyer mk4" :
        return new DestroyerMK4HullCreator().create(race);
    case "capital ship" :
        return new CapitalShipHullCreator().create(race);
    default :
        return null;
    }
  }

}