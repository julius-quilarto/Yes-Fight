/**
 * Button: https://www.javatpoint.com/java-jbutton
 * Adding Text: https://www.javatpoint.com/java-jlabel, https://www.youtube.com/watch?v=FR2UptJyaSM
 * Font: https://www.javatpoint.com/java-font
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BattleScreenDriver {
	
	public static void main(int xPos, int yPos) {
		Moves[] tnMoves = new Moves[] {Moves.shootGun, Moves.pummel,Moves.shadowPunch,Moves.rockThrow};
		Spells[] tnsps = new Spells[] {Spells.TanukiEvade,Spells.TanukiTeamEvade,Spells.LookKawaii,Spells.none};
		ThiccTanuki TN = new ThiccTanuki(2000,20,Armor.kevlar,Weapons.colt1911,199,tnMoves,tnsps);
		
		//initializes the anthro dragon's move and spell set
		Moves[] dbMoves = new Moves[] {Moves.dragonFist,Moves.fireFist,Moves.swordSlash,Moves.dragonBreath};
		Spells[] dbsps = new Spells[] {Spells.DragonSurge,Spells.fissure, Spells.none,Spells.none};
		RippedDragonborn db = new RippedDragonborn(1600,20,Armor.MedievalArmor,Weapons.sword,199,dbMoves,dbsps);
		
		//initializes the Magical Girl's move and spell set
		Moves[] mgMoves = new Moves[] {Moves.etherealBlade,Moves.moonKick, Moves.megabuster, Moves.shockingFist};
		Spells[] mgsps = new Spells[] {Spells.healSpell,Spells.teamHeal,Spells.tidalWave,Spells.FairyGlitter};
		MagicalGirl mg = new MagicalGirl(1500,20, Armor.none, Weapons.none, 199, mgMoves, mgsps);
		
		Player pl = new Player(1800, 20, Armor.fursuit, Weapons.techSword, 199);
		
		byte numOfEnemies = 4;//this only matters for non boss fights where there can be between 1 and 4 enemies
		byte enemyTier = 1;//4 = Kitsune boss fight; 1 = tier one enemy battle
		byte[] enemyLevels = new byte[] {5,2,4,7};//says what level each enemy will be in the order of enemy1, enemy2, enemy3,enemy4
		short[] enemyIDs = new short[] {0,1,0,2};//contains the ID of each enemy for a tier so the battle manager knows which enemies ot spawn
		Inventory inv = new Inventory();
		inv.initializeInventory();
		int[] taniaDecision = {1, 1, 1};
		int[] dargoniDecision = {1, 1, 1};
		int[] selenaDecision = {1, 1, 1};
		int[] yourNameDecision = {1, 1, 1};
		BattleManager bm = new BattleManager(TN,db,mg,pl,numOfEnemies,enemyTier,enemyLevels,enemyIDs,inv);
		TaniaDecisionMaker.main(taniaDecision, dargoniDecision, selenaDecision, yourNameDecision, bm, xPos, yPos);
	}
}
