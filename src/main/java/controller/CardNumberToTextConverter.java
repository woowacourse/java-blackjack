package controller;

import domain.Rank;

public class CardNumberToTextConverter {
	public static String convert(final Rank cardNumber) {
		return switch (cardNumber) {
			case ACE -> "A";
			case TWO -> "2";
			case THREE -> "3";
			case FOUR -> "4";
			case FIVE -> "5";
			case SIX -> "6";
			case SEVEN -> "7";
			case EIGHT -> "8";
			case NINE -> "9";
			case TEN -> "10";
			case JACK -> "J";
			case QUEEN -> "Q";
			case KING -> "K";
		};
	}
}
