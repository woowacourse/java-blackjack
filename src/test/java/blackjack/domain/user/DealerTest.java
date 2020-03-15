package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
	private static Card aceSpade;
	private static Card sixDiamond;
	private static Card tenClub;
	private static Card jackHeart;

	private Playable dealer;

	@BeforeAll
	static void beforeAll() {
		aceSpade = Card.of(Symbol.ACE, Type.SPADE);
		sixDiamond = Card.of(Symbol.SIX, Type.DIAMOND);
		tenClub = Card.of(Symbol.TEN, Type.CLUB);
		jackHeart = Card.of(Symbol.JACK, Type.HEART);
	}

	@BeforeEach
	void setUp() {
		dealer = Dealer.dealer();
	}

	@Test
	void dealer_IsNotNull() {
		// then
		assertThat(dealer).isNotNull();
	}

	@Test
	void getStartHand_IsEqualToFirstCard() {
		dealer.giveCards(Arrays.asList(tenClub, sixDiamond));
		assertThat(dealer.getStartHand()).isEqualTo(Collections.singletonList(tenClub));

		dealer.giveCards(Arrays.asList(jackHeart, aceSpade));
		assertThat(dealer.getStartHand()).isEqualTo(Collections.singletonList(tenClub));
	}

	@Test
	void isWinner() {
		dealer.giveCard(tenClub);
		assertThat(dealer.isWinner(Score.of(11))).isTrue();

		dealer.giveCards(Arrays.asList(tenClub, aceSpade));
		assertThat(dealer.isWinner(Score.of(20))).isTrue();

		dealer.giveCard(aceSpade);
		assertThat(dealer.isWinner(Score.of(0))).isFalse();
	}

	@Test
	void giveCards() {
		dealer.giveCards(Arrays.asList(aceSpade, sixDiamond));
		assertThat(dealer.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond));

		dealer.giveCards(Arrays.asList(tenClub, jackHeart));
		assertThat(dealer.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@Test
	void getScore() {
		dealer.giveCard(aceSpade);
		assertThat(dealer.getScore()).isEqualTo(Score.of(11));

		dealer.giveCard(jackHeart);
		assertThat(dealer.getScore()).isEqualTo(Score.of(21));

		dealer.giveCard(sixDiamond);
		assertThat(dealer.getScore()).isEqualTo(Score.of(17));

		dealer.giveCard(aceSpade);
		assertThat(dealer.getScore()).isEqualTo(Score.of(18));

		dealer.giveCard(sixDiamond);
		assertThat(dealer.getScore()).isEqualTo(Score.of(24));
	}

	@Test
	void isBust() {
		dealer.giveCards(Arrays.asList(tenClub, aceSpade));
		assertThat(dealer.isBust()).isFalse();

		dealer.giveCard(tenClub);
		assertThat(dealer.isBust()).isFalse();

		dealer.giveCard(aceSpade);
		assertThat(dealer.isBust()).isTrue();
	}

	@Test
	void getCards() {
		// given
		List<Card> expected = new ArrayList<>();

		// when-then
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCard(aceSpade);
		expected.add(aceSpade);
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCard(sixDiamond);
		expected.add(sixDiamond);
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCard(tenClub);
		expected.add(tenClub);
		assertThat(dealer.getHand()).isEqualTo(expected);

		dealer.giveCard(jackHeart);
		expected.add(jackHeart);
		assertThat(dealer.getHand()).isEqualTo(expected);
	}

	@Test
	void countCards() {
		dealer.giveCard(aceSpade);
		assertThat(dealer.countCards()).isEqualTo(1);

		dealer.giveCard(tenClub);
		assertThat(dealer.countCards()).isEqualTo(2);

		dealer.giveCard(sixDiamond);
		assertThat(dealer.countCards()).isEqualTo(3);

		dealer.giveCards(Arrays.asList(jackHeart, jackHeart));
		assertThat(dealer.countCards()).isEqualTo(5);
	}

	@Test
	void getName() {
		// then
		assertThat(dealer.getName()).isEqualTo("딜러");
	}

	@Test
	void canReceiveCard_ReturnTrue() {
		dealer.giveCard(tenClub);
		dealer.giveCard(sixDiamond);
		assertThat(dealer.canReceiveCard()).isTrue();
	}

	@Test
	void canReceiveCard_ReturnFalse() {
		dealer.giveCard(tenClub);
		dealer.giveCard(sixDiamond);
		dealer.giveCard(aceSpade);
		assertThat(dealer.canReceiveCard()).isFalse();
	}
}