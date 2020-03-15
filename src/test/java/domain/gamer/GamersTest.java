package domain.gamer;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GamersTest {
	@Test
	@DisplayName("게이머들 생성자 제대로 생성되는지 테스트")
	public void gamersInitTest() {
		Gamers gamers = new Gamers("pobi, json", new Dealer());

		assertThat(gamers).isNotNull();
	}

	@Test
	public void gamersCardInitTest() {
		Deck deck = new Deck(CardsFactory.getCards());
		Gamers gamers = new Gamers("pobi, json", new Dealer());
		gamers.initCard(deck);
		Player player = gamers.getPlayers().get(0);

		assertThat(player.getCards()).hasSize(2);
		assertThat(gamers.getDealer().getCards()).hasSize(2);
	}

	@Test
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

		assertThat(gamers.generateGameResults()
			.getGameResult().values())
			.contains(MatchResult.LOSE);
	}
}