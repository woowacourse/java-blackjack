package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.factory.CardMockFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Card 테스트")
class CardTest {

	private final Card card = CardMockFactory.of("A클로버");

	@DisplayName("카드가 Ace인지 확인")
	@ParameterizedTest(name = "{index} {displayName} card={0} expected={1}")
	@CsvSource(value = {"A클로버, true", "K클로버, false"})
	void check_Has_Ace(String cardInfo, boolean expectedIsAce) {
		final Card card = CardMockFactory.of(cardInfo);

		assertThat(card.isAce()).isEqualTo(expectedIsAce);
	}

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
