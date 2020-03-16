package domain.gamer;

/**
 *    게임 플레이어 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Player extends Gamer {
	private PlayerGameResult playerGameResult;

	public Player(String name) {
		super(name);
	}

	public void findResult(int dealerScore) {
		playerGameResult = PlayerGameResult.matchResult(dealerScore, this.scoreHands());
	}

	public PlayerGameResult getPlayerGameResult() {
		return playerGameResult;
	}
}
