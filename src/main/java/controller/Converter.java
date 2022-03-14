package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.participant.Name;

public class Converter {
	public static List<String> convertNamesToString(List<Name> names) {
		return names.stream().map(Name::getName).collect(Collectors.toList());
	}

	public static List<String> convertCardsToString(List<Card> cards) {
		return cards.stream()
			.map(Card::getCardInfo)
			.collect(Collectors.toList());
	}

	public static List<List<String>> convertCardsListToString(List<List<Card>> cardsList) {
		return cardsList.stream()
			.map(cards -> convertCardsToString(cards))
			.collect(Collectors.toList());
	}
}
