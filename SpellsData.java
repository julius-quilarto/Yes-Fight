/**
 * A class that keeps track of the data of each spell.
 * @Version 1.0
 * @author Alexander
 */

import java.util.Random;
import java.util.Scanner;

public class SpellsData {
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	//a bunch of static short variables that keep track of the spells base power
	static final short noneBP = 0;
	static final short DragonSurgeBP = 115;
	static final short MissingnoBP = 255;
	static final short NaturePowerBP = 135;
	static final short KitsuneFireBP = 75;
	static final short BinaryBeamBP = 128;
	static final short fissureBP = 185;
	static final short tidalWaveBP = 155;
	static final short FairyGlitterBP = 135;
	static final short NEObusterBP = 190;
	static final short tornadoBP = 100;
	static final short hurricaneBP = 155;
	static final short EXbusterBP = 88;
	static final short spellFailedBP = 0;
	static final short LookKawaiiBP = 0;
	
	//a bunch of static byte variables that keep track of the spell's cost
	static final byte noneCST = 0;
	static final byte DragonSurgeCST = 27;
	static final byte MissingnoCST = 127;
	static final byte NaturePowerCST = 50;
	static final byte KitsuneFireCST = 45;
	static final byte BinaryBeamCST = 64;
	static final byte fissureCST = 100;
	static final byte tidalWaveCST = 85;
	static final byte FairyGlitterCST = 55;
	static final byte NEObusterCST = 100;
	static final byte tornadoCST = 40;
	static final byte hurricaneCST = 75;
	static final byte EXbusterCST = 45;
	static final byte spellFailedCST = 0;
	static final byte LookKawaiiCST = 25;
	static final byte teamHealCST = 65;
	
	//a bunch of static Elementals enums for the spells type
	static final Elementals noneTYPE = Elementals.neutral;
	static final Elementals DragonSurgeTYPE = Elementals.dragon;
	static final Elementals MissingnoTYPE = Elementals.tech;
	static final Elementals NaturePowerTYPE = Elementals.nature;
	/**
	 * A method that returns one of the types of KitsuneFire
	 * @return returns an enum of type Elementals of the move's type. It can be either ethereal or fire.
	 */
	public Elementals getKitsuneFireType() {
		int rn = rand.nextInt(2);
		if(rn == 1)return Elementals.fire;
		return Elementals.ethereal;
	}
	static final Elementals KitsuneFireTYPE1 = Elementals.fire;
	static final Elementals KitsuneFireTYPE2 = Elementals.ethereal;
	/**
	 * A method to get the type of damage for BinaryBeam
	 * @return returns an enum of type Elementals and can be either electric or earth
	 */
	public Elementals getBinaryBeamType() {
		int rn = rand.nextInt(2);
		if(rn == 1)return Elementals.electric;
		return Elementals.earth;
	}
	static final Elementals BinaryBeamTYPE1 = Elementals.tech;
	static final Elementals BinaryBeamTYPE2 = Elementals.electric;
	static final Elementals fissureTYPE = Elementals.earth;
	static final Elementals tidalWaveTYPE = Elementals.water;
	static final Elementals FairyGlitterTYPE = Elementals.ethereal;
	static final Elementals NEObusterTYPE = Elementals.tech;
	static final Elementals tornadoTYPE = Elementals.air;
	static final Elementals hurricaneTYPE = Elementals.air;
	static final Elementals[] EXbusterTYPE = new Elementals[] {
		Elementals.fire, Elementals.water, Elementals.ethereal, Elementals.electric,
		Elementals.neutral, Elementals.dragon, Elementals.earth, Elementals.air,
		Elementals.nature, Elementals.tech
	};
	/**
	 * A method to get the type of damage for EX buster
	 * @return returns an enum of type Elementals of the damage type the user selected
	 */
	public Elementals getEXbusterType() {
		println("Select which damage type you want EXbuster to be:");
		String tempString = "";
		for (byte i = 0; i<EXbusterTYPE.length; i++) {
			if (i%4 == 0)tempString = tempString +"\n";
			tempString = tempString + i+ ": "+EXbusterTYPE[i];
		}
		println(tempString);
		byte selector = scan.nextByte();
		if(selector < 0 || selector > 10)selector = 0;
		return EXbusterTYPE[selector];
	}
	/**
	 * A method for the AI to interface with getting the type for EXbuster
	 * @param i accepts a byte from 0 to 10
	 * @return returns an enum of type Elementals of the type for EXbuster
	 */
	public Elementals getEXbusterTypeAI(byte i) {
		if (i>10)i = 10;
		if (i<0)i=0;
		return EXbusterTYPE[i];
	}
	static final Elementals spellFailedTYPE = Elementals.neutral;
	static final Elementals LookKawaiiTYPE = Elementals.neutral;
	
	//a bunch of variable for status moves and their cost
	static final byte TanukiEvadeCST = 20;//this move allows any teammate to dodge an attack
	static final byte TanukiTeamEvadeCST = 35;//this move allows the whole team to dodge an attack
	static final byte healSpellCST = 25;
	
	//a bunch of static strings with move descriptions
	static final String noneDisc = "This spell does nothing.";
	static final String DragonSurgeDisc = "This move surges the enemy with a powerful flow of dragon magic and boosts the user's special.";
	static final String MissingnoDisc = "This spell is a glitch and isn't supposed to exist.";
	static final String NaturePowerDisc = "A Nymph, Fairy, or Magical Girl uses the power of nature to overpower her enemies.";
	static final String KitsuneFireDisc = "A Kitsune uses is ethereal fox fire to burn its enemies with magic and damages with both ethereal and fire damage.";
	static final String BinaryBeamDisc = "The user of this spell turn code into magical energy and attacks its foe with a beam that damages for both tech and electric damage.";
	static final String fissureDisc = "The user uses earth magic to creat a fissure and crushes its enemies in it.";
	static final String titalWaveDisc = "The user summons and massive wave of water and surges the enememy with water";
	static final String FairyGlitterDisc = "The user summons the power of the mysteries of Fae to attack its opponent with ethereal magic.";
	static final String NEObusterDisc = "The user concentrates it's full magical energy into a single cannon blast dealing heavy damage to the entire enemy team.";
	static final String tornadoDisc = "The user summons a tornado that flings its enemies around.";
	static final String hurricaneDisc = "The user summons a powerfule storm that flings enemies around and pelts them with objects.";
	static final String EXbusterDisc = "While the uesr has the EX_Core ability active, the EX buster will allow the user to make their to make their blaster beam any type except dark.";
	
	//a bunch of static strings for non damage dealing spells
	static final String TanukiEvadeDisc = "This move allows any teammate to dodge an attack.";
	static final String TanukiTeamEvadeDisc = "This moves allows the whole team to dodge an attack but uses more than half of the Tanuki's magic.";
	static final String healSpellDisc = "This spell will heal any team mate 30% of their total health.";
	static final String spellFailedDisc = "The spell failed.";//used for when a spell fails, mostly because of a magic meter that is too low
	static final String LookKawaiiDisc = "You look extra Kawaii to melt the hearts of your opponenets and lower their attack power for the whole team.";
	static final String teamHealDisc = "This spell will heal all members of the team 30% of the their full health.";
	
	private void println(String words) {
		System.out.println(words);
	}
}
