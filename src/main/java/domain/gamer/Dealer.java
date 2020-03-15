package domain.gamer;

/**
 *   딜러 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Dealer extends Gamer {
	private static final int DRAW_CONDITION = 16;

	private DealerGameResult dealerGameResult;

	public Dealer() {
		super("딜러");
	}

	public boolean canDraw() {
		return scoreHands() <= DRAW_CONDITION;
	}

	public void findResult(int win, int lose, int draw) {
		dealerGameResult = new DealerGameResult(win, lose, draw);
	}

	public DealerGameResult getDealerGameResult() {
		return dealerGameResult;
	}
}
