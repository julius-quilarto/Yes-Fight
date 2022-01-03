/*
 * A class that stores the information on tier 1 enemies which is the lowest tier of enemies.
 * All static variables ending in 'Type' returns an Elementals enum of the enemy's type
 * All static variables ending in BS return an an enemy's base stats as an integer array in the order of [HP,Atk,Def,Spec,Base_EXP,ID_NUMBER]
 * All static variables ending in MS will return an enemy's move set as an enum array of type Moves in the format of [move1,move2]
 * All static variables ending in Name are the name of the enemy.
 * All static variables ending in Disc are the description of the enemy.
 * All static variables ending in ID are the ID numbers of the enemy
 * Version 1.1
 */
public class Tier1EnemyList {
	
	//test enemy stats
	static final String testName = "Test Enemy";
	static final Elementals testType = Elementals.neutral; 
	static final int[] testBS = new int[] {50,30,40,60,20,0};
	static final Moves[] testMS = new Moves[] {Moves.scratch,Moves.pummel};
	static final String testDisc = "A test enemy";
	static final short testID = 0;
	
	//placeholder enemy
	static final String placeholderName = "Placeholder";
	static final Elementals placeholderType = Elementals.neutral;
	static final int[] placeholderBS = new int[] {1,0,1,1,0,1};
	static final Moves[] placeholderMS = new Moves[] {Moves.placeholder,Moves.placeholder};
	static final short placeholderID = 1;
	
	//a glitch enemy that appears if there's something wrong with the enemyID number in the battle manager(also a reference to the glitch pokemon from Red, Blue, Green and Yellow)
	static final String MissingnoName = "Missingno";
	static final Elementals MissingnoType = Elementals.tech;
	static final int[] MissingnoBS = new int[] {33,136,1,6,10,2};
	static final Moves[] MissingnoMS = new Moves[] {Moves.missingno,Moves.shootGun};
	static final String MissingnoDisc = "This enemy is a glitch and will corrupt your hall of fame data.";
	static final short MissingnoID = 2;
	
	//actual enemies
	static final String turretName = "Turret";
	static final Elementals turretType = Elementals.tech;
	static final int[] turretBS = new int[] {25,80,25,1,5,3};
	static final Moves[] turretMS = new Moves[] {Moves.shootGun,Moves.shootGun};
	static final String turretDisc = "These turrets are the basic sentry turrets with no rockets and can be defeated by knocking them over.";
	static final short turretID = 3;
	
	static final String purpleHornetName = "Purple Hornet";
	static final Elementals purpleHornetType = Elementals.nature;
	static final int[] purpleHornetBS = new int[] {35,65,45,25,4,4};
	static final Moves[] purpleHornetMS = new Moves[] {Moves.sting,Moves.tackle};
	static final String purpleHornetDisc = "This hornet comes from the Nymph jungle and is very aggressive.";
	static final short purpleHornetID = 4;
	
	static final String dragonLizardName = "Dragon lizard";
	static final Elementals dragonLizardType = Elementals.dragon;
	static final int[] dragonLizardBS = new int[] {35,85,85,25,7,5};
	static final Moves[] dragonLizardMS = new Moves[] {Moves.dragonFist,Moves.dragonBreath};
	static final String dragonLizardDisc = "This small lizard can fight like a dragon.";
	static final short dragonLizardID = 5;
	
	static final String hellHoundName = "Hell Hound";
	static final Elementals hellHoundType = Elementals.dark;
	static final int[] hellHoundBS = new int[] {45,75,25,75,6,6};
	static final Moves[] hellHoundMS = new Moves[] {Moves.fireBreath,Moves.bite};
	static final String hellHoundDisc = "This is a corrupted hound that is filled with nothing but hate for its enemy and lacks compassion.";
	static final short hellHoundID = 6;
	
	static final String killerBirdName = "Killer Bird";
	static final Elementals killerBirdType = Elementals.air;
	static final int[] killerBirdBP = new int[] {25,65,25,25,3,7};
	static final Moves[] killerBirdMS = new Moves[] {Moves.windGust,Moves.scratch};
	static final String killerBirdDisc = "This is one angry bird.";
	static final short killerBirdID = 7;
	
	static final String smallOrcName = "Small Orc";
	static final Elementals smallOrcType = Elementals.dark;
	static final int[] smallOrcBS = new int[] {80,78,50,10,7,8};
	static final Moves[] smallOrcMS = new Moves[] {Moves.pummel,Moves.rockThrow};
	static final String smallOrcDisc = "This orc is small and dumb.";
	static final short smallOrcID = 8;

}
