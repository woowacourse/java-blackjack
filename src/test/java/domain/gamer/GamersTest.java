package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.CardNumber;
import domain.card.CardSuit;
import domain.card.Deck;

public class GamersTest {
	@Test
	@DisplayName("게이머들 생성자 제대로 생성되는지 테스트")
	public void gamersInitTest() {
		String names = "pobi, json";
		Gamers gamers = new Gamers(names, new Dealer());

		assertThat(gamers).isNotNull();
	}

	@Test
	public void gamersCardInitTest() {
		String names = "pobi, json";
		Deck deck = new Deck(CardFactory.getInstance());
		Gamers gamers = new Gamers(names, new Dealer());

		gamers.initCard(deck);

		Player player = gamers.getPlayers().get(0);
		assertThat(player.getCards()).hasSize(2);
		assertThat(gamers.getDealer().getCards()).hasSize(2);
	}

	@Test
	public void generateGameResultsTest() {
		String names = "pobi, json";
		Gamers gamers = new Gamers(names, new Dealer());

		gamers.getPlayers().forEach(x -> {
			x.addCard(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.SIX),
				new Card(CardSuit.CLOVER, CardNumber.TEN))
			);
		});

		gamers.getDealer().addCard(Arrays.asList(
			new Card(CardSuit.CLOVER, CardNumber.SEVEN),
			new Card(CardSuit.CLOVER, CardNumber.TEN))
		);

		assertThat(gamers.generateGameResults().get("pobi")).isEqualTo(WinOrLose.LOSE);
	}
}