package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class ReadyTest {

	@Test
	@DisplayName("draw를 한번 실행하면 Ready 상태이다.")
	void ready_ready() {
		Ready ready = new Ready();

		assertThat(ready.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER)))
			.isInstanceOf(Ready.class);
	}

	@Test
	@DisplayName("draw를 두번 실행했을 때 21이면 BlackJack 상태이다.")
	void ready_blackjack() {
		Ready ready = new Ready();

		assertThat(ready.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER))
			.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER)))
			.isInstanceOf(BlackJack.class);
	}

	@Test
	@DisplayName("draw를 두번 실행했을 때 21미만이면 Hit 상태이다.")
	void ready_hit() {
		Ready ready = new Ready();

		assertThat(ready.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER))
			.draw(Card.valueOf(Denomination.QUEEN, Suit.CLOVER)))
			.isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("stay를 실행하면 Stay 상태이다.")
	void ready_stay() {
		Ready ready = new Ready();

		assertThat(ready.stay()).isInstanceOf(Stay.class);
	}

	@Test
	@DisplayName("턴을 계속 진행할 수 있다.")
	void is_finished() {
		Ready ready = new Ready();

		assertThat(ready.isFinished()).isFalse();
	}

	@Test
	@DisplayName("Ready 상태는 결과를 비교하면 에러가 발생한다.")
	void match() {
		Ready standard = new Ready();

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.SPADE),
			Card.valueOf(Denomination.NINE, Suit.SPADE)));
		Stay opposite = new Stay(oppositeCards);

		assertThatThrownBy(() -> standard.match(opposite))
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("턴이 종료되지 않았습니다.");
	}
}
