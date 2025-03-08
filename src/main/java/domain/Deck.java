package domain;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import constant.Suit;

public class Deck {
	private final Queue<Card> deck;

	public Deck(final Queue<Card> deck) {
		validateDuplicateCard(deck);
		this.deck = new ArrayDeque<>(deck);
	}

	public static Deck createShuffledDeck() {
		final List<Card> cards = Arrays.stream(Rank.values())
			.flatMap(Deck::makeCards)
			.collect(Collectors.toList());
		Collections.shuffle(cards);
		return new Deck(new ArrayDeque<>(cards));
	}

	private static Stream<Card> makeCards(final Rank rank) {
		return Arrays.stream(Suit.values())
			.map(emblem -> new Card(rank, emblem));
	}

	private void validateDuplicateCard(final Queue<Card> deck) {
		final HashSet<Card> notDuplicateCards = new HashSet<>(deck);
		if (deck.size() != notDuplicateCards.size()) {
			throw new IllegalArgumentException("덱에는 중복된 카드가 들어올 수 없습니다!");
		}
	}

	public Card pickCard() {
		if (deck.isEmpty()) {
			throw new NullPointerException("덱에 남아있는 카드가 없습니다.");
		}
		return deck.poll();
	}
}
