package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	void create() {
		assertThat(new Dealer()).isInstanceOf(Dealer.class);
	}

	@Test
	void giveCard_플레이어_한명() {
		Player player = new Player("플레이어");
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();
		dealer.giveCard(cardDeck, player);
		assertThat(player.getCardSize()).isEqualTo(1);
	}

	@Test
	void giveCard_플레이어_다수() {
		Players players = new Players(PlayerFactory.create("a,b,c,d"));
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();
		dealer.giveCard(cardDeck, players);
		players.forEach(player -> assertThat(player.getCardSize()).isEqualTo(1));
	}

	@Test
	void shouldAddCard() {
		Dealer dealer = new Dealer();
		assertThat(dealer.shouldAddCard()).isTrue();
	}
}
