package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void create() {
		assertThat(new Player("이름")).isInstanceOf(Player.class);
	}

	@Test
	void isWin_딜러와_플레이어가_동점() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		assertThat(player.isWin(dealer)).isTrue();
	}

	@Test
	void isWin_딜러와_플레이어_둘_다_버스트() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "Q"));
		dealer.addCard(Card.of("하트", "K"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "Q"));
		player.addCard(Card.of("클로버", "K"));
		assertThat(player.isWin(dealer)).isFalse();
	}

	@Test
	void isWin_플레이어가_딜러보다_21에_가까운_경우() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "9"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "Q"));
		assertThat(player.isWin(dealer)).isTrue();
	}

	@Test
	void isWin_딜러가_플레이어보다_21에_가까운_경우() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "Q"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "9"));
		assertThat(player.isWin(dealer)).isFalse();
	}
}
