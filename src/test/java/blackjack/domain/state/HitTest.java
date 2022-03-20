package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class HitTest {

	@Test
	@DisplayName("draw를 실행했을 때 21 이하이면 Hit 상태이다.")
	void hit_hit() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Hit hit = new Hit(cards);

		State state = hit.draw(Card.valueOf(Denomination.NINE, Suit.CLOVER));

		assertThat(state).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("draw를 실행했을 때 21 초과이면 Bust 상태이다.")
	void hit_bust() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Hit hit = new Hit(cards);

		State state = hit.draw(Card.valueOf(Denomination.TEN, Suit.CLOVER));

		assertThat(state).isInstanceOf(Bust.class);
	}

	@Test
	@DisplayName("stay를 실행하면 Stay 상태이다.")
	void hit_stay() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Hit hit = new Hit(cards);

		assertThat(hit.stay()).isInstanceOf(Stay.class);
	}

	@Test
	@DisplayName("턴을 계속 진행할 수 있다.")
	void is_finished() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Hit hit = new Hit(cards);

		assertThat(hit.isFinished()).isFalse();
	}

	@Test
	@DisplayName("Hit 상태는 결과를 비교하면 에러가 발생한다.")
	void match() {
		Cards standardCards = new Cards(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.CLOVER),
			Card.valueOf(Denomination.NINE, Suit.CLOVER)));
		Hit standard = new Hit(standardCards);

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.SPADE),
			Card.valueOf(Denomination.NINE, Suit.SPADE)));
		Stay opposite = new Stay(oppositeCards);

		assertThatThrownBy(() -> standard.match(opposite))
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("턴이 종료되지 않았습니다.");
	}
}
