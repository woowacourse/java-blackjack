package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.factory.CardMockFactory;

@DisplayName("Card 테스트")
class CardTest {

	private final Card card = CardMockFactory.of("A클로버");

	@Test
	@DisplayName("카드의 정보가 제대로 반환되는지 확인")
	void check_Card_Information() {
		final String expectedInformation = "A클로버";
		final String actualInformation = card.getInformation();

		assertThat(actualInformation).isEqualTo(expectedInformation);
	}

	@Test
	@DisplayName("카드의 점수가 제대로 반환되는지 확인")
	void check_Card_Score() {
		final int actualScore = card.getScore();

		assertThat(actualScore).isEqualTo(1);
	}

}
