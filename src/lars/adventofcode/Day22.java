package lars.adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Day22 {

	private static final List<Item> spells = new ArrayList<>();
	private static final boolean debug = false;

	private static int cheapestWin = Integer.MAX_VALUE;

	public static void main(String[] args) {

		spells.add(new Item("Magic Missile", 53).instantDamage(4));
		spells.add(new Item("Drain", 73).instantDamage(2).heal(2));
		spells.add(new Item("Shield", 113).armor(7).turns(6));
		spells.add(new Item("Poison", 173).damage(3).turns(6));
		spells.add(new Item("Recharge", 229).mana(101).turns(5));

		play(true, 50, 500, 0, Collections.emptyMap(), 55, 8, 0);

		System.out.println("Cheapest win: " + cheapestWin);
	}

	private static void play(final boolean myTurn, int meHitPoints, int meMana, int meArmor, Map<Item, Integer> items, int bossHitPoints, int bossDamage, int manaSpent) {
		if (debug) {
			System.out.println("\n-- " + (myTurn ? "Player" : "Boss") + " turn -- " + items.size());
			System.out.println("- Player has " + meHitPoints + " hit points, " + meArmor + " armor, " + meMana + " mana");
			System.out.println("- Boss has " + bossHitPoints + " hit points");
		}

		// Part 2; 953 < X < 1295,
		if (myTurn) {
			meHitPoints--;
			if (meHitPoints <= 0) {
				if (debug) {
					System.out.println("Loss");
				}
				return;
			}
		}

		// Apply effects
		Iterator<Entry<Item, Integer>> it = items.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Item, Integer> entry = it.next();
			Item item = entry.getKey();
			Integer timer = entry.getValue();

			if (debug) {
				System.out.println(item.name + ", " + item.damage + " damage; timer " + timer);
			}

			meMana += item.mana;
			meArmor = item.armor;
			meHitPoints += item.heal;
			bossHitPoints -= item.damage;

			// Decrease timer
			timer = timer - 1;
			if (timer == 0) {
				it.remove();
			}
			else {
				entry.setValue(timer);
			}
		}

		if (bossHitPoints <= 0) {
			cheapestWin = Math.min(cheapestWin, manaSpent);
			System.out.println("Win, manaSpent: " + manaSpent + " cheapest: " + cheapestWin);
			return;
		}

		if (myTurn) {

			// My turn
			boolean spellCast = false;
			for (Item item : spells) {

				if (item.cost > meMana || items.containsKey(item)) {
					continue;
				}

				spellCast = true;
				if (debug) {
					System.out.println("Player casts " + item.name + " for " + item.instantDamage + " damage");
				}

				int newMeHitPoints = meHitPoints + item.heal;
				int newMeMana = meMana - item.cost;
				int newMeArmor = meArmor + item.armor;
				int newBossHitPoints = bossHitPoints - item.instantDamage;
				int newManaSpent = manaSpent + item.cost;

				Map<Item, Integer> newItems = new HashMap<>(items);
				if (item.turns > 0) {
					newItems.put(item, item.turns);
				}

				if (newBossHitPoints <= 0) {
					cheapestWin = Math.min(cheapestWin, newManaSpent);
					System.out.println("Win, manaSpent: " + newManaSpent + " cheapest: " + cheapestWin);
					return;
				}

				// Already spent more than the lowest - just stop
				if (newManaSpent > cheapestWin) {
					return;
				}

				play(false, newMeHitPoints, newMeMana, newMeArmor, newItems, newBossHitPoints, bossDamage, newManaSpent);
			}

			// Could not afford any spell -> lose
			if (!spellCast) {
				if (debug) {
					System.out.println("Loss, can't afford spell, mana " + meMana);
				}
				return;
			}
		}
		else {
			// Boss turn
			int damage = Math.max(bossDamage - meArmor, 1);
			meHitPoints -= damage;
			if (debug) {
				System.out.println("Boss attacks for " + damage + " damage");
			}

			if (meHitPoints <= 0) {
				if (debug) {
					System.out.println("Loss, manaSpent: " + manaSpent);
				}
				return;
			}

			play(true, meHitPoints, meMana, meArmor, items, bossHitPoints, bossDamage, manaSpent);
		}

	}

	private static class Item {
		final String name;
		final int cost;

		int instantDamage;
		int damage;
		int armor;
		int heal;
		int mana;

		int turns;

		Item(String name, int price) {
			this.name = name;
			this.cost = price;
		}

		Item instantDamage(int value) {
			this.instantDamage = value;
			return this;
		}

		Item damage(int value) {
			this.damage = value;
			return this;
		}

		Item armor(int value) {
			this.armor = value;
			return this;
		}

		Item heal(int value) {
			this.heal = value;
			return this;
		}

		Item mana(int value) {
			this.mana = value;
			return this;
		}

		Item turns(int value) {
			this.turns = value;
			return this;
		}

	}

}
