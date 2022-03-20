package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public class StayTest {

	private Cards cards;
	private Dealer dealer;
	private final Money money = new Money(10000);

	@BeforeEach
	void init_cards() {
		cards = new Cards();
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
	}

	@BeforeEach
	void init_dealer() {
		dealer = new Dealer(new MockDeck(List.of(Card.of(Denomination.NINE, Suit.DIAMOND),
			Card.of(Denomination.ACE, Suit.DIAMOND))));
	}

	@Test
	void stay_state() {
		//given
		State state = InitialTurn.createState(cards);
		//when
		final State newState = state.stay();
		//given
		assertThatThrownBy(() -> newState.draw(Card.of(Denomination.TWO, Suit.DIAMOND)))
			.isInstanceOf(IllegalStateException.class);
	}

	@Nested
	@DisplayName("스테이 상태에서 수익이")
	class StayStateProfitTest {
		@Test
		@DisplayName("플레이어가 패배했을때 -1배가 되는지 확인")
		void profit_player_lose() {
			//given
			State playerState = InitialTurn.createState(cards)
				.stay();
			//when
			double profitRate = playerState.profitRate(dealer);
			//then
			assertThat(profitRate).isEqualTo(-1);
		}

		@Test
		@DisplayName("플레이어가 승리했을때 1배가 되는지 확인")
		void profit_player_win() {
			//given
			State playerState = InitialTurn.createState(cards);
			playerState = playerState.draw(Card.of(Denomination.THREE, Suit.DIAMOND))
				.stay();
			//when
			double profitRate = playerState.profitRate(dealer);
			//then
			assertThat(profitRate).isEqualTo(1);
		}

		@Test
		@DisplayName("무승부일때 0배가 되는지 확인")
		void profit_push() {
			//given
			State playerState = InitialTurn.createState(cards);
			playerState = playerState.draw(Card.of(Denomination.TWO, Suit.DIAMOND))
				.stay();
			//when
			double profitRate = playerState.profitRate(dealer);
			//then
			assertThat(profitRate).isEqualTo(0);
		}
	}

	@Test
	@DisplayName("Running 중이 아닌지 확인")
	void not_running() {
		//given
		State state = InitialTurn.createState(cards)
			.stay();
		//when
		boolean isRunning = state.isRunning();
		//given
		assertThat(isRunning).isFalse();
	}

}
