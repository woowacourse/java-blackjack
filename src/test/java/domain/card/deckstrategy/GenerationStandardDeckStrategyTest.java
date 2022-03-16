package domain.card.deckstrategy;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GenerationStandardDeckStrategyTest {

	@Test
	@DisplayName("카드가 정상적으로 52개가 생성되는지 테스트")
	void generateCardsForBlackJack() {
		GenerationStandardDeckStrategy strategy = new GenerationStandardDeckStrategy();
		assertThat(strategy.generateCardsForBlackJack().size()).isEqualTo(52);
	}
}
