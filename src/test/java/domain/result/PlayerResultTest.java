package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Money;
import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultTest {
	@ParameterizedTest
	@MethodSource("cardInput")
	@DisplayName("점수비교를 통해 올바른 결과(Enum)을 생성하는지 테스트")
	void ofTest(List<Card> playerCards, List<Card> dealerCards, PlayerResult expected) {
		Player player = new Player(new Name("pobi"), Money.ZERO);
		for (Card card : playerCards) {
			player.hit(card);
		}
		Dealer dealer = new Dealer();
		for (Card card : dealerCards) {
			dealer.hit(card);
		}

		assertThat(PlayerResult.of(player, dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> cardInput() {
		Card seven = new Card(Symbol.SEVEN, Type.CLUB);
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card six = new Card(Symbol.SIX, Type.CLUB);
		Card ten = new Card(Symbol.TEN, Type.CLUB);

		return Stream.of(Arguments.of(Arrays.asList(seven, six), Arrays.asList(seven, six, ace), PlayerResult.LOSE),
				Arguments.of(Arrays.asList(ten, ace), Arrays.asList(seven, six, ace), PlayerResult.BLACK_JACK_WIN),
				Arguments.of(Arrays.asList(ten, ace), Arrays.asList(ten, ace), PlayerResult.DRAW),
				Arguments.of(Arrays.asList(six), Arrays.asList(six), PlayerResult.DRAW),
				Arguments.of(Arrays.asList(ten, six), Arrays.asList(seven, six, ace), PlayerResult.WIN),
				Arguments.of(Arrays.asList(ace, six), Arrays.asList(seven, six, ace), PlayerResult.WIN),
				Arguments.of(Arrays.asList(ten, ten, six), Arrays.asList(seven, six, ace), PlayerResult.LOSE),
				Arguments.of(Arrays.asList(ten, ten, ten), Arrays.asList(ten, ten, six), PlayerResult.LOSE));
	}

	@ParameterizedTest
	@MethodSource("calculateInput")
	@DisplayName("게임결과에 따라 정확한 수익금을 계산하는지 테스트")
	void calculateTest(PlayerResult result, int moneyInput, int expected) {
		Money money = new Money(moneyInput);

		assertThat(result.calculateProfit(money)).isEqualTo(expected);
	}

	static Stream<Arguments> calculateInput() {
		return Stream.of(Arguments.of(PlayerResult.BLACK_JACK_WIN, 10000, 5000),
				Arguments.of(PlayerResult.WIN, 10000, 10000),
				Arguments.of(PlayerResult.DRAW, 10000, 0),
				Arguments.of(PlayerResult.LOSE, 10000, -10000));
	}
}