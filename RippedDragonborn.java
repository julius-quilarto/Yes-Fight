/**
 * A class for the ripped dragonborn guy are gal.
 * @author Alexander
 * @version 1.0
 *
 */

import java.util.Random;

public class RippedDragonborn {
Random rand = new Random();
	
	// base stats
	private final Elementals type = Elementals.dragon;
	private final int HPbase = 80;//average hp
	private final int AtkBase = 145;//he/she is ripped so they have a high physical attack stat
	private final int DefBase = 110;
	private final int SpecBase = 45;//all muscle so low special
	private final int AGILITY= 0;//maximum value is 100 which would mean the dodges everything
	private final String FIRST_NAME = "Dargoni";
	private final String LAST_NAME = "Drakovasilia";
	private final String NICKNAME = "Anthro Dragon";
	private final int BASE_EXP = 100;//base exp for leling up
	private final int MAX_MAGIC = 100;//a stat that is used for the maximum number of magic points a character can have
	private final Abilities ability = Abilities.DragonCore;//sets the unchanged default ability of the character
	private final char GENDER = 'M';//'M' for male and 'F' for female
	private final Races race = Races.dragonborn;//character race in case it become relevant later
	private final String[] TAUNTS = new String[] {
			"ANTHRO DRAGON: I will take Jerusalem!",
			"ANTHRO DRAGON: The only thing they fear is me!",
			"ANTHRO DRAGON: I'm gonnna make an anouncement! You're about to be thrashed until there's nothing left to thrash!",
			"ANTHRO DRAGON: *Anthro Dragon is flexing on Giga Chad*",
			"ANTHRO DRAGON: Do you even lift?",
			"ANTHRO DRAGON: *Anthro Dragon is listening to heavy metal while grinning menacingly*",
			"ANTHRO DRAGON: *flashes sword* It's time for a crusade!",
			"ANTHRO DRAGON: As an anthro dragon, I can easily rip your face off."
	};
	
	//in battle stats and default to zero
	private int level = 0;
	private int HP = 1;
	private int Atk = 0;
	private int Def = 0;
	private int Spec = 0;
	private boolean isAlive = true;//will default to true when spawned
	private Moves move1 = Moves.dragonFist;
	private Moves move2 = Moves.fireFist;
	private Moves move3 = Moves.swordSlash;
	private Moves move4 = Moves.dragonBreath;
	private Spells spell1 = Spells.DragonSurge;
	private Spells spell2 = Spells.fissure;
	private Spells spell3 = Spells.none;
	private Spells spell4 = Spells.none;
	private Moves superMove = Moves.DragonForce;//Ripped Dragonborn's signature supermove
	private Weapons weapon = Weapons.sword;//defaults to her signature Colt 1911
	private Armor armor = Armor.kevlar;//defaults to her agent kevlar bullet resistant armor
	private boolean isDefending = false;//defaults to false during battle
	private int superPowerMeter = 0;//defaults to zero at the start of battle and is available once the meter goes above 100
	private int EXP = 1;//exp defaults to 100 when the value is not saved
	private int magicMeter = 0;//you need to build up magic in battle to use spells on this character
	
	/**
	 * Default ThiccTanuki constructor if no save file is loaded.
	 */
	public RippedDragonborn() {
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
	public RippedDragonborn(int currentHP,int level,Armor a, Weapons w, int exp, Moves[] moveSet, Spells[] spellSet) {
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
		double damageMultiplier = 1;//is set to 1 by default
		int defendingMultiplier = 1;//is set to 1 by default and set to 2 when defending is set to true
		if(this.isDefending == true) defendingMultiplier = 2;
		
		//type effectiveness table
		if (damageType == Elementals.neutral && this.type == Elementals.ethereal) {
			System.out.println("Ethereal spirits are unaffected by normal attacks.");
			return 0;
		}
		if (damageType == Elementals.electric && this.type == Elementals.earth) {
			System.out.println("The earth type has grounded your electrical current.");
			return 0;
		}
		if (damageType == Elementals.earth && this.type == Elementals.air) {
			System.out.println("Air types float above the ground making them immune to Earth types.");
			return 0;
		}
		if (damageType == Elementals.fire && this.type == Elementals.dragon) {
			damageMultiplier = -2;
			this.Atk = this.Atk * 2;
			this.Spec = this.Spec * 2;
			System.out.println("Don't you know dragons breath fire? Fire literally heals them and ups their stats.");
		}
		if (damageType == Elementals.electric && this.type == Elementals.tech) {
			damageMultiplier = -2;
			this.Spec = this.Spec * 2;
			System.out.println("You have doubled the enemy's special and regained it's health.");
		}
		if (damageType == Elementals.fire && this.type == Elementals.nature) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.fire && this.type == Elementals.dark) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.fire && this.type == Elementals.ethereal) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.fire && this.type == Elementals.fire) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.tech && this.type == Elementals.nature) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.tech && this.type == Elementals.dark) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.tech && this.type == Elementals.dragon) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.tech && this.type == Elementals.fire) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.water && this.type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && this.type == Elementals.earth) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && this.type == Elementals.tech) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && this.type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && this.type == Elementals.nature) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.water && this.type == Elementals.water) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.nature && this.type == Elementals.water) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.nature && this.type == Elementals.earth) {
			damageMultiplier = 2.5;
		}
		if (damageType == Elementals.nature && this.type == Elementals.dark) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.nature && this.type == Elementals.dragon) {
			damageMultiplier = 0.9;
		}
		if (damageType == Elementals.nature && this.type == Elementals.nature) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.dragon) {
			damageMultiplier = 1.5;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.tech) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.water) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.earth) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.air) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.dark) {
			damageMultiplier = 1.75;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.electric) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.ethereal) {
			damageMultiplier = 0.25;
		}
		if (damageType == Elementals.dragon && this.type == Elementals.nature) {
			damageMultiplier = 0.55;
		}
		if (damageType == Elementals.electric && this.type == Elementals.nature) {
			damageMultiplier = 0.65;
		}
		if (damageType == Elementals.electric && this.type == Elementals.dark) {
			damageMultiplier = 3;
		}
		if (damageType == Elementals.electric && this.type == Elementals.air) {
			damageMultiplier = 2.5;
		}
		if (damageType == Elementals.electric && this.type == Elementals.water) {
			damageMultiplier = 1.85;
		}
		if (damageType == Elementals.electric && this.type == Elementals.dragon) {
			damageMultiplier = 0.65;
		}
		if (damageType == Elementals.electric && this.type == Elementals.ethereal) {
			damageMultiplier = 1.15;
		}
		if (damageType == Elementals.electric && this.type == Elementals.nature) {
			damageMultiplier = 0.65;
		}
		if (damageType == Elementals.ethereal && this.type == Elementals.dragon) {
			damageMultiplier = 1.85;
		}
		if (damageType == Elementals.ethereal && this.type == Elementals.ethereal) {
			damageMultiplier = 3.5;
		}
		if (damageType == Elementals.ethereal && this.type == Elementals.dark) {
			damageMultiplier = 0.1;
		}
		if (damageType == Elementals.ethereal && this.type == Elementals.neutral) {
			damageMultiplier = 2.85;
		}
		if (damageType == Elementals.dark && this.type == Elementals.dark) {
			damageMultiplier = -2;
		}
		if (damageType == Elementals.dark && this.type == Elementals.dragon) {
			damageMultiplier = 0.28;
		}
		if (damageType == Elementals.dark && this.type == Elementals.ethereal) {
			damageMultiplier = 1.85;
		}
		if (damageType == Elementals.dark && this.type == Elementals.neutral) {
			damageMultiplier = 2.8;
		}
		if (damageType == Elementals.dark && this.type == Elementals.electric) {
			System.out.println("Darkness had made the electricity shine brighter.");
			this.Spec = this.Spec * 2;
			damageMultiplier = 1;
		}
		if (damageType == Elementals.dark && this.type == Elementals.nature) {
			damageMultiplier = 3.25;
		}
		if (damageType == Elementals.dark && this.type == Elementals.fire) {
			System.out.println("Darkness makes the fire shine brighter.");
			this.Spec = this.Spec * 2;
			damageMultiplier = 1;
		}
		if (damageType == Elementals.dark && this.type == Elementals.earth) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.earth && this.type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.earth && this.type == Elementals.tech) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.earth && this.type == Elementals.electric) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.earth && this.type == Elementals.nature) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.earth && this.type == Elementals.water) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.air && this.type == Elementals.fire) {
			System.out.println("Oxygen makes fire burn hotter.");
			this.Spec = this.Spec *2;
			damageMultiplier = 1;
		}
		if (damageType == Elementals.air && this.type == Elementals.water) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.air && this.type == Elementals.ethereal) {
			damageMultiplier = 2.5;
		}
		if (damageType == Elementals.air && this.type == Elementals.nature) {
			damageMultiplier = 0.45;
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
			System.out.println(this.FIRST_NAME + " has evaded the attack.");
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
		this.magicMeter = this.magicMeter + 10;//restores magic meter by using regular attacks
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
			Atk = Atk * 2;
			Def = Def * 2;
			Spec = Spec * 3;
			return this.superMove;
		}
		else {
			println("Invalid move selection");
			return Moves.placeholder;
			
		}
	}
	
	/**
	 * A method to disable Dragon Force and return stats back to normal
	 */
	public void disableDragonForce() {
		println("Dragon Force has worn off.");
		Atk = AtkBase * level;
		Def = DefBase * level;
		Spec = SpecBase* level;
		
		giveArmor(this.armor);
		giveWeapon(this.weapon);
	}
	
	/**
	 * A method for choosing a spell
	 * @param selection - accepts a byte of the spell selected
	 * If magic meter is too low or an invalid spell selection, the spell is will return an enum of Spells.spellFailed and 
	 * set your magic meter to zero for this character.
	 * @return Returns an enum of type Spells
	 */
	public Spells chooseSpell(byte selection) {
		if(selection == 1 && magicMeter >= UsefulMethods.getMagicSpellCost(spell1)) {
			this.magicMeter = magicMeter - UsefulMethods.getMagicSpellCost(spell1);
			return spell1;
		}
		else if(selection == 2 && magicMeter >= UsefulMethods.getMagicSpellCost(spell2)) {
			this.magicMeter = magicMeter - UsefulMethods.getMagicSpellCost(spell2);
			return spell2;
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
		
		if (weapon == Weapons.sword) {
			Atk = (int)(Atk * 1.1);
		}
		else if (weapon == Weapons.techSword) {
			Atk = (int)(Atk * 1.2);
		}
		else if (weapon == Weapons.energySword) {
			Atk = (int)(Atk * 1.3);
			Spec = (int)(Spec * 1.2);
		}
		else if (weapon == Weapons.DragonAxe) {
			Atk = (int)(Atk * 1.8);
		}
		else if (weapon == Weapons.DeusVultSword) {
			Atk = (int)(Atk * 2.3);
			Spec = (int)(Spec * 1.4);
		}
		else if(w == Weapons.KitsuneKatana) Atk = (int)(Atk*1.25);
	}
	
	/**
	 * A method for giving the character armor
	 * @a accepts a valid Armor enum
	 */
	public void giveArmor(Armor a) {
		this.armor = a;
		
		//these stat modifiers based on armor are unique to this character
		if(a == Armor.MedievalArmor) {
			this.Def = (int)(this.Def*1.8);
		}
		else if (a == Armor.DeusVultCrusadesArmor){
			Def = Def * 3;
			Spec = (int)(Spec * 2.1);
		}
		else if (a == Armor.tungstenPlateArmor) {
			Def = Def * 4;//only a ripped anthro dragon is physically strong enough to dawn tungsten plate armor
		}
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
		restoreMagic(15);//defending restores 15 magic points to this character
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
		if (weapon == Weapons.chainsaw) return TAUNTS[5];
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


