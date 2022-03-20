package blackjack.domain.role;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_FIVE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_NINE;
import static blackjack.util.Fixtures.CLOVER_QUEEN;
import static blackjack.util.Fixtures.CLOVER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Player 테스트")
class PlayerTest {

	@Test
	@DisplayName("플레이어의 드로우 검증")
	void draw() {
		Role player = new Player("player", new Ready().draw(CLOVER_ACE).draw(CLOVER_FIVE));
		Deck deck = new Deck();
		player.draw(deck);
		assertThat(player.getCards().getSize()).isEqualTo(3);
	}

	@Test
	@DisplayName("플레이어의 패 오픈 전략 확인")
	void check_Open_Cards() {
		final State state = new Ready().draw(CLOVER_ACE).draw(CLOVER_FIVE);
		Role player = new Player("player", state);
		List<Card> expectedOpenCards = List.of(CLOVER_ACE, CLOVER_FIVE);
		assertThat(player.openCards()).isEqualTo(expectedOpenCards);
	}

	@Test
	@DisplayName("베팅 금액 나누는 메서드 예외 검증")
	void settle() {
		final State blackjack = new Ready().draw(CLOVER_ACE).draw(CLOVER_KING);
		Role dealer = new Dealer(blackjack, DealerDrawChoice::chooseDraw);

		assertThatThrownBy(() -> dealer.settle(dealer, new Money(1000)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("딜러는 승부를 겨룰 수 없습니다.");
	}

	@DisplayName("배팅 금액을 결과에 따라서 정산하는 메서드 검증")
	@ParameterizedTest(name = "{index} {displayName} expectedValue: {2}")
	@MethodSource("createState")
	void settle(State playerState, State dealerState, int expectedValue) {
		Role player = new Player("player", playerState);
		Role dealer = new Dealer(dealerState, DealerDrawChoice::chooseDraw);

		Money profit = player.settle(dealer, new Money(10000));
		assertThat(profit).isEqualTo(new Money(expectedValue));
	}

	private static Stream<Arguments> createState() {
		final State blackjack = new Ready().draw(CLOVER_ACE).draw(CLOVER_KING);
		final State stayTop = new Ready().draw(CLOVER_KING).draw(CLOVER_NINE).draw(CLOVER_TWO).stay();
		final State stayBottom = new Ready().draw(CLOVER_TWO).draw(CLOVER_FIVE).stay();
		final State bust = new Ready().draw(CLOVER_KING).draw(CLOVER_QUEEN).draw(CLOVER_TWO);

		return Stream.of(
				//Blackjack
				Arguments.of(blackjack, blackjack, 0),
				Arguments.of(blackjack, stayTop, 15000),
				//Stay
				Arguments.of(stayTop, stayTop, 0),
				Arguments.of(stayTop, stayBottom, 10000),
				Arguments.of(stayBottom, stayTop, -10000),
				//Bust
				Arguments.of(bust, stayBottom, -10000),
				Arguments.of(bust, bust, -10000)
		);
	}
}
