package domains.result;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domains.card.Card;
import domains.card.Symbol;
import domains.card.Type;
import domains.user.Dealer;
import domains.user.Hands;
import domains.user.Player;

public class WinOrLoseTest {
	@DisplayName("플레이어가 이겼을 때, WIN 반환")
	@ParameterizedTest
	@MethodSource("winnerData")
	void checkWinOrLose_playerWin_returnWin(List<Card> winCards, List<Card> loseCards) {
		Player player = new Player("또링", new Hands(winCards));
		Dealer dealer = new Dealer(new Hands(loseCards));

		assertThat(WinOrLose.checkWinOrLose(player, dealer)).isEqualTo(WinOrLose.WIN);
	}

	static Stream<Arguments> winnerData() {
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card ten = new Card(Symbol.TEN, Type.SPADE);
		Card king = new Card(Symbol.KING, Type.HEART);
		Card four = new Card(Symbol.FOUR, Type.DIAMOND);

		return Stream.of(
			Arguments.of(new ArrayList<>(Arrays.asList(ace, king)), new ArrayList<>(Arrays.asList(ace, four))),
			Arguments.of(new ArrayList<>(Arrays.asList(ace, ten, king)), new ArrayList<>(Arrays.asList(ace, four, ten)))
		);
	}
}
