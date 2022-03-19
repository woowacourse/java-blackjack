package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;

public class BlackjackTest {

	@Test
	@DisplayName("카드를 목표 플레이어에게만 한 장 나누어 준다")
	void handOutCardTo() {
		List<Player> players = Arrays.asList(
			new Player("a", 10),
			new Player("b", 10));
		Blackjack blackJack = Blackjack.from(players);

		blackJack.handOutCardTo(blackJack.getPlayers().get(0));

		assertThat(blackJack.getPlayers().get(0).getCards().size()).isEqualTo(1);
		assertThat(blackJack.getPlayers().get(1).getCards().size()).isEqualTo(0);
	}

	@Test
	@DisplayName("starting card는 2장씩 나누어준다")
	void handOutStartingCards() {
		List<Player> players = Arrays.asList(
			new Player("a", 10),
			new Player("b", 10));
		Blackjack blackJack = Blackjack.from(players);

		blackJack.handOutStartingCards();

		assertThat(blackJack.getDealer().getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(0).getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(1).getCards().size()).isEqualTo(2);
	}
}
