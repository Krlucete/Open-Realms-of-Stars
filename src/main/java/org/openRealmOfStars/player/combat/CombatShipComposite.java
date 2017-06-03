package org.openRealmOfStars.player.combat;

import java.util.ArrayList;
import java.util.Collections;

import org.openRealmOfStars.player.PlayerInfo;
import org.openRealmOfStars.player.ship.Ship;

/**
*
* Open Realm of Stars game project
* Copyright (C) 2017  Diocto
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
* This class is responsible for the composite in a design
* pattern called the composite pattern and implement the method
* to management combatShips .
*/
public class CombatShipComposite implements CombatShipComponent {
    /**
     * combatShip in combat.
     */
    private ArrayList<CombatShipComponent> combatShipComponents;
    @Override
    /**
     * Reinitialize all ships for next round
     */
    public void reInitShipForRound() {
        for (CombatShipComponent combatShip : combatShipComponents) {
            combatShip.reInitShipForRound();
        }
    }

    @Override
    /**
     * Use certain component for this round
     * @param index Component index
     */
    public void useComponent(final int index) {
        for (CombatShipComponent combatShip : combatShipComponents) {
            combatShip.useComponent(index);
        }
    }

    @Override
    /**
     * Define bonus accuracy for all combat ships.
     * @param bonusAccuracy for combat ship
     */
    public void setBonusAccuracy(final int bonusAccuracy) {
        for (CombatShipComponent combatShip : combatShipComponents) {
            combatShip.setBonusAccuracy(bonusAccuracy);
        }
    }

    /**
     * create new CombatShipList
     */
    public void initialize() {
        combatShipComponents = new ArrayList<CombatShipComponent>();
    }

    /**
     * sort by reverseOrder
     */
    public void sortRevers() {
        Collections.sort(combatShipComponents, Collections.reverseOrder());
    }

    /**
     * @param combatShip combatShip to add
     */
    public void add(final CombatShipComponent combatShip) {
        combatShipComponents.add(combatShip);
    }

    /**
     * @param info enemy player
     * @return most powerful enemy ship
     */
    public CombatShipComponent getMostPowerfulShip(final PlayerInfo info) {
        int strongestPower = 0;
        CombatShipComponent strongestShip = null;
        for (CombatShipComponent ship : combatShipComponents) {
          if (ship.getPlayer() != info
              && ship.getShip().getTotalMilitaryPower() > strongestPower) {
            strongestShip = ship;
            strongestPower = strongestShip.getShip().getTotalMilitaryPower();
          }
        }
        return strongestShip;
    }

    @Override
    public PlayerInfo getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ship getShip() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param ship combatShip
     * @return parameter's index
     */
    public int indexOf(final CombatShip ship) {
        return combatShipComponents.indexOf(ship);
    }

    /**
     * @param ship combatShip to remove
     */
    public void remove(final CombatShip ship) {
        combatShipComponents.remove(ship);
    }
    /**
     * @return this combatShip list
     */
    public ArrayList<CombatShipComponent> getCombatShipList() {
        return combatShipComponents;
    }

    /**
     * @return this combatShipList size
     */
    public int size() {
        return combatShipComponents.size();
    }

    /**
     * @param shipIndex index of target ship
     * @return target ship instance
     */
    public CombatShipComponent get(final int shipIndex) {
        return combatShipComponents.get(shipIndex);
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return 0;
    }

}
