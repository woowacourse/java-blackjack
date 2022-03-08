package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	void distribute_card_to_dealer() {
		//given
		Gamer dealer = new Dealer();
		Deck deck = new Deck();
		//when
		dealer.addCard(deck.distributeCard());
		//then
		assertThat(dealer.getCards().size()).isEqualTo(1);
	}
}
