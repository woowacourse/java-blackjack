package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.RealDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public class HitTest {

	private Cards cards;

	@BeforeEach
	void init_cards() {
		cards = new Cards();
		cards.addCard(Card.of(Denomination.EIGHT, Suit.CLOVER));
		cards.addCard(Card.of(Denomination.EIGHT, Suit.CLOVER));
	}

	@Test
	void hit_state() {
		//given
		//when
		State state = InitialTurn.createState(cards);
		//then
		assertThat(state).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("Hit상태에서 수익을 계산하면 에러 발생")
	void occur_error_hit_state_profit_() {
		//given
		Money money = new Money(10000);
		State state = InitialTurn.createState(cards);
		Dealer dealer = new Dealer(new RealDeck());
		//when
		//then
		assertThatThrownBy(() -> state.calculateProfit(money, dealer))
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("Running 중이 맞는지 확인")
	void not_running() {
		//given
		State state = InitialTurn.createState(cards)
			.draw(Card.of(Denomination.FOUR, Suit.DIAMOND));
		//when
		boolean isRunning = state.isRunning();
		//given
		assertThat(isRunning).isTrue();
	}
}
