package blackjack.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultDealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = DefaultDealer.create();
	}

	@Test
	void create() {
		assertThat(dealer).isNotNull();
	}

	@Test
	void shouldReceiveCard() {
	}

	@Test
	void showFirstCard() {
	}

	@Test
	void isWinner() {
	}

	@Test
	void giveCards() {
	}

	@Test
	void calculateScore() {
	}

	@Test
	void isBust() {
	}

	@Test
	void getCards() {
	}

	@Test
	void isName() {
	}

	@Test
	void countCards() {
	}

	@Test
	void getName() {
	}

	@Test
	void isNotBust() {
	}
}