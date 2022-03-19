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

public class BlackJackTest {

	private Cards cards;

	@BeforeEach
	void init_cards() {
		cards = new Cards();
		cards.addCard(Card.of(Denomination.ACE, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.JACK, Suit.DIAMOND));
	}

	@Test
	@DisplayName("블랙잭인 상태일 때 수익이 1.5배가 되는지 확인")
	void blackjack_profit() {
		//given
		State state = InitialTurn.createState(cards);
		Money money = new Money(10000);
		//when
		Money profit = state.calculateProfit(money, new Dealer(new RealDeck()));
		//then
		assertThat(profit).isEqualTo(new Money(15000));
	}

	@Test
	@DisplayName("Running 중이 아닌지 확인")
	void not_running() {
		//given
		State state = InitialTurn.createState(cards);
		//when
		boolean isRunning = state.isRunning();
		//given
		assertThat(isRunning).isFalse();
	}
}
