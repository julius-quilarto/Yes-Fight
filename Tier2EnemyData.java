/**
 * These enemies are tier two enemies that have stats closer to the playable characters and better movesets than tier 1 enemies
 * Variables ending in DESC are enemy description
 * Varaibles ending in NAME are enemy names
 * Varaibles ending in T are enemy types (all tier two enemies are dual types althogh mono types just have their second type 
 * set to Elementals.none)
 * Varaibles ending in BS are enemy base stats [HP,Atk,Def,Spec,Agility,EXP] (base stat total 350 and 380)
 * Varaibles ending in M are enemy Movesets [move1,move2,move3,move4]
 * Varaibles ending in S is the enemy spell
 * Varaibles ending in W are enemy weapons
 * Varaibles ending in A are enemy abilities
 * Varaibles ending in ID are enemy ID numbers
 * Varaibles ending in TEXT are enemy flavor texts
 * @author Alexander
 * @version 1.0
 *
 */
public class Tier2EnemyData {
	
	//Elf Battle information
	static final String elfNAME = "Elf";
	static final Elementals[] elfT = new Elementals[] {Elementals.neutral,Elementals.none};
	static final short[] elfBS = new short[] {75,80,95,100,0,50};
	static final Moves[] elfM = new Moves[] {Moves.shootGun,Moves.swordSlash,Moves.pummel,Moves.swordSlash};
	static final Spells elfS = Spells.none;
	static final Weapons elfW = Weapons.ElvenCrossbow;
	static final Abilities elfA = Abilities.noAbility;
	static final String elfDESC = "A haughty creature with long pointy ears.";
	static final String[] elfTEXT = new String[] {
			"ELF: Elves were always the superior race.",
			"ELF: All will bow before queen Galadriel."
	};
	static final byte elfID = 21;
	
	//Skeleton Battle information
	static final String skeletonNAME = "Skeleton";
	static final Elementals[] skeletonT = new Elementals[] {Elementals.ethereal,Elementals.earth};
	static final short[] skeletonBS = new short[] {65,105,65,105,10,55};
	static final Moves[] skeletonM = new Moves[] {Moves.boneClub,Moves.etherealBlade,Moves.bite,Moves.boneClub};
	static final Spells skeletonS = Spells.none;
	static final Weapons skeletonW = Weapons.ElvenCrossbow;
	static final Abilities skeletonA = Abilities.noAbility;
	static final String skeletonDESC = "A spooky scary skeleton.";
	static final String[] skeletonTEXT = new String[] {
			"SKELETON: NYEH HEH HEH!!.",
			"SKELETON: 3 spooky 5 you."
	};
	static final byte skeletonID = 22;
	
	//graphicsManEx Battle information
		static final String graphicsManExNAME = "Graphics Man";
		static final Elementals[] graphicsManExT = new Elementals[] {Elementals.tech,Elementals.electric};
		static final short[] graphicsManExBS = new short[] {85,100,85,100,4,64};
		static final Moves[] graphicsManExM = new Moves[] {Moves.thunderbolt,Moves.shockingFist,Moves.swordSlash,Moves.megabuster};
		static final Spells graphicsManExS = Spells.BinaryBeam;
		static final Weapons graphicsManExW = Weapons.techSword;
		static final Abilities graphicsManExA = Abilities.noAbility;
		static final String graphicsManExDESC = "A spooky scary graphicsManEx.";
		static final String[] graphicsManExTEXT = new String[] {
				"GRAPHICS MAN: I swear that electric rat was the real culprit!.",
				"GRAPHICS MAN: It's time for you to stop rendering."
		};
		static final byte graphicsManExID = 23;
		
		//glitchedManNeo Battle information (this enemy is only supposed to spawn if an invalid ID is in the constructor
		//for tier 2 enemies. It's basically a secret boss battle for data miners and modders or an invalid ID was sent for
		//tier 2 enemies of the constructor of the Battle Manger class)
		static final String glitchedManNeoNAME = "MissignNO NEO";
		static final Elementals[] glitchedManNeoT = new Elementals[] {Elementals.ethereal,Elementals.earth};
		static final short[] glitchedManNeoBS = new short[] {1,190,9,190,95,2048};
		static final Moves[] glitchedManNeoM = new Moves[] {Moves.missingno,Moves.megabuster,Moves.hyperlinkBlocked,Moves.shootGun};
		static final Spells glitchedManNeoS = Spells.NEObuster;
		static final Weapons glitchedManNeoW = Weapons.lever_action_rifle_45_70;
		static final Abilities glitchedManNeoA = Abilities.TanukiSense;
		static final String glitchedManNeoDESC = "An enemy that spawns if an invalid enemy ID is put into the system.";
		static final String[] glitchedManNeoTEXT = new String[] {
				"MISSINGNO NEO: BEHOLD THE POWER OF GAME BREAKING GLITCHES!!",
				"MISSINGNO NEO: TRUE PROGRAMS ARE MADE IN Z80 ASSEMBLY!"
		};
		static final byte glitchedManNeoID = 24;
		
		//dobermanFurry Battle information
		static final String dobermanFurryNAME = "Doberman Furry";
		static final Elementals[] dobermanFurryT = new Elementals[] {Elementals.dark,Elementals.fire};
		static final short[] dobermanFurryBS = new short[] {83,110,82,110,5,78};
		static final Moves[] dobermanFurryM = new Moves[] {Moves.boneClub,Moves.etherealBlade,Moves.bite,Moves.fireBreath};
		static final Spells dobermanFurryS = Spells.KitsuneFire;
		static final Weapons dobermanFurryW = Weapons.ElvenCrossbow;
		static final Abilities dobermanFurryA = Abilities.noAbility;
		static final String dobermanFurryDESC = "An anthropomorphic Domerman that has a bloodlust against everyone.";
		static final String[] dobermanFurryTEXT = new String[] {
				"DOBERMAN FURRY: *snarl*",
				"DOBERMAN FURRY: *hate*"
		};
		static final byte dobermanFurryID = 25;
		
		//grifon Battle information
		static final String grifonNAME = "Grifon";
		static final Elementals[] grifonT = new Elementals[] {Elementals.air,Elementals.none};
		static final short[] grifonBS = new short[] {85,90,80,80,15,67};
		static final Moves[] grifonM = new Moves[] {Moves.windGust,Moves.scratch,Moves.bite,Moves.sting};
		static final Spells grifonS = Spells.tornado;
		static final Weapons grifonW = Weapons.ElvenCrossbow;
		static final Abilities grifonA = Abilities.noAbility;
		static final String grifonDESC = "A grifon with big talons.";
		static final String[] grifonTEXT = new String[] {
				"GRIFON: *up to no good*",
				"GRIFON: *eagle screech*"
		};
		static final byte grifonID = 26;
		
		//Naiad Battle information
		static final String naiadNAME = "Naiad";
		static final Elementals[] naiadT = new Elementals[] {Elementals.water,Elementals.ethereal};
		static final short[] naiadBS = new short[] {90,60,100,130,0,83};
		static final Moves[] naiadM = new Moves[] {Moves.naturePower,Moves.frostBite,Moves.boneClub,Moves.frostBite};
		static final Spells naiadS = Spells.tidalWave;
		static final Weapons naiadW = Weapons.ElvenCrossbow;
		static final Abilities naiadA = Abilities.noAbility;
		static final String naiadDESC = "A nature spirit of lakes and streams.";
	    static final String[] naiadTEXT = new String[] {
						"NAIAD: Behold the power of the ocean!",
						"NAIAD: I am the bulkiest of bulky water types."
		};
		static final byte naiadID = 27;
		
		//harpy Battle information
		static final String harpyNAME = "harpy";
		static final Elementals[] harpyT = new Elementals[] {Elementals.air,Elementals.dark};
		static final short[] harpyBS = new short[] {60,118,40,100,12,91};
		static final Moves[] harpyM = new Moves[] {Moves.bite,Moves.dragonBreath,Moves.windGust,Moves.thunderbolt};
		static final Spells harpyS = Spells.hurricane;
		static final Weapons harpyW = Weapons.ElvenCrossbow;
		static final Abilities harpyA = Abilities.noAbility;
		static final String harpyDESC = "An evil creature with bat wings.";
		 static final String[] harpyTEXT = new String[] {
						"HARPY: *screech*",
						"HARPY: *hate*"
		};
		static final byte harpyID = 28;

		
		//thunderDog Battle information
		static final String thunderDogNAME = "Thunder Dog";
		static final Elementals[] thunderDogT = new Elementals[] {Elementals.electric,Elementals.none};
		static final short[] thunderDogBS = new short[] {60,115,60,115,10,81};
		static final Moves[] thunderDogM = new Moves[] {Moves.voltcharge,Moves.thunderbolt,Moves.bite,Moves.voltcharge};
		static final Spells thunderDogS = Spells.KitsuneFire;
		static final Weapons thunderDogW = Weapons.ElvenCrossbow;
		static final Abilities thunderDogA = Abilities.noAbility;
		static final String thunderDogDESC = "An evil creature with bat wings.";
		static final String[] thunderDogTEXT = new String[] {
								"THUNDER DOG: *Howl*",
								"THUNDER DOG: *barking with lightning*"
		};
		static final byte thunderDogID = 29;
		
		//Dryad Battle information(basically a pseudoboss)
		static final String dryadNAME = "Dryad";
		static final Elementals[] dryadT = new Elementals[] {Elementals.nature,Elementals.none};
		static final short[] dryadBS = new short[] {100,20,100,135,5,105};
		static final Moves[] dryadM = new Moves[] {Moves.naturePower,Moves.mudSlide,Moves.shockingFist,Moves.etherealBlade};
		static final Spells dryadS = Spells.NaturePower;
		static final Weapons dryadW = Weapons.none;
		static final Abilities dryadA = Abilities.noAbility;
		static final String dryadDESC = "A powerfule nature spirit that is basically a miniboss.";
		static final String[] dryadTEXT = new String[] {
				"DRYAD: By the power of the forest, I will strike you down!",
				"DRYAD: I am the bulky nature type everyone fears!"
		};
		static final byte dryadID = 30;
		
		//placeholder Battle information(basically a pseudoboss)
		static final String placeholderNAME = "placeholder";
		static final Elementals[] placeholderT = new Elementals[] {Elementals.nature,Elementals.none};
		static final short[] placeholderBS = new short[] {100,20,100,135,5,105};
		static final Moves[] placeholderM = new Moves[] {Moves.naturePower,Moves.mudSlide,Moves.shockingFist,Moves.etherealBlade};
		static final Spells placeholderS = Spells.NaturePower;
		static final Weapons placeholderW = Weapons.none;
		static final Abilities placeholderA = Abilities.noAbility;
		static final String placeholderDESC = "A powerfule nature spirit that is basically a miniboss.";
		static final String[] placeholderTEXT = new String[] {
				"placeholder: By the power of the forest, I will strike you down!",
				"placeholder: I am the bulky nature type everyone fears!"
		};
		static final byte placeholderID = 31;


}
