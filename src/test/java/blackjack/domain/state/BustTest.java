package blackjack.domain.state;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_QUEEN;
import static blackjack.util.Fixtures.CLOVER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bust 테스트")
class BustTest {

	@Test
	@DisplayName("현재 상태에서 드로우시 예외 발생")
	void draw() {
		State state = new Bust(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO)));

		assertThatThrownBy(() -> state.draw(CLOVER_ACE))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("현재 상태에서는 더이상 드로우 할 수 없습니다.");
	}

	@Test
	@DisplayName("현재 상태에서 스테이시 예외 발생")
	void stay() {
		State state = new Bust(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO)));

		assertThatThrownBy(state::stay)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("현재 상태에서는 스테이할 수 없습니다.");
	}

	@Test
	@DisplayName("게임 진행이 끝났다는 것을 확인")
	void is_Finished() {
		State state = new Bust(new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO)));

		assertThat(state.isFinished()).isTrue();
	}

	@Test
	@DisplayName("게임 결과에 따른 수익 계산 확인")
	void profit() {
		Cards bustCards = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO));
		State state = new Bust(bustCards);

		assertThat(state.profit(bustCards, new Money(10000))).isEqualTo(new Money(-10000));
	}
}
