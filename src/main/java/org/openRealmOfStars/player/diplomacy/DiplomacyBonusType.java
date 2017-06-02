package org.openRealmOfStars.player.diplomacy;

/**
 *
 * Open Realm of Stars game project Copyright (C) 2017 Tuomo Untinen
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see http://www.gnu.org/licenses/
 *
 *
 * Diplomacy Bonus Type
 *
 */
public enum DiplomacyBonusType {
    /**
     * Two players are in war.
     */
    IN_WAR,
    /**
     * Another player has made war declaration to other players.
     */
    WAR_DECLARTION,
    /**
     * Situation where non combat ships can travel on each other space.
     */
    IN_TRADE_ALLIANCE,
    /**
     * Situation where all ships can travel on each other's space.
     */
    IN_ALLIANCE,
    /**
     * Other player has captured a diplomat.
     */
    DIPLOMAT_CAPTURED,
    /**
     * Border has been crossed with any ship where there is no alliance.
     */
    BORDER_CROSSED,
    /**
     * Another player has given something free.
     */
    GIVEN_VALUABLE_FREE,
    /**
     * Another player has demanded something for free.
     */
    MADE_DEMAND,
    /**
     * Players have traded something.
     */
    DIPLOMATIC_TRADE,
    /**
     * Players share the same space race.
     */
    SAME_RACE,
    /**
     * Players have had long peace time together.
     */
    LONG_PEACE;

    /**
     * Number of Bonus type. This should be one larger than actual bonus types.
     */
    public static final int MAX_BONUS_TYPE = 11;

    /**
     * Get ShipHullType index
     * 
     * @return int
     */
    public int getIndex() {
        switch (this) {
        case IN_WAR:
            return 0;
        case WAR_DECLARTION:
            return 1;
        case IN_TRADE_ALLIANCE:
            return 2;
        case IN_ALLIANCE:
            return 3;
        case DIPLOMAT_CAPTURED:
            return 4;
        case BORDER_CROSSED:
            return 5;
        case GIVEN_VALUABLE_FREE:
            return 6;
        case MADE_DEMAND:
            return 7;
        case DIPLOMATIC_TRADE:
            return 8;
        case SAME_RACE:
            return 9;
        case LONG_PEACE:
            return 10;
        default:
            throw new IllegalArgumentException("No such Diplomacy Bonus" + " Type!");
        }
    }

    /**
     * Return diplomacy bonus type by index.
     * 
     * @param index
     *            This must be between 0-10
     * @return Diplomacy Bonus Type
     */
    public static DiplomacyBonusType getTypeByIndex(final int index) {
        switch (index) {
        case 0:
            return DiplomacyBonusType.IN_WAR;
        case 1:
            return DiplomacyBonusType.WAR_DECLARTION;
        case 2:
            return DiplomacyBonusType.IN_TRADE_ALLIANCE;
        case 3:
            return DiplomacyBonusType.IN_ALLIANCE;
        case 4:
            return DiplomacyBonusType.DIPLOMAT_CAPTURED;
        case 5:
            return DiplomacyBonusType.BORDER_CROSSED;
        case 6:
            return DiplomacyBonusType.GIVEN_VALUABLE_FREE;
        case 7:
            return DiplomacyBonusType.MADE_DEMAND;
        case 8:
            return DiplomacyBonusType.DIPLOMATIC_TRADE;
        case 9:
            return DiplomacyBonusType.SAME_RACE;
        case 10:
            return DiplomacyBonusType.LONG_PEACE;
        default:
            throw new IllegalArgumentException("Unexpected diplomacy bonus type!");
        }
    }

}
