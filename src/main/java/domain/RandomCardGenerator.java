package domain;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Stream;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

public class RandomCardGenerator implements CardGenerator {

	private static final Random RANDOM = new Random();
	private static final Queue<Card> CARDS = new PriorityQueue<>((card1, card2) -> {
		if (RANDOM.nextBoolean()) {
			return 1;
		}
		return -1;
	});

	static {
		Stream.of(Suit.values())
			.flatMap(suit ->
				Stream.of(Denomination.values())
					.map(denomination -> new Card(suit, denomination)))
			.forEach(CARDS::add);
	}

	@Override
	public Card generate() {
		return CARDS.poll();
	}
}
