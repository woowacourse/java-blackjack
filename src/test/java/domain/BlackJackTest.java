package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Deck;

public class BlackJackTest {

	private Users users;
	private BlackJack blackJack;

	@BeforeEach
	void setUsers() {
		Deck deck = new Deck(cards -> {
		});
		users = Users.from(List.of("hongo", "kiara"), deck);
		blackJack = new BlackJack(users, deck);
	}

	@DisplayName("유저가 요청하면 카드를 하나 더 준다")
	@Test
	void giveCard_whenRequest() {
		List<Player> players = users.getPlayers();

		Player player = players.get(0);
		int oldScore = player.getScore();
		blackJack.giveCard(player);
		assertThat(player.getScore()).isGreaterThan(oldScore);
	}
}
