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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DefaultPlayerTest {
	Player player;
	private Card aceSpade;
	private Card sixDiamond;
	private Card tenClub;
	private Card jackHeart;

	@BeforeEach
	void setUp() {
		player = DefaultPlayer.of("그니");

		aceSpade = Card.of(Symbol.ACE, Type.SPADE);
		sixDiamond = Card.of(Symbol.SIX, Type.DIAMOND);
		tenClub = Card.of(Symbol.TEN, Type.CLUB);
		jackHeart = Card.of(Symbol.JACK, Type.HEART);
	}

	@Test
	void create() {
		assertThat(player).isNotNull();
	}

	@Test
	void isWinner() {
		player.giveCards(tenClub);
		assertThat(player.isWinner(Score.of(9))).isTrue();
		assertThat(player.isWinner(Score.of(10))).isFalse();

		player.giveCards(aceSpade);
		assertThat(player.isWinner(Score.of(20))).isTrue();
		assertThat(player.isWinner(Score.of(21))).isFalse();

		player.giveCards(aceSpade);
		assertThat(player.isWinner(Score.of(11))).isTrue();
		assertThat(player.isWinner(Score.of(12))).isFalse();

		player.giveCards(jackHeart);
		assertThat(player.isWinner(Score.of(0))).isFalse();
	}

	@Test
	void giveCards() {
		player.giveCards(aceSpade, sixDiamond);
		assertThat(player.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond));

		player.giveCards(tenClub, jackHeart);
		assertThat(player.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@Test
	void getScore() {
		player.giveCards(aceSpade);
		assertThat(player.getScore()).isEqualTo(Score.of(11));

		player.giveCards(jackHeart);
		assertThat(player.getScore()).isEqualTo(Score.of(21));

		player.giveCards(sixDiamond);
		assertThat(player.getScore()).isEqualTo(Score.of(17));

		player.giveCards(aceSpade);
		assertThat(player.getScore()).isEqualTo(Score.of(18));

		player.giveCards(sixDiamond);
		assertThat(player.getScore()).isEqualTo(Score.of(24));
	}

	@Test
	void isBust() {
		player.giveCards(tenClub, aceSpade);
		assertThat(player.isBust()).isFalse();

		player.giveCards(tenClub);
		assertThat(player.isBust()).isFalse();

		player.giveCards(aceSpade);
		assertThat(player.isBust()).isTrue();
	}

	@Test
	void isNotBust() {
		player.giveCards(tenClub, aceSpade);
		assertThat(player.isNotBust()).isTrue();

		player.giveCards(tenClub);
		assertThat(player.isNotBust()).isTrue();

		player.giveCards(aceSpade);
		assertThat(player.isNotBust()).isFalse();
	}

	@Test
	void getCards() {
		// given
		List<Card> expected = new ArrayList<>();

		// when-then
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCards(aceSpade);
		expected.add(aceSpade);
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCards(sixDiamond);
		expected.add(sixDiamond);
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCards(tenClub);
		expected.add(tenClub);
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCards(jackHeart);
		expected.add(jackHeart);
		assertThat(player.getHand()).isEqualTo(expected);
	}

	@Test
	void countCards() {
		player.giveCards();
		assertThat(player.countCards()).isEqualTo(0);

		player.giveCards(aceSpade);
		assertThat(player.countCards()).isEqualTo(1);

		player.giveCards(tenClub);
		assertThat(player.countCards()).isEqualTo(2);

		player.giveCards(sixDiamond);
		assertThat(player.countCards()).isEqualTo(3);

		player.giveCards(jackHeart, jackHeart);
		assertThat(player.countCards()).isEqualTo(5);
    }

	@Test
	void getName() {
	    assertThat(player.getName()).isEqualTo("그니");
	}
}
