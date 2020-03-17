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
import domain.gamer.Money;
import domain.gamer.Name;
import domain.gamer.Player;

class ResultTest {
	@ParameterizedTest
	@MethodSource("cardInput")
	@DisplayName("점수비교를 통해 올바른 결과(Enum)을 생성하는지 테스트")
	void ofTest(List<Card> playerCards, List<Card> dealerCards, Result expected) {
		Player player = new Player(new Name("pobi"), Money.ZERO);
		for (Card card : playerCards) {
			player.hit(card);
		}
		Dealer dealer = new Dealer();
		for (Card card : dealerCards) {
			dealer.hit(card);
		}

		assertThat(Result.of(player, dealer)).isEqualTo(expected);
	}

	static Stream<Arguments> cardInput() {
		Card seven = new Card(Symbol.SEVEN, Type.CLUB);
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card six = new Card(Symbol.SIX, Type.CLUB);
		Card ten = new Card(Symbol.TEN, Type.CLUB);

		return Stream.of(Arguments.of(Arrays.asList(seven, six), Arrays.asList(seven, six, ace), Result.LOSE),
			Arguments.of(Arrays.asList(ten, ace), Arrays.asList(seven, six, ace), Result.BLACK_JACK),
			Arguments.of(Arrays.asList(ten, ace), Arrays.asList(ten, ace), Result.DRAW),
			Arguments.of(Arrays.asList(six), Arrays.asList(six), Result.DRAW),
			Arguments.of(Arrays.asList(ten, six), Arrays.asList(seven, six, ace), Result.WIN),
			Arguments.of(Arrays.asList(ace, six), Arrays.asList(seven, six, ace), Result.WIN),
			Arguments.of(Arrays.asList(ten, ten, six), Arrays.asList(seven, six, ace), Result.LOSE),
			Arguments.of(Arrays.asList(ten, ten, ten), Arrays.asList(ten, ten, six), Result.LOSE));
	}

	@Test
	@DisplayName("게임결과에 따라 정확한 수익금을 계산하는지 테스트")
	void calculateTest() {
		Money money = Money.of(10000);

		assertThat(Result.BLACK_JACK.calculate(money)).isEqualTo(5000);
		assertThat(Result.WIN.calculate(money)).isEqualTo(10000);
		assertThat(Result.DRAW.calculate(money)).isEqualTo(0);
		assertThat(Result.LOSE.calculate(money)).isEqualTo(-10000);
	}
}