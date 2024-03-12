package blackjack.domain.card;

public record Card(CardShape cardShape, CardNumber cardNumber) {

	public boolean isAce() {
		return cardNumber == CardNumber.ACE;
	}

	public int getNumber() {
		return cardNumber.getValue();
	}
}
