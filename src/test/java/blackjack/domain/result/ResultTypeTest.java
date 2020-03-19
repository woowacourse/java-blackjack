package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidResultTypeException;

class ResultTypeTest {
	@Test
	void from_NullPlayerResultScoreOrNullDealerResultScore_InvalidResultTypeExceptionThrown() {
		Hand playerHand = new Hand();
		playerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND)));
		Hand dealerHand = new Hand();
		dealerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND)));

		assertThatThrownBy(() -> ResultType.from(null, ResultScore.of(dealerHand)))
			.isInstanceOf(InvalidResultTypeException.class)
			.hasMessage(InvalidResultTypeException.NULL);
		assertThatThrownBy(() -> ResultType.from(ResultScore.of(playerHand), null))
			.isInstanceOf(InvalidResultTypeException.class)
			.hasMessage(InvalidResultTypeException.NULL);
	}

	@Test
	void isBlackjackWin_PlayerResultScoreIsBlackjackAndDealerResultScoreIsNotBlackjack_ReturnBlackjackInstance() {
		Hand playerHand = new Hand();
		playerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND)));
		Hand dealerHand = new Hand();
		dealerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.DIAMOND)));

		assertThat(ResultType.from(ResultScore.of(playerHand), ResultScore.of(dealerHand)))
			.isEqualTo(ResultType.BLACKJACK_WIN);
	}

	@Test
	void isWin_PlayerResultScoreIsNormalAndDealerResultScoreIsBust_ReturnWinInstance() {
		Hand playerHand = new Hand();
		playerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.THREE, Type.DIAMOND)));
		Hand dealerHand = new Hand();
		dealerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.SPADE),
			Card.of(Symbol.KING, Type.DIAMOND)));

		assertThat(ResultType.from(ResultScore.of(playerHand), ResultScore.of(dealerHand)))
			.isEqualTo(ResultType.WIN);
	}

	@Test
	void isDraw_PlayerResultScoreIsBlackjackAndDealerResultScoreIsBlackjack_ReturnDrawInstance() {
		Hand playerHand = new Hand();
		playerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND)));
		Hand dealerHand = new Hand();
		dealerHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.SPADE),
			Card.of(Symbol.ACE, Type.HEART)));

		assertThat(ResultType.from(ResultScore.of(playerHand), ResultScore.of(dealerHand)))
			.isEqualTo(ResultType.DRAW);
	}

	@Test
	void isLose_PlayerResultScoreIsBustOrIsNotBlackjackAndDealerResultScoreIsBlackjack_ReturnLoseInstance() {
		Hand playerNotBlackjackHand = new Hand();
		playerNotBlackjackHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.THREE, Type.DIAMOND)));
		Hand playerBustHand = new Hand();
		playerBustHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.SPADE),
			Card.of(Symbol.KING, Type.DIAMOND)));
		Hand dealerBlackjackHand = new Hand();
		dealerBlackjackHand.add(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.DIAMOND)));

		assertThat(ResultType.from(ResultScore.of(playerBustHand), ResultScore.of(dealerBlackjackHand)))
			.isEqualTo(ResultType.LOSE);
		assertThat(ResultType.from(ResultScore.of(playerNotBlackjackHand), ResultScore.of(dealerBlackjackHand)))
			.isEqualTo(ResultType.LOSE);
	}

	@ParameterizedTest
	@MethodSource("provideDealerAndPlayerResultScoreWithReturnType")
	void compareResultScoreFrom_PlayerResultScoreAndDealerResultScore_ReturnCompareResult(ResultScore playerResultScore,
		ResultScore dealerResultScore, ResultType expected) {

		assertThat(ResultType.from(playerResultScore, dealerResultScore)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDealerAndPlayerResultScoreWithReturnType() {
		Hand dealerHand = new Hand();
		dealerHand.add(Arrays.asList(
			Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		ResultScore dealerResultScore = ResultScore.of(dealerHand);

		Hand winHand = new Hand();
		winHand.add(Arrays.asList(
			Card.of(Symbol.QUEEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		ResultScore winPlayerResultScore = ResultScore.of(winHand);

		Hand drawHand = new Hand();
		drawHand.add(Arrays.asList(Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		ResultScore drawPlayerResultScore = ResultScore.of(drawHand);

		Hand loseHand = new Hand();
		loseHand.add(Arrays.asList(Card.of(Symbol.SEVEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		ResultScore losePlayerResultScore = ResultScore.of(loseHand);

		return Stream.of(
			Arguments.of(winPlayerResultScore, dealerResultScore, ResultType.WIN),
			Arguments.of(drawPlayerResultScore, dealerResultScore, ResultType.DRAW),
			Arguments.of(losePlayerResultScore, dealerResultScore, ResultType.LOSE));
	}
}
