package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.RealDeck;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;

public class BustTest {

	private Cards cards;

	@BeforeEach
	void init_cards() {
		cards = new Cards();
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
	}

	@Test
	@DisplayName("draw를 했을때 카드의 합이 21을 초과하면 버스트 상태로 되는지 확인")
	void bust_state() {
		//given
		State state = InitialTurn.createState(cards);
		//when
		state = state.draw(Card.of(Denomination.FOUR, Suit.DIAMOND));
		//given
		assertThat(state).isInstanceOf(Bust.class);
	}

	@Test
	@DisplayName("버스트인 상태일 때 수익이 -1배가 되는지 확인")
	void bust_profit() {
		//given
		State state = new Bust(cards);
		//when
		double profitRate = state.profitRate(new Dealer(new RealDeck()));
		//then
		assertThat(profitRate).isEqualTo(-1);
	}

	@Test
	@DisplayName("Running 중이 아닌지 확인")
	void not_running() {
		//given
		State state = InitialTurn.createState(cards)
			.draw(Card.of(Denomination.FOUR, Suit.DIAMOND));
		//when
		boolean isRunning = state.isRunning();
		//given
		assertThat(isRunning).isFalse();
	}
}
