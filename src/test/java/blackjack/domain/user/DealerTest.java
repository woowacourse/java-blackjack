package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidDealerException;

class DealerTest {
	@Test
	void Dealer_InputDealerName_GenerateInstance() {
		Dealer dealer = new Dealer("dealer");

		assertThat(dealer).isInstanceOf(Dealer.class);
		assertThat(dealer).isInstanceOf(User.class);
	}

	@Test
	void from_InputDealerNameAndCards_GenerateInstance() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.SEVEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND));

		assertThat(Dealer.from(cards)).isInstanceOf(Dealer.class)
			.extracting("hand").isEqualTo(cards);
	}

	@ParameterizedTest
	@NullSource
	void validate_EmptyCards_InvalidDealerExceptionThrown(List<Card> cards) {
		assertThatThrownBy(() -> Dealer.from(cards))
			.isInstanceOf(InvalidDealerException.class)
			.hasMessage(InvalidDealerException.EMPTY);
	}

	@Test
	void isBlackjack_CalculateResultScore_CheckHandType() {
		Dealer dealer = Dealer.from(Arrays.asList(
			Card.of(Symbol.TEN, Type.DIAMOND),
			Card.of(Symbol.ACE, Type.SPADE)));

		assertThat(dealer.isBlackjack()).isTrue();
	}

	@Test
	void canDraw_CurrentScoreLowerThanDrawableMaxScore_ReturnTrue() {
		Dealer dealer = Dealer.from(Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		assertThat(dealer.canDraw()).isTrue();
	}

	@ParameterizedTest
	@MethodSource("provideUndrawableDealer")
	void canDraw_CurrentScoreMoreThanDrawableMaxScore_ReturnFalse(Dealer dealer) {
		assertThat(dealer.canDraw()).isFalse();
	}

	private static Stream<Arguments> provideUndrawableDealer() {
		final int WORST_CASE_OF_DRAWABLE_COUNT = 12;

		Dealer dealer = new Dealer("dealer");
		Deck deck = new Deck(CardFactory.create());

		for (int i = 0; i < WORST_CASE_OF_DRAWABLE_COUNT; i++) {
			dealer.hit(deck);
		}
		return Stream.of(Arguments.of(dealer));
	}

	@Test
	void getInitialDealtHand_DealerDealInitialTwoCards_ReturnOneCard() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.SEVEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND));
		Dealer dealer = Dealer.from(cards);

		assertThat(dealer.getInitialDealtHand()).hasSize(1)
			.isEqualTo(Arrays.asList(Card.of(Symbol.SEVEN, Type.CLUB)));
	}
}
