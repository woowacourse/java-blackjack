package domain.gamer;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.CardsFactory;
import domain.card.Deck;

public class GamersTest {
	@Test
	@DisplayName("게이머들 생성자 제대로 생성되는지 테스트")
	public void gamersInitTest() {
		Gamers gamers = Stream.of("pobi", "json")
			.map(name -> new Player(name, "50"))
			.collect(collectingAndThen(toList(), players -> new Gamers(players, new Dealer())));

		assertThat(gamers).isNotNull();
	}

	@Test
	public void gamersCardInitTest() {
		Deck deck = new Deck(CardsFactory.getCards());

		Gamers gamers = Stream.of("pobi", "json")
			.map(name -> new Player(name, "50"))
			.collect(collectingAndThen(toList(), players -> new Gamers(players, new Dealer())));

		gamers.initCard(deck);
		Player player = gamers.getPlayers().get(0);

		assertThat(player.getCards()).hasSize(2);
		assertThat(gamers.getDealer().getCards()).hasSize(2);
	}
}