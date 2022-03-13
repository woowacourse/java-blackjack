package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Denomination;

@DisplayName("Denomination 테스트")
class DenominationTest {

	@DisplayName("카드의 해당 점수가 잘 반환 되는지 확인 테스트")
	@ParameterizedTest(name = "{index} {displayName} denomination={0} expectedScore={1}")
	@CsvSource(value = {"ACE, 1", "NINE, 9", "KING, 10", "JACK, 10"})
	void check_Score(final Denomination denomination, final int expectedScore) {
		assertThat(denomination.getScore()).isEqualTo(expectedScore);
	}

	@DisplayName("카드의 해당 이름이 잘 반환되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} denomination={0} expectedName={1}")
	@CsvSource(value = {"ACE, A", "TEN, 10", "KING, K"})
	void check_Name(final Denomination denomination, final String expectedName) {
		assertThat(denomination.getName()).isEqualTo(expectedName);
	}
}
