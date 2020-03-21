package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidResultTypeException;

class ResultTypeTest {
	@Test
	void validate_NullPlayerResultScoreOrNullDealerResultScore_InvalidResultTypeExceptionThrown() {
		List<Card> playerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND));
		List<Card> dealerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND));

		assertThatThrownBy(() -> ResultType.from(null, ResultScore.of(dealerCards)))
			.isInstanceOf(InvalidResultTypeException.class)
			.hasMessage(InvalidResultTypeException.NULL);
		assertThatThrownBy(() -> ResultType.from(ResultScore.of(playerCards), null))
			.isInstanceOf(InvalidResultTypeException.class)
			.hasMessage(InvalidResultTypeException.NULL);
	}

	@Test
	void isBlackjackWin_PlayerResultScoreIsBlackjackAndDealerResultScoreIsNotBlackjack_ReturnBlackjackInstance() {
		List<Card> playerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND));
		List<Card> dealerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND));

		assertThat(ResultType.from(ResultScore.of(playerCards), ResultScore.of(dealerCards)))
			.isEqualTo(ResultType.BLACKJACK_WIN);
	}

	@Test
	void isWin_PlayerResultScoreIsNormalAndDealerResultScoreIsBust_ReturnWinInstance() {
		List<Card> playerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.THREE, Type.DIAMOND));
		List<Card> dealerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.SPADE),
			Card.of(Symbol.KING, Type.DIAMOND));

		assertThat(ResultType.from(ResultScore.of(playerCards), ResultScore.of(dealerCards)))
			.isEqualTo(ResultType.WIN);
	}

	@Test
	void isDraw_PlayerResultScoreIsBlackjackAndDealerResultScoreIsBlackjack_ReturnDrawInstance() {
		List<Card> playerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND));
		List<Card> dealerCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.SPADE),
			Card.of(Symbol.ACE, Type.HEART));

		assertThat(ResultType.from(ResultScore.of(playerCards), ResultScore.of(dealerCards)))
			.isEqualTo(ResultType.DRAW);
	}

	@Test
	void isLose_PlayerResultScoreIsBustOrIsNotBlackjackAndDealerResultScoreIsBlackjack_ReturnLoseInstance() {
		List<Card> playerNotBlackjackCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.THREE, Type.DIAMOND));
		List<Card> playerBustCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.SPADE),
			Card.of(Symbol.KING, Type.DIAMOND));
		List<Card> dealerBlackjackCards = Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND));

		assertThat(ResultType.from(ResultScore.of(playerBustCards), ResultScore.of(dealerBlackjackCards)))
			.isEqualTo(ResultType.LOSE);
		assertThat(ResultType.from(ResultScore.of(playerNotBlackjackCards), ResultScore.of(dealerBlackjackCards)))
			.isEqualTo(ResultType.LOSE);
	}

	@ParameterizedTest
	@MethodSource("provideDealerAndPlayerResultScoreWithReturnType")
	void compareResultScoreFrom_PlayerResultScoreAndDealerResultScore_ReturnCompareResult(ResultScore playerResultScore,
		ResultScore dealerResultScore, ResultType expected) {

		assertThat(ResultType.from(playerResultScore, dealerResultScore)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDealerAndPlayerResultScoreWithReturnType() {
		List<Card> dealerCards = Arrays.asList(
			Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND));
		ResultScore dealerResultScore = ResultScore.of(dealerCards);

		List<Card> winCards = Arrays.asList(
			Card.of(Symbol.QUEEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND));
		ResultScore winPlayerResultScore = ResultScore.of(winCards);

		List<Card> drawCards = Arrays.asList(Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND));
		ResultScore drawPlayerResultScore = ResultScore.of(drawCards);

		List<Card> loseCards = Arrays.asList(Card.of(Symbol.SEVEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND));
		ResultScore losePlayerResultScore = ResultScore.of(loseCards);

		return Stream.of(
			Arguments.of(winPlayerResultScore, dealerResultScore, ResultType.WIN),
			Arguments.of(drawPlayerResultScore, dealerResultScore, ResultType.DRAW),
			Arguments.of(losePlayerResultScore, dealerResultScore, ResultType.LOSE));
	}

	@Test
	void calculateProfitFrom_BettingMoney_ReturnMultipliedBettingMoney() {
		BettingMoney value = BettingMoney.valueOf("10000");

		BettingMoney expected = BettingMoney.valueOf(15000);
		assertThat(ResultType.BLACKJACK_WIN.calculateProfitFrom(value)).isEqualTo(expected);
	}
}
