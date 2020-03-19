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

	@Test
	@DisplayName("게임 결과들을 가져오는 테스트")
	public void generateGameResultsTest() {
		Gamers gamers = new Gamers("pobi, json", new Dealer());

		gamers.getPlayers().forEach(player -> {
			player.addCard(Arrays.asList(
					new Card(CardSuit.CLOVER, CardNumber.SIX),
					new Card(CardSuit.CLOVER, CardNumber.TEN))
			);
		});

		gamers.getDealer().addCard(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.SEVEN),
				new Card(CardSuit.CLOVER, CardNumber.TEN))
		);

		assertThat(gamers.generateGameResults().get(gamers.getPlayers().get(0))).isEqualTo(MatchResult.LOSE);
	}
}