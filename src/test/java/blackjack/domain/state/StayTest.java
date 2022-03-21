package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.MatchResult;

class StayTest {

	@Test
	@DisplayName("draw를 실행하면 에러가 발생한다.")
	void draw() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.NINE, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay stay = new Stay(cards);

		assertThatThrownBy(() -> stay.draw(Card.valueOf(Denomination.ACE, Suit.SPADE)))
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("턴이 종료된 상태입니다.");
	}

	@Test
	@DisplayName("stay를 실행하면 에러가 발생한다.")
	void stay() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.NINE, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay stay = new Stay(cards);

		assertThatThrownBy(() -> stay.stay())
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("턴이 종료된 상태입니다.");
	}

	@Test
	@DisplayName("턴을 더이상 진행할 수 없다.")
	void is_finished() {
		Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.NINE, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay stay = new Stay(cards);

		assertThat(stay.isFinished()).isTrue();
	}

	@Test
	@DisplayName("상대가 blackjack이면 결과는 LOSE이다.")
	void match_blackjack() {
		Cards standardCards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay standard = new Stay(standardCards);

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.SPADE),
			Card.valueOf(Denomination.JACK, Suit.SPADE)));
		BlackJack opposite = new BlackJack(oppositeCards);

		assertThat(standard.match(opposite)).isEqualTo(MatchResult.LOSE);
	}

	@Test
	@DisplayName("상대가 bust이면 결과는 WIN이다.")
	void match_bust() {
		Cards standardCards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay standard = new Stay(standardCards);

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.SPADE),
			Card.valueOf(Denomination.JACK, Suit.SPADE),
			Card.valueOf(Denomination.QUEEN, Suit.SPADE)));
		Bust opposite = new Bust(oppositeCards);

		assertThat(standard.match(opposite)).isEqualTo(MatchResult.WIN);
	}

	@Test
	@DisplayName("상대가 stay이고 카드 점수가 더 높으면 WIN이다.")
	void match_stay() {
		Cards standardCards = new Cards(Arrays.asList(Card.valueOf(Denomination.FOUR, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay standard = new Stay(standardCards);

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.THREE, Suit.SPADE),
			Card.valueOf(Denomination.JACK, Suit.SPADE)));
		Stay opposite = new Stay(oppositeCards);

		assertThat(standard.match(opposite)).isEqualTo(MatchResult.WIN);
	}

	@Test
	@DisplayName("상대가 stay이고 카드 점수가 더 낮으면 LOSE이다.")
	void match_stay1() {
		Cards standardCards = new Cards(Arrays.asList(Card.valueOf(Denomination.TWO, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay standard = new Stay(standardCards);

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.THREE, Suit.SPADE),
			Card.valueOf(Denomination.JACK, Suit.SPADE)));
		Stay opposite = new Stay(oppositeCards);

		assertThat(standard.match(opposite)).isEqualTo(MatchResult.LOSE);
	}

	@Test
	@DisplayName("상대가 stay이고 카드 점수가 같으면 DRAW이다.")
	void match_stay2() {
		Cards standardCards = new Cards(Arrays.asList(Card.valueOf(Denomination.FOUR, Suit.CLOVER),
			Card.valueOf(Denomination.JACK, Suit.CLOVER)));
		Stay standard = new Stay(standardCards);

		Cards oppositeCards = new Cards(Arrays.asList(Card.valueOf(Denomination.FOUR, Suit.SPADE),
			Card.valueOf(Denomination.JACK, Suit.SPADE)));
		Stay opposite = new Stay(oppositeCards);

		assertThat(standard.match(opposite)).isEqualTo(MatchResult.DRAW);
	}
}
