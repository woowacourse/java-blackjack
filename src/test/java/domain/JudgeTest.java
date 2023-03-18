package domain;

import static domain.Judge.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suits;

class JudgeTest {
	Player player;
	Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player("kiara", new Cards());
		dealer = new Dealer(new Cards());
	}

	@DisplayName("플레이어와 딜러 모두 블랙잭이면 플레이어의 수익은 없다")
	@Test
	void bothBlackjack() {
		makeBlackjack(player);
		makeBlackjack(dealer);
		setBet(1000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(0);
	}

	@DisplayName("플레이어만 블랙잭이면 플레이어의 수익은 1.5배이다")
	@Test
	void playerBlackjack() {
		makeBlackjack(player);
		make20(dealer);
		setBet(2000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(3000);
	}

	@DisplayName("딜러만 블랙잭이면 플레이어의 수익은 -1배이다")
	@Test
	void dealerBlackjack() {
		make20(player);
		makeBlackjack(dealer);
		setBet(2000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(-2000);
	}

	@DisplayName("플레이어가 버스트인 경우 플레이어의 수익은 -1배이다")
	@Test
	void playerBurst() {
		makeBurst(player);
		make20(dealer);
		setBet(3000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(-3000);
	}

	@DisplayName("딜러가 버스트인 경우 플레이어의 수익은 1배이다")
	@Test
	void dealerBurst() {
		make20(player);
		makeBurst(dealer);
		setBet(3000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(3000);
	}

	@DisplayName("둘다 블랙잭, 버스트가 아닐 때 플레이어가 딜러보다 점수 높으면 수익은 1배이다")
	@Test
	void playerHigh() {
		make20(player);
		make19(dealer);
		setBet(4000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(4000);
	}

	@DisplayName("둘다 블랙잭, 버스트가 아닐 때 플레이어가 딜러보다 점수 낮으면 수익은 -1배이다")
	@Test
	void dealerHigh() {
		make19(player);
		make20(dealer);
		setBet(4000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(-4000);
	}

	@DisplayName("둘다 블랙잭, 버스트가 아닐 때 플레이어 딜러 점수 같으면 수익은 없다")
	@Test
	void sameScore() {
		make19(player);
		make19(dealer);
		setBet(5000);

		assertThat(calculateProfit(player, dealer)).isEqualTo(0);
	}

	void makeBlackjack(User user) {
		Card card1 = new Card(Denomination.TEN, Suits.CLOVER);
		Card card2 = new Card(Denomination.ACE, Suits.CLOVER);
		user.hit(card1);
		user.hit(card2);
	}

	void make20(User user) {
		Card card1 = new Card(Denomination.NINE, Suits.CLOVER);
		Card card2 = new Card(Denomination.ACE, Suits.CLOVER);
		user.hit(card1);
		user.hit(card2);
	}

	void make19(User user) {
		Card card1 = new Card(Denomination.NINE, Suits.DIAMOND);
		Card card2 = new Card(Denomination.TEN, Suits.DIAMOND);
		user.hit(card1);
		user.hit(card2);
	}

	void makeBurst(User user) {
		Card card1 = new Card(Denomination.NINE, Suits.DIAMOND);
		Card card2 = new Card(Denomination.TEN, Suits.DIAMOND);
		Card card3 = new Card(Denomination.JACK, Suits.DIAMOND);
		user.hit(card1);
		user.hit(card2);
		user.hit(card3);
	}

	private void setBet(double bet) {
		player.setBet(bet);
	}
}
