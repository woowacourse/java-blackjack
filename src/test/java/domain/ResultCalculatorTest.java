package domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {
	@Test
	void getResults() {
		Player player = new Player("플레이어");
		Player player2 = new Player("플레이어2");
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();
		cardDeck.shuffle();
		dealer.giveCard(cardDeck, player);
		dealer.giveCard(cardDeck, player);
		dealer.giveCard(cardDeck, player2);
		dealer.giveCard(cardDeck, player2);
		dealer.addCard(cardDeck.drawOne());
		dealer.addCard(cardDeck.drawOne());
		Results results = ResultCalculator.getResults(dealer, new Players(Arrays.asList(player, player2)));
	}
}
