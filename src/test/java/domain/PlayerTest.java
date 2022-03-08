package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void distribute_card_to_player() {
		//given
		Gamer player = new Player("pobi");
		Deck deck = new Deck();
		//when
		player.addCard(deck.distributeCard());
		//then
		assertThat(player.getCards().size()).isEqualTo(1);
	}
}
