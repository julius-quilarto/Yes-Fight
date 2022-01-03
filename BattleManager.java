import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;

import Soundtrack.SoundtrackController;

import java.io.IOException;
import java.util.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A class that manages the battle and the player inputs for battle
 * @version  2.0
 * @author Alexander
 */
public class BattleManager{
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	
	SoundtrackController soundTrack = new SoundtrackController();//for battle music
	SoundtrackController sfx = new SoundtrackController();//for sound effects
	SoundtrackController voices = new SoundtrackController();//for character voices
	
	
	private ThiccTanuki TN;//an uninitialized varaible to hold the ThiccTanuki object
	private Moves TNmovesSelection;//for regular moves
	private Spells TNspellSelection;//for spells
	private short TNmoveBS;//is for both magic and regular move base power
	private Elementals TNmoveType;
	private boolean TNmoveIsSpecial;//for both magic and regular attacks
	private byte TNtarget;
	private boolean TNusingMagic = false;//is set to false by default
	private boolean[] teamMembersUsingTNevade = new boolean[] {false,false,false,false};//is set to false by default
	private boolean TNillusionActive = false;//is set to false by default
	
	private RippedDragonborn DB;
	private Moves DBmoveSelection = Moves.placeholder;
	private Spells DBspellSelection = Spells.spellFailed;
	private short DBmoveBS = 0;
	private Elementals DBmoveType;
	private boolean DBmoveIsSpecial = false;
	private byte DBtarget;
	private boolean DBusingMagic = false;
	private short turnDragonForceUsed;//used so the battle manager knows to stop Dragon Force so the super move doesn't go on forever.
	
	private MagicalGirl MG;
	private Moves MGmoveSelection = Moves.placeholder;
	private Spells MGspellSelection = Spells.spellFailed;
	private short MGmoveBS = 0;
	private Elementals MGmoveType;
	private boolean MGmoveIsSpecial = false;
	private byte MGtarget;
	private boolean MGusingMagic = false;
	
	private Player PL;
	private Moves PLmoveSelection = Moves.placeholder;
	private Spells PLspellSelection = Spells.spellFailed;
	private short PLmoveBS = 0;
	private Elementals PLmoveType;
	private boolean PLmoveIsSpecial = false;
	private byte PLtarget;
	private boolean PLusingMagic = false;
	
	private Tier1AI[] t1ai = new Tier1AI[4];//an array that holds the tier 1 enemy AI objects
	private Tier2AI[] t2ai = new Tier2AI[4];//an array that holds the tier 2 enemy AI objects
	private Boss1 Kitsune;//for the boss ai object. It's slot for ai data is in index 2
	private boolean KitsuneIllusionActive = false;//this is set to false by default
	private boolean[] aiAlive = new boolean[] {true,true,true,true};
	private byte enemyTier;
	private byte[] aiTarget = new byte[4];//says which character the ai will target
	private Moves[] aiMoveChoice = new Moves[4];//holds which moves the ai chose
	private short[] aiMoveBP = new short[4];//holds the base power of the enemies' move
	private Elementals[] aiMoveType = new Elementals[4];//holds an array of the enemy moves' type
	private boolean[] aiMoveIsSpecial = new boolean[4];//holds an array of the enemy moves' physical/special status
	private boolean[] aiUsingMagic = new boolean[] {false,false,false,false};
	private Spells[] aiSpellChoice = new Spells[] {Spells.none,Spells.none,Spells.none,Spells.none};
	
	private short turnCounter = 0;//a variable that counts which turn it is and will default to zero when the battle starts
	private boolean battleOver = false;//defaults to false
	private boolean canRun = true;//defaults to true except for important battles
	private Inventory inventory;
	private boolean tauntActive = false;//defaults to false and will make enemies target the character last taunted
	private byte characterLastTaunted;//doesn't have an initual value but [0=Thicc Tanuki; 1=Ripped Dragonborn; 2=Player Character; 3 = Magical girl]
	private double KawaiiDamageMultiplier = 1.0;//this value is initially set to one so that the enemy damage isn't reduced by Kawaii cuteness by default
	private boolean[] alliesAlive = new boolean[] {true,true,true,true};
	private boolean[] characterHasMadeMove = new boolean[] {false,false,false,false};
	
	/**
	 * A constructor that says how to create a battle scene and is treated like a Main() for the current battle
	 * @param tn1 Accepts a ThiccTanuki object loaded from a save file
	 * @param numberOfEnemies accepts a byte of the number of enemies in the battle
	 * @param enemyTiers accepts a byte of the tier of the enemies
	 * @param enemyLevels accepts an array of 4 bytes of the enemy levels
	 * @param enemyID accepts an array of 4 short's of the enemy(s)'s ID number which can be found in the enemy list classes
	 */
	public BattleManager(ThiccTanuki tn1, RippedDragonborn db,MagicalGirl mg,Player pl,byte numberOfEnemies, byte enemyTiers, byte[] enemyLevels, short[] enemyID,Inventory inv) {
		TN = tn1;//sets up the ThiccTanuki object in teh fields
		DB = db;//sets up the Ripped Dragonborn object
		MG = mg;//sets up the magical girl object
		PL = pl;
		enemyTier = enemyTiers;//sets the enemy tier
		inventory = inv;//a method that initializes your inventory for this battle
		
		
		
		if (enemyTier == 1 || enemyTier == 2) {
			try {
				soundTrack.playSoundtrack(1);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(enemyTier == 4) {
			try {
				soundTrack.playSoundtrack(2);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//a block of code that says what enemies or boss to spawn
		switch (enemyTier) {
		case 1:
			constructTier1Enemies(numberOfEnemies,enemyID,enemyLevels);
			break;
		case 2:
			constructTier2Enemies(numberOfEnemies, enemyID,enemyLevels);
		break;
		case 4:
			contructKitsuneFight();
			break;
		default:
			constructTier1Enemies(numberOfEnemies,enemyID,enemyLevels);
		}
		
		
		//runBattle();
		//println("Battle has ended.");
		
	}
	
	/**
	 * A method that return your inventory as an object
	 * @return returns an Inventory object of the class
	 */
	public Inventory getInventory() {
		return inventory;
	}
	/**
	 * A method so other classes can get the ThiccTanuki object
	 * @return returns a ThiccTanuki object of the BattleManager class
	 */
	public ThiccTanuki getThiccTanuki() {
		return TN;
	}
	/**
	 * A method that returns the RippedDragonborn object from teh BattleManager
	 * @return returns a RippedDragonborn object
	 */
	public RippedDragonborn getAnthroDragon() {
		return DB;
	}
	/**
	 * A method that returns the Player object of the BattleManager class
	 * @return returns a Player object
	 */
	public Player getPlayer() {
		return PL;
	}
	/**
	 * A method that returns the MagicalGirl object of the BattleManager class
	 * @return returns a MagicalGirl object
	 */
	public MagicalGirl getMagicalGirl() {
		return MG;
	}
	
	/**
	 * A private helper method that constructs tier 1 enemies for the tel1 field
	 * @param enemyNum accepts a byte of the number of enemies in the battle
	 * @param enemyID accepts a short[] array of 4 short's of enemy ID's
	 * @param enemyLevels accepts a byte[] array of 4 bytes of the enemy levels
	 */
	private void constructTier1Enemies(byte enemyNum, short[] enemyID, byte[] enemyLevels) {
		//for constructing the default enemies
		for(byte i = 0; i<4;i++) {
			t1ai[i] = new Tier1AI(Tier1EnemyList.placeholderType,Tier1EnemyList.placeholderBS,1,Tier1EnemyList.placeholderMS);
		}
		//for setting the actual enemies
		for(byte i = 0; i<enemyNum;i++) {
			Elementals tempType;
			int[] tempBS;
			byte tempLevel = enemyLevels[i];
			Moves[] tempMoves;
			switch (enemyID[i]) {
			case 0:
				tempType = Tier1EnemyList.testType;
				tempBS = Tier1EnemyList.testBS;
				tempMoves = Tier1EnemyList.testMS;
				break;
			case 1:
				tempType = Tier1EnemyList.placeholderType;
				tempBS = Tier1EnemyList.placeholderBS;
				tempMoves = Tier1EnemyList.placeholderMS;
				break;
			case 2:
				tempType = Tier1EnemyList.MissingnoType;
				tempBS = Tier1EnemyList.MissingnoBS;
				tempMoves = Tier1EnemyList.MissingnoMS;
				break;
			case 3:
				tempType = Tier1EnemyList.turretType;
				tempBS = Tier1EnemyList.turretBS;
				tempMoves = Tier1EnemyList.turretMS;
				break;
			case 4:
				tempType = Tier1EnemyList.purpleHornetType;
				tempBS = Tier1EnemyList.purpleHornetBS;
				tempMoves = Tier1EnemyList.purpleHornetMS;
				break;
			case 5:
				tempType = Tier1EnemyList.dragonLizardType;
				tempBS = Tier1EnemyList.dragonLizardBS;
				tempMoves = Tier1EnemyList.dragonLizardMS;
				break;
			case 6:
				tempType = Tier1EnemyList.hellHoundType;
				tempBS = Tier1EnemyList.hellHoundBS;
				tempMoves = Tier1EnemyList.hellHoundMS;
				break;
			case 7:
				tempType = Tier1EnemyList.killerBirdType;
				tempBS = Tier1EnemyList.killerBirdBP;
				tempMoves = Tier1EnemyList.killerBirdMS;
				break;
			case 8:
				tempType = Tier1EnemyList.smallOrcType;
				tempBS = Tier1EnemyList.smallOrcBS;
				tempMoves = Tier1EnemyList.smallOrcMS;
				break;
			default:
				tempType = Tier1EnemyList.MissingnoType;
				tempBS = Tier1EnemyList.MissingnoBS;
				tempMoves = Tier1EnemyList.MissingnoMS;
			}
			t1ai[i] = new Tier1AI(tempType,tempBS,tempLevel,tempMoves);
			
			
			println("ContructTier1Enemies has been executed.");
		}
		
		//a for loop that instakills any placeholder enemies
		for (byte i = 0; i<4; i++) {
			int tempNum = t1ai[i].getID();
			if(tempNum == 1) {
				t1ai[i].instaKill();
				aiAlive[i] = false;
				println("Placeholder enemy in slot "+ i +" has been instakilled.");
			}
		}
		
	}
	
	/**
	 * A helper method that constructs tier 2 enemies
	 * @param enemyNum - accepts a byte of the number of enemies from 1 to 4
	 * @param enemyID - accepts an array of 4 bytes of the enemy's ID number 21 to 31
	 * @param enemyLevels - accepts an array of 4 bytes of enemy levels
	 */
	private void constructTier2Enemies(byte enemyNum, short[] enemyID,byte[] enemyLevels) {
		//checks to make sure that are not more than 4 enemies
		if(enemyNum > 4 || enemyNum < 1) {
			println("There are too many enemies. Enemies must not exceed 4 enemies or be less than one.");
			println("Failed to spawn enemies.");
			battleOver =true;
			return;
		}
		//for constructing the defualt enemies
		for (byte i = 0; i < 4; i++) {
			t2ai[i] = new Tier2AI(Tier2EnemyData.placeholderT, Tier2EnemyData.placeholderBS, Tier2EnemyData.placeholderID, i, Tier2EnemyData.placeholderM, Weapons.none, Abilities.noAbility, Tier2EnemyData.placeholderTEXT, "", Tier2EnemyData.placeholderS);
		}
		
		//for actually construction real tier two enemies
		for (byte i = 0; i < enemyNum; i++) {
			//defaults to the glitched Missingno NEO
			Elementals[] tempType = Tier2EnemyData.glitchedManNeoT;
			short[] tempBS = Tier2EnemyData.glitchedManNeoBS;
			short tempID = enemyID[i];
			byte tempLevel = enemyLevels[i];
			Moves[] tempMoves = Tier2EnemyData.glitchedManNeoM;
			Weapons tempW = Tier2EnemyData.glitchedManNeoW;
			Abilities tempAbility = Tier2EnemyData.glitchedManNeoA;
			String[] tempFT = Tier2EnemyData.glitchedManNeoTEXT;
			String tempDiscription = Tier2EnemyData.glitchedManNeoDESC;
			Spells tempSpell = Tier2EnemyData.glitchedManNeoS;
			
			//switchboard for enemies stats
			switch (enemyID[i]) {
			case 21:
				tempType = Tier2EnemyData.elfT;
				tempBS = Tier2EnemyData.elfBS;
				tempMoves =Tier2EnemyData.elfM;
				tempW =Tier2EnemyData.elfW;
				tempAbility =Tier2EnemyData.elfA;
				tempFT =Tier2EnemyData.elfTEXT;
				tempDiscription =Tier2EnemyData.elfDESC;
				tempSpell = Tier2EnemyData.elfS;
				break;
			case 22:
				tempType = Tier2EnemyData.skeletonT;
				tempBS = Tier2EnemyData.skeletonBS;
				tempMoves =Tier2EnemyData.skeletonM;
				tempW =Tier2EnemyData.skeletonW;
				tempAbility =Tier2EnemyData.skeletonA;
				tempFT =Tier2EnemyData.skeletonTEXT;
				tempDiscription =Tier2EnemyData.skeletonDESC;
				tempSpell = Tier2EnemyData.skeletonS;
				break;
			case 23:
				tempType = Tier2EnemyData.graphicsManExT;
				tempBS = Tier2EnemyData.graphicsManExBS;
				tempMoves =Tier2EnemyData.graphicsManExM;
				tempW =Tier2EnemyData.graphicsManExW;
				tempAbility =Tier2EnemyData.graphicsManExA;
				tempFT =Tier2EnemyData.graphicsManExTEXT;
				tempDiscription =Tier2EnemyData.graphicsManExDESC;
				tempSpell = Tier2EnemyData.graphicsManExS;
				break;
			case 25:
				tempType = Tier2EnemyData.dobermanFurryT;
				tempBS = Tier2EnemyData.dobermanFurryBS;
				tempMoves =Tier2EnemyData.dobermanFurryM;
				tempW =Tier2EnemyData.dobermanFurryW;
				tempAbility =Tier2EnemyData.dobermanFurryA;
				tempFT =Tier2EnemyData.dobermanFurryTEXT;
				tempDiscription =Tier2EnemyData.dobermanFurryDESC;
				tempSpell = Tier2EnemyData.dobermanFurryS;
				break;
			case 26:
				tempType = Tier2EnemyData.grifonT;
				tempBS = Tier2EnemyData.grifonBS;
				tempMoves =Tier2EnemyData.grifonM;
				tempW =Tier2EnemyData.grifonW;
				tempAbility =Tier2EnemyData.grifonA;
				tempFT =Tier2EnemyData.grifonTEXT;
				tempDiscription =Tier2EnemyData.grifonDESC;
				tempSpell = Tier2EnemyData.grifonS;
				break;
			case 27:
				tempType = Tier2EnemyData.naiadT;
				tempBS = Tier2EnemyData.naiadBS;
				tempMoves =Tier2EnemyData.naiadM;
				tempW =Tier2EnemyData.naiadW;
				tempAbility =Tier2EnemyData.naiadA;
				tempFT =Tier2EnemyData.naiadTEXT;
				tempDiscription =Tier2EnemyData.naiadDESC;
				tempSpell = Tier2EnemyData.naiadS;
				break;
			case 28:
				tempType = Tier2EnemyData.harpyT;
				tempBS = Tier2EnemyData.harpyBS;
				tempMoves =Tier2EnemyData.harpyM;
				tempW =Tier2EnemyData.harpyW;
				tempAbility =Tier2EnemyData.harpyA;
				tempFT =Tier2EnemyData.harpyTEXT;
				tempDiscription =Tier2EnemyData.harpyDESC;
				tempSpell = Tier2EnemyData.harpyS;
				break;
			case 29:
				tempType = Tier2EnemyData.thunderDogT;
				tempBS = Tier2EnemyData.thunderDogBS;
				tempMoves =Tier2EnemyData.thunderDogM;
				tempW =Tier2EnemyData.thunderDogW;
				tempAbility =Tier2EnemyData.thunderDogA;
				tempFT =Tier2EnemyData.thunderDogTEXT;
				tempDiscription =Tier2EnemyData.thunderDogDESC;
				tempSpell = Tier2EnemyData.thunderDogS;
				break;
			case 30:
				tempType = Tier2EnemyData.dryadT;
				tempBS = Tier2EnemyData.dryadBS;
				tempMoves =Tier2EnemyData.dryadM;
				tempW =Tier2EnemyData.dryadW;
				tempAbility =Tier2EnemyData.dryadA;
				tempFT =Tier2EnemyData.dryadTEXT;
				tempDiscription =Tier2EnemyData.dryadDESC;
				tempSpell = Tier2EnemyData.dryadS;
				break;
			default:
				break;
			}
			
			t2ai[i] = new Tier2AI(tempType,tempBS,(byte)tempID,tempLevel,tempMoves,tempW,tempAbility,tempFT,tempDiscription,tempSpell);
			
		}
		
		//makes sure to removes all the placeholder enemies
		for (byte i = 0; i<4;i++) {
			if (t2ai[i].getID()==31)t2ai[i].instaKill();
		}
	}
	
	
	/**
	 * A private method so that the Kitsune boss fight is contstructed
	 */
	private void contructKitsuneFight() {
		canRun = false;
		for (byte i = 0;i < 4; i++) {
			if(i==2)continue;
			aiAlive[i] = false;
		}
		Kitsune = new Boss1();
		println(Boss1.KitsuneDiscription);
	}
	
	/**
	 * A private helper method that runs the battle keeps track of the turns and ends
	 * when the boolean battleOver == TRUE.
	 */
	private void runBattle() {
		turnCounter = 1;
		while (battleOver == false) {
			
			//a sub-method that checks to see if all the enemies are dead and ends the battle if true
			boolean allEnemiesDead = true;
			for(byte i =0; i<4; i++) {
				if(aiAlive[i] == true) {
					allEnemiesDead = false;
					break;
				}
			}
			if (allEnemiesDead == true) {
				println("All enemies have been defeated.");
				break;
			}
			
			//changes turns between player and enemy
			if(turnCounter % 2 == 0 && battleOver == false) {
				switch (enemyTier) {
				case 1:
					makeT1EnemiesMoves();
					break;
				case 2:
					makeT2EnemiesMoves();
					break;
				case 4:
					makeKitsuneMoves();
					break;
				default:
					makeT1EnemiesMoves();
					break;
				}
			}
			
		}
	}
	
	/**
	 * A public method so other classes can make moves and control the characters.
	 * All arrays must be 3 integer indexes long.
	 * @param taniaDecision accepts an array of integers of the Tania's move choices
	 * @param dargoniDecision accepts an array of integers of Dargoni's move choices
	 * @param selenaDecision accepts an array of integers of Selena's move choices
	 * @param yourNameDecision accepts an array of integers of the player's move choices
	 */
	public void makePlayerMoves(int[] taniaDecision, int[] dargoniDecision, int[] selenaDecision, int[] yourNameDecision) {
		//boolean[] characterHasMadeMove = new boolean[] {false,false,false,false};//holds which characters have made moves
		boolean[] charsAlive = new boolean[] {TN.getIsAlive(),DB.getIsAlive(),PL.getIsAlive(),MG.getIsAlive()};//a boolean array that holds if all the characters are alive
		alliesAlive = charsAlive;
		TNillusionActive = false;//makes sure to set this value to false so the supermove doesn't exist perpetually
		if (turnCounter - turnDragonForceUsed > 7) {//makes it so that Dragon Force does't last more than 3 moves.
			DB.disableDragonForce();
		}
		tauntActive = false;// disables taunting at the beginning of each turn to prevent enemy targeting glitches
		KawaiiDamageMultiplier = 1;//resets the KawaiiDamageMultiplier
		
		//an if statement that checks to see if all characters are alive before being able to make a move
		if(charsAlive[0] == false && charsAlive[1] == false && charsAlive[2] == false && charsAlive[3] == false) {
			battleOver = true;
			println("You have been defeated in battle.");
			return;
		}
		//stops defending so there isn't a permanent defense boost
		TN.stopDefending();
		DB.stopDefending();
		MG.stopDefending();
		PL.stopDefending();
	
			
			//makes sure character's can't use moves when the character is down
			if(charsAlive[0] == false) {
				println("Downed characters can't use moves.");
				TNmovesSelection = Moves.placeholder;
				TNmoveBS = 0;
				TNmoveIsSpecial = false;
				TNusingMagic = false;
				//break;
			}
			else {
				int selection = taniaDecision[0];
				//a big switchboard for the different selections
				switch (selection) {
				case 1:
					int tempSelect = taniaDecision[1];
					if (tempSelect == 5 && TN.getSuperMeter() < 100) {
						println("You don't have enough super energy to use your super move.");
						break;
					}
					//enables Thicc Tanuki's supermove
					else if(tempSelect == 5 && TN.getSuperMeter() >= 100) {
						println("You used the power of the Tanuki Illusion magic to trick your enemies into attacking eachother.");
						TNillusionActive = true;
						TNmovesSelection = TN.chooseMove(5);
						break;
					}
					
					//sets up the Thicc Tanuki's move choice stats
					TNmovesSelection = TN.chooseMove(tempSelect);
					TNmoveBS = getMoveBasePower(TNmovesSelection,TN.getWeapon(),TN.getAbility());
					TNmoveIsSpecial = checkIfMoveIsSpecial(TNmovesSelection);
					TNmoveType = getMoveType(TNmovesSelection,TN.getWeapon(),TN.getAbility());
					TNusingMagic = false;
					byte targetedEnemy = (byte)(taniaDecision[2] - 1);
						TNtarget = targetedEnemy;//targets which enemy Thicc Tanuki will attack
					break;
					
				//allows players to use magic spells	
				case 2:
					int choice = taniaDecision[1];
					if(choice == 0)break;//allows the player to cancel a magic spell choice
					
					
					//get's the spells data
					TNspellSelection = TN.chooseSpell((byte)choice);
					TNmoveBS = getMoveBasePowerM(TNspellSelection,TN.getWeapon(),TN.getAbility());
					TNmoveIsSpecial = checkIfMoveIsSpeicalM(TNspellSelection);
					TNmoveType = getMoveTypeM(TNspellSelection,TN.getWeapon(),TN.getAbility());
					TNusingMagic = false;
					if(TNspellSelection == Spells.LookKawaii) KawaiiDamageMultiplier = 0.5;
					//allows the player to select a target
					byte target = (byte)(taniaDecision[2]-1);//initialized it to 8 as a dummy variable in case the user inputs an invalid number
						TNtarget = target;//targets which enemy Thicc Tanuki will attack
					
					//sets the damage and type
					
					TNusingMagic = true;
					break;
					
				//allows the player to use items during battle
				case 3:
					
					//allows the player to access the inventory
					useInventory();
					
					//makes sure the player can't attack while using items
					TNusingMagic = false;
					TNmovesSelection = Moves.placeholder;
					TNmoveBS = 0;
					TNmoveIsSpecial = false;
					break;
					
				//allows the player to defend against physical attacks	
				case 4:
					
					//makes sure the player can defend
					TN.defend();
					
					//makes sure the player can't attack while using items
					TNmovesSelection = Moves.placeholder;
					TNmoveBS = 0;
					TNmoveIsSpecial = false;
					TNusingMagic = false;
					//makes sure a move is counted when the player makes a choice
					break;
					
				//taunts aren't really used for anything except skipping moves without wasting any items and listening
				//to some secret chraracter dialogue
				case 5:
					
					//calls a random taunt from the Thicc Tanuki Class
					println(TN.getTaunt());
					
					//makes sure the player can't attack while using items
					TNmovesSelection = Moves.placeholder;
					TNmoveBS = 0;
					TNmoveIsSpecial = false;
					TNusingMagic = false;
					
					//set up taunt target
					tauntActive = true;
					characterLastTaunted = 0;
					break;
				//allows the player to run from battles although this will fail for important battles or against strong opponents
				case 6:
					//makes it so you can't attack if you have a failed attempt at running from battle
					TNmovesSelection = Moves.placeholder;
					TNmoveBS = 0;
					TNmoveIsSpecial = false;
					TNusingMagic = false;
					//checks to make sure you can actually run from the current battle
					if (canRun == false) {
						println("You can't run from your current battles.");
						break;
					}
					
					println("Thicc Tanuki ran from battle.");
					battleOver = true;
					characterHasMadeMove[0] = true;
					break;
				default:
					break;
				}
			}
			
			//makes sure character's can't use moves when the character is down
			if(charsAlive[1] == false) {
				println("Downed characters can't use moves.");
				DBmoveSelection = Moves.placeholder;
				DBmoveBS = 0;
				DBmoveIsSpecial = false;
				DBusingMagic = false;
			}
			
			else {
				int selection = dargoniDecision[0];
				switch (selection) {
				case 1:
					int tempSelect = dargoniDecision[1];
					if (tempSelect == 5 && DB.getSuperMeter() < 100) {
						println("You don't have enough super enery to use your super move.");
						break;
					}
					//enables Ripped Dragonborn's supermove
					else if(tempSelect == 5 && DB.getSuperMeter() >= 100) {
						turnDragonForceUsed = turnCounter;
						DBmoveSelection = DB.chooseMove(5);
						DBmoveBS = 77;
						DBmoveIsSpecial = false;
						DBmoveType = Elementals.dragon;
						DBusingMagic = false;
						println("You used the magic of the dragon inside you to become one.");
						break;
					}
					
					//sets up the Ripped Dragonborn's move choice stats
					DBmoveSelection = DB.chooseMove(tempSelect);
					DBmoveBS = getMoveBasePower(DBmoveSelection,DB.getWeapon(),DB.getAbility());
					DBmoveIsSpecial = checkIfMoveIsSpecial(DBmoveSelection);
					DBmoveType = getMoveType(DBmoveSelection,DB.getWeapon(),DB.getAbility());
					DBusingMagic = false;
					
					byte targetedEnemy = (byte)(dargoniDecision[2] - 1);//initialized it to 8 as a dummy variable in case the user inputs an invalid number
						DBtarget = targetedEnemy;//targets which enemy Ripped Dragonborn will attack
					break;
					
				//allows players to use magic spells	
				case 2:
					println(DB.getSpells());
					println(UsefulMethods.getMagicSpellCostAsString(DB.getSpellsByID()));
					println("Press '0' to cancel.");
					int choice = dargoniDecision[1];
					DBspellSelection = DB.chooseSpell((byte)choice);
					DBmoveBS = getMoveBasePowerM(DBspellSelection,DB.getWeapon(),DB.getAbility());
					DBmoveIsSpecial = checkIfMoveIsSpeicalM(DBspellSelection);
					DBmoveType = getMoveTypeM(DBspellSelection,DB.getWeapon(),DB.getAbility());
					DBusingMagic = false;
					//allows the player to select a target
					byte target = (byte)(dargoniDecision[2] - 1);//initialized it to 8 as a dummy variable in case the user inputs an invalid number
						DBtarget = target;//targets which enemy Ripped Dragonborn will attack
					DBusingMagic = true;
					break;
					
				//allows the player to use items during battle
				case 3:
					
					//allows the player to access the inventory
					useInventory();
					
					//makes sure the player can't attack while using items
					DBusingMagic = false;
					DBmoveSelection = Moves.placeholder;
					DBmoveBS = 0;
					DBmoveIsSpecial = false;
					break;
					
				//allows the player to defend against physical attacks	
				case 4:
					
					//makes sure the player can defend
					println("Dargoni raises his defense with dragon magic.");
					DB.defend();
					
					//makes sure the player can't attack while using items
					DBmoveSelection = Moves.placeholder;
					DBmoveBS = 0;
					DBmoveIsSpecial = false;
					DBusingMagic = false;
					break;
					
				//taunts aren't really used for anything except skipping moves without wasting any items and listening
				//to some secret chraracter dialogue
				case 5:
					
					//calls a random taunt from the Thicc Tanuki Class
					println(DB.getTaunt());
					
					//makes sure the player can't attack while using items
					DBmoveSelection = Moves.placeholder;
					DBmoveBS = 0;
					DBmoveIsSpecial = false;
					DBusingMagic = false;
					
					//set up taunt target
					tauntActive = true;
					characterLastTaunted = 1;
					break;
				//allows the player to run from battles although this will fail for important battles or against strong opponents
				case 6:
					//makes it so you can't attack if you have a failed attempt at running from battle
					DBmoveSelection = Moves.placeholder;
					DBmoveBS = 0;
					DBmoveIsSpecial = false;
					DBusingMagic = false;
					//checks to make sure you can actually run from the current battle
					if (canRun == false) {
						println("You can't run from your current battles.");
						break;
					}
					
					println("Dargoni ran from battle.");
					battleOver = true;
					characterHasMadeMove[1] = true;
					break;
				default:
					break;
				}
			}
			//makes sure character's can't use moves when the character is down
			if(charsAlive[3] == false) {
				println("Downed characters can't use moves.");
				MGmoveSelection = Moves.placeholder;
				MGmoveBS = 0;
				MGmoveIsSpecial = false;
				MGusingMagic = false;
			}
			
			else {
				int selection = selenaDecision[0];
				switch (selection) {
				case 1:
					int tempSelect = selenaDecision[1];
					if (tempSelect == 5 && MG.getSuperMeter() < 100) {
						println("You don't have enough super enery to use your super move.");
						break;
					}
					//enables Ripped Dragonborn's supermove
					else if(tempSelect == 5 && MG.getSuperMeter() >= 100) {
						MGmoveSelection = MG.chooseMove(5);
						MGmoveBS = 50;
						MGmoveIsSpecial = true;
						MGmoveType = Elementals.ethereal;
						MGusingMagic = false;
						short friendshipPower = UsefulMethods.getPowerOfFriendshipMagicPoints(charsAlive);
						short friendshipHealth = UsefulMethods.healthHealedByThePowerOfFriendship(charsAlive);
						TN.revive();
						DB.revive();
						PL.revive();
						TN.restoreMagic(friendshipPower);
						DB.restoreMagic(friendshipPower);
						PL.restoreMagic(friendshipPower);
						TN.restoreHP(friendshipHealth);
						DB.restoreHP(friendshipHealth);
						PL.restoreHP(friendshipHealth);
						println("You used your magical girl powers to support your friends and realized the true magic was friendship all along.");
						break;
					}
					
					//sets up the Ripped Dragonborn's move choice stats
					MGmoveSelection = MG.chooseMove(tempSelect);
					MGmoveBS = getMoveBasePower(MGmoveSelection,MG.getWeapon(),MG.getAbility());
					MGmoveIsSpecial = checkIfMoveIsSpecial(MGmoveSelection);
					MGmoveType = getMoveType(MGmoveSelection,MG.getWeapon(),MG.getAbility());
					MGusingMagic = false;
					byte targetedEnemy = (byte)(selenaDecision[2] - 1);//initialized it to 8 as a dummy variable in case the user inputs an invalid number
					MGtarget = targetedEnemy;//targets which enemy Ripped Dragonborn will attack
					
					break;
					
				//allows players to use magic spells	
				case 2:
					int choice = selenaDecision[1];
					if(choice == 0)break;//allows the player to cancel a magic spell choice
					
					//get's the spells data
					MGspellSelection = MG.chooseSpell((byte)choice);
					MGmoveBS = getMoveBasePowerM(MGspellSelection,MG.getWeapon(),MG.getAbility());
					MGmoveIsSpecial = checkIfMoveIsSpeicalM(MGspellSelection);
					MGmoveType = getMoveTypeM(MGspellSelection,MG.getWeapon(),MG.getAbility());
					MGusingMagic = false;
					
					//for if you're using healing spells with magical girl
					if(MGspellSelection == Spells.healSpell) {
						println("Select a character to heal:\n1: Thicc Tanuki\n2: Anthro Dragon\n3: Player\n4: MagicalGirl\n");
						byte choice2 = (byte)selenaDecision[2];
						short healingPower;
						switch(choice2) {
						case 1:
							healingPower = UsefulMethods.getCharacterHealingPowerForHealSpell(TN.getStats()[0], TN.getStats()[1]/TN.getStats()[0]);
							TN.restoreHP(healingPower);
							break;
						case 2:
							healingPower = UsefulMethods.getCharacterHealingPowerForHealSpell(DB.getStats()[0], DB.getStats()[1]/DB.getStats()[0]);
							DB.restoreHP(healingPower);
							break;
						default:
							healingPower = UsefulMethods.getCharacterHealingPowerForHealSpell(MG.getStats()[0], MG.getStats()[1]/MG.getStats()[0]);
							MG.restoreHP(healingPower);
							break;
						}
					}
					else if(MGspellSelection == Spells.teamHeal) {
						short healingPower = UsefulMethods.getCharacterHealingPowerForHealSpell(TN.getStats()[0], TN.getStats()[1]/TN.getStats()[0]);
						TN.restoreHP(healingPower);
						healingPower = UsefulMethods.getCharacterHealingPowerForHealSpell(DB.getStats()[0], DB.getStats()[1]/DB.getStats()[0]);
						DB.restoreHP(healingPower);
						healingPower = UsefulMethods.getCharacterHealingPowerForHealSpell(MG.getStats()[0], MG.getStats()[1]/MG.getStats()[0]);
						MG.restoreHP(healingPower);
						break;
					}
					//allows the player to select a target
					byte target = (byte)selenaDecision[2];//initialized it to 8 as a dummy variable in case the user inputs an invalid number
						MGtarget = target;//targets which enemy Ripped Dragonborn will attack
					
					//sets the damage and type
					
					MGusingMagic = true;
					break;
					
				//allows the player to use items during battle
				case 3:
					
					//allows the player to access the inventory
					useInventory();
					
					//makes sure the player can't attack while using items
					MGusingMagic = false;
					MGmoveSelection = Moves.placeholder;
					MGmoveBS = 0;
					MGmoveIsSpecial = false;
					break;
					
				//allows the player to defend against physical attacks	
				case 4:
					
					//makes sure the player can defend
					println("Magical Girl makes a pathetic attemp to defend herself from non-magic-based attacks.");
					MG.defend();
					
					//makes sure the player can't attack while using items
					MGmoveSelection = Moves.placeholder;
					MGmoveBS = 0;
					MGmoveIsSpecial = false;
					MGusingMagic = false;
					break;
					
				//taunts aren't really used for anything except skipping moves without wasting any items and listening
				//to some secret chraracter dialogue
				case 5:
					
					//calls a random taunt from the Thicc Tanuki Class
					println(MG.getTaunt());
					
					//makes sure the player can't attack while using items
					MGmoveSelection = Moves.placeholder;
					MGmoveBS = 0;
					MGmoveIsSpecial = false;
					MGusingMagic = false;
					
					//set up taunt target
					tauntActive = true;
					characterLastTaunted = 3;
					
					//makes sure the move is counted when teh player makes a choice
					characterHasMadeMove[3] = true;
					break;
				//allows the player to run from battles although this will fail for important battles or against strong opponents
				case 6:
					//makes it so you can't attack if you have a failed attempt at running from battle
					MGmoveSelection = Moves.placeholder;
					MGmoveBS = 0;
					MGmoveIsSpecial = false;
					MGusingMagic = false;
					//checks to make sure you can actually run from the current battle
					if (canRun == false) {
						println("You can't run from your current battles.");
						break;
					}
					
					println("Selena ran from battle.");
					battleOver = true;
					characterHasMadeMove[3] = true;
					break;
				default:
					break;
				}
			}
			
			//makes sure character's can't use moves when the character is down
			if(charsAlive[2] == false) {
				println("Downed characters can't use moves.");
				PLmoveSelection = Moves.placeholder;
				PLmoveBS = 0;
				PLmoveIsSpecial = false;
				PLusingMagic = false;
				characterHasMadeMove[3] = true;
			}
			else {
				int selection = yourNameDecision[0];
				switch (selection) {
				case 1:
					int tempSelect = yourNameDecision[1];
					if (tempSelect == 5 && PL.getSuperMeter() < 100) {
						println("You don't have enough super enery to use your super move.");
						break;
					}
					//enables Ripped Dragonborn's supermove
					else if(tempSelect == 5 && PL.getSuperMeter() >= 100) {
						PLmoveSelection = PL.chooseMove(5);
						PLmoveBS = 100;
						PLmoveIsSpecial = false;
						PLmoveType = Elementals.neutral;
						PLusingMagic = false;
						if(enemyTier == 1) {
							t1ai[0].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
							t1ai[1].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
							t1ai[2].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
							t1ai[3].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
						}
						else if(enemyTier ==2 ) {
							t2ai[0].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
							t2ai[1].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
							t2ai[2].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
							t2ai[3].damageCalculator(100, Elementals.neutral, false, PL.getStats()[2], 0, PL.getStats()[0]);
						}
						else if(enemyTier == 4)Kitsune.takeDamage((short) 100, Elementals.fire, false, PL.getStats()[2], 0, PL.getStats()[0]);
						break;
					}
					
					//sets up the Ripped Dragonborn's move choice stats
					PLmoveSelection = PL.chooseMove(tempSelect);
					PLmoveBS = getMoveBasePower(PLmoveSelection,PL.getWeapon(),PL.getAbility());
					PLmoveIsSpecial = checkIfMoveIsSpecial(PLmoveSelection);
					PLmoveType = getMoveType(PLmoveSelection,PL.getWeapon(),PL.getAbility());
					PLusingMagic = false;
					
					showEnemyTargets();
					byte targetedEnemy = (byte)yourNameDecision[2];//initialized it to 8 as a dummy variable in case the user inputs an invalid number
						PLtarget = targetedEnemy;//targets which enemy Ripped Dragonborn will attack
					break;
					
				//allows players to use magic spells	
				case 2:
					int choice = yourNameDecision[1];
					PLspellSelection = PL.chooseSpell((byte)choice);
					PLmoveBS = getMoveBasePowerM(PLspellSelection,PL.getWeapon(),PL.getAbility());
					PLmoveIsSpecial = checkIfMoveIsSpeicalM(PLspellSelection);
					PLmoveType = getMoveTypeM(PLspellSelection,PL.getWeapon(),PL.getAbility());
					PLusingMagic = false;
					
					showEnemyTargets();
					//allows the player to select a target
					byte target = (byte)yourNameDecision[2];//initialized it to 8 as a dummy variable in case the user inputs an invalid number
						PLtarget = target;//targets which enemy Ripped Dragonborn will attack
					
					//sets the damage and type
					
					PLusingMagic = true;
					break;
					
				//allows the player to use items during battle
				case 3:
					
					//allows the player to access the inventory
					useInventory();
					
					//makes sure the player can't attack while using items
					PLusingMagic = false;
					PLmoveSelection = Moves.placeholder;
					PLmoveBS = 0;
					PLmoveIsSpecial = false;
					break;
					
				//allows the player to defend against physical attacks	
				case 4:
					PL.defend();
					
					//makes sure the player can't attack while using items
					PLmoveSelection = Moves.placeholder;
					PLmoveBS = 0;
					PLmoveIsSpecial = false;
					PLusingMagic = false;
					break;
					
				//taunts aren't really used for anything except skipping moves without wasting any items and listening
				//to some secret chraracter dialogue
				case 5:
					
					//calls a random taunt from the Thicc Tanuki Class
					println(PL.getTaunt());
					
					//makes sure the player can't attack while using items
					PLmoveSelection = Moves.placeholder;
					PLmoveBS = 0;
					PLmoveIsSpecial = false;
					PLusingMagic = false;
					
					//set up taunt target
					tauntActive = true;
					characterLastTaunted = 2;
					break;
				//allows the player to run from battles although this will fail for important battles or against strong opponents
				case 6:
					//makes it so you can't attack if you have a failed attempt at running from battle
					PLmoveSelection = Moves.placeholder;
					PLmoveBS = 0;
					PLmoveIsSpecial = false;
					PLusingMagic = false;
					//checks to make sure you can actually run from the current battle
					if (canRun == false) {
						println("You can't run from your current battles.");
						break;
					}
					
					println("Player ran from battle.");
					battleOver = true;
					break;
				default:
					break;
				}
			}
			
			System.out.println("ALL PLAYERS DONE");
			
		//makes enemies take damage after all the characters have selected their moves
		//makeEnemiesTakeDamage();
		turnCounter++;
	}
	
	/**
	 * A private void method so the Kitsune Boss can make moves against the player
	 */
	public void makeKitsuneMoves() {
		//stops the battle after the Kitsune has been defeated
		if(!Kitsune.checkIfBossAlive()) {
			//soundTrack.stopPlayingSoundtrack();
			println("HOST: Looks like this battle is settled folks! Would our losing contestant like any final words?");
			scan.nextInt();
			println("KITSUNE: Yes! First off, I hate you Tanuki trash panda!"+
			"So why don't you just eat some garbage and crawl back to that trash can where you came from!");
			scan.nextInt();
			println("KITSUNE: And then there's you anthro dragon boy! You're not even a real dragon but an\n"
					+ "angry little dragon boy that should have stayed in the middle ages where he belongs!");
			scan.nextInt();
			println("KITSUNE: Alright, maybe the dragon comment was a little bit over the top but at the\n"
					+"but at the end of the day you're just another fake chad attempting to flex on his superiors.");
			scan.nextInt();
			println("KITSUNE: As for you player, why are you even playing this game? How about actually do something productive\n"
					+"with your life and get some sun.");
			scan.nextInt();
			println("KITSUNE: And don't think I'm not onto you too, Magical Girl.");
			scan.nextInt();
			println("HOST: Sorry contestant but I'm afraid your rant is a little too long! Enjoy your time in the dungeon!\n"
					+ "Until next time! Praise our god, Ya's'keel!");
			battleOver = true;
			return;
		}
		
		println("KITSUNE: "+Kitsune.getTaunt());
		//makes it so Kitsune Illusions isn't always active and only lasts one turn
		KitsuneIllusionActive = false;
		
		//makes sure the Kitsune attacks 4 times in one turn
		for(byte i = 0; i<4; i++) {
			aiUsingMagic[2] =false;
			aiTarget[2] = UsefulMethods.getKitsuneTarget(alliesAlive);
			byte attackType = (byte)(Kitsune.decideMagicOrRegularAttack());
			if (attackType == 2) {
				aiMoveIsSpecial[2] = false;
				aiMoveBP[2] = 0;
			}
			else if(attackType==1) {
				aiUsingMagic[2] = true;
				aiSpellChoice[2] = Boss1.KitsuneSpells;
				aiMoveType[2] = getMoveTypeM(aiSpellChoice[2],Boss1.KitsuneWeapon,Abilities.noAbility);
				if(aiTarget[2]==1)aiMoveType[2] = Elementals.ethereal;
				aiMoveBP[2] = (short) (getMoveBasePowerM(aiSpellChoice[2],Boss1.KitsuneWeapon,Abilities.noAbility)/KawaiiDamageMultiplier);//makes it so that looking kawaii enrages the kitsune and makes him attack harder
			}
			else {
				aiUsingMagic[2] = false;
				aiMoveChoice[2] = Kitsune.chooseMove();
				aiMoveType[2] = getMoveType(aiMoveChoice[2],Boss1.KitsuneWeapon,Abilities.noAbility);
				aiMoveBP[2] = (short)(getMoveBasePower(aiMoveChoice[2],Boss1.KitsuneWeapon,Abilities.noAbility));
			}
			makeEnemiesAttack();
		}
		
		turnCounter++;
	}
	
	/**
	 * A private helper method that makes it so the enemy list can make moves
	 */
	public void makeT1EnemiesMoves() {
		
		//a for loop so tier 1 enemies can attack.
		for (byte i = 0; i<4; i++) {
			if(aiAlive[i] == false)continue;//as an optimization measure, it skips the following code if the enemy ai has been defeated
			aiTarget[i] = (byte)(rand.nextInt(4));// will change this to 4 when more characters are added
			if(tauntActive == true) {
				aiTarget[i] = characterLastTaunted;
			}
			aiMoveChoice[i] = t1ai[i].moveChooser();
			aiMoveType[i] = getMoveType(aiMoveChoice[i], t1ai[i].getWeapon(), t1ai[i].getAbility());
			aiMoveBP[i] = getMoveBasePower(aiMoveChoice[i], t1ai[i].getWeapon(), t1ai[i].getAbility());
			aiMoveIsSpecial[i] = checkIfMoveIsSpecial(aiMoveChoice[i]);
		}
		
		//a function call so enemies attack regardless of tier status
		makeEnemiesAttack();
		turnCounter++;
	}
	
	/**
	 * A private helper method so that the tier 2 enemies can make moves.
	 */
	public void makeT2EnemiesMoves() {
		for (byte i = 0; i<4;i++) {
			if(aiAlive[i] == false)continue;
			println(t2ai[i].getFlavorText());//prints flavor tet to the console
			if(tauntActive)aiTarget[i] = characterLastTaunted;
			else aiTarget[i] = UsefulMethods.advancedTargeting(alliesAlive);
			boolean magicOrRegular = t2ai[i].decideMoveOrMagic();
			if (magicOrRegular)
			{
				aiUsingMagic[i] = true;
				aiSpellChoice[i] = t2ai[i].chooseSpell();
				aiMoveBP[i] = getMoveBasePowerM(aiSpellChoice[i],t2ai[i].getWeapon(),t2ai[i].getAbility());
				aiMoveType[i] = getMoveTypeM(aiSpellChoice[i],t2ai[i].getWeapon(),t2ai[i].getAbility());
			}
			else {
				aiUsingMagic[i] = false;
				aiMoveChoice[i] = t2ai[i].moveChooser();
				aiMoveIsSpecial[i] = checkIfMoveIsSpecial(aiMoveChoice[i]);
				aiMoveType[i] = getMoveType(aiMoveChoice[i],t2ai[i].getWeapon(),t2ai[i].getAbility());
				aiMoveBP[i] = getMoveBasePower(aiMoveChoice[i], t2ai[i].getWeapon(), t2ai[i].getAbility());
			}
		}
		
		//a function call so enemies can attack players
		makeEnemiesAttack();
		turnCounter++;
	}
	
	/**
	 * A private helper method so enenmies can take damage.
	 */
	public void makeEnemiesTakeDamage() {
		
		//cycles through all the characters making moves
		for(byte i = 0; i < 4; i++) {
			
		//a big "if-tree" so that enemies take damage all in one go
		if(TNusingMagic == true && enemyTier == 1 && i == 0) {
			int enemyDamageTaken;
			enemyDamageTaken = t1ai[TNtarget].damageCalculator(TNmoveBS,TNmoveType,true, TN.getStats()[2], TN.getStats()[3], TN.getStats()[0]);
		    println(getEnemyName((short)t1ai[TNtarget].getID())+" took "+enemyDamageTaken+" damage.");
		    if (t1ai[TNtarget].checkIfAlive() == false) {
		    	int expGain = t1ai[TNtarget].getEXP();
		    	println("Thicc Tanuki gained "+expGain+"exp.");
		    	TN.updateEXP(expGain);
		    	aiAlive[TNtarget] = false;
		    }
		}
		else if (TNusingMagic == false && enemyTier == 1 && i == 0) {
			int enemyDamageTaken;
			enemyDamageTaken = t1ai[TNtarget].damageCalculator(TNmoveBS,TNmoveType,TNmoveIsSpecial, TN.getStats()[2], TN.getStats()[3], TN.getStats()[0]);
			println(getEnemyName((short)t1ai[TNtarget].getID())+" took "+enemyDamageTaken+" damage.");
			if (t1ai[TNtarget].checkIfAlive() == false) {
		    	int expGain = t1ai[TNtarget].getEXP();
		    	println("Thicc Tanuki gained "+expGain+"exp.");
		    	TN.updateEXP(expGain);
		    	aiAlive[TNtarget] = false;
		    }
		}
		
		// for Ripped DragonBorn to attack enemies
		else if (enemyTier == 1 && DBusingMagic == false && i == 1) {
			if(DBusingMagic == false) {
				int enemyDamageTaken;
				enemyDamageTaken = t1ai[DBtarget].damageCalculator(DBmoveBS, DBmoveType, DBmoveIsSpecial, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
				println(getEnemyName((short)t1ai[DBtarget].getID())+" took "+enemyDamageTaken+" damage.");
				if (t1ai[DBtarget].checkIfAlive() == false) {
			    	int expGain = t1ai[DBtarget].getEXP();
			    	println("Anthro Dragon gained "+expGain+"exp.");
			    	DB.updateEXP(expGain);
			    	aiAlive[DBtarget] = false;
			    }
			}
		}
		else if(enemyTier == 1 && DBusingMagic == true && i == 1) {
			int enemyDamageTaken;
			enemyDamageTaken = t1ai[DBtarget].damageCalculator(DBmoveBS, DBmoveType, true, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
			println(getEnemyName((short)t1ai[DBtarget].getID())+" took "+enemyDamageTaken+" damage.");
			if (t1ai[DBtarget].checkIfAlive() == false) {
		    	int expGain = t1ai[DBtarget].getEXP();
		    	println("Anthro Dragon gained "+expGain+"exp.");
		    	DB.updateEXP(expGain);
		    	aiAlive[DBtarget] = false;
				}
			}
		
		//for Magical Girl
		else if (enemyTier == 1 && MGusingMagic == false && i == 2) {
			if(MGusingMagic == false) {
				int enemyDamageTaken;
				enemyDamageTaken = t1ai[MGtarget].damageCalculator(MGmoveBS, MGmoveType, MGmoveIsSpecial, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
				println(getEnemyName((short)t1ai[MGtarget].getID())+" took "+enemyDamageTaken+" damage.");
				if (t1ai[MGtarget].checkIfAlive() == false) {
			    	int expGain = t1ai[MGtarget].getEXP();
			    	println("Magical Girl gained "+expGain+"exp.");
			    	MG.updateEXP(expGain);
			    	aiAlive[MGtarget] = false;
			    }
			}
		}
		else if(enemyTier == 1 && MGusingMagic == true && i == 2) {
			int enemyDamageTaken;
			enemyDamageTaken = t1ai[MGtarget].damageCalculator(MGmoveBS, MGmoveType, true, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
			println(getEnemyName((short)t1ai[MGtarget].getID())+" took "+enemyDamageTaken+" damage.");
			if (t1ai[MGtarget].checkIfAlive() == false) {
		    	int expGain = t1ai[MGtarget].getEXP();
		    	println("Magical Girl gained "+expGain+"exp.");
		    	MG.updateEXP(expGain);
		    	aiAlive[MGtarget] = false;
				}
			}
		
		//for Player
		else if (enemyTier == 1 && PLusingMagic == false && i == 3) {
			if(PLusingMagic == false) {
				int enemyDamageTaken;
				enemyDamageTaken = t1ai[PLtarget].damageCalculator(PLmoveBS, PLmoveType, PLmoveIsSpecial, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
				println(getEnemyName((short)t1ai[PLtarget].getID())+" took "+enemyDamageTaken+" damage.");
				if (t1ai[PLtarget].checkIfAlive() == false) {
			    	int expGain = t1ai[PLtarget].getEXP();
			    	println("Player gained "+expGain+"exp.");
			    	PL.updateEXP(expGain);
			    	aiAlive[PLtarget] = false;
			    }
			}
		}
		else if(enemyTier == 1 && PLusingMagic == true && i == 3) {
			int enemyDamageTaken;
			enemyDamageTaken = t1ai[PLtarget].damageCalculator(PLmoveBS, PLmoveType, true, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
			println(getEnemyName((short)t1ai[PLtarget].getID())+" took "+enemyDamageTaken+" damage.");
			if (t1ai[PLtarget].checkIfAlive() == false) {
		    	int expGain = t1ai[PLtarget].getEXP();
		    	println("Player gained "+expGain+"exp.");
		    	PL.updateEXP(expGain);
		    	aiAlive[PLtarget] = false;
				}
			}
		else if(enemyTier == 2) {//allows tier 2 enemies to take damage from the players
			//please write code here so tier 2 enemies can take damage
			if(i==0) {
				//Tanuki code here
				if(TNusingMagic) {
					int enemyDamageTaken;
					enemyDamageTaken = t1ai[TNtarget].damageCalculator(TNmoveBS,TNmoveType,true, TN.getStats()[2], TN.getStats()[3], TN.getStats()[0]);
				    println(getEnemyName((short)t1ai[TNtarget].getID())+" took "+enemyDamageTaken+" damage.");
				    if (t1ai[TNtarget].checkIfAlive() == false) {
				    	int expGain = t1ai[TNtarget].getEXP();
				    	println("Thicc Tanuki gained "+expGain+"exp.");
				    	TN.updateEXP(expGain);
				    	aiAlive[TNtarget] = false;
				    }
				}
				else {
					int enemyDamageTaken;
					enemyDamageTaken = t1ai[TNtarget].damageCalculator(TNmoveBS,TNmoveType,TNmoveIsSpecial, TN.getStats()[2], TN.getStats()[3], TN.getStats()[0]);
				    println(getEnemyName((short)t1ai[TNtarget].getID())+" took "+enemyDamageTaken+" damage.");
				    if (t1ai[TNtarget].checkIfAlive() == false) {
				    	int expGain = t1ai[TNtarget].getEXP();
				    	println("Thicc Tanuki gained "+expGain+"exp.");
				    	TN.updateEXP(expGain);
				    	aiAlive[TNtarget] = false;
				    }
				}
			}
			else if (i==1) {
				//anthro dragon code here
				if(DBusingMagic) {
					int enemyDamageTaken;
					enemyDamageTaken = t2ai[DBtarget].damageCalculator(DBmoveBS, DBmoveType, true, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
					println(getEnemyName((short)t2ai[DBtarget].getID())+" took "+enemyDamageTaken+" damage.");
					if (t2ai[DBtarget].checkIfAlive() == false) {
				    	int expGain = t2ai[DBtarget].getEXP();
				    	println("Anthro Dragon gained "+expGain+"exp.");
				    	DB.updateEXP(expGain);
				    	aiAlive[DBtarget] = false;
						}
				}
				else {
					int enemyDamageTaken;
					enemyDamageTaken = t2ai[DBtarget].damageCalculator(DBmoveBS, DBmoveType, DBmoveIsSpecial, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
					println(getEnemyName((short)t2ai[DBtarget].getID())+" took "+enemyDamageTaken+" damage.");
					if (t2ai[DBtarget].checkIfAlive() == false) {
				    	int expGain = t2ai[DBtarget].getEXP();
				    	println("Anthro Dragon gained "+expGain+"exp.");
				    	DB.updateEXP(expGain);
				    	aiAlive[DBtarget] = false;
				    }
				}
			}
			else if (i==2) {
				//player code here
				if(PLusingMagic) {
					int enemyDamageTaken;
					enemyDamageTaken = t2ai[PLtarget].damageCalculator(PLmoveBS, PLmoveType, true, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
					println(getEnemyName((short)t2ai[PLtarget].getID())+" took "+enemyDamageTaken+" damage.");
					if (t2ai[PLtarget].checkIfAlive() == false) {
				    	int expGain = t2ai[PLtarget].getEXP();
				    	println("Player gained "+expGain+"exp.");
				    	PL.updateEXP(expGain);
				    	aiAlive[PLtarget] = false;
						}
				}
				else {
					int enemyDamageTaken;
					enemyDamageTaken = t2ai[PLtarget].damageCalculator(PLmoveBS, PLmoveType, PLmoveIsSpecial, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
					println(getEnemyName((short)t2ai[PLtarget].getID())+" took "+enemyDamageTaken+" damage.");
					if (t2ai[PLtarget].checkIfAlive() == false) {
				    	int expGain = t2ai[PLtarget].getEXP();
				    	println("Player gained "+expGain+"exp.");
				    	PL.updateEXP(expGain);
				    	aiAlive[PLtarget] = false;
				    }
				}
			}
			else {
				//magical girl code here
				if(MGusingMagic) {
					int enemyDamageTaken;
					enemyDamageTaken = t2ai[MGtarget].damageCalculator(MGmoveBS, MGmoveType, true, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
					println(getEnemyName((short)t2ai[MGtarget].getID())+" took "+enemyDamageTaken+" damage.");
					if (t2ai[MGtarget].checkIfAlive() == false) {
				    	int expGain = t2ai[MGtarget].getEXP();
				    	println("Magical Girl gained "+expGain+"exp.");
				    	MG.updateEXP(expGain);
				    	aiAlive[MGtarget] = false;
						}
				}
				else {
					int enemyDamageTaken;
					enemyDamageTaken = t2ai[MGtarget].damageCalculator(MGmoveBS, MGmoveType, MGmoveIsSpecial, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
					println(getEnemyName((short)t2ai[MGtarget].getID())+" took "+enemyDamageTaken+" damage.");
					if (t2ai[MGtarget].checkIfAlive() == false) {
				    	int expGain = t2ai[MGtarget].getEXP();
				    	println("Magical Girl gained "+expGain+"exp.");
				    	MG.updateEXP(expGain);
				    	aiAlive[MGtarget] = false;
				    }
				}
			}
		}
		else if(enemyTier == 4) {//makes it so playable characters attack themselves if Kitsune Illusions is active
			if(KitsuneIllusionActive) {
				TN.damageCalculator(TNmoveBS, TNmoveType, TNmoveIsSpecial, TN.getStats()[2], TN.getStats()[3], TN.getStats()[0]);
				DB.damageCalculator(DBmoveBS, DBmoveType, DBmoveIsSpecial, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
				PL.damageCalculator(PLmoveBS, PLmoveType, PLmoveIsSpecial, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
				MG.damageCalculator(MGmoveBS, MGmoveType, MGmoveIsSpecial, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
			}
			else if(!TNusingMagic && i ==0) {
				int enemyDamageTaken = Kitsune.takeDamage(TNmoveBS, TNmoveType, TNmoveIsSpecial, TN.getStats()[2], TN.getStats()[3], TN.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			//no need for when Thicc Tanuki is using magic because she doesn't have any damage dealing spells
			else if (!DBusingMagic&& i ==1) {
				int enemyDamageTaken = Kitsune.takeDamage(DBmoveBS, DBmoveType, DBmoveIsSpecial, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			else if(DBusingMagic&& i ==1) {
				int enemyDamageTaken = Kitsune.takeDamage(DBmoveBS, DBmoveType, true, DB.getStats()[2], DB.getStats()[3], DB.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			else if(!PLusingMagic&& i ==2) {
				int enemyDamageTaken = Kitsune.takeDamage(PLmoveBS, PLmoveType, PLmoveIsSpecial, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			else if(PLusingMagic&& i ==2) {
				int enemyDamageTaken = Kitsune.takeDamage(PLmoveBS, PLmoveType, true, PL.getStats()[2], PL.getStats()[3], PL.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			else if(!MGusingMagic&& i ==3) {
				int enemyDamageTaken = Kitsune.takeDamage(MGmoveBS, MGmoveType, MGmoveIsSpecial, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			else if(MGusingMagic&& i ==3){
				int enemyDamageTaken = Kitsune.takeDamage(MGmoveBS, MGmoveType, true, MG.getStats()[2], MG.getStats()[3], MG.getStats()[0]);
				println("Kitsune has taken "+enemyDamageTaken+" damage.");
				if(!Kitsune.checkIfBossAlive()) {
					TN.updateEXP(2000);
					DB.updateEXP(2000);
					PL.updateEXP(2000);
					MG.updateEXP(2000);
				}
			}
			}
		}
	}
	
	/**
	 * A private helper method so enemies can attack players
	 */
	private void makeEnemiesAttack() {
		
		
		//cycles through all the living enemies
		for (byte i = 0; i<4; i++) {
			if(aiAlive[i] == false)continue;
			aiMoveBP[i] = (short)(aiMoveBP[i] * KawaiiDamageMultiplier);//applies the damage weakening multiplier if you're kawaii
			if(TNspellSelection == Spells.TanukiTeamEvade && TNusingMagic == true) {
				println("None of the enemy attacks could hit any party members.");
				break;
			}
			else if(TNspellSelection == Spells.TanukiEvade && TNusingMagic == true && (teamMembersUsingTNevade[aiTarget[i]] == true)) {
				println(getEnemyName(i) + " has missed its attack.");
				continue;
			}
			else if(TNillusionActive) {
				if (enemyTier == 1) {
					println("Thicc Tanuki has used the power of Tanuki Illusion magic to make " +getEnemyName((short)t1ai[i].getID())+
							" hurt itself in its illusion.");
					int enemyDamageTaken = t1ai[i].damageCalculator((aiMoveBP[i]*3),aiMoveType[i],aiMoveIsSpecial[i], t1ai[i].getStats()[2], t1ai[i].getStats()[4], t1ai[i].getStats()[0]);
					println(getEnemyName((short)t1ai[i].getID())+" hurt itself with "+enemyDamageTaken+" damage under the influence of the magic of Tanuki illusions.");
				}
				else if(enemyTier == 2) {
					println("Thicc Tanuki has used the power of Tanuki ILlusion magic to make "+getEnemyName((short)t2ai[i].getID())+
							" hurt itself in its illusion.");
					int enemyDamageTaken = t2ai[i].damageCalculator((aiMoveBP[i]*2),aiMoveType[i],aiMoveIsSpecial[i], t2ai[i].getStats()[2],t2ai[i].getStats()[4],t2ai[i].getStats()[0]);
					println(getEnemyName((short)t2ai[i].getID())+" hurt itself with "+enemyDamageTaken+" damage under the influence of the magic of Tanuki illusions.");
				}
				else if(enemyTier == 4) {
					int damageTaken = Kitsune.takeDamage(aiMoveBP[2], aiMoveType[2], aiMoveIsSpecial[2], 2000, 2700, 20);
					println("Kitsune dealt "+damageTaken+" to itself under Thicc Tanuki's illusion magic.");
				}
			}
			else {
				if (enemyTier == 1) {
					if(aiTarget[i] == 0) {
						int enemyDamageTaken;
						enemyDamageTaken = TN.damageCalculator(aiMoveBP[i],aiMoveType[i],aiMoveIsSpecial[i], t1ai[i].getStats()[2], t1ai[i].getStats()[4], t1ai[i].getStats()[0]);
						println(getEnemyName((short)t1ai[i].getID())+" used "+aiMoveChoice[i]+" .");
						println(getEnemyName((short)t1ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Thicc Tanuki.");	
					}
					else if(aiTarget[i] == 1) {
						int enemyDamageTaken;
						enemyDamageTaken = DB.damageCalculator((aiMoveBP[i]),aiMoveType[i],aiMoveIsSpecial[i], t1ai[i].getStats()[2], t1ai[i].getStats()[4], t1ai[i].getStats()[0]);
						println(getEnemyName((short)t1ai[i].getID())+" used "+aiMoveChoice[i]+" .");
						println(getEnemyName((short)t1ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Anthro Dragon.");	
					}
					else if(aiTarget[i] == 2) {
						int enemyDamageTaken;
						enemyDamageTaken = PL.damageCalculator(aiMoveBP[i],aiMoveType[i],aiMoveIsSpecial[i], t1ai[i].getStats()[2], t1ai[i].getStats()[4], t1ai[i].getStats()[0]);
						println(getEnemyName((short)t1ai[i].getID())+" used "+aiMoveChoice[i]+" .");
						println(getEnemyName((short)t1ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Magical Girl.");	
					}
					else {
						int enemyDamageTaken;
						enemyDamageTaken = MG.damageCalculator(aiMoveBP[i],aiMoveType[i],aiMoveIsSpecial[i], t1ai[i].getStats()[2], t1ai[i].getStats()[4], t1ai[i].getStats()[0]);
						println(getEnemyName((short)t1ai[i].getID())+" used "+aiMoveChoice[i]+" .");
						println(getEnemyName((short)t1ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Player.");	
					}
				}
				else if (enemyTier == 2) {
					if(aiUsingMagic[i]) {
						if(aiTarget[i] == 0) {
							int enemyDamageTaken;
							enemyDamageTaken = TN.damageCalculator(aiMoveBP[i],aiMoveType[i],true, (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Thicc Tanuki.");	
						}
						else if(aiTarget[i] == 1) {
							int enemyDamageTaken;
							enemyDamageTaken = DB.damageCalculator((aiMoveBP[i]),aiMoveType[i],true, (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Anthro Dragon.");	
						}
						else if(aiTarget[i] == 2) {
							int enemyDamageTaken;
							enemyDamageTaken = PL.damageCalculator(aiMoveBP[i],aiMoveType[i],true, (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Magical Girl.");	
						}
						else {
							int enemyDamageTaken;
							enemyDamageTaken = MG.damageCalculator(aiMoveBP[i],aiMoveType[i],true, (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Player.");	
						}
					}
					else {
						if(aiTarget[i] == 0) {
							int enemyDamageTaken;
							enemyDamageTaken = TN.damageCalculator(aiMoveBP[i],aiMoveType[i],aiMoveIsSpecial[i], (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Thicc Tanuki.");	
						}
						else if(aiTarget[i] == 1) {
							int enemyDamageTaken;
							enemyDamageTaken = DB.damageCalculator((aiMoveBP[i]),aiMoveType[i],aiMoveIsSpecial[i], (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Anthro Dragon.");	
						}
						else if(aiTarget[i] == 2) {
							int enemyDamageTaken;
							enemyDamageTaken = PL.damageCalculator(aiMoveBP[i],aiMoveType[i],aiMoveIsSpecial[i], (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Magical Girl.");	
						}
						else {
							int enemyDamageTaken;
							enemyDamageTaken = MG.damageCalculator(aiMoveBP[i],aiMoveType[i],aiMoveIsSpecial[i], (int)(t2ai[i].getStats()[2]*KawaiiDamageMultiplier), (int)(t2ai[i].getStats()[4]*KawaiiDamageMultiplier), t2ai[i].getStats()[0]);
							println(getEnemyName((short)t2ai[i].getID())+" used "+aiMoveChoice[i]+" .");
							println(getEnemyName((short)t2ai[i].getID())+" dealt "+enemyDamageTaken+" damage to Player.");	
						}
					}
				}
				else if (enemyTier ==4) {
					int damageDealt = 0;
					if (aiUsingMagic[2] == false) {
						println("Kitsune used "+aiMoveChoice[2]+".\n");
						int attackPower = 350;
						int attackPower2 = 200;
						if(aiTarget[2] == 0) {
							damageDealt = TN.damageCalculator(aiMoveBP[2],aiMoveType[2],aiMoveIsSpecial[2], attackPower2, attackPower, 20);
							println("Kitsune dealt "+damageDealt+" to Thicc Tanuki.\n");
						}
						else if (aiTarget[1]==1) {
							damageDealt = DB.damageCalculator(aiMoveBP[2],aiMoveType[2],aiMoveIsSpecial[2], attackPower2, attackPower, 20);
							println("Kitsune dealt "+damageDealt+"to Anthro Dragon.\n");
						}
						else if (aiTarget[2] ==2) {
							damageDealt = PL.damageCalculator(aiMoveBP[2],aiMoveType[2],aiMoveIsSpecial[2], attackPower2, attackPower, 20);
							println("Kitsune dealt "+damageDealt+"to Player.\n");
						}
						else {
							damageDealt = MG.damageCalculator(aiMoveBP[2],aiMoveType[2],aiMoveIsSpecial[2], attackPower2, attackPower, 20);
							println("Kitsune dealt "+damageDealt+"to Magical Girl.\n");
						}
					}
					else {
						println("Kitsune used "+aiSpellChoice[2]+".\n");
						int attackPower = 350;
						if(aiTarget[2] == 0) {
							damageDealt = TN.damageCalculator(aiMoveBP[2],aiMoveType[2],true, 2000, attackPower, 20);
							println("Kitsune dealt "+damageDealt+" to Thicc Tanuki.\n");
						}
						else if (aiTarget[2]==1) {
							damageDealt = DB.damageCalculator(aiMoveBP[2],aiMoveType[2],true, 2000, attackPower, 20);
							println("Kitsune dealt "+damageDealt+"to Anthro Dragon.\n");
						}
						else if (aiTarget[2] ==2) {
							damageDealt = PL.damageCalculator(aiMoveBP[2],aiMoveType[2],true, 2000, attackPower, 20);
							println("Kitsune dealt "+damageDealt+"to Player.\n");
						}
						else {
							damageDealt = MG.damageCalculator(aiMoveBP[2],aiMoveType[2],true, 2000, attackPower, 20);
							println("Kitsune dealt "+damageDealt+"to Magical Girl.\n");
						}
					}
				}
			}
		}
	}
	
	/**
	 * A method that returns the name of the enemy based on its ID
	 * @param ID accepts a short of the enemy ID
	 * @return returns a String of the enemy's name
	 */
	private String getEnemyName(short ID) {
		switch (ID) {
		case 0:
			return "Test Enemy";
		case 1:
			return "";
		case 3:
			return Tier1EnemyList.turretName;
		case 4:
			return Tier1EnemyList.purpleHornetName;
		case 5:
			return Tier1EnemyList.dragonLizardName;
		case 6:
			return Tier1EnemyList.hellHoundName;
		case 7:
			return Tier1EnemyList.killerBirdName;
		case 8:
			return Tier1EnemyList.smallOrcName;
		case 21:
			return Tier2EnemyData.elfNAME;
		case 22:
			return Tier2EnemyData.skeletonNAME;
		case 23:
			return Tier2EnemyData.graphicsManExNAME;
		case 24:
			return Tier2EnemyData.glitchedManNeoNAME;
		case 25:
			return Tier2EnemyData.dobermanFurryNAME;
		case 26:
			return Tier2EnemyData.grifonNAME;
		case 27:
			return Tier2EnemyData.naiadNAME;
		case 28:
			return Tier2EnemyData.harpyNAME;
		case 29:
			return Tier2EnemyData.thunderDogNAME;
		case 30:
			return Tier2EnemyData.dryadNAME;
		default:
			return "Missingno";
		}
	}
	
	/**
	 * A private helper method to get the enemy HP and Level
	 * @param ID Accepts a short of the enemy's index in teh array
	 * @return
	 */
	private String getEnemyHPandLevel(short ID) {
	
		if (enemyTier == 1) {
		String tempString = "Level: ";
		switch (ID) {
		case 0:
			tempString += t1ai[ID].getStats()[0] + " HP: "+t1ai[ID].getStats()[1];
			return tempString;
		case 1:
			tempString += t1ai[ID].getStats()[0] + " HP: "+t1ai[ID].getStats()[1];
			return tempString;
		case 2:
			tempString += t1ai[ID].getStats()[0] + " HP: "+t1ai[ID].getStats()[1];
			return tempString;
		case 3:
			tempString += t1ai[ID].getStats()[0] + " HP: "+t1ai[ID].getStats()[1];
			return tempString;
		default:
			return "Glitched";
		}
		}
		else {
			return "Error: Invalid enemy tier.";
		}
	}
	
	/**
	 * A private helper method that helps print which enemies are targetable
	 */
	private void showEnemyTargets() {
		String tempString = "";
		for (byte i = 0; i<4; i++) {
			
			if(enemyTier == 1) {
				if(t1ai[i].checkIfAlive() == false)tempString +="";
			}
			else if(enemyTier == 4) {
				tempString = UsefulMethods.getHPandLevelAsString("9-Tailed Kitsune",Kitsune.getStats()[0],20);
			}
			else {
				if(enemyTier ==1)tempString = tempString + i + ": "+getEnemyName((short)t1ai[i].getID())+"\n"+getEnemyHPandLevel(i)+"\n\n";
			}
		}
		println(tempString);
	}
	
	/**
	 * A private helper method so I don't have to type System.out.println every time I want to print something.
	 * @param words accepts a String to be printed
	 */
	private void println(String words) {
		System.out.println(words);
	}
	
	/**
	 * A helper method that checks if a move's selection is special
	 * @param move accepts an enum of type Moves to get the move of the character
	 * @return returns a boolean of whether or not a move is special FALSE == physical and TRUE == special
	 */
	private boolean checkIfMoveIsSpecial(Moves move) {
		MovesData md = new MovesData();
		switch (move) {
		case placeholder:
			return MovesData.placeholderSP;
		case voltcharge:
			return MovesData.voltChargeSP;
		case etherealBlade:
			return MovesData.etherealBladeSP;
		case missingno:
			return MovesData.missingnoSP;
		case isDead:
			return MovesData.isDeadSP;
		case shootGun:
			return MovesData.shootGunSP;
		case tackle:
			return MovesData.tackleSP;
		case scratch:
			return MovesData.scratchSP;
		case pummel:
			return MovesData.pummelSP;
		case swordSlash:
			return MovesData.swordSlashSP;
		case dragonFist:
			return MovesData.dragonFistSP;
		case fireFist:
			return MovesData.fireFistSP;
		case frostBite:
			return MovesData.frostBiteSP;
		case moonKick:
			return MovesData.moonKickSP;
		case shadowPunch:
			return MovesData.shadowPunchSP;
		case fireBreath:
			return MovesData.fireBreathSP;
		case thunderbolt:
			return MovesData.thunderboltSP;
		case shockingFist:
			return MovesData.shockingFistSP;
		case rockThrow:
			return MovesData.rockThrowSP;
		case mudSlide:
			return MovesData.mudSlideSP;
		case windGust:
			return MovesData.windGustSP;
		case megabuster:
			return MovesData.megabusterSP;
		case naturePower:
			return MovesData.naturePowerSP;
		case dragonBreath:
			return MovesData.dragonBreathSP;
		case sting:
			return MovesData.stingSP;
		case bite:
			return MovesData.biteSP;
		case hyperlinkBlocked:
			return md.getHyperlinkBlockedSP();
		case boneClub:
			return MovesData.boneClubSP;
		default:
			return false;
		}
	}

	/**
	 * A method that returns whether or not a move is special
	 * @param spell accepts an enum of type Spell to check if the spell is special
	 * @return returns a boolean of whether or not a spell is special [true = special/false = physical]
	 */
	private boolean checkIfMoveIsSpeicalM(Spells spell) {
		return true;
	}
	
	/**
	 * A private helper method that returns a short of a move's base power
	 * @return returns a short of the base power
	 * @param move accepts an enum of type Moves to be used as a move selection
	 * @param w accepts an enum fo type Weapons the character's weapon
	 * @param a accepts an enum of type Abilities of the character's abilities
	 */
	private short getMoveBasePower(Moves move, Weapons w, Abilities a) {
		MovesData md = new MovesData();
		switch (move) {
		case placeholder:
			return MovesData.placeholderBP;
		case voltcharge:
			return MovesData.voltChargeBP;
		case etherealBlade:
			return MovesData.etherealBladeBP;
		case missingno:
			return MovesData.missingnoBP;
		case isDead:
			return MovesData.isDeadBP;
		case shootGun:
			short tempShort =  md.getBasePowerForShootGun(a,w);
			return tempShort;
		case tackle:
			return MovesData.tackleBP;
		case scratch:
			return MovesData.scratchBP;
		case pummel:
			return MovesData.pummelBP;
		case swordSlash:
			return md.getSwordSlashBP(a,w);
		case dragonFist:
			return MovesData.dragonFistBP;
		case fireFist:
			return MovesData.fireFistBP;
		case frostBite:
			return MovesData.frostBiteBP;
		case moonKick:
			return MovesData.moonKickBP;
		case shadowPunch:
			return MovesData.shadowPunchBP;
		case fireBreath:
			return MovesData.fireBreathBP;
		case thunderbolt:
			return MovesData.thunderboltBP;
		case shockingFist:
			return MovesData.shockingFistBP;
		case rockThrow:
			return MovesData.rockThrowBP;
		case mudSlide:
			return MovesData.mudSlideBP;
		case windGust:
			return MovesData.windGustBP;
		case megabuster:
			return MovesData.megabusterBP;
		case naturePower:
			return MovesData.naturePowerBP;
		case dragonBreath:
			return MovesData.dragonBreathBP;
		case sting:
			return MovesData.stingBP;
		case bite:
			return MovesData.biteBP;
		case hyperlinkBlocked:
			return md.getHyperlinkBlockedBP();
		case boneClub:
			return MovesData.boneClubBP;
		default:
			return 0;
		}
	}
	
	/**
	 * A method that returns the base power of a spell and access other methods when necessary
	 * @param spell accepts an enum of type Spells to check for base power or other stuff
	 * @param w accepts an enum of type Weapons of the character's weapon as some spells change based on weapon
	 * @param a accepts an enum of type Abilities of the character's ability as some spells can change based on character abilities
	 * @return returns a short of the base power of a spell
	 */
	private short getMoveBasePowerM(Spells spell, Weapons w, Abilities a) {
		switch (spell) {
		
		//for regular damage-dealing Magic spells
		case none:
			return 0;
		case DragonSurge:
			return SpellsData.DragonSurgeBP;
		case NaturePower:
			return SpellsData.NaturePowerBP;
		case KitsuneFire:
			return SpellsData.KitsuneFireBP;
		case BinaryBeam:
			return SpellsData.BinaryBeamBP;
		case fissure:
			return SpellsData.fissureBP;
		case tidalWave:
			return SpellsData.tidalWaveBP;
		case FairyGlitter:
			return SpellsData.FairyGlitterBP;
		case NEObuster:
			return SpellsData.NEObusterBP;
		case tornado:
			return SpellsData.tornadoBP;
		case hurricane:
			return SpellsData.hurricaneBP;
		case EXbuster:
			return SpellsData.EXbusterBP;
		case spellFailed:
			return 0;
		case LookKawaii:
			KawaiiDamageMultiplier = 0.5;
			return 0;
		case healSpell:
			return 0;
		case teamHeal:
			return 0;
		default:
			return 0;
		}		
	}
	
	/**
	 * A private helper method that returns a move's type
	 * @param move accepts an enum of type Moves
	 * @param w accepts an enum of type Weapons
	 * @param a accepts an enum of type Abilities
	 * @return returns an enum of type Elementals of the move's type
	 */
	private Elementals getMoveType(Moves move, Weapons w, Abilities a) {
		MovesData md = new MovesData();
		switch (move) {
		case placeholder:
			return MovesData.placeholderT;
		case voltcharge:
			return MovesData.voltChargeT;
		case etherealBlade:
			return MovesData.etherealBladeT;
		case missingno:
			return MovesData.missingnoT;
		case isDead:
			return MovesData.isDeadT;
		case shootGun:
			return MovesData.shootGunT;
		case tackle:
			return MovesData.tackleT;
		case scratch:
			return MovesData.scratchT;
		case pummel:
			return MovesData.pummelT;
		case swordSlash:
			return md.getSwordSlashType(w);
		case dragonFist:
			return MovesData.dragonFistT;
		case fireFist:
			return MovesData.fireFistT;
		case frostBite:
			return MovesData.frostBiteT;
		case moonKick:
			return MovesData.moonKickT;
		case shadowPunch:
			return MovesData.shadowPunchT;
		case fireBreath:
			return MovesData.fireBreathT;
		case thunderbolt:
			return MovesData.thunderboltT;
		case shockingFist:
			return MovesData.shockingFistT;
		case rockThrow:
			return MovesData.rockThrowT;
		case mudSlide:
			return MovesData.mudSlideT;
		case windGust:
			return MovesData.windGustT;
		case megabuster:
			return MovesData.megabusterT;
		case naturePower:
			return MovesData.naturePowerT;
		case dragonBreath:
			return MovesData.dragonBreathT;
		case sting:
			return MovesData.stingT;
		case bite:
			return MovesData.biteT;
		case hyperlinkBlocked:
			return md.getHyperlinkBlockedT();
		case boneClub:
			return MovesData.boneClubT;
		default:
			return Elementals.neutral;
		}
	}
	
	/**
	 * A method that returns the type of the spell being used
	 * @param spell accepts an enum of type Spells
	 * @param w accepts an enum of type Weapons of the character's weapon
	 * @param a accepts an enum of type Abilities of the character's ability
	 * @return returns an enum of type Elementals of the spell's type
	 */
	private Elementals getMoveTypeM(Spells spell, Weapons w, Abilities a) {
		SpellsData sd = new SpellsData();
		switch (spell) {
		case KitsuneFire:
			return sd.getKitsuneFireType();
		case DragonSurge:
			return SpellsData.DragonSurgeTYPE;
		case NaturePower:
			return SpellsData.NaturePowerTYPE;
		case BinaryBeam:
			return sd.getBinaryBeamType();
		case fissure:
			return SpellsData.fissureTYPE;
		case tidalWave:
			return SpellsData.tidalWaveTYPE;
		case FairyGlitter:
			return SpellsData.FairyGlitterTYPE;
		case NEObuster:
			return SpellsData.NEObusterTYPE;
		case tornado:
			return SpellsData.tornadoTYPE;
		case hurricane:
			return SpellsData.hurricaneTYPE;
		case EXbuster:
			return sd.getEXbusterType();
		default:
			return Elementals.neutral;
		}
	}
	
	/**
	 * A method that allows players to use items in battle
	 */
	private void useInventory() {
		println("You called the useInventory() method.");
		String items = "";
		Items[] itemEnums = inventory.getItemsAry();
		for(byte i = 0; i<24;i++) {
			if(itemEnums[i] == Items.none)continue;
			if(i%4 == 0)items = items + "\n";
			items = items + i+": "+itemEnums[i]+" ";
		}
		println(items);
		println("Select an item to be used.");
		byte selection = scan.nextByte();
		Items itemSelected = inventory.getItem(selection);
		
		//switch table for selecting the actions to follow specific item choices
		switch (itemSelected) {
		case bicycle:
			println("You remembered the Dr. Reed's words. 'You can't ride a bicycle indoors.'");
			inventory.changeItem(Items.none,selection);
			break;
		case none:
			println("Congradulations! You wasted a move using an item that doesn't exist!");
			break;
		case potion100HP:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case healingPotion500HP:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case healingPotion2000HP:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case maxHealing:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case healingPotion50Percent:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case healingPotion25Percent:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case freeLevelUp:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case restoreMagicMeter20PowerPoints:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case restoreMagicMeter50PowerPoints:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case restoreMagicMeter75PowerPoints:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case restoreMagicMeterMAX:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case revive:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		case superRevive:
			useHealingItems(itemSelected);
			inventory.changeItem(Items.none,selection);
			break;
		default:
			println("Error: You have used an invalid item.");
		}
	}
	
	/**
	 * A private help method for using healing items
	 * @param item accepts an enum of type Items for healing item
	 */
	private void useHealingItems(Items item) {
		println("Select which character to use the healing item on:\n"
				+ "1: Thicc Tanuki\n"
				+ "2: Anthro Dragon\n"
				+ "3: Player\n"
				+ "4: Magical Girl\n");
		byte characterSelect = scan.nextByte();
		if(item == Items.potion100HP) {
			switch (characterSelect) {
			case 1:
				TN.restoreHP(100);
				break;
			case 2:
				DB.restoreHP(100);
				break;
			case 3:
				PL.restoreHP(100);
				break;
			default:
				MG.restoreHP(100);
				break;
			}
		}
		else if(item == Items.healingPotion500HP) {
			switch (characterSelect) {
			case 1:
				TN.restoreHP(500);
				break;
			case 2:
				DB.restoreHP(500);
				break;
			case 3:
				PL.restoreHP(500);
				break;
			default:
				MG.restoreHP(500);
				break;
			}
		}
		
		else if (item == Items.healingPotion2000HP) {
			switch (characterSelect) {
			case 1:
				TN.restoreHP(2000);
				break;
			case 2:
				DB.restoreHP(2000);
				break;
			case 3:
				PL.restoreHP(2000);
			default:
				MG.restoreHP(2000);
				break;
			}
		}
		else if (item == Items.maxHealing) {
			switch (characterSelect) {
			case 1:
				TN.restoreHP(2000000000);
				break;
			case 2:
				DB.restoreHP(2000000000);
				break;
			case 3:
				PL.restoreHP(2000000000);
				break;
			default:
				MG.restoreHP(2000000000);
				break;
			}
		}
		else if(item == Items.healingPotion25Percent) {
			switch (characterSelect) {
			case 1:
				int tempRestore = (int)(100*TN.getStats()[0]*0.25);
				TN.restoreHP(tempRestore);
				break;
			case 2:
				int tempRestore3 = (int)(100*DB.getStats()[0]*0.25);
				DB.restoreHP(tempRestore3);
				break;
			case 3:
				int tempRestore4 = (int)(100*PL.getStats()[0]*0.25);
				PL.restoreHP(tempRestore4);
				break;
			default:
				int tempRestore2 = (int)(100*MG.getStats()[0]*0.25);
				MG.restoreHP(tempRestore2);
				break;
			}
		}
		else if(item == Items.healingPotion50Percent) {
			switch (characterSelect) {
			case 1:
				int tempRestore = (int)(100*TN.getStats()[0]*0.5);
				TN.restoreHP(tempRestore);
				break;
			case 2:
				int tempRestore3 = (int)(100*DB.getStats()[0]*0.5);
				DB.restoreHP(tempRestore3);
				break;
			case 3:
				int tempRestore4 = (int)(100*PL.getStats()[0]*0.5);
				PL.restoreHP(tempRestore4);
				break;
			default:
				int tempRestore2 = (int)(100*MG.getStats()[0]*0.5);
				MG.restoreHP(tempRestore2);
				break;
			}
		}
		else if(item == Items.freeLevelUp) {
			switch (characterSelect) {
			case 1:
				int tempLevel = TN.getStats()[0] + 1;
				TN.setLevel(tempLevel);
				break;
			case 2:
				int tempLevel3 = DB.getStats()[0] + 1;
				DB.setLevel(tempLevel3);
				break;
			case 3: 
				int tempLevel4 = PL.getStats()[0] +1;
				PL.setLevel(tempLevel4);
				break;
			default:
				int tempLevel2 = MG.getStats()[0] + 1;
				MG.setLevel(tempLevel2);
				break;
			}
		}
		else if(item == Items.restoreMagicMeter20PowerPoints) {
			switch (characterSelect) {
			case 1:
				TN.restoreMagic(20);
				break;
			case 2:
				DB.restoreMagic(20);
				break;
			case 3:
				PL.restoreMagic(20);
				break;
			default:
				MG.restoreMagic(20);
				break;
			}
		}
		else if(item == Items.restoreMagicMeter50PowerPoints) {
			switch (characterSelect) {
			case 1:
				MG.restoreMagic(50);
				break;
			case 2:
				DB.restoreMagic(50);
				break;
			case 3:
				PL.restoreMagic(50);
				break;
			default:
				MG.restoreMagic(50);
				break;
			}
		}
		else if(item == Items.restoreMagicMeter75PowerPoints) {
			switch (characterSelect) {
			case 1:
				TN.restoreMagic(75);
				break;
			case 2:
				DB.restoreMagic(75);
				break;
			case 3:
				PL.restoreMagic(75);
				break;
			default:
				MG.restoreMagic(75);
				break;
			}
		}
		else if(item == Items.restoreMagicMeterMAX) {
			switch (characterSelect) {
			case 1:
				TN.restoreMagic(777);//praise Jesus number
				break;
			case 2:
				DB.restoreMagic(777);//praise Jesus number
				break;
			case 3:
				PL.restoreMagic(777);//praise Jesus number
			default:
				MG.restoreMagic(777);//praise Jesus number
				break;
			}
		}
		else if(item == Items.revive) {
			switch (characterSelect) {
			case 1:
				TN.revive();
				break;
			case 2:
				DB.revive();
				break;
			case 3:
				PL.revive();
			default:
				MG.revive();
				break;
			}
		}
		else if(item == Items.superRevive) {
			switch (characterSelect) {
			case 1:
				TN.revive();
				TN.restoreHP(20000000);
				break;
			case 2:
				DB.revive();
				DB.restoreHP(20000000);
			case 3:
				PL.revive();
				PL.restoreHP(20000000);
			default:
				MG.revive();
				MG.restoreHP(20000000);
				break;
			}
		}
		else {
			TN.restoreHP(0);
		}
	}
	
	private void resetCharacterHasMovedArray() {
		for(int x=0; x > 4; x++) {
			characterHasMadeMove[x] = false;
		}
	}
	
	/**
	 * A method that returns the current turn of the current battle
	 * @return returns a short of the current turn
	 */
	public short getTurnCounter() {
		return turnCounter;
	}
	
	/**
	 * A method that returns the enemy tier of the current battle
	 * @return returns byte representing enemy tier level
	 */
	
	public byte getEnemyTier() {
		return enemyTier;
	}
	
	/**
	 * A method that returns an array representing which characters are still alive
	 * @return array representing which allies are alive
	 */
	
	public boolean[] getAlliesAlive() {
		return alliesAlive;
	}
	
	/**
	 * A method that returns an array representing which enemies are still alive
	 * @return array representing which enemies are alive
	 */
	
	public boolean[] getAiAlive() {
		return aiAlive;
	}
	
}
