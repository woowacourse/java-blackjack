package blackjack.domain.state;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Ready 테스트")
class ReadyTest {

	@Test
	@DisplayName("한장 드로우 한 경우 레디 상테를 반환")
	void draw_One_Card_Still_Ready() {
		State state = new Ready();
		assertThat(state.draw(CLOVER_ACE)).isInstanceOf(Ready.class);
	}

	@Test
	@DisplayName("두장 드로우 한 경우 히트 상테를 반환")
	void draw_Two_Cards_To_Hit() {
		State state = new Ready().draw(CLOVER_ACE);

		assertThat(state.draw(CLOVER_TWO)).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("두장 드로우 한 경우 블랙잭 상테를 반환")
	void draw_Two_Cards_To_Blackjack() {
		State state = new Ready().draw(CLOVER_ACE);

		assertThat(state.draw(CLOVER_KING)).isInstanceOf(Blackjack.class);
	}

	@Test
	@DisplayName("현재 상태에서 스테이시 예외 발생")
	void stay() {
		State state = new Ready();

		assertThatThrownBy(state::stay)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("현재 상태에서는 스테이할 수 없습니다.");
	}

	@Test
	@DisplayName("아직 게임 진행이 안 끝났다는 것을 확인")
	void is_Finished() {
		State state = new Ready();

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	@DisplayName("게임 수익 계산 요청시 예외 발생")
	void profit() {
		State state = new Ready();

		assertThatThrownBy(() -> state.profit(new Cards(), new Money(10000)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("아직 게임이 끝나지 않아서 수익을 계산할 수 없습니다.");
	}
}
