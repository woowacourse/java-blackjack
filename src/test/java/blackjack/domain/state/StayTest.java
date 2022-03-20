package blackjack.domain.state;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_NINE;
import static blackjack.util.Fixtures.CLOVER_QUEEN;
import static blackjack.util.Fixtures.CLOVER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Stay 테스트")
class StayTest {

	@Test
	@DisplayName("현재 상태에서 드로우시 예외 발생")
	void draw() {
		State state = new Stay(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN)));

		assertThatThrownBy(() -> state.draw(CLOVER_ACE))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("현재 상태에서는 더이상 드로우 할 수 없습니다.");
	}

	@Test
	@DisplayName("현재 상태에서 스테이시 예외 발생")
	void stay() {
		State state = new Stay(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN)));

		assertThatThrownBy(state::stay)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("현재 상태에서는 스테이할 수 없습니다.");
	}

	@Test
	@DisplayName("게임 진행이 끝났다는 것을 확인")
	void is_Finished() {
		State state = new Stay(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN)));

		assertThat(state.isFinished()).isTrue();
	}

	@Test
	@DisplayName("이겼을 경우 수익 계산 확인")
	void profit_Victory() {
		State state = new Stay(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN)));
		Cards bustCards = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO));
		Cards lowerScoreCards = new Cards(List.of(CLOVER_KING, CLOVER_NINE));

		assertAll(
				() -> assertThat(state.profit(bustCards, new Money(10000))).isEqualTo(new Money(10000)),
				() -> assertThat(state.profit(lowerScoreCards, new Money(10000))).isEqualTo(new Money(10000))
		);
	}

	@Test
	@DisplayName("졌을 경우 수익 계산 확인")
	void profit_Defeat() {
		State state = new Stay(new Cards(List.of(CLOVER_KING, CLOVER_NINE)));
		Cards blackjackCards = new Cards(List.of(CLOVER_KING, CLOVER_ACE));
		Cards higherScoreCards = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN));

		assertAll(
				() -> assertThat(state.profit(blackjackCards, new Money(10000))).isEqualTo(new Money(-10000)),
				() -> assertThat(state.profit(higherScoreCards, new Money(10000))).isEqualTo(new Money(-10000))
		);
	}

	@Test
	@DisplayName("무승부일 경우 따른 수익 계산 확인")
	void profit_Tie() {
		State state = new Stay(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN)));
		Cards sameScoreCards = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN));

		assertThat(state.profit(sameScoreCards, new Money(10000))).isEqualTo(new Money(0));
	}
}
