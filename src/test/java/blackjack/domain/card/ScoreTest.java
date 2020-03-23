package blackjack.domain.card;

import blackjack.domain.card.exceptions.ScoreException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static blackjack.domain.testAssistant.TestAssistant.createScore;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

	@DisplayName("of()가 0 이상인 수가 들어왔을 때 인스턴스를 생성하는지 테스트")
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 21, 22, 23, 10000})
	void of_ZeroOrPlusNum_IsNotNull(int input) {
		Score score = createScore(input);

		assertThat(score).isNotNull();
	}

	@DisplayName("of()가 마이너스 숫자가 들어왔을 때 예외를 던지는지 테스트")
	@Test
	void of_MinusNum_ThrowScoreException() {
		assertThatThrownBy(() -> createScore(-1))
				.isInstanceOf(ScoreException.class);
	}

	@DisplayName("add()가 적절하게 더하는지 테스트")
	@ParameterizedTest
	@CsvSource(value = {"0,5,5", "0,0,0", "5,0,5", "1,2,3"})
	void add_TwoScore_ReturnAddedScore(int input1, int input2, int input3) {
		Score a = createScore(input1);
		Score b = createScore(input2);
		Score expect = createScore(input3);

		assertThat(a.add(b)).isEqualTo(expect);
	}
}