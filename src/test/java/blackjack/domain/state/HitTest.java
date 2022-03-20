package blackjack.domain.state;

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

@DisplayName("Hit 테스트")
class HitTest {

	@Test
	@DisplayName("히트 상태에서 드로우 후 히트 상태를 반환")
	void draw_To_Hit() {
		State state = new Hit(new Cards(List.of(CLOVER_TWO, CLOVER_KING)));

		assertThat(state.draw(CLOVER_TWO)).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("히트 상태에서 드로우 후 버스트 상태를 반환")
	void draw_To_Bust() {
		State state = new Hit(new Cards(List.of(CLOVER_QUEEN, CLOVER_KING)));

		assertThat(state.draw(CLOVER_KING)).isInstanceOf(Bust.class);
	}

	@Test
	@DisplayName("현재 상태에서 스테이 반환")
	void stay() {
		State state = new Hit(new Cards(List.of(CLOVER_TWO, CLOVER_KING)));

		assertThat(state.stay()).isInstanceOf(Stay.class);
	}

	@Test
	@DisplayName("아직 게임 진행이 안 끝났다는 것을 확인")
	void is_Finished() {
		State state = new Hit(new Cards(List.of(CLOVER_TWO, CLOVER_KING)));

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	@DisplayName("게임 수익 계산 요청시 예외 발생")
	void profit() {
		State state = new Hit(new Cards(List.of(CLOVER_TWO, CLOVER_KING)));

		assertThatThrownBy(() -> state.profit(new Cards(), new Money(10000)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("아직 게임이 끝나지 않아서 수익을 계산할 수 없습니다.");
	}
}
