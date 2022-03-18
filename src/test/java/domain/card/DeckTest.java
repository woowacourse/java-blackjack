package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

	@Test
	@DisplayName("덱이 정상적으로 카드를 드로우하는지 테스트")
	void draw() {
		Deck deck = Deck.generateDeck();
		assertThat(deck.draw() instanceof Card).isTrue();
	}

}
