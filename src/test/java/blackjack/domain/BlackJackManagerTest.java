package blackjack.domain;

import static blackjack.CardConstant.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BettingResult;

class BlackJackManagerTest {

	private final Map<String, Integer> nameAndBet = Stream.of("pobi", "jason")
		.collect(toMap(name -> name, name -> 10));
	private final BlackJackManager manager = new BlackJackManager(nameAndBet);

	@Test
	@DisplayName("처음에 카드를 2장씩 나눠준다.")
	void handOutFirst() {
		manager.handOutFirst(new Deck(Card.getCards()));
		Dealer dealer = manager.getDealer();
		List<Player> players = manager.getPlayers();

		assertThat(dealer.getCards().size()).isEqualTo(2);
		assertThat(players)
			.map(player -> player.getCards().size())
			.containsOnly(2);
	}

	@Test
	@DisplayName("이름으로 플레이어를 찾아낸다.")
	void findPlayerByName() {
		Player jason = manager.findPlayerByName("jason");
		assertThat(jason.isSameName("jason")).isTrue();
	}

	@Test
	@DisplayName("모든 플레이어의 이름을 찾아낸다.")
	void findPlayerNames() {
		List<String> names = manager.findPlayerNames();
		assertThat(names).containsExactly("pobi", "jason");
	}

	@Test
	@DisplayName("배팅 결과를 반환한다.")
	void createBettingResult() {
		Dealer dealer = new Dealer(CLOVER_ACE, CLOVER_TEN);
		Player pobi = new Player("pobi", 10, CLOVER_TEN, CLOVER_TEN);
		Player jason = new Player("jason", 10, CLOVER_TEN, CLOVER_TEN);
		BlackJackManager manager = new BlackJackManager(dealer, pobi, jason);

		BettingResult result = manager.createBettingResult();
		int dealerEarning = result.getDealerEarning();
		Map<String, Integer> playerEarnings = result.getPlayerEarnings();

		assertThat(dealerEarning).isEqualTo(20);
		assertThat(playerEarnings.get("pobi")).isEqualTo(-10);
		assertThat(playerEarnings.get("jason")).isEqualTo(-10);
	}
}