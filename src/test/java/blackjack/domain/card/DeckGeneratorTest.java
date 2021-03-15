package blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {
	@Test
	@DisplayName("덱 생성 테스트")
	void create() {
		CardsGenerator cardsGenerator = new DeckGenerator();
		assertEquals(52, cardsGenerator.makeCards().size());
	}
}