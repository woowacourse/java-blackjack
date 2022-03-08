package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

	@Test
	void check_deck_size() {
		//given
		Deck deck = new Deck();
		//when
		deck.distributeCard();
		//then
		assertThat(deck.getCards().size()).isEqualTo(51);
	}

	@Test
	void check_deck_empty_size() {
		//given
		Deck deck = new Deck();
		//when
		for (int i = 0; i < 52; i++) {
			deck.distributeCard();
		}
		//then
		assertThatThrownBy(() -> deck.distributeCard())
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("덱의 카드가 다 소진되었습니다.");
	}
}
