package controller;

import domain.card.CardDeck;
import domain.player.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackJackGameControllerTest {
	@DisplayName("딜러가 16이하일 경우 계속해서 카드를 받는지")
	@Test
	void dealerHit() {
		CardDeck cardDeck = new CardDeck();
		Dealer dealer = new Dealer();

		BlackJackGameController.dealerHit(dealer, cardDeck);

		assertThat(dealer.calculateScore() > 16).isTrue();
	}
}
