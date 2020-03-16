package domain.gamer;

/**
 *    Dealer의 점수를 가지는 클래스
 *
 *   @author ParkDooWon
 */
public class DealerGameResult {
	private int win;
	private int lose;
	private int draw;

	DealerGameResult(int win, int lose, int draw) {
		this.win = win;
		this.lose = lose;
		this.draw = draw;
	}

	public int getWin() {
		return win;
	}

	public int getLose() {
		return lose;
	}

	public int getDraw() {
		return draw;
	}
}
