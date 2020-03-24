package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProfitTest {
	@Test
	void of_무승부() {
		double actual = Profit.of(new Player("a"), new Dealer(), new BettingMoney(100)).getValue();
		double expected = 0;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void of_블랙잭_승() {
		User player = new Player("a");
		player.addCard(Card.of("스페이드", "A"));
		player.addCard(Card.of("스페이드", "J"));
		double actual = Profit.of(player, new Dealer(), new BettingMoney(100)).getValue();
		double expected = 150;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void of_승() {
		User player = new Player("a");
		player.addCard(Card.of("스페이드", "A"));
		double actual = Profit.of(player, new Dealer(), new BettingMoney(100)).getValue();
		double expected = 100;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void of_패() {
		User dealer = new Dealer();
		dealer.addCard(Card.of("스페이드", "A"));
		double actual = Profit.of(new Player("a"), dealer, new BettingMoney(100)).getValue();
		double expected = -100;
		assertThat(actual).isEqualTo(expected);
	}
}
