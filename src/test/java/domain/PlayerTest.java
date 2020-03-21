package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void create() {
		assertThat(new Player("이름")).isInstanceOf(Player.class);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void 딜러와_플레이어가_동점() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isTrue();
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void 딜러와_플레이어_둘_다_버스트() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "Q"));
		dealer.addCard(Card.of("하트", "K"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "Q"));
		player.addCard(Card.of("클로버", "K"));
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void 플레이어가_딜러보다_21에_가까운_경우() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "9"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "Q"));
		assertThat(player.isWin(dealer)).isTrue();
		assertThat(player.isLose(dealer)).isFalse();
		assertThat(player.isDraw(dealer)).isFalse();
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void 딜러가_플레이어보다_21에_가까운_경우() {
		Dealer dealer = new Dealer();
		Player player = new Player("플레이어");
		dealer.addCard(Card.of("하트", "J"));
		dealer.addCard(Card.of("하트", "Q"));
		player.addCard(Card.of("클로버", "J"));
		player.addCard(Card.of("클로버", "9"));
		assertThat(player.isWin(dealer)).isFalse();
		assertThat(player.isLose(dealer)).isTrue();
		assertThat(player.isDraw(dealer)).isFalse();
	}

	@Test
	void shouldAddCard_카드_추가를_허용하고_받을_수_있는_경우() {
		User user = new Player("a");
		user.addCard(Card.of("하트", "10"));
		user.addCard(Card.of("하트", "A"));
		assertThat(user.shouldAddCard(true)).isTrue();
	}

	@Test
	void shouldAddCard_카드_추가를_허용하지_않고_받을_수_있는_경우() {
		User user = new Player("a");
		user.addCard(Card.of("하트", "10"));
		user.addCard(Card.of("하트", "A"));
		assertThat(user.shouldAddCard(false)).isFalse();
	}

	@Test
	void shouldAddCard_카드_추가는_허용하고_받을_수_없는_경우() {
		User user = new Player("a");
		user.addCard(Card.of("하트", "J"));
		user.addCard(Card.of("하트", "Q"));
		user.addCard(Card.of("하트", "K"));
		assertThat(user.shouldAddCard(true)).isFalse();
	}

	@Test
	void shouldAddCard_카드_추가는_허용하지_않고_받을_수_없는_경우() {
		User user = new Player("a");
		user.addCard(Card.of("하트", "J"));
		user.addCard(Card.of("하트", "Q"));
		user.addCard(Card.of("하트", "K"));
		assertThat(user.shouldAddCard(false)).isFalse();
	}
}
