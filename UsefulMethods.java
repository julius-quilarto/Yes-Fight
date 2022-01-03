import java.util.Random;
/**
 * A class with a bunch of repeatedly used static methods accross a lot of situations
 * @author Alexander
 * @version 1.0
 *
 */
public class UsefulMethods {
	
	/**
	 * A method that returns a double of the damage multiplier of the character based on move type used
	 * @param damageType Accepts an enum of type Elementals of the move's type
	 * @param type Accepts an enum of type Elementals of the character taking damage's type
	 * @return returns a double of the type damage multiplier
	 */
	public static double getDamageMultiplier(Elementals damageType, Elementals type) {
		double damageMultiplier = 1;
		if (type == Elementals.none)return 1.0;
		if (damageType == Elementals.neutral && type == Elementals.ethereal) {
			System.out.println("Ethereal spirits are unaffected by normal attacks.");
			return 0;
		}
		if (damageType == Elementals.electric && type == Elementals.earth) {
			System.out.println("The earth type has grounded your electrical current.");
			return 0;
		}
		if (damageType == Elementals.earth && type == Elementals.air) {
			System.out.println("Air types float above the ground making them immune to Earth types.");
			return 0;
		}
		if (damageType == Elementals.fire && type == Elementals.dragon) {
			damageMultiplier = -2;
			System.out.println("Don't you know dragons breath fire? Fire literally heals them and ups their stats.");
		}
		if (damageType == Elementals.electric && type == Elementals.tech) {
			damageMultiplier = -2;
			System.out.println("You have doubled the enemy's special and regained it's health.");
		}
		if (damageType == Elementals.fire && type == Elementals.nature) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.fire && type == Elementals.dark) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.fire && type == Elementals.ethereal) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.fire && type == Elementals.fire) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.tech && type == Elementals.nature) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.tech && type == Elementals.dark) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.tech && type == Elementals.dragon) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.tech && type == Elementals.fire) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.water && type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && type == Elementals.earth) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && type == Elementals.tech) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.water && type == Elementals.nature) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.water && type == Elementals.water) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.nature && type == Elementals.water) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.nature && type == Elementals.earth) {
			damageMultiplier = 2.5;
		}
		if (damageType == Elementals.nature && type == Elementals.dark) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.nature && type == Elementals.dragon) {
			damageMultiplier = 0.9;
		}
		if (damageType == Elementals.nature && type == Elementals.nature) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.dragon && type == Elementals.dragon) {
			damageMultiplier = 1.5;
		}
		if (damageType == Elementals.dragon && type == Elementals.tech) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.dragon && type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.dragon && type == Elementals.water) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.dragon && type == Elementals.earth) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.dragon && type == Elementals.air) {
			damageMultiplier = 0.75;
		}
		if (damageType == Elementals.dragon && type == Elementals.dark) {
			damageMultiplier = 1.75;
		}
		if (damageType == Elementals.dragon && type == Elementals.electric) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.dragon && type == Elementals.ethereal) {
			damageMultiplier = 0.25;
		}
		if (damageType == Elementals.dragon && type == Elementals.nature) {
			damageMultiplier = 0.55;
		}
		if (damageType == Elementals.electric && type == Elementals.nature) {
			damageMultiplier = 0.65;
		}
		if (damageType == Elementals.electric && type == Elementals.dark) {
			damageMultiplier = 3;
		}
		if (damageType == Elementals.electric && type == Elementals.air) {
			damageMultiplier = 2.5;
		}
		if (damageType == Elementals.electric && type == Elementals.water) {
			damageMultiplier = 1.85;
		}
		if (damageType == Elementals.electric && type == Elementals.dragon) {
			damageMultiplier = 0.65;
		}
		if (damageType == Elementals.electric && type == Elementals.ethereal) {
			damageMultiplier = 1.15;
		}
		if (damageType == Elementals.electric && type == Elementals.nature) {
			damageMultiplier = 0.65;
		}
		if (damageType == Elementals.ethereal && type == Elementals.dragon) {
			damageMultiplier = 1.85;
		}
		if (damageType == Elementals.ethereal && type == Elementals.ethereal) {
			damageMultiplier = 3.5;
		}
		if (damageType == Elementals.ethereal && type == Elementals.dark) {
			damageMultiplier = 0.1;
		}
		if (damageType == Elementals.ethereal && type == Elementals.neutral) {
			damageMultiplier = 2.85;
		}
		if (damageType == Elementals.dark && type == Elementals.dark) {
			damageMultiplier = -2;
		}
		if (damageType == Elementals.dark && type == Elementals.dragon) {
			damageMultiplier = 0.28;
		}
		if (damageType == Elementals.dark && type == Elementals.ethereal) {
			damageMultiplier = 1.85;
		}
		if (damageType == Elementals.dark && type == Elementals.neutral) {
			damageMultiplier = 2.8;
		}
		if (damageType == Elementals.dark && type == Elementals.nature) {
			damageMultiplier = 3.25;
		}
		if (damageType == Elementals.dark && type == Elementals.earth) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.earth && type == Elementals.fire) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.earth && type == Elementals.tech) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.earth && type == Elementals.electric) {
			damageMultiplier = 2;
		}
		if (damageType == Elementals.earth && type == Elementals.nature) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.earth && type == Elementals.water) {
			damageMultiplier = 0.5;
		}
		if (damageType == Elementals.air && type == Elementals.water) {
			damageMultiplier = 1.25;
		}
		if (damageType == Elementals.air && type == Elementals.ethereal) {
			damageMultiplier = 2.5;
		}
		if (damageType == Elementals.air && type == Elementals.nature) {
			damageMultiplier = 0.45;
		}
		return damageMultiplier;
	}
	
	/**
	 * A method that returns the cost of a magical spell as a byte.
	 * @param spell Accepts an enum of type Spells of the spell to messure the cost.
	 * @return Returns a byte of the Spell's cost
	 */
	public static byte getMagicSpellCost(Spells spell) {
		switch (spell) {
		case DragonSurge:
			return SpellsData.DragonSurgeCST;
		case Missingno:
			return SpellsData.MissingnoCST;
		case NaturePower:
			return SpellsData.NaturePowerCST;
		case KitsuneFire:
			return SpellsData.KitsuneFireCST;
		case BinaryBeam:
			return SpellsData.BinaryBeamCST;
		case fissure:
			return SpellsData.fissureCST;
		case tidalWave:
			return SpellsData.tidalWaveCST;
		case FairyGlitter:
			return SpellsData.FairyGlitterCST;
		case NEObuster:
			return SpellsData.NEObusterCST;
		case tornado:
			return SpellsData.tornadoCST;
		case hurricane:
			return SpellsData.hurricaneCST;
		case EXbuster:
			return SpellsData.EXbusterCST;
		case LookKawaii:
			return SpellsData.LookKawaiiCST;
		case TanukiEvade:
			return SpellsData.TanukiEvadeCST;
		case TanukiTeamEvade:
			return SpellsData.TanukiTeamEvadeCST;
		case healSpell:
			return SpellsData.healSpellCST;
		case teamHeal:
			return SpellsData.teamHealCST;
		default:
			return 0;
		}
	}
	
	/**
	 * A method that take an array of Moves enums and returns them as an array of Strings
	 * @param theMoves accepts an array of enum of type Moves
	 * @return returns an array of Strings of the moves in the array
	 */
	public static String[] getMovesAsArrayOfString(Moves[] theMoves) {
		String[] moveArray = new String[theMoves.length];
		for (byte i = 0; i < moveArray.length;i++) {
			moveArray[i] = ""+theMoves[i];
		}
		return moveArray;
	}
	
	/**
	 * A method that takes an array of Spells enums and returns the values as an array of Strings
	 * @param theSpells accepts an array of enums of type spells
	 * @return returns an array of Strings of the Spells in the spell enum array
	 */
	public static String[] getSpellsAsArrayOfStrings(Spells[] theSpells) {
		String[] moveArray = new String[theSpells.length];
		for (byte i = 0; i < moveArray.length;i++) {
			moveArray[i] = ""+theSpells[i];
		}
		return moveArray;
	}
	
	/**
	 * A static method that returns the cost of a character's spells as a string
	 * @param spells Accepts an array of enums of type Spells but cannot be more than 127 indexes long
	 * @return Returns a string of the cost of the character's spells in Magic Points [MP]
	 */
	public static String getMagicSpellCostAsString(Spells[] spells) {
		String tempString = "Cost: ";
		int j = spells.length;
		for (byte i = 0; i<j;i++) {
			tempString = tempString + (i+1) + ": "+getMagicSpellCost(spells[i])+"MP ";
		}
		return tempString;
	}
	
	/**
	 * A method that returns the magic points gained based on how many friends haven't fainted
	 * @param friendship accepts an array of 4 booleans of which characters haven't fainted.
	 * @return returns a short of the magic points gained based on how many characters haven't fainted.
	 */
	public static short getPowerOfFriendshipMagicPoints(boolean[] friendship) {
		short friendshipMagicPoint = 0;
		for (byte i = 0; i < 4; i++) {
			if(friendship[i])friendshipMagicPoint += 50;
		}
		return friendshipMagicPoint;
	}
	
	/**
	 * A method that returns the health healed by the power of friendship based on how many allies are alive.
	 * It will double for every ally alive.
	 * @param friendship accepts a boolean array of 4 booleans of the characters that haven't fainted
	 * @return returns a short of the health healed from the power of friendship
	 */
	public static short healthHealedByThePowerOfFriendship(boolean[] friendship) {
		short friendshipPower = 1000;
		for (byte i = 0; i < 4; i++) {
			if(friendship[i])friendshipPower *= 2;
		}
		return friendshipPower;
	}
	
	/**
	 * A method that returns the health restored for heal spell for the whole team
	 * @param level accepts an integer of the character's level
	 * @param baseHP accepts an integer of the character's base HP stat
	 * @return returns a short of the health restored
	 */
	public static short getCharacterHealingPowerForHealSpell(int level, int baseHP) {
		short maxHealth = (short) (level * baseHP);
		short healthRestored = (short)(maxHealth*0.3);
		return healthRestored;
	}
	
	/**
	 * A method that returns the Kitsune's targeted enemy and will call itself against if the randomized target is downed
	 * @param allies accepts a boolean array of 4 booleans of the characters still alive
	 * @return returns a byte of the character the Kitsune is targeting 0=ThiccTanuki, 1=RippedDragonborn, 2=Player, 3=MagicalGirl
	 */
	public static byte getKitsuneTarget(boolean[] allies) {
		Random rand = new Random();
		byte target = 0;
		if(allies[0] == true) {
			target = (byte)(rand.nextInt(5));
			if(target<2)target = 0;
			if(target>1)target = advancedTargeting(allies);
		}
		if(!allies[target])target=advancedTargeting(allies);
		return target;
	}
	
	/**
	 * A method that uses targeting that ignores downed players and only targets characters that are alive and will call itself again if the 
	 * targeted character is already down
	 * @param allies accepts an array of 4 booleans of the characters still alive
	 * @return returns a byte of the character being targeted 0=ThiccTanuki, 1=RippedDragonborn, 2=Player, 3=MagicalGirl
	 */
	public static byte advancedTargeting(boolean[] allies) {
		Random rand = new Random();
		byte target = (byte) rand.nextInt(4);
		if(allies[target] == false)advancedTargeting(allies);
		return target;
	}
	
	public static String getHPandLevelAsString(String characterName, int HP,int level) {
		String tempString = characterName+"\nHP: "+HP+" Level: "+level+"\n\n";
		return tempString;
	}

}
