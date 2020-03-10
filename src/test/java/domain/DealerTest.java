package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import factory.CardFactory;

public class DealerTest {
	@Test
	void create() {
		assertThat(new Dealer()).isInstanceOf(Dealer.class);
	}

	@Test
	void giveOneCard() {
		Player player = new Player("플레이어");
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck(CardFactory.create());
		dealer.giveOneCard(cardDeck, player);
		assertThat(player.getCardSize()).isEqualTo(1);
	}

	@Test
	void shouldAddCard() {
		Dealer dealer = new Dealer();
		assertThat(dealer.shouldAddCard()).isTrue();
	}
}
