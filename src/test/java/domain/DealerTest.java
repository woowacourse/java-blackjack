package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	void create() {
		assertThat(new Dealer()).isInstanceOf(Dealer.class);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void giveCard_플레이어_한명() {
		Player player = new Player("플레이어");
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();
		dealer.giveCard(cardDeck, player);
		assertThat(player.getCardSize()).isEqualTo(1);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void giveCard_플레이어_다수() {
		Players players = PlayersFactory.create("a,b,c,d");
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

	@Test
	void getFirstCard() {
		Dealer dealer = new Dealer();
		dealer.addCard(Card.of("스페이드", "A"));
		dealer.addCard(Card.of("하트", "A"));
		assertThat(dealer.getFirstCard()).isEqualTo(Card.of("스페이드", "A"));
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void isWin_딜러와_플레이어가_동점() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void isWin_딜러와_플레이어_둘_다_버스트() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "Q"));
		dealer.addCard(Card.of("하트", "K"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "Q"));
		player.addCard(Card.of("클로버", "K"));
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void isWin_딜러가_플레이어보다_21에_가까운_경우() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "Q"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "9"));
		assertThat(dealer.isWin(player)).isTrue();
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void isWin_플레이어가_딜러보다_21에_가까운_경우() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "9"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "Q"));
		assertThat(dealer.isWin(player)).isFalse();
	}
}
