package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
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
	@ParameterizedTest
	@MethodSource("provideDealerAndPlayerScoreWithReturnType")
	void from_DealerScoreAndPlayerScore_ReturnResultType(User targetUser, User compareUser, ResultType expected) {
		assertThat(ResultType.from(targetUser, compareUser)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDealerAndPlayerScoreWithReturnType() {
		List<Card> dealerCards = Arrays.asList(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));
		List<Card> pobiCards = Arrays.asList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));
		List<Card> sonyCards = Arrays.asList(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));
		List<Card> stitchCards = Arrays.asList(new Card(Symbol.SEVEN, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));

		Player pobi = Player.valueOf("pobi", pobiCards);
		Player sony = Player.valueOf("sony", sonyCards);
		Player stitch = Player.valueOf("stitch", stitchCards);

		Dealer dealer = Dealer.valueOf("dealer", dealerCards);
		return Stream.of(
			Arguments.arguments(dealer, pobi, ResultType.LOSE),
			Arguments.arguments(dealer, sony, ResultType.DRAW),
			Arguments.arguments(dealer, stitch, ResultType.WIN));
	}
}
