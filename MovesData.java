
/**
 * A class meant to hold a bunch of static data types for moves being used.
 * Version 1.1
 */

import java.util.Random;
public class MovesData {
	Random rand = new Random();
	
	//a bunch of satatic varables for move base powers
	static final short placeholderBP = 0;
	static final short voltChargeBP = 55;
	static final short etherealBladeBP = 85;
	static final short missingnoBP = 255;//this move is a glitch and isn't supposed to show
	static final short isDeadBP = 0;//used for when a character is dead or fainted
	/**
	 * A special method for getting the base power of shooting guns as it changes based on character abilit and weapon being held
	 * @a Accepts an enum of the character's ability using the move
	 * @w Accepts an enum of the character's weapon being held
	 * If the weapon being held is not a gun, the base power defaults to 40.
	 */
	public short getBasePowerForShootGun(Abilities a, Weapons w) {
		short basePower = 40;
		double specialCritMultiplier = 1;
		
		//An if statment which allows characters with TanukiSense to get a higher damage output when using guns and higher crit chance
		if(a == Abilities.TanukiSense) {
			int diceRoll = rand.nextInt(5);
			if (diceRoll > 2) {
				specialCritMultiplier = 2;
			}
			if(w == Weapons.colt1911) {
				double BP = 20.55;
				int diceRoll2 = rand.nextInt(10);
				if (diceRoll2 < 5)diceRoll2 = 5;
				basePower = (short)(BP * diceRoll2*specialCritMultiplier);
				return basePower;
			}
			else if (w == Weapons.magnum357revolver) {
				double BP = 53.9;
				int diceRoll2 = rand.nextInt(8);
				if(diceRoll2 < 4)diceRoll2 = 4;
				basePower = (short)(BP * diceRoll2*specialCritMultiplier);
				return basePower;
			}
			else if(w == Weapons.lever_action_rifle_45_70) {
				int BP = 228;
				int diceRoll2 = rand.nextInt(4);
				if(diceRoll2 < 2)diceRoll2 = 2;
				basePower = (short)(BP*diceRoll2*specialCritMultiplier);
				return basePower;
			}
		}
		else if(a != Abilities.TanukiSense) {
			if(w == Weapons.colt1911) {
				double BP = 20.55;
				int diceRoll2 = rand.nextInt(10);
				if (diceRoll2 < 2)diceRoll2 = 2;
				basePower = (short)(BP * diceRoll2);
				return basePower;
			}
			else if (w == Weapons.magnum357revolver) {
				double BP = 53.9;
				int diceRoll2 = rand.nextInt(7);
				if(diceRoll2 < 1)diceRoll2 = 1;
				basePower = (short)(BP * diceRoll2);
				return basePower;
			}
			else if(w == Weapons.lever_action_rifle_45_70) {
				int BP = 228;
				int diceRoll2 = rand.nextInt(3);
				if(diceRoll2 < 1)diceRoll2 = 1;
				basePower = (short)(BP*diceRoll2);
				return basePower;
			}
			else if(w==Weapons.ElvenCrossbow) {
				return 45;
			}
		}
		else {
			return basePower;			
		}
		return basePower;
	}
	static final short tackleBP = 35;
	static final short scratchBP = 15;
	static final short pummelBP = 45;
	/**
	 * A special method that determines the base power of a sword slash based on the character's
	 * ability and weapon equipped.
	 * @param a - Accepts an enum of type Abilities of the character's ability
	 * @param w - Accepts an enum of ype Weapons of the character's equipped weapon
	 */
	public short getSwordSlashBP(Abilities a, Weapons w) {
		short basePower = 55;
		
		//checks for weapons type
		switch (w) {
		case sword:
			basePower = 75;
			break;
		case DeusVultSword:
			basePower = 135;
			break;
		case techSword:
			basePower = 65;
			break;
		case energySword:
			basePower = 85;
			break;
		case chainsaw:
			basePower = 115;
			break;
		case KitsuneKatana:
			basePower = 95;
			break;
		default:
			basePower = 55;
			break;
		}
		
		//checking to see if the ability the character has is DragonCore
		if(a == Abilities.DragonCore)basePower = (short)(1.25 * basePower);
		return basePower;
	}
	static final short dragonFistBP = 85;
	static final short fireFistBP = 75;
	static final short frostBiteBP = 65;
	static final short moonKickBP = 70;
	static final short shadowPunchBP = 65;
	static final short fireBreathBP = 85;
	static final short thunderboltBP = 85;
	static final short shockingFistBP = 76;
	static final short rockThrowBP = 55;
	static final short mudSlideBP = 95;
	static final short windGustBP = 45;
	static final short megabusterBP = 64;
	static final short naturePowerBP = 115;
	static final short dragonBreathBP = 95;
	static final short stingBP = 35;
	static final short biteBP = 45;
	static final short boneClubBP = 50;
	/**
	 * A method that returns the base power of [HyperlinkBlocked]
	 * @return returns a short of the base power of [HyperlinkBlocked]
	 */
	public short getHyperlinkBlockedBP() {
		short bp = (short)(rand.nextInt(128) - 28);
		int diceRoll = rand.nextInt(16);
		String[] responses = new String[] {
				"YOU WANT IT!!! YOU WANT [HyperlinkBlocked]!!",
				"DON'T YOU WANNA BE A          [BIG SHoT]!",
				"BeHOLD THe [power] oF [HyperlinkBlocked]",
				"WILD prizes, HOT singles, [HyperlinkBlocked], you want it? It's yours [you little slime], so long as you have enough [in-game currency]!"
		};
		
		switch (diceRoll) {
		case 0:
			println(responses[0]);
			break;
		case 1:
			println(responses[1]);
			break;
		case 2:
			println(responses[2]);
			break;
		case 3:
			println(responses[3]);
			break;
		default:
			break;
		}
		return bp;
	}
	
	//a list of static variables of a move's type
	static final Elementals placeholderT = Elementals.neutral;
	static final Elementals voltChargeT = Elementals.electric;
	static final Elementals etherealBladeT = Elementals.ethereal;
	static final Elementals missingnoT = Elementals.tech;
	static final Elementals isDeadT = Elementals.neutral;
	static final Elementals shootGunT = Elementals.neutral;
	static final Elementals tackleT = Elementals.neutral;
	static final Elementals scratchT = Elementals.neutral;
	static final Elementals pummelT = Elementals.neutral;
	/**
	 * A special method that changes the type of damage based on the weapon weilded.
	 * @param w - Accepts an enum of type Weapons
	 * @return Returns an enum of type Elementals
	 */
	public Elementals getSwordSlashType(Weapons w) {
		switch(w) {
		case DeusVultSword:
			return Elementals.dragon;
		case techSword:
			return Elementals.tech;
		case energySword:
			return Elementals.electric;
		case chainsaw:
			return Elementals.dark;
		case KitsuneKatana:
			return Elementals.fire;
		default:
			return Elementals.neutral;
		}
	}
	static final Elementals dragonFistT = Elementals.dragon;
	static final Elementals fireFistT = Elementals.fire;
	static final Elementals frostBiteT = Elementals.water;
	static final Elementals moonKickT = Elementals.ethereal;
	static final Elementals shadowPunchT = Elementals.ethereal;
	static final Elementals fireBreathT = Elementals.fire;
	static final Elementals thunderboltT = Elementals.electric;
	static final Elementals shockingFistT = Elementals.electric;
	static final Elementals rockThrowT = Elementals.earth;
	static final Elementals mudSlideT = Elementals.earth;
	static final Elementals windGustT = Elementals.air;
	static final Elementals megabusterT = Elementals.tech;
	static final Elementals naturePowerT = Elementals.nature;
	static final Elementals dragonBreathT = Elementals.dragon;
	static final Elementals stingT = Elementals.nature;
	static final Elementals biteT = Elementals.dark;
	static final Elementals boneClubT = Elementals.earth;
	/**
	 * A method that gets the type for [HyperlinkBlocked] which is random
	 * @return returns an enum of type Elementals of the type of [HyperlinkBlocked]
	 */
	public Elementals getHyperlinkBlockedT() {
		int randomType = rand.nextInt(11);
		switch (randomType) {
		case 0:
			return Elementals.fire;
		case 1:
			return Elementals.water;
		case 2:
			return Elementals.ethereal;
		case 3:
			return Elementals.electric;
		case 4:
			return Elementals.neutral;
		case 5:
			return Elementals.dragon;
		case 6:
			return Elementals.earth;
		case 7:
			return Elementals.air;
		case 8:
			return Elementals.dark;
		case 9:
			return Elementals.nature;
		case 10:
			return Elementals.tech;
		default:
			break;
		}
		return Elementals.neutral;// dummy return statement
	}
	
	//a bunch of static boolean variable determining where a move is special or physical i.e. [Special = true/false]
	static final boolean placeholderSP = false;
	static final boolean voltChargeSP = false;
	static final boolean etherealBladeSP = true;
	static final boolean missingnoSP = false;
	static final boolean isDeadSP = false;
	static final boolean shootGunSP = false;
	static final boolean tackleSP = false;
	static final boolean scratchSP = false;
	static final boolean pummelSP = false;
	static final boolean swordSlashSP = false;
	static final boolean dragonFistSP = false;
	static final boolean fireFistSP = false;
	static final boolean frostBiteSP = true;
	static final boolean moonKickSP = true;
	static final boolean shadowPunchSP = true;
	static final boolean fireBreathSP = true;
	static final boolean thunderboltSP = true;
	static final boolean shockingFistSP = false;
	static final boolean rockThrowSP = false;
	static final boolean mudSlideSP = true;
	static final boolean windGustSP = true;
	static final boolean megabusterSP = false;
	static final boolean naturePowerSP = true;
	static final boolean dragonBreathSP = true;
	static final boolean stingSP = false;
	static final boolean biteSP = false;
	static final boolean boneClubSP = false;
	/**
	 * A method that returns the physical/special status of [HyperlinkBlocked] which can be either physical or special
	 * @return returns a boolean of the physical/special status of [HyperlinkBlocked]
	 */
	public boolean getHyperlinkBlockedSP() {
		int trueFalse = rand.nextInt(2);
		switch (trueFalse) {
		case 0:
			return true;
		default:
			return false;
		}
	}
	
	//a bunch of static string variables for move descriptions
	static final String placeholderDisc = "This move does nothing.";
	static final String voltChargeDisc = "The user powers up their electrical energy and charges the enemy.";
	static final String etherealBladeDisc = "The user summons a blade made of magical energy and slashes at the enemy.";
	static final String missingnoDisc = "This move is a glitch. Make no attempt to use it.";
	static final String isDeadDisc = "This is the default move after a character has fainted or been killed.(Nuzlock feature maybe?)";
	static final String shootGunDisc = "The user uses the gun they are equipped with. Otherwise they just melee the enemy for minimal damage.";
	static final String tackleDisc = "This user tackles the enemy to the ground.";
	static final String scratchDisc = "The user uses their claws or finger nails to scratch at their opponent.";
	static final String pummelDisc = "The user uses their hands and feet to beat up their opponent.";
	static final String swordSlashDisc = "The user uses the sword equipped to slash at their enemy with damge and type varying based on sword equiped.";
	static final String dragonFistDisc = "The user infuses their fists with dragon magic pummels their enemy.";
	static final String fireFistDisc = "The user encases their fists with fire magic and pummels their enemy.";
	static final String frostBiteDics = "The user freezes moisture in the air and damges the enemy with cold ice.";
	static final String moonKickDisc = "As a magical girl, you use the moon's cosmic power and infuse it into your knee-high boots and kick your enemy in the balls.";
	static final String shadowPunchDisc = "You summon hands made from magical energy and pummel your enemy blind.";
	static final String fireBreathDisc = "You build up the fire inside you and breath it out in a torrent of fire accompanied by a mighty roar.";
	static final String thunderboltDisc = "You ionize the air around you and strike the enemy with a powerful bolt entirely made from electric magic.";
	static final String shockingFistDisc = "You electrify your fists and beat the enemy with them.";
	static final String rockThrowDisc = "You pick up a rock and throw it at your enemy for earth damage.";
	static final String mudSlideDisc = "You summon earth magic and burry your enemy in a mudslide.";
	static final String windGustDisc = "You use send out an extremely powerful gust of wind that flings objects at the enemy.";
	static final String megabusterDisc = "You use your arm cannon to fire a beam of energy at your enemy.";
	static final String naturePowerDisc = "You summon the forces of nature and user them against your enemy.";
	static final String dragonBreathDisc = "You charge up a massive amount of dragon magic from within an unleash a torrent of magic with a mighty roar.";
	static final String stingDisc = "The character uses a stinger to sting it's opponent.";
	static final String biteDisc = "The character summons its animalistic instincts and bites its enemy";
	static final String hyperlinkBlockedDisc = "You can find the full description of this move at http://[HyperlinkBlocked].";
	static final String boneClubDisc = "The character beats the enemy with a bone.";
	
	//super move descriptions
	static final String DragonForceDisc = "You use the stored up dragon magic inside yourself to transform yourself into a dragon for 5 turns and quadroupling your stats while in dragon form.";
	static final String TanukiIllusionsDisc = "You use the power of the Tanuki Illusion magic to trick your enemies into attacking eachother for triple the damage they meant to attack you with.";
	
	private void println(String words) {
		System.out.println(words);
	}
}
