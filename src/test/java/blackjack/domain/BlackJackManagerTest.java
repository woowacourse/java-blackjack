package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Player;

class BlackJackManagerTest {

	private final List<Player> players = List.of(
		new Player("pobi", 10), new Player("jason", 10));

	@Test
	@DisplayName("이름으로 플레이어를 찾아낸다.")
	void findPlayerByName() {
		BlackJackManager gamers = new BlackJackManager(players);
		Player jason = gamers.findPlayerByName("jason");
		assertThat(jason.isSameName("jason")).isTrue();
	}

	@Test
	@DisplayName("모든 플레이어의 이름을 찾아낸다.")
	void findPlayerNames() {
		BlackJackManager blackJackManager = new BlackJackManager(players);
		List<String> names = blackJackManager.findPlayerNames();
		assertThat(names).containsExactly("pobi", "jason");
	}

	@ParameterizedTest
	@CsvSource(value = {"TWO:true", "ACE:false"}, delimiter = ':')
	@DisplayName("이름을 입력 받아, 해당 플레이어가 카드 총 합이 21이 넘는 버스트 상태인지 확인한다.")
	void isBurst(String input, boolean result) {
		BlackJackManager blackJackManager = new BlackJackManager(players);
		Player pobi = blackJackManager.findPlayerByName("pobi");

		pobi.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.KING));
		pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.KING));
		pobi.addCard(Card.getInstance(CardShape.HEART, CardNumber.valueOf(input)));

		assertThat(!pobi.isDrawable()).isEqualTo(result);
	}
}