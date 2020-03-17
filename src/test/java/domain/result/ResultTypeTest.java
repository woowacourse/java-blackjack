package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;

class ResultTypeTest {
	@Test
	@DisplayName("플레이어의 승패와 대칭되는 딜러의 승패가 반환되는지")
	void reverseTest() {
		assertThat(ResultType.reverse(ResultType.WIN)).isEqualTo(ResultType.LOSE);
		assertThat(ResultType.reverse(ResultType.LOSE)).isEqualTo(ResultType.WIN);
		assertThat(ResultType.reverse(ResultType.DRAW)).isEqualTo(ResultType.DRAW);
	}

	@ParameterizedTest
	@MethodSource("cardInput")
	@DisplayName("점수비교를 통해 올바른 결과(Enum)을 생성하는지 테스트")
	void ofTest(List<Card> playerCards, List<Card> dealerCards, ResultType expected) {
		Player player = new Player(new Name("pobi"));
		for (Card card : playerCards) {
			player.hit(card);
		}
		Dealer dealer = new Dealer();
		for (Card card : dealerCards) {
			dealer.hit(card);
		}

		assertThat(ResultType.of(player, dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> cardInput() {
		Card seven = new Card(Symbol.SEVEN, Type.CLUB);
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card six = new Card(Symbol.SIX, Type.CLUB);
		Card ten = new Card(Symbol.TEN, Type.CLUB);

		return Stream.of(Arguments.of(Arrays.asList(seven, six), Arrays.asList(seven, six, ace), ResultType.LOSE),
			Arguments.of(Arrays.asList(ten, ace), Arrays.asList(seven, six, ace), ResultType.BLACK_JACK),
			Arguments.of(Arrays.asList(ten, ace), Arrays.asList(ten, ace), ResultType.DRAW),
			Arguments.of(Arrays.asList(six), Arrays.asList(six), ResultType.DRAW),
			Arguments.of(Arrays.asList(ten, six), Arrays.asList(seven, six, ace), ResultType.WIN),
			Arguments.of(Arrays.asList(ace, six), Arrays.asList(seven, six, ace), ResultType.WIN),
			Arguments.of(Arrays.asList(ten, ten, six), Arrays.asList(seven, six, ace), ResultType.LOSE),
			Arguments.of(Arrays.asList(ten, ten, ten), Arrays.asList(ten, ten, six), ResultType.LOSE));
	}
}