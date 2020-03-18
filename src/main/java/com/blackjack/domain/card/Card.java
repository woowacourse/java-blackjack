package com.blackjack.domain.card;

import java.util.Objects;

public class Card {
	private final CardNumber cardNumber;
	private final CardSymbol cardSymbol;

	public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
		validateNull(cardNumber, cardSymbol);
		this.cardNumber = cardNumber;
		this.cardSymbol = cardSymbol;
	}

	private void validateNull(CardNumber cardNumber, CardSymbol cardSymbol) {
		if (Objects.isNull(cardNumber) || Objects.isNull(cardSymbol)) {
			throw new IllegalArgumentException("카드의 번호와 문양은 null을 가질 수 없습니다.");
		}
	}

	public int getNumber() {
		return cardNumber.getNumber();
	}

	public boolean isAce() {
		return cardNumber.isAce();
	}

	@Override
	public String toString() {
		return cardNumber.toString() + cardSymbol.toString();
	}
}
