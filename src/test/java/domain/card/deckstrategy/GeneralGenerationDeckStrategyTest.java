package domain.card.deckstrategy;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralGenerationDeckStrategyTest {

	@Test
	@DisplayName("일반적인 카드덱 생성")
	void generateCardsForBlackJack() {
		GeneralGenerationDeckStrategy strategy = new GeneralGenerationDeckStrategy();
		assertThat(strategy.generateCardsForBlackJack().size()).isEqualTo(52);
	}
}
