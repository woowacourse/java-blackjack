package blackjack.domain.card;

public record Card(Shape shape, Number number) {

	public boolean isAce() {
		return number == Number.ACE;
	}

	public int getNumber() {
		return number.getValue();
	}
}
