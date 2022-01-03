import java.util.Random;
public class Tier1AI {

	/**
	 * A class designed for how the low level enemy types will behave and how their 
	 * damage and move choice works.
	 * version 1.5
	 */
	Random rand = new Random();
	
	// base stats
	private Elementals type;
	private int HPbase;
	private int AtkBase;
	private int DefBase;
	private int SpecBase;
	private final Abilities ability = Abilities.noAbility;
	private final Weapons w = Weapons.none;
	private int ID_NUMBER;
	int baseEXP;
	
	//in battle stats and default to zero
	private int level = 0;
	private int HP = 1;
	private int Atk = 0;
	private int Def = 0;
	private int Spec = 0;
	private boolean isAlive = true;//will default to true when spawned
	private int EXPonDefeat = 0;
	private Moves[] moveSet = new Moves[] {
			Moves.placeholder, Moves.placeholder
	};
	private boolean expGained = false;
	
	/**
	 * A constructor that generates the behavior and attack type for the low tier enemies
	 * @elementType Accepts an enumeration of Elementals
	 * @param baseStats - Acceps an array of the enemy's base stats in the order of [HP,Atk,Def,Spec,Base_EXP,ID_NUMBER]
	 * @levels Accept and int of the level of the enemy
	 * @param moveSet - Accepts an array of enum type Moves in the order of [move1,move2]; Tier 1 enemies can only attack and learn two moves
	 */
	public Tier1AI(Elementals elementType, int[] baseStats, int levels, Moves[] moveSet) {
		this.type = elementType;
		this.HPbase = baseStats[0];
		this.AtkBase = baseStats[1];
		this.DefBase = baseStats[2];
		this.SpecBase = baseStats[3];
		baseEXP = baseStats[4];
		ID_NUMBER = baseStats[5];
		
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
		double damageMultiplier = 1;//is set to 1 by default
		
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
			System.out.println("You sure gave that regular guy some fright.");
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
		
		//calculate damage based on attack and special
		if(isSpecial == false) {
			int damage = (int) (baseDamage * levels * damageMultiplier*(atkStat / this.Def));
			this.HP = this.HP - damage;
			if(HP <= 0)this.isAlive = false;
			return damage;
		}
		else if (isSpecial == true) {
			int damage = (int) (baseDamage * levels * damageMultiplier*(specStat / this.Spec));
			this.HP = this.HP - damage;
			if(HP <= 0)this.isAlive = false;
			return damage;
		}
		else {
			return 0;
		}
	}
	
	//how the base level AI selects moves
	public Moves moveChooser() {
		if(this.isAlive == false)return Moves.isDead;
		int choice = rand.nextInt(2);
		return moveSet[choice];
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
}