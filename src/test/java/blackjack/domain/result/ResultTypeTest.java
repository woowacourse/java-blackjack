package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

class ResultTypeTest {
	private static Stream<Arguments> provideDealerAndPlayerScoreWithReturnType() {
		Dealer dealer = Dealer.valueOf("dealer", Arrays.asList(
			Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));

		Player pobi = Player.valueOf("pobi", Arrays.asList(
			Card.of(Symbol.QUEEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		Player sony = Player.valueOf("sony", Arrays.asList(
			Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(
			Card.of(Symbol.SEVEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		Player bustPlayer = Player.valueOf("bust", Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.KING, Type.DIAMOND),
			Card.of(Symbol.TWO, Type.CLUB)));

		return Stream.of(
			Arguments.of(dealer, pobi, ResultType.LOSE),
			Arguments.of(dealer, sony, ResultType.DRAW),
			Arguments.of(dealer, stitch, ResultType.WIN),
			Arguments.of(dealer, bustPlayer, ResultType.WIN));
	}

	@ParameterizedTest
	@MethodSource("provideDealerAndPlayerScoreWithReturnType")
	void of_DealerScoreAndPlayerScore_ReturnResultType(User targetUser, User compareUser, ResultType expected) {
		assertThat(ResultType.of(targetUser, compareUser)).isEqualTo(expected);
	}
}
