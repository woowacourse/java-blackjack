package domain.card.deckstrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class GeneralGenerationDeckStrategy implements GenerationDeckStrategy {

	@Override
	public Queue<Card> generateCardsForBlackJack() {
		List<Card> cards = new ArrayList<>();
		Arrays.stream(Rank.values()).forEach(rank -> {
			Arrays.stream(Suit.values()).map(suit -> new Card(rank, suit)).forEach(cards::add);
		});
		Collections.shuffle(cards);
		return new LinkedList<>(cards);
	}
}
