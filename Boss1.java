import java.util.Random;
/**
 * A class that tells the first boss what to do
 * @author Alexander
 *@version 1.0
 */
public class Boss1 {
	Random rand = new Random();
	
	private int HP = 20000;//make this 20000 later
	private int Atk = 1600;
	private int Def = 2600;
	private int Spec = 2700;
	static final int EXP = 2000;
	static final byte KitsuneAGILITIY = 5;
	static final byte Kitsunelevel = 20;
	static final Elementals Kitsunetype1 = Elementals.ethereal;
	static final Elementals Kitsunetype2 = Elementals.nature;
	static final Moves[] KitsuneMoves = new Moves[] {
			Moves.etherealBlade,
			Moves.naturePower,
			Moves.fireFist,
			Moves.swordSlash,
			Moves.shadowPunch,
			Moves.thunderbolt
			};
	static final Spells KitsuneSpells = Spells.KitsuneFire;
	static final Moves KitsuneSuperMove = Moves.KitsuneIllusions;
	static final Armor KitsuneArmor = Armor.samurai;
	static final Weapons KitsuneWeapon = Weapons.KitsuneKatana;
	static final String KitsuneDiscription = "This Kitsune has an extreme prejudice towards Tanukis and has a high special.\n"
			+ "Level: 20 Atk: 1600 Def: 2600 Spec: 2700 Agility: 15";
	static final String[] KitsuneFlavorText = new String[] {
		"What is it with you Tanukis thinking you can overpower your superior Kitsunes?",
		"Do you honestly think you even have a chance against a 9-tailed fox?",
		"As I live more than 1000 years, I'll enjoy watching you grow old and frail.",
		"You anthro Dragons really are pathetic aren't you? I could easily one-shot you with your special that's as pathetic as your life."
	};
	static final short MAX_MAGIC = 400;
	static short MagicMeter = 0;
	static short superPowerMeter = 0;
	private boolean KitsuneAlive = true;
	private byte healingPotions = 1;
	
	public Boss1() {
		Def=(int)(Def*1.5);//because of samurai armor
		Atk = (int)(Atk*1.25);//because of kitsune katana
	}
	
	/**
	 * A method that allows the Kitsune to take damage
	 * @param basePower acceps a short of the base power of a moves to be used on the Kitsune
	 * @param type accepts the elemental damage type of the move being used on the Kitsune as an enum of type Elementals
	 * @param isSpecial accepts a boolean of whether or not the attack being used on the Kitsune is special
	 * @param atk accepts an integer of the atack stat of the character attacking the Kitsune
	 * @param spec accepts an integer of the special stat of the character attacking the Kitsune
	 * @param levels accepts the level of the character attacking the Kitsune
	 * @return returns an integer of the damage taken by the Kitsune but will not be more than 500
	 */
	public int takeDamage(short basePower, Elementals type, boolean isSpecial,int atk, int spec, int levels) {
		//int evasion = rand.nextInt(100)-KitsuneAGILITIY;
		//int randomNum = rand.nextInt(100);
		//if (randomNum >= evasion) {
			//System.out.println("Kitsune has evaded the attack.");
			//return 0;
		//}
		//this conditional prevents overleveled players from one-shotted the boss like a regular enemy
		if(levels > 20) {
			atk = atk/levels*20;
			spec = spec/levels*20;
			levels = 20;
		}
		double typeMultiplier = UsefulMethods.getDamageMultiplier(type,Kitsunetype1);
	    typeMultiplier *= UsefulMethods.getDamageMultiplier(type,Kitsunetype2);
	    
	    int damageTaken = 1;
	    if(!isSpecial) damageTaken = (int)(1.0*basePower * levels*typeMultiplier * (1.0*Spec/spec));
	    else if(isSpecial) damageTaken = (int)(1.0*basePower*levels*typeMultiplier*(1.0*atk/Def));
	    if(damageTaken > 2000)damageTaken = 2000;
	    HP = HP-damageTaken;
	    if(HP<0) {
	    	KitsuneAlive = false;
	    }
		return damageTaken;
	}
	
	/**
	 * A method that returns whether the next move is regular attacks or magic
	 * @return returns an int of the move type: 0= regular moves, 1=magic, 2=SuperMove
	 */
	public int decideMagicOrRegularAttack() {
		MagicMeter += 25;
		superPowerMeter += 4;
		if(HP<5000) {
			int chanceToUseHealingPotion = rand.nextInt(16);
			if(chanceToUseHealingPotion == 1 && healingPotions>0) {
				HP+=4000;
				System.out.println("Kitsune has used a healing potion in the middle of battle.");
			}
			if(HP<2000) {
				HP+=4000;
				System.out.println("Kitsune has used a healing potion in attempt to stall the fight.");
			}
		}
		if(MagicMeter == MAX_MAGIC)return 1;
		if(superPowerMeter >99)return 2;
		if(MagicMeter <45)return 0;
		return rand.nextInt(2);
	}
	
	/**
	 * A method that returns a random move from the Kitsune's moveset
	 * @return returns an enum of type Moves
	 */
	public Moves chooseMove() {
		int moveChoice = rand.nextInt(6);
		return KitsuneMoves[moveChoice];
	}
	
	/**
	 * A method that returns the stats of the Kitsune Boss class
	 * @return returns an array of int's in the form of [HP,level,Atk,Def,Spec]
	 */
	public int[] getStats() {
		int[] x = new int[] {HP,20,Atk,Def,Spec};
		return x;
	}
	
	/**
	 * A method that returns the living status of the Kitsune Boss class
	 * @return returns a boolean of the living status of the Kitsune class TRUE=Alive/FALSE=Dead
	 */
	public boolean checkIfBossAlive() {
		return KitsuneAlive;
	}
	
	public String getTaunt() {
		return KitsuneFlavorText[rand.nextInt(4)];
	}

}
