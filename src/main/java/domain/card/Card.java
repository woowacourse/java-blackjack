package domain.card;

/**
 *    카드를 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Card {
	private Type type;
	private Symbol symbol;

	public Card(Type type, Symbol symbol) {
		this.type = type;
		this.symbol = symbol;
	}

	public int score() {
		return symbol.getScore();
	}

	public boolean isAce() {
		return this.symbol == Symbol.ACE;
	}

	public String shape() {
		return type.getType() + symbol.getSymbol();
	}
}
