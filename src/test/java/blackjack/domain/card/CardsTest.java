package blackjack.domain.card;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_NINE;
import static blackjack.util.Fixtures.CLOVER_QUEEN;
import static blackjack.util.Fixtures.CLOVER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Cards 테스트")
class CardsTest {

	@Test
	@DisplayName("드로우한 카드가 제대로 추가되는지 확인")
	void add() {
		Cards cards = new Cards();
		final Card card = CLOVER_ACE;

		cards = cards.add(card);
		final List<Card> expectedHand = List.of(card);
		final List<Card> actualHand = cards.getCards();

		assertThat(actualHand.containsAll(expectedHand)).isTrue();
	}

	@DisplayName("패에 Ace 카드가 있는지 확인")
	@ParameterizedTest(name = "{index} {displayName} card={0} expectedHasAce={1}")
	@CsvSource(value = {"ACE, true", "KING, false"})
	void check_Has_Ace(Denomination denomination, boolean expectedHasAce) {
		Cards cards = new Cards();
		final Card card = new Card(Suit.CLOVER, denomination);
		cards = cards.add(card);

		assertThat(cards.hasAce()).isEqualTo(expectedHasAce);
	}

	@Test
	@DisplayName("패가 파산 했는지 확인")
	void check_Is_Bust() {
		final Cards cards = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO));
		assertThat(cards.isBust()).isEqualTo(true);
	}

	@DisplayName("패가 블랙잭인지 확인")
	@ParameterizedTest(name = "{index} {displayName} cards={0} expectedBlackJack={1}")
	@MethodSource("getHandAndBlackjack")
	void check_Is_Blackjack(Cards cards, boolean expectedBlackJack) {
		assertThat(cards.isBlackjack()).isEqualTo(expectedBlackJack);
	}

	private static Stream<Arguments> getHandAndBlackjack() {
		final Cards cards1 = new Cards(List.of(CLOVER_ACE, CLOVER_KING));
		final Cards cards2 = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN));
		final Cards cards3 = new Cards(List.of(CLOVER_ACE, CLOVER_NINE, CLOVER_TWO));
		final Cards cards4 = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_NINE));

		return Stream.of(
				Arguments.of(cards1, true),
				Arguments.of(cards2, false),
				Arguments.of(cards3, false),
				Arguments.of(cards4, false));
	}

	@DisplayName("현재 패에 가지고 있는 최적의 점수 계산 확인")
	@ParameterizedTest(name = "{index} {displayName} cards={0}")
	@MethodSource("getCardsAndScore")
	void check_Get_Score(Cards cards, int expectedScore) {
		final int actualScore = cards.getScore();
		assertThat(actualScore).isEqualTo(expectedScore);
	}

	private static Stream<Arguments> getCardsAndScore() {
		final Cards cards1 = new Cards(List.of(CLOVER_ACE, CLOVER_KING));
		final Cards cards2 = new Cards(List.of(CLOVER_ACE, CLOVER_KING, CLOVER_QUEEN));
		final Cards cards3 = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN, CLOVER_NINE));
		final Cards cards4 = new Cards(List.of(CLOVER_KING, CLOVER_QUEEN));

		return Stream.of(
				Arguments.of(cards1, 21),
				Arguments.of(cards2, 21),
				Arguments.of(cards3, 29),
				Arguments.of(cards4, 20));
	}
}
