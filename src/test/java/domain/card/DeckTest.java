package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.deckstrategy.GeneralGenerationDeckStrategy;

public class DeckTest {

	@Test
	@DisplayName("덱에서 카드를 드로우 하는 기능")
	void draw() {
		Deck deck = Deck.from(new GeneralGenerationDeckStrategy());
		assertThat(deck.draw() instanceof Card).isTrue();
	}
}
