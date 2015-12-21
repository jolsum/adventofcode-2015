package lars.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day21 {

	private static final List<Item> weapons = new ArrayList<>();
	private static final List<Item> armors = new ArrayList<>();
	private static final List<Item> rings = new ArrayList<>();

	public static void main(String[] args) {

		weapons.add(new Item("Dagger", 8, 4, 0));
		weapons.add(new Item("Shortsword", 10, 5, 0));
		weapons.add(new Item("Warhammer", 25, 6, 0));
		weapons.add(new Item("Longsword", 40, 7, 0));
		weapons.add(new Item("Greataxe", 74, 8, 0));

		armors.add(new Item("Leather", 13, 0, 1));
		armors.add(new Item("Chainmail", 31, 0, 2));
		armors.add(new Item("Splintmail", 53, 1, 3));
		armors.add(new Item("Bandedmail", 75, 1, 4));
		armors.add(new Item("Platemail", 102, 1, 5));

		rings.add(new Item("-", 0, 0, 0));
		rings.add(new Item("-", 0, 0, 0));
		rings.add(new Item("Damage+1", 25, 1, 0));
		rings.add(new Item("Damage+1", 50, 2, 0));
		rings.add(new Item("Damage+1", 100, 3, 0));
		rings.add(new Item("Defense+1", 20, 0, 1));
		rings.add(new Item("Defense+2", 40, 0, 2));
		rings.add(new Item("Defense+3", 80, 0, 3));

		int minPrice = Integer.MAX_VALUE;
		for (Item weapon : weapons) {
			for (Item armor : armors) {
				for (Item ring1 : rings) {
					for (Item ring2 : rings) {
						if (ring1 == ring2) {
							continue;
						}
						int meDamage = weapon.damage + ring1.damage + ring2.damage;
						int meArmor = armor.armor + ring1.armor + ring2.armor;
						int price = weapon.cost + armor.cost + ring1.cost + ring2.cost;

						if (play(100, meDamage, meArmor, 109, 8, 2) && price < minPrice) {
							System.out.println(weapon.name + " " + armor.name + " " + ring1.name + " " + ring2.name
									+ " = " + price);
							minPrice = price;
						}
					}
				}
			}
		}

	}

	private static boolean play(int meHitPoints, final int meDamage, final int meArmor, int bossHitPoints,
			final int bossDamage, final int bossArmor) {

		boolean myTurn = true;
		while (true) {
			if (myTurn) {
				bossHitPoints -= Math.max(meDamage - bossArmor, 1);
				if (bossHitPoints <= 0) {
					return true;
				}
			} else {
				meHitPoints -= Math.max(bossDamage - meArmor, 1);
				if (meHitPoints <= 0) {
					return false;
				}
			}
			myTurn = !myTurn;

		}
	}

	private static class Item {
		final String name;
		final int cost;
		final int damage;
		final int armor;

		Item(String name, int price, int damage, int armor) {
			this.name = name;
			this.cost = price;
			this.damage = damage;
			this.armor = armor;
		}
	}

}
