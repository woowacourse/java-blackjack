package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
	@Test
	void distribute_Card() {
		Deck deck = new Deck();
		assertThat(deck.distributeCard()).isInstanceOf(Card.class);
	}

	@Test
	void distribute_card_to_dealer() {
		//given
		Gamer dealer = new Dealer();
		Deck deck = new Deck();
		//when
		dealer.addCard(deck);
		//then
		assertThat(dealer.getCards().size()).isEqualTo(1);
	}

	@Test
	void distribute_card_to_player() {
		//given
		Gamer player = new Player();
		Deck deck = new Deck();
		//when
		player.addCard(deck);
		//then
		assertThat(player.getCards().size()).isEqualTo(1);
	}
}
