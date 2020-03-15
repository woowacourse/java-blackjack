package domain.gamer;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class DealerGameResult {
	int win;
	int lose;
	int draw;

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
