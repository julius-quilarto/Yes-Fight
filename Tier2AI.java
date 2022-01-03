/**
 * A class that says how the Tier2AI will Opperate
 * @author Alexander
 *
 */
import java.util.Random;
public class Tier2AI {
	Random rand = new Random();
	
	// base stats
	private Elementals[] type = new Elementals[2];
	private short HPbase;
	private short AtkBase;
	private short DefBase;
	private short SpecBase;
	private short AGILITY;
	private final Abilities ability;
	private final Weapons w;
	private int ID_NUMBER;
	int baseEXP;
	private String[] FLAVOR_TEXT= new String[2];
	private String characterDescription;
	
	//in battle stats and default to zero
	private int level = 0;
	private int HP = 1;
	private int Atk = 0;
	private int Def = 0;
	private int Spec = 0;
	private boolean isAlive = true;//will default to true when spawned
	private int EXPonDefeat = 0;
	private Moves[] moveSet = new Moves[4];
	private Spells spells;
	private boolean expGained = false;
	private final byte MAX_MAGIC = 100;
	private byte magicMeter = 0;
	
	/**
	 * A constructor fo the tier 2 AI class
	 * @param elementType - Accepts an array of two enums of type Elementals of the character's type
	 * @param baseStats - Accepts an array of shorts of the character's base stats in the order of [HP,Atk,Def,Spec,Agility,EXP]
	 * @param ID - Accepts a byte of the character's ID number
	 * @param levels - Accepts an int of the character's level
	 * @param moveSet - Accepts an array of 4 enums of type Moves
	 * @param we - accepts an enum of type Weapons for the character's weapon
	 * @param abs - accepts an enum of type Abilities of the character's ability
	 * @param ft - Accepts an array of 2 Strings of the character's flavor text
	 * @param cd - Accepts a String of the character's description
	 */
	public Tier2AI(Elementals[] elementType, short[] baseStats, byte ID, int levels, Moves[] moveSet, Weapons we,Abilities abs,String[] ft,String cd, Spells sp) {
		this.type = elementType;
		this.HPbase = baseStats[0];
		this.AtkBase = baseStats[1];
		this.DefBase = baseStats[2];
		this.SpecBase = baseStats[3];
		this.AGILITY = baseStats[4];
		baseEXP = baseStats[5];
		ID_NUMBER = ID;
		w = we;
		ability = abs;
		FLAVOR_TEXT = ft;
		characterDescription = cd;
		spells = sp;
		
		//generating battle stats
		this.level = levels;
		this.HP = this.HPbase * levels;
		this.Atk = this.AtkBase * levels;
		this.Def = this.DefBase * levels;
		this.Spec = this.SpecBase * levels;
		this.EXPonDefeat = baseEXP * levels;
		this.moveSet = moveSet;
	}
	
	//a method for calculating damage taken by the enemy
	public int damageCalculator(int baseDamage, Elementals damageType, boolean isSpecial, int atkStat, int specStat, int levels) {
		if(isAlive==false) {
			return 0;
		}
		int randomNumber = rand.nextInt(100);
		if (randomNumber < AGILITY) {
			System.out.println("Enemy ID: "+ID_NUMBER+" has dodged the attack.");
			return 0;
		}
		double damageMultiplier = UsefulMethods.getDamageMultiplier(type[0],damageType);
		damageMultiplier *= UsefulMethods.getDamageMultiplier(type[1],damageType);
		
		//for calculating critical hits. 1 in 10 chance for a critical hit.
		boolean crit = false;
		if (rand.nextInt(10) == 1) {
			crit = true;
		}
		if(crit == true)damageMultiplier = damageMultiplier * 1.5;
		
		//calculate damage based on attack and special
		if(isSpecial == false) {
			int damage = (int) (1.0*baseDamage * levels * damageMultiplier*(1.0*atkStat / this.Def));
			this.HP = this.HP - damage;
			if(HP <= 0)this.isAlive = false;
			return damage;
		}
		else if (isSpecial == true) {
			int damage = (int) (1.0*baseDamage * levels * damageMultiplier*(1.0*specStat / this.Spec));
			this.HP = this.HP - damage;
			if(HP <= 0)this.isAlive = false;
			return damage;
		}
		else {
			return 0;
		}
	}

	/**
	 * A method that returns the move the AI selected
	 * @return returns an enum of type Moves
	 */
	public Moves moveChooser() {
		if(this.isAlive == false)return Moves.isDead;
		int choice = rand.nextInt(4);
		return moveSet[choice];
	}
	
	/**
	 * A method that allows the AI to use a spell
	 * @return returns an enum of type Spells that the AI used
	 */
	public Spells chooseSpell() {
		magicMeter -= UsefulMethods.getMagicSpellCost(spells);
		return spells;//dummy return statement. Change this later.
	}
	
	/**
	 * A method that allows the tier two AI to choose between regular and magic based attacks.
	 * @return returns an boolean that is FALSE if the move is regular and TRUE if the move being used is magic
	 */
	public boolean decideMoveOrMagic() {
		magicMeter += 10;
		if(magicMeter > MAX_MAGIC) magicMeter = MAX_MAGIC;
		if(magicMeter < UsefulMethods.getMagicSpellCost(spells))return false;
		int randomInt = rand.nextInt(2);
		if (randomInt>0)return true;
		return false;
	}
	
	
	/**
	 * A method that returns an array of the battle stat integers in the order of:
	 * [level, HP, Atk, Def, Spec]
	 */
	public int[] getStats(){
		int statAry[] = new int[] {
			this.level, this.HP, this.Atk, this.Def, this.Spec
		};
		return statAry;
	}
	
	/**
	 * A method that returns an integer of the experience of a defeated enemy when called but only once.
	 */
	public int getEXP() {
		if(this.expGained == false && this.isAlive == false) {
			this.expGained = true;
			return this.EXPonDefeat;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * A method that gets the character's ability.
	 * @return Returns an enum of type Abilities of the characher's ability
	 */
	public Abilities getAbility() {
		return this.ability;
	}
	
	/**
	 * A method that gets the weapon of the character
	 * @return returns an enum of type Weapons of the character's weapon.
	 */
	public Weapons getWeapon() {
		return w;
	}
	/**
	 * A method that instantly kills the enemy.
	 */
	public void instaKill() {
		HP = 0;
		isAlive = false;
	}
	
	/**
	 * A method that is used to check if the character is still alive.
	 * @return returns a boolean if the character is still alive.[false if dead/true if alive]
	 */
	public boolean checkIfAlive() {
		return isAlive;
	}
	
	/**
	 * A method that gets the character's ID number.
	 * @return returns an int of the character's ID number.
	 */
	public int getID() {
		return ID_NUMBER;
	}
	
	/**
	 * A method that gets the character's description.
	 * @return returns as String of the character's description.
	 */
	public String getCharacterDescription() {
		return characterDescription;
	}
	
	/**
	 * A method that return a String of the character flavor text at random.
	 * @return Returns a String of the character flavor text with a one in 16 chance.
	 */
	public String getFlavorText() {
		int randomNumber = rand.nextInt(16);
		if (randomNumber > 1)return "";
		else if(randomNumber > 0) return FLAVOR_TEXT[1];
		else {
			return FLAVOR_TEXT[0];
		}
	}

}
