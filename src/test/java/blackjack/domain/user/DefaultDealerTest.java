package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultDealerTest {
	private Dealer dealer;
	private Card aceSpade;
	private Card sixDiamond;
	private Card tenClub;
	private Card jackHeart;

	@BeforeEach
	void setUp() {
		dealer = DefaultDealer.dealer();

		aceSpade = Card.of(Symbol.ACE, Type.SPADE);
		sixDiamond = Card.of(Symbol.SIX, Type.DIAMOND);
		tenClub = Card.of(Symbol.TEN, Type.CLUB);
		jackHeart = Card.of(Symbol.JACK, Type.HEART);
	}

	@Test
	void create() {
		// then
		assertThat(dealer).isNotNull();
	}

	@Test
	void shouldReceiveCard() {
		dealer.giveCards(tenClub, sixDiamond);
		assertThat(dealer.shouldReceiveCard()).isTrue();

		dealer.giveCards(aceSpade);
		assertThat(dealer.shouldReceiveCard()).isFalse();
	}

	@Test
	void showFirstCard() {
		dealer.giveCards(tenClub, sixDiamond);
		assertThat(dealer.getFirstCard()).isEqualTo(tenClub);

		dealer.giveCards(jackHeart, aceSpade);
		assertThat(dealer.getFirstCard()).isEqualTo(tenClub);
	}

	@Test
	void isWinner() {
		dealer.giveCards(tenClub, aceSpade);
		assertThat(dealer.isWinner(dealer.getScore())).isTrue();

		dealer.giveCards(tenClub);
		assertThat(dealer.isWinner(dealer.getScore())).isTrue();

		dealer.giveCards(aceSpade);
		assertThat(dealer.isWinner(dealer.getScore())).isFalse();
	}

	@Test
	void giveCards() {
		dealer.giveCards(aceSpade, sixDiamond);
		assertThat(dealer.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond));

		dealer.giveCards(tenClub, jackHeart);
		assertThat(dealer.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@Test
	void getScore() {
		dealer.giveCards(aceSpade);
		assertThat(dealer.getScore()).isEqualTo(Score.of(11));

		dealer.giveCards(jackHeart);
		assertThat(dealer.getScore()).isEqualTo(Score.of(21));

		dealer.giveCards(sixDiamond);
		assertThat(dealer.getScore()).isEqualTo(Score.of(17));

		dealer.giveCards(aceSpade);
		assertThat(dealer.getScore()).isEqualTo(Score.of(18));

		dealer.giveCards(sixDiamond);
		assertThat(dealer.getScore()).isEqualTo(Score.of(24));
	}

	@Test
	void isBust() {
		dealer.giveCards(tenClub, aceSpade);
		assertThat(dealer.isBust()).isFalse();

		dealer.giveCards(tenClub);
		assertThat(dealer.isBust()).isFalse();

		dealer.giveCards(aceSpade);
		assertThat(dealer.isBust()).isTrue();
	}

	@Test
	void isNotBust() {
		dealer.giveCards(tenClub, aceSpade);
		assertThat(dealer.isNotBust()).isTrue();

		dealer.giveCards(tenClub);
		assertThat(dealer.isNotBust()).isTrue();

		dealer.giveCards(aceSpade);
		assertThat(dealer.isNotBust()).isFalse();
	}

	@Test
	void getCards() {
		// given
		List<Card> expected = new ArrayList<>();

		// when-then
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCards(aceSpade);
		expected.add(aceSpade);
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCards(sixDiamond);
		expected.add(sixDiamond);
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCards(tenClub);
		expected.add(tenClub);
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCards(jackHeart);
		expected.add(jackHeart);
		assertThat(dealer.getHand()).isEqualTo(expected);
	}

	@Test
	void countCards() {
		dealer.giveCards();
		assertThat(dealer.countCards()).isEqualTo(0);

		dealer.giveCards(aceSpade);
		assertThat(dealer.countCards()).isEqualTo(1);

		dealer.giveCards(tenClub);
		assertThat(dealer.countCards()).isEqualTo(2);

		dealer.giveCards(sixDiamond);
		assertThat(dealer.countCards()).isEqualTo(3);

		dealer.giveCards(jackHeart, jackHeart);
		assertThat(dealer.countCards()).isEqualTo(5);
	}

	@Test
	void getName() {
		// then
		assertThat(dealer.getName()).isEqualTo("딜러");
	}
}