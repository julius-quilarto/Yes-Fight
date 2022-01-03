/**
 * A class for the Magical Girl Character and how she works
 * @author Alexander
 * @version 1.0
 *
 */

import java.util.Random;

public class MagicalGirl {
	
Random rand = new Random();
	
	// base stats
	private final Elementals type = Elementals.ethereal;
	private final int HPbase = 75;
	private final int AtkBase = 35;
	private final int DefBase = 55;//magical girls are strong magially but not physically
	private final int SpecBase = 160;//she's magical so she has a high special
	private final int AGILITY= 10;//maximum value is 100 which would mean she dodges everything
	private final String FIRST_NAME = "Selena";
	private final String LAST_NAME = "Silverstone";
	private final String NICKNAME = "Magical Girl";
	private final int BASE_EXP = 100;//base exp for leling up
	private final int MAX_MAGIC = 220;//a stat that is used for the maximum number of magic points a character can have
	private final Abilities ability = Abilities.MagicalGirl;//sets the unchanged default ability of the character
	private final char GENDER = 'F';//'M' for male and 'F' for female
	private final Races race = Races.magicalGirl;//character race in case it become relevant later
	private final String[] TAUNTS = new String[] {
			"MAGICAL GIRL: In the name of magicl girls and anime, I will punish you!",
			"MAGICAL GIRL: I'm not going to get hit at all!",
			"MAGICAL GIRL: *poses dramatically after transforming into magical girl form*",
			"MAGICAL GIRL: *powers up using the magic of friendship*",
			"MAGICAL GIRL: By the power of Diamond Castle!*a magical girl transformation theme starts playing*",
			"MAGICAL GIRL: Because I read the manga, I'm always 10 years ahead of you.",
			"MAGICAL GIRL: Omae wa mou shinderu!\nENEMIES: NANI!",
			"MAGICAL GIRL: Behold the power of marketing and ad sponsers and overused transformation cutscenes!"
	};
	
	//in battle stats and default to zero
	private int level = 0;
	private int HP = 1;
	private int Atk = 0;
	private int Def = 0;
	private int Spec = 0;
	private boolean isAlive = true;//will default to true when spawned
	private Moves move1 = Moves.etherealBlade;
	private Moves move2 = Moves.moonKick;
	private Moves move3 = Moves.megabuster;
	private Moves move4 = Moves.shockingFist;
	private Spells spell1 = Spells.healSpell;
	private Spells spell2 = Spells.teamHeal;
	private Spells spell3 = Spells.tidalWave;
	private Spells spell4 = Spells.FairyGlitter;
	private Moves superMove = Moves.TheRealPrizeWasFriendship;//A magical girl's signature trope although this could also be a reference to My Little Pony: Friendship is Magic
	private Weapons weapon = Weapons.none;//yeah, you actually have to unlock her signature weapons because of that high special
	private Armor armor = Armor.none;//no armor for you
	private boolean isDefending = false;//defaults to false during battle
	private int superPowerMeter = 0;//defaults to zero at the start of battle and is available once the meter goes above 100
	private int EXP = 1;//exp defaults to 100 when the value is not saved
	private int magicMeter = 0;//you need to build up magic in battle to use spells on this character
	
	/**
	 * Default ThiccTanuki constructor if no save file is loaded.
	 */
	public MagicalGirl() {
		int levels = 1;
		this.level = levels;
		this.HP = this.HPbase * levels;
		this.Atk = this.AtkBase * levels;
		this.Def = DefBase * levels;
		this.Spec = this.SpecBase * levels;
		setLevel(1);
	}
	
	/**
	 * The other constructor for when a save file is loaded.
	 * @param currentHP - accepts an int of the current HP stat loaded from a save file and injected into a contructor from the battle manager
	 * @param level - accept an int of the current level loaded from a save file
	 * @param a - accepts an enum of type Armor of the character's armor loaded from a save file
	 * @param w - accepts an enum of type Weapons of the character's weapon loaded form a save file
	 * @param exp - accepts an int of the character's EXP loaded from a save file
	 * @param moveSet - accepts an enum array of the character's moveset loaded from a save file in teh form of moveSet[a,b,c,d]
	 * @param spellSet - accepts an enum array of the character's spellset loaded from a save file in the form of spellSet[a,b,c,d]
	 */
	public MagicalGirl(int currentHP,int level,Armor a, Weapons w, int exp, Moves[] moveSet, Spells[] spellSet) {
		this.HP = currentHP; 
		if(currentHP < 1) {
			this.HP = 0;
			this.isAlive = false;
		}
		this.level = level;
		Atk = level * AtkBase;
		Def = level * DefBase;
		Spec = SpecBase * level;
		giveArmor(a);
		giveWeapon(w);
		setEXP(exp);
		for(byte i = 0; i<4;i++) {
			changeMoveset(moveSet[i],i+1);
		}
		for(byte i = 0; i<4;i++) {
			changeSpells(spellSet[i],i+1);
		}
	}
	
	/**
	 * a method for calculating damage taken by the enemy
	 * @baseDamage accepts an int of the base damage of a move
	 * @damageType accepts an Elementals enum of the move's type
	 * @isSpecial accepts a boolean if true the dame is special, otherwise it uses the regular Atk stat
	 * @atkStat accepts an int of the attackers atk stat
	 * @special accepts an int of the attackers special stat
	 * @return returns an int of the damage taken
	 */
	public int damageCalculator(int baseDamage, Elementals damageType, boolean isSpecial, int atkStat, int specStat, int levels) {
		double damageMultiplier = UsefulMethods.getDamageMultiplier(damageType, type);//is set to 1 by default
		int defendingMultiplier = 1;//is set to 1 by default and set to 2 when defending is set to true
		if(this.isDefending == true) defendingMultiplier = 2;
		if(HP < 1) {
			this.HP = 0;
			this.isAlive = false;
			return 0;
		}
		
		//for calculating critical hits. 1 in 10 chance for a critical hit.
		boolean crit = false;
		if (rand.nextInt(10) == 1) {
			crit = true;
		}
		if(crit == true)damageMultiplier = damageMultiplier * 1.5;
		
		// for calculating evasion
		int evasion = 100 - this.AGILITY;
		int randomNum = rand.nextInt(100);
		if (randomNum >= evasion) {
			System.out.println(this.FIRST_NAME + " has evades the attack.");
			return 0;
		}
		
		//calculate damage based on attack and special
		if(isSpecial == false) {
			int damage = (int) (Math.ceil(1.0*baseDamage * levels * damageMultiplier *(1.0*atkStat / this.Def) / defendingMultiplier));
			this.HP = this.HP - damage;
			if(HP <= 0) {
				this.isAlive = false;
				this.HP = 0;
			}
			return damage;
		}
		else if (isSpecial == true) {
			int damage = (int) (Math.ceil(1.0*baseDamage * levels * damageMultiplier * (1.0*specStat / this.Spec)));
			this.HP = this.HP - damage;
			if(HP <= 0) {
				this.isAlive = false;
				this.HP = 0;
			}
			return damage;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * A method that returns an array of the battle stat integers in the order of:
	 * [level, HP, Atk, Def, Spec, EXP]
	 * @return returns an array of integerts in teh order of [level,hp,atk,def,spec,exp]
	 */
	public int[] getStats(){
		int statAry[] = new int[] {
			this.level, this.HP, this.Atk, this.Def, this.Spec,this.EXP
		};
		return statAry;
	}
	
	/**
	 * A method that returns the character stats as a String
	 * @return returns the character's stats as a String
	 */
	public String getStatsAsString() {
		int[] statAry = getStats();
		String Stats = NICKNAME + "\nLevel: " + statAry[0]+"\nHP: "+HP+"\nAtk: "+Atk+"\nDef: "+Def
				+"\nSpec: "+Spec+"\nMagic Meter: "+getMagicMeter()+"\nSuper Meter: "+getSuperMeter()+"%";
		return Stats;//dummy return statement
	}
	
	/**
	 * A method for choosing a Move
	 * @moveNumber accepts an integer of the move in the order of the move list between 1-5 inclusive, however only the placeholder move will activate if another number is selected and 5 can only be used if the superPowerMeter is greater than or equal to 100 otherwise it defaults to the placeholder moves.
	 * This function will use the isDead move if the character has been defeated in battle until revived
	 */
	public Moves chooseMove(int moveNumber) {
		this.magicMeter = this.magicMeter + 25;//restores magic meter by using regular attacks
		this.superPowerMeter += 15;
		if (this.magicMeter > this.MAX_MAGIC)this.magicMeter = this.MAX_MAGIC;//prevents the magic meter from going above max magic
		if (this.isAlive == false) {
			return Moves.isDead;
		}
		else if(moveNumber == 1) {
			return this.move1;
		}
		else if(moveNumber == 2) {
			return this.move2;
		}
		else if(moveNumber == 3) {
			return this.move3;
		}
		else if(moveNumber == 4) {
			return this.move4;
		}
		else if(moveNumber == 5 && superPowerMeter >= 100) {
			this.superPowerMeter = 0;
			return this.superMove;
		}
		else {
			println("Invalid move selection");
			return Moves.placeholder;
			
		}
	}
	
	/**
	 * A method for choosing a spell
	 * @param selection - accepts a byte of the spell selected
	 * If magic meter is too low or an invalid spell selection, the spell is will return an enum of Spells.spellFailed and 
	 * set your magic meter to zero for this character.
	 * @return Returns an enum of type Spells
	 */
	public Spells chooseSpell(byte selection) {
		if (selection == 1 && magicMeter >= UsefulMethods.getMagicSpellCost(spell1)) {
			magicMeter = magicMeter - UsefulMethods.getMagicSpellCost(spell1);
			return spell1;
		}
		else if (selection == 2 && magicMeter >= UsefulMethods.getMagicSpellCost(spell2)) {
			magicMeter = magicMeter - UsefulMethods.getMagicSpellCost(spell2);
			return spell2;
		}
		else if (selection == 3 && magicMeter >= UsefulMethods.getMagicSpellCost(spell3)) {
			magicMeter = magicMeter - UsefulMethods.getMagicSpellCost(spell3);
			return spell3;
		}
		else if (selection == 4 && magicMeter >= UsefulMethods.getMagicSpellCost(spell4)) {
			magicMeter = magicMeter - UsefulMethods.getMagicSpellCost(spell4);
			return spell4;
		}
		println("Your spell failed because you don't have enough Magic Points.");
		return Spells.spellFailed;
	}
	
	/**
	 * A void method used to revive a character in battle if a revive is used.
	 * This revived character will have half HP.
	 * Otherwise this method does nothing.
	 */
	public void revive() {
		if(this.isAlive == false) {
			this.isAlive =true;
			this.HP = (int)(this.HPbase * this.level / 2);
		}
		else {
			System.out.println("Revives only work on fainted characters.");
		}
	}
	
	/**
	 * A method for giving the character a weapon and modifies the stats of the character
	 * @w accepts a Weapons enum
	 */
	public void giveWeapon(Weapons w) {
		this.weapon = w;
		
		//These stat modifiers are unique to the specific character class
		if(w == Weapons.SilverTiara) {
			weapon = w;
			Spec = (int) (Spec*1.1);
		}
		else if(w == Weapons.fairyScepter) {
			weapon = w;
			Spec = (int) (Spec*1.2);
		}
	}
	
	/**
	 * A method for giving the character armor
	 * @a accepts a valid Armor enum
	 */
	public void giveArmor(Armor a) {
		this.armor = a;
		
		//these stat modifiers based on armor are unique to this character
		
	}
	
	/**
	 * Sets the armor value to none and removes armor stat modifiers.
	 */
	public void takeArmor() {
		this.Def = this.DefBase * this.level;
		this.Spec = this.SpecBase *this.level;
		this.armor = Armor.none;
	}
	
	/**
	 * Sets the weapon value to none and removes weapon stat modifiers
	 */
	public void takeWeapon() {
		this.Atk = this.AtkBase *this.level;
		this.Spec = this.SpecBase *this.level;
		this.weapon = Weapons.none;
	}
	
	/**
	 * A method that returns a string of the character's first name
	 */
	public String getFirstName() {
		return this.FIRST_NAME;
	}
	
	/**
	 * A method that returns the string of the character's last name
	 */
	public String getLastName() {
		return this.LAST_NAME;
	}
	
	/**
	 * A method that returns the string of the character's nickname
	 */
	public String getNickname() {
		return this.NICKNAME;
	}
	
	/**
	 * A method for getting the armor of the character and returns an Armor enum.
	 */
	public Armor getArmor() {
		return this.armor;
	}
	
	/**
	 * A method for getting the weapon of the character and returns a Weapons enum
	 */
	public Weapons getWeapon() {
		return this.weapon;
	}
	
	/**
	 * A method that returns a string of all the characters moves.
	 */
	public String getMoves() {
		return "1: " + move1 + " 2: "+ move2 + " 3: "+move3+" 4: "+move4+"5 Super Move: "+ superMove;
	}
	
	/** 
	 * A method that retuns a string of all the character's spell.
	 */
	public String getSpells() {
		return "1: " + spell1 + " 2: "+ spell2 + " 3: "+spell3+" 4: "+spell4;
	}
	
	/**
	 * A method that returns a Moves enum array of the characters moveset in the order of:
	 * [move1,move2,move3,move4,superMove]
	 */
	public Moves[] getMovesByID() {
		Moves[] moves = new Moves[] {
				move1,move2,move3,move4,superMove
		};
		return moves;
	}
	
	/**
	 * A method that returns an array of Spell enum of the character in the order of:
	 * [spell1,spell2,spell3,spell4]
	 */
	public Spells[] getSpellsByID() {
		Spells[] spells = new Spells[] {
				spell1,spell2,spell3,spell4
		};
		return spells;
	}
	
	/**
	 * A method that accept an int of the exp gained from battle and updates the 
	 * character's EXP and levels them up when available.
	 * However, this method will prevent characters from goign above level 100.
	 * @expGained accepts an integer of the exp gained from battle.
	 */
	public void updateEXP(int expGained) {
		this.EXP = this.EXP + expGained;
		int nextLevel = this.level + 1;
		int nextLevelEXP = (int)(this.BASE_EXP * Math.pow(1.149,nextLevel));
		
		//determines if a character levels up
		if(this.EXP > nextLevelEXP) {
			this.level = this.level + 1;
			
			//updates stats when you level up
			HP = HPbase * level;
			Atk = AtkBase * level;
			Def = DefBase * level;
			Spec = SpecBase * level;
			
			//makes sure your armor and weapons stat modifiers are reapplied
			giveArmor(this.armor);
			giveWeapon(this.weapon);
			
			updateEXP(0);//an attempt to have the EXP not level up every turn if the player leveled up more than once with enough EXP
		}
		
		//doesn't allow the character to go above level 100
		if (this.level > 100)this.level = 100;
	}
	
	/**
	 * A method that allows the program to manually set a specific level
	 * @levels accepts an integer of the desired level
	 */
	public void setLevel(int levels) {
		this.EXP = (int)(this.BASE_EXP * Math.pow(1.149,levels)+1);
		this.level = levels;
		if (level > 100)level = 100;
		
		//updates stats when you level up
		HP = HPbase * level;
		Atk = AtkBase * level;
		Def = DefBase * level;
		Spec = SpecBase * level;
		
		//makes sure your armor and weapons stat modifiers are reapplied
		giveArmor(this.armor);
		giveWeapon(this.weapon);
	}
	
	/**
	 * A method that allows the program to restore HP for the character but will prevent the HP from 
	 * going above it's maximum HP given the character's level.
	 * @hpRestored accepts an integer of the HP being restored.(can be negative)
	 */
	public void restoreHP(int hpRestored) {
		this.HP = hpRestored + this.HP;
		if(this.HP > this.HPbase * this.level)this.HP = this.HPbase * this.level;
	}
	
	/**
	 * A method that allows the user to change the character's moveset. 
	 * @newMove accepts a Moves enum of the new move.
	 * @place accepts an integer from [1-4] signifying which move gets replaced. Otherwise an error message
	 * is printed out that states that the new move position is invalid.
	 */
	public void changeMoveset(Moves newMove, int place) {
		if(place == 1) this.move1 = newMove;
		else if(place == 2) this.move2 = newMove;
		else if(place == 3) this.move3 = newMove;
		else if(place == 4) this.move4 = newMove;
		else {
			System.out.println("Error: Invalid move selection.");
		}
	}
	
	/**
	 * A method that allows the user to change the character's spells. 
	 * @newMove accepts a Spells enum of the new spell.
	 * @place accepts an integer from [1-4] signifying which move gets replaced. Otherwise an error message
	 * is printed out that states that the new move position is invalid.
	 */
	public void changeSpells(Spells newMove, int place) {
		if(place == 1) this.spell1 = newMove;
		else if(place == 2) this.spell2 = newMove;
		else if(place == 3) this.spell3 = newMove;
		else if(place == 4) this.spell4 = newMove;
		else {
			System.out.println("Error: Invalid move selection.");
		}
	}
	
	/**
	 * A method for setting the EXP value of the character.
	 * This method is meant to be used in tandem with the setLevel method.
	 * @exp Accepts an int of the exp value to be set to the character
	 */
	public void setEXP(int exp) {
		this.EXP = exp;
	}
	
	/**
	 * A method that returns an int of the character's agility stat.
	 */
	public int getAgility() {
		return this.AGILITY;
	}
	
	/**
	 * A void method that sets the defending value to true
	 */
	public void defend() {
		this.isDefending = true;
		this.superPowerMeter += 10;
		restoreMagic(30);//defending restores 15 magic points to this character
	}
	
	/**
	 * A void method so the defending stat doesn't go on forever and can be stopped by the battle manager
	 */
	public void stopDefending() {
		this.isDefending = false;
	}
	
	/**
	 * A void method for restoring magicPoints but will not set the magic points beyond
	 * the specified maximum value.
	 * @magicPoints Accepts an int of the amount of magic points restored.
	 */
	public void restoreMagic(int magicPoints) {
		this.magicMeter = this.magicMeter + magicPoints;
		if(this.magicMeter > this.MAX_MAGIC)this.magicMeter = this.MAX_MAGIC;
	}
	
	/**
	 * A void method that reduces the magic points of the character.
	 * The method will not let the character's magic points go bellow zero.
	 * @lostPoints Accepts an int of the magic points to be reduced by.
	 */
	public void reduceMagic(int lostPoints) {
		this.magicMeter = this.magicMeter - lostPoints;
		if(this.magicMeter < 0)this.magicMeter = 0;
	}
	
	/**
	 * A method that returns an int of the value of the magic meteer for the battle manager class.
	 */
	public int getMagicMeter() {
		return this.magicMeter;
	}
	
	/**
	 * A method that returns an enum of the Abilities type of the character's set ability
	 */
	public Abilities getAbility() {
		return this.ability;
	}
	
	/**
	 * A method that returns the race of the character.
	 * @return Returns an enum of type Races of the character's race
	 */
	public Races getRace() {
		return this.race;
	}
	
	/**
	 * A method that returns the gender of the character.
	 * @return Returns a char of the gender of the character. 'M' is male and 'F' is female.
	 */
	public char getGender() {
		return this.GENDER;
	}
	
	/**
	 * A method that returns a string of random character taunts
	 * @return Returns a string of the taunt
	 */
	public String getTaunt() {
		int tauntNum = rand.nextInt(8);
		return this.TAUNTS[tauntNum];
	}
	
	/**
	 * A method that returns an int of the Super Meter
	 */
	public int getSuperMeter() {
		return this.superPowerMeter;
	}
	
	/**
	 * A helper method so I don't have to write System.out.println every time
	 */
	private void println(String words) {
		System.out.println(words);
	}
	
	/**
	 * A method that returns if Thicc Tanuki is alive
	 * @return returns a boolean of the character's isAlive status
	 */
	public boolean getIsAlive() {
		return isAlive;
	}
}
