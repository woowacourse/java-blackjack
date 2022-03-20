package blackjack.domain.role;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_FIVE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_NINE;
import static blackjack.util.Fixtures.CLOVER_SEVEN;
import static blackjack.util.Fixtures.CLOVER_SIX;
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

@DisplayName("Dealer 테스트")
class DealerTest {

	@DisplayName("딜러가 현재 가지고 있는 패에 따라 한 장을 더 드로우 할지 말지를 결정한다")
	@ParameterizedTest(name = "{index} {displayName} cards={0} expectedResult={1} drawSelect={2}")
	@MethodSource("createState")
	void draw(final State state, final int expectedResult, final boolean drawSelect) {
		Deck deck = new Deck();
		Role dealer = new Dealer(state, () -> drawSelect);
		dealer.draw(deck);
		assertThat(dealer.getCards().getSize()).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> createState() {
		final State blackjack = new Ready().draw(CLOVER_ACE).draw(CLOVER_KING);
		final State randomDrawWithAce = new Ready().draw(CLOVER_ACE).draw(CLOVER_NINE);
		final State drawWithAce = new Ready().draw(CLOVER_ACE).draw(CLOVER_FIVE);
		final State notDrawWithoutAce = new Ready().draw(CLOVER_KING).draw(CLOVER_SEVEN);
		final State drawWithoutAce = new Ready().draw(CLOVER_KING).draw(CLOVER_SIX);

		return Stream.of(
				Arguments.of(blackjack, 2, true),
				Arguments.of(randomDrawWithAce, 2, false),
				Arguments.of(randomDrawWithAce, 3, true),
				Arguments.of(drawWithAce, 3, false),
				Arguments.of(notDrawWithoutAce, 2, true),
				Arguments.of(drawWithoutAce, 3, false)
		);
	}

	@Test
	@DisplayName("딜러의 패 오픈 전략 확인")
	void check_Open_Cards() {
		final State blackjack = new Ready().draw(CLOVER_ACE).draw(CLOVER_KING);
		Role dealer = new Dealer(blackjack, DealerDrawChoice::chooseDraw);
		List<Card> expectedOpenCards = List.of(CLOVER_ACE);
		assertThat(dealer.openCards()).isEqualTo(expectedOpenCards);
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
}

