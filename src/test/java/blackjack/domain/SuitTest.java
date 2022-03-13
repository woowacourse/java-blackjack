package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Suit;

@DisplayName("Suit 테스트")
class SuitTest {

	@DisplayName("카드 심볼 이름이 제대로 반환되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} symbol={0} symbolName={1}")
	@CsvSource(value = {"HEART, 하트", "CLOVER, 클로버", "SPADE, 스페이드", "DIAMOND, 다이아몬드"})
	void check_Symbol_Name(final Suit symbol, final String expectedName) {
		final String actualName = symbol.getName();

		assertThat(actualName).isEqualTo(expectedName);
	}
}
