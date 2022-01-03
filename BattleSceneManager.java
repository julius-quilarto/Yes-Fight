
public class BattleSceneManager {

	public static void main(int[] taniaDecision, int[] dargoniDecision, int[] selenaDecision, int[] yourNameDecision, BattleManager bm,
			int xPos, int yPos) {
		// TODO Auto-generated method stub
		
		//Insert code where decisions are inserted into battle manager after YourNameDecisionMaker.main()
		bm.makePlayerMoves(taniaDecision, dargoniDecision, selenaDecision, yourNameDecision);
		
		
		
		//Insert code where enemies take damage
		bm.makeEnemiesTakeDamage();
		
		
		
		//Insert code where enemies damage players
		if(bm.getEnemyTier() == 1) {
			bm.makeT1EnemiesMoves();
		}
		else if(bm.getEnemyTier() == 2) {
			bm.makeT2EnemiesMoves();
		}
		else if(bm.getEnemyTier() == 4) {
			bm.makeKitsuneMoves();
		}
		
		
		//If enemies all dead, end battle and go back to exploration
		boolean allEnemiesDead = true;
		for(byte i =0; i<4; i++) {
			if(bm.getAiAlive()[i] == true) {
				allEnemiesDead = false;
				break;
			}
		}
		
		if(allEnemiesDead == true) {
			//Insert Game() with coordinates
			Game game = new Game(xPos, yPos);
		}
		
		
		//If players defeated, game over. Go back to main screen
		else if(bm.getAlliesAlive()[0] == false && bm.getAlliesAlive()[1] == false && bm.getAlliesAlive()[2] == false && bm.getAlliesAlive()[3] == false) {
			MainMenu.main(null);
		}
		
		else {
			//If enemies still alive, loop back to TaniaDecisionMaker and have player make decisions once more
			taniaDecision[0] = 1;
			taniaDecision[1] = 1;
			taniaDecision[2] = 1;
			
			dargoniDecision[0] = 1;
			dargoniDecision[1] = 1;
			dargoniDecision[2] = 1;
			
			selenaDecision[0] = 1;
			selenaDecision[1] = 1;
			selenaDecision[2] = 1;
			
			yourNameDecision[0] = 1;
			yourNameDecision[1] = 1;
			yourNameDecision[2] = 1;
			TaniaDecisionMaker.main(taniaDecision, dargoniDecision, selenaDecision, yourNameDecision, bm, xPos, yPos);
		}
	}

}
