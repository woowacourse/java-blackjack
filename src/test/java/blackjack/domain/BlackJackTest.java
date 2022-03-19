package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;

public class BlackJackTest {

	@Test
	@DisplayName("참가자의 수가 8명을 초과하면 예외가 발생한다")
	void playerNumberException_8() {
		assertThatThrownBy(() -> {
			List<Player> players = Arrays.asList(
				new Player("a", 10),
				new Player("b", 10),
				new Player("c", 10),
				new Player("d", 10),
				new Player("e", 10),
				new Player("f", 10),
				new Player("g", 10),
				new Player("h", 10),
				new Player("i", 10));
			BlackJack.from(players);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.");
	}

	@Test
	@DisplayName("카드를 목표 플레이어에게만 한 장 나누어 준다")
	void handOutCardTo() {
		List<Player> players = Arrays.asList(
			new Player("a", 10),
			new Player("b", 10));
		BlackJack blackJack = BlackJack.from(players);
		blackJack.handOutCardTo(blackJack.getPlayers().get(0));
		assertThat(blackJack.getPlayers().get(0).getCards().getCards().size()).isEqualTo(1);
		assertThat(blackJack.getPlayers().get(1).getCards().getCards().size()).isEqualTo(0);
	}

	@Test
	@DisplayName("starting card는 2장씩 나누어준다")
	void handOutStartingCards() {
		List<Player> players = Arrays.asList(
			new Player("a", 10),
			new Player("b", 10));
		BlackJack blackJack = BlackJack.from(players);
		blackJack.handOutStartingCards();
		assertThat(blackJack.getDealer().getCards().getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(0).getCards().getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(1).getCards().getCards().size()).isEqualTo(2);
	}
}
