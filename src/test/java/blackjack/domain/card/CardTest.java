package blackjack.domain.card;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Card 테스트")
class CardTest {

	private final Card card = CLOVER_ACE;

	@DisplayName("카드가 Ace인지 확인")
	@ParameterizedTest(name = "{index} {displayName} card={0} expected={1}")
	@CsvSource(value = {"ACE, true", "KING, false"})
	void check_Has_Ace(Denomination cardInfo, boolean expectedIsAce) {
		final Card card = new Card(Suit.CLOVER, cardInfo);

		assertThat(card.isAce()).isEqualTo(expectedIsAce);
	}

	@Test
	@DisplayName("카드의 정보가 제대로 반환되는지 확인")
	void check_Card_Information() {
		final String expectedInformation = Denomination.ACE.getName() + Suit.CLOVER.getName();
		final String actualInformation = card.getDenominationAndSuit();

		assertThat(actualInformation).isEqualTo(expectedInformation);
	}

	@Test
	@DisplayName("카드의 점수가 제대로 반환되는지 확인")
	void check_Card_Score() {
		final int actualScore = card.getScore();

		assertThat(actualScore).isEqualTo(1);
	}
}
