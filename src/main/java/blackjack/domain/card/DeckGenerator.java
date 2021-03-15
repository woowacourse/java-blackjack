package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator implements CardsGenerator {
	@Override
	public List<Card> makeCards() {
		List<Card> cards = Arrays.stream(CardPattern.values())
			.flatMap(cardPattern -> Arrays.stream(CardNumber.values())
				.map(cardNumber -> new Card(cardPattern, cardNumber)))
			.collect(Collectors.toList());
		Collections.shuffle(cards);
		return cards;
	}
}
