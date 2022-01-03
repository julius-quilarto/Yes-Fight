/*
 * A class used to create an inventory of your items.
 * Version 1.0
 */
public class Inventory {
	
	private Armor[] armors = new Armor[16];//can have up to 16 different armors in your inventory
	private Weapons[] weapons = new Weapons[16];//can have up to 16 different weapons in your inventory
	private Items[] items = new Items[24];//you can have up to 24 items in your inventory
	private KeyItems[] keyItems = new KeyItems[64];//i just added 64 key item slots because I don't know how many we'll need
	private int Money;
	
	/*
	 * A method that defaults the whole inventory fields
	 */
	public void initializeInventory() {
		
		for(byte i = 0; i < 16; i++) {
			armors[i] = Armor.none;
			if(i == 0)armors[i] = Armor.kevlar;//ensures that you start the game with the Thicc Tanuki's kevlar armor
		}
		
		//initializes the weapons
		for(byte i = 0; i < 16; i++) {
			weapons[i] = Weapons.none;
			if(i == 0)weapons[i] = Weapons.colt1911;//makes sure the Thicc Tanuki has the Colt1911 in her inventory
		}
		
		//initializes the items to none
		for(byte i = 0; i < 24; i++) {
			items[i] = Items.none;
			items[0] = Items.potion100HP;
			items[1] = Items.healingPotion500HP;
			items[2] = Items.healingPotion2000HP;
			items[3] = Items.maxHealing;
			items[4] = Items.freeLevelUp;
			items[23] = Items.bicycle;
		}
		
		//makes sure you start out with no key items
		for(byte i = 0; i < 64; i++) {
			keyItems[i] = KeyItems.none;
		}
		
		//defaults the money to 3000
		Money = 3000;
	}
	
	/*
	 * A method that allows the program to ad money to Inventory.
	 * @mon Accepts an int of the money to be added.
	 */
	public void addMoney(int mon) {
		Money = Money + mon;
	}
	
	/*
	 * A method that allows the program to take away money.
	 * @mon Accepts an int of the money to be take away.
	 */
	public void takeMoney(int mon) {
		Money -= mon;
	}
	
	/*
	 * A method that returns an int of the current money stat.
	 */
	public int getMoney() {
		return Money;
	}
	
	/*
	 * A method that allows the user to manually set the money value but cannot go
	 * bellow zero.
	 * @mon Accepts an int of the set monetary value
	 */
	public void setMoney(int mon) {
		Money = mon;
		if(Money <0)Money = 0;
	}
	
	/*
	 * A method that returns an enum of the selected item in the keyItems array
	 * @position Accepts a byte of the position in the array of keyItems between 0 and 63
	 */
	public KeyItems getKeyItem(byte position) {
		if(position > 63){
			return keyItems[63];
		}
		else if (position < 0){
			return keyItems[0];
		}
		else {
			return keyItems[position];
		}
	}
	
	/*
	 * A method that returns an enum of the selected item in the items array
	 * @position Accepts a byte of the position in the array of items between 0 and 23
	 */
	public Items getItem(byte position) {
		if(position > 23){
			return items[23];
		}
		else if (position < 0){
			return items[0];
		}
		else {
			return items[position];
		}
	}
	
	/*
	 * A method that returns an enum of the selected item in the weapons array
	 * @position Accepts a byte of the position in the array of weapons between 0 and 15
	 */
	public Weapons getWeapon(byte position) {
		if(position > 15){
			return weapons[15];
		}
		else if (position < 0){
			return weapons[0];
		}
		else {
			return weapons[position];
		}
	}
	
	/*
	 * A method that returns an enum of the selected item in the armors array
	 * @position Accepts a byte of the position in the array of armors between 0 and 15
	 */
	public Armor getArmor(byte position) {
		if(position > 15){
			return armors[15];
		}
		else if (position < 0){
			return armors[0];
		}
		else {
			return armors[position];
		}
	}
	
	/*
	 * A method that allows the user to change the armor at a given position in the armors array.
	 * @a Accepts an enum of type Armor to have the armor item replaced with
	 * @pos Accepts a byte of the position of the array the armor is to be in between 0 and 15. Note that numbers less than 0 will edit position 0 and numbers greater than 15 will edit position 15
	 */
	public void changeArmor(Armor a, byte pos) {
		if(pos < 0)armors[0] = a;
		else if(pos > 15)armors[15] = a;
		else {
			armors[pos] = a;
		}
	}
	
	/*
	 * A method that allows the user to change the weapon at a given position in the weapons array.
	 * @a accepts an enum of type Weapons
	 * @pos accepts a byte of the position in the array between 0 and 15.
	 * If the byte is less than 0, it will edit position 0.
	 * If the byte is greater than 15, it will edit position 15.
	 */
	public void changeWeapon(Weapons a, byte pos) {
		if(pos < 0)weapons[0] = a;
		else if(pos > 15)weapons[15] = a;
		else {
			weapons[pos] = a;
		}
	}
	
	/*
	 * A method that allows the user to change the item in the items array at a given postion.
	 * @item Acceps an enum of type Items
	 * @pos Accept a byte of the postion in the array between 0 and 23.
	 * If the pos is greater than 23, it will edit item slot 23.
	 * If the pos is less than 0, it will edit item slot 0.
	 */
	public void changeItem(Items item, byte pos) {
		if(pos < 0)items[0] = item;
		else if (pos > 23)items[23] = item;
		else {
			items[pos] = item;
		}
	}
	
	/*
	 * A method that allow the user to change keyItems in the keyItem array.
	 * @k Accepts an enum of type KeyItems
	 * @pos Accepts a byte of the item slot in the array
	 * If pos is greater than 63, it will edit slot 63.
	 * If pos is less than 0, it will edit slot 0.
	 */
	public void changeKeyItem(KeyItems k, byte pos) {
		if(pos > 63) keyItems[63] = k;
		else if (pos<0)keyItems[0] = k;
		else {
			keyItems[pos] = k;
		}
	}
		
	/*
	 * A method that returns an array of the keyItems list
	 * @return Returns an enum array of type keyItems[64]
	 */
	public KeyItems[] getKetItemsAry() {
		return keyItems;
	}
	
	/*
	 * A method that returns the array of items.
	 * @return Returns an enum array of of type Items[16]
	 */
	public Items[] getItemsAry() {
		return items;
	}
	
	/*
	 * A method that returns the armor list as an array.
	 * @return Returns an array of enum type Armor[16]
	 */
	public Armor[] getArmorsAry() {
		return armors;
	}
	
	/*
	 * A method that returns the list of weapons in the inventory as an array
	 * @return Returns an array of enum type Weapons[16]
	 */
	public Weapons[] getWeaponsAry() {
		return weapons;
	}
	
	/*
	 * A method that checks to see if wapons storage is full.
	 * @return Returns a boolean of true if it is full and false if the array has at least one instance of Weapons.none
	 */
	public boolean isWeaponsStorageFull() {
		boolean answer = true;
		for (byte i = 0; i < 16; i++) {
			if(weapons[i] == Weapons.none)answer = false;
		}
		return answer;
	}
	
	/*
	 * A method that checks to see if armor storage is full
	 * @return Returns a boolean of true if it array doesn't contain any instances of Armor.none
	 */
	public boolean isArmorStorageFull() {
		boolean answer = true;
		for (byte i = 0; i < 16; i++) {
			if(armors[i] == Armor.none)answer = false;
		}
		return answer;
	}
	
	/*
	 * A method that checks ot see if Items storage is full.
	 * @return Returns a boolean true if there are no instances of Items.none
	 */
	public boolean isItemsStorageFull() {
		boolean answer = true;
		for (byte i = 0; i < 24; i++) {
			if(items[i] == Items.none)answer = false;
		}
		return answer;
	}
	
	/*
	 * A method that checks to see if the keyItems list is full
	 * @return Returns a boolean if there are no instances of KeyItems.none
	 */
	public boolean isKeyItemStorageFull() {
		boolean answer = true;
		for (byte i = 0; i < 64; i++) {
			if(keyItems[i] == KeyItems.none)answer = false;
		}
		return answer;
	}
}
