import java.util.Random;
/**
 * A class that acts as the player's character.
 * I'd like to have aded more features here but ran out of time in my schedule
 * @author Alexander
 * @verison 1.0
 *
 */
public class Player {
	Random rand = new Random();
	// base stats
		private final Elementals type = Elementals.neutral;
		private final int HPbase = 90;
		private final int AtkBase = 95;
		private final int DefBase = 95;
		private final int SpecBase = 95;
		private final int AGILITY= 5;
		private final String FIRST_NAME = "YOUR NAME";
		private final String LAST_NAME = "YOUR NAME";
		private final String NICKNAME = "NICKNAME";
		private final int BASE_EXP = 100;
		private final int MAX_MAGIC = 100;
		private final Abilities ability = Abilities.noAbility;
		private final char GENDER = 'T';
		private final Races race = Races.human;
		private final String taunt = "PLAYER: Imagines their own personal taunt towards the enemies.";
		//in battle stats and default to zero
		private int level = 0;
		private int HP = 1;
		private int Atk = 0;
		private int Def = 0;
		private int Spec = 0;
		private boolean isAlive = true;
		private final Moves[] moves = new Moves[] {Moves.swordSlash,Moves.pummel,Moves.thunderbolt,Moves.frostBite};
		private final Spells[] spells = new Spells[] {Spells.BinaryBeam,Spells.tornado,Spells.KitsuneFire,Spells.EXbuster};
		private Weapons weapon = Weapons.techSword;
		private Armor armor = Armor.fursuit;
		private boolean isDefending;
		private int superPowerMeter=0;
		private int EXP = 1;
		private int magicMeter = 0;
		private Moves superMove = Moves.ForceOfWill;
		/**
		 * A method to construct the player object.
		 * @param currentHP Accepts an int of the player's current HP stat
		 * @param level accepts an int of the player's current level
		 * @param a accepts an enum of type Armor of the player's current armor
		 * @param w accepts an enum of type Weapons of the player's current weapon
		 * @param exp accepts an int of the player's current exp stat
		 */
	public Player(int currentHP,int level,Armor a, Weapons w, int exp) {
		HP = currentHP;
		setLevel(level);
		EXP = exp;
		giveWeapon(w);
		giveArmor(a);
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
			int damage = (int) (Math.ceil(1.0*baseDamage * levels * damageMultiplier *(atkStat / this.Def) / defendingMultiplier));
			this.HP = this.HP - damage;
			if(HP <= 0) {
				this.isAlive = false;
				this.HP = 0;
			}
			return damage;
		}
		else if (isSpecial == true) {
			int damage = (int) (Math.ceil(1.0*baseDamage * levels * damageMultiplier * (specStat / this.Spec)));
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
		if (moveNumber < 1 || moveNumber > 4) {
			println("ERROR: Invalid move selection.");
			return Moves.placeholder;
			}
		return moves[moveNumber-1];
	}
	/**
	 * A method for choosing a spell
	 * @param selection - accepts a byte of the spell selected
	 * If magic meter is too low or an invalid spell selection, the spell is will return an enum of Spells.spellFailed and 
	 * set your magic meter to zero for this character.
	 * @return Returns an enum of type Spells
	 */
	public Spells chooseSpell(byte selection) {
		if (magicMeter < UsefulMethods.getMagicSpellCost(spells[selection-1])) {
		println("Your spell failed because you don't have enough Magic Points.");
		return Spells.spellFailed;
		}
		magicMeter -= UsefulMethods.getMagicSpellCost(spells[selection-1]);
		return spells[selection-1];
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
		weapon = w;
		//These stat modifiers are unique to the specific character class
		if(w == Weapons.sword || w == Weapons.techSword)Atk = (int) (Atk*1.1);
		else if(w == Weapons.colt1911) Atk = (int)(Atk*1.15);
		else if(w == Weapons.magnum357revolver)Atk = (int)(Atk*1.25);
		else if(w == Weapons.fairyScepter)Spec = (int)(Spec*1.25);
	}
	
	/**
	 * A method for giving the character armor
	 * @a accepts a valid Armor enum
	 */
	public void giveArmor(Armor a) {
		armor = a;
		//these stat modifiers based on armor are unique to this character
		if(a == Armor.MedievalArmor)Def = (int)(Def*1.5);
		else if(a == Armor.fursuit) {
			Def = (int)(Def*1.01);
			Spec = (int)(Spec*1.05);
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
		return "1: " + moves[0] + " 2: "+ moves[1] + " 3: "+moves[2]+" 4: "+moves[3]+"5 Super Move: "+ superMove;
	}
	
	/** 
	 * A method that retuns a string of all the character's spell.
	 */
	public String getSpells() {
		return "1: " + spells[0] + " 2: "+ spells[1] + " 3: "+spells[2]+" 4: "+spells[3];
	}
	
	/**
	 * A method that returns a Moves enum array of the characters moveset in the order of:
	 * [move1,move2,move3,move4,superMove]
	 */
	public Moves[] getMovesByID() {
		return moves;
	}
	
	/**
	 * A method that returns an array of Spell enum of the character in the order of:
	 * [spell1,spell2,spell3,spell4]
	 */
	public Spells[] getSpellsByID() {
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
		restoreMagic(20);//defending restores 15 magic points to this character
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
		return taunt;
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
