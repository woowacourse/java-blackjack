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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
	private static Card aceSpade;
	private static Card sixDiamond;
	private static Card tenClub;
	private static Card jackHeart;

	private Playable player;

	@BeforeAll
	static void beforeAll() {
		aceSpade = Card.of(Symbol.ACE, Type.SPADE);
		sixDiamond = Card.of(Symbol.SIX, Type.DIAMOND);
		tenClub = Card.of(Symbol.TEN, Type.CLUB);
		jackHeart = Card.of(Symbol.JACK, Type.HEART);
	}

	@BeforeEach
	void setUp() {
		player = Player.of("그니");
	}

	@Test
	void of_IsNotNull() {
		assertThat(player).isNotNull();
	}

	@Test
	void isWinner() {
		player.giveCard(tenClub);
		assertThat(player.isWinner(Score.of(9))).isTrue();
		assertThat(player.isWinner(Score.of(10))).isFalse();

		player.giveCard(aceSpade);
		assertThat(player.isWinner(Score.of(20))).isTrue();
		assertThat(player.isWinner(Score.of(21))).isFalse();

		player.giveCard(aceSpade);
		assertThat(player.isWinner(Score.of(11))).isTrue();
		assertThat(player.isWinner(Score.of(12))).isFalse();

		player.giveCard(jackHeart);
		assertThat(player.isWinner(Score.of(0))).isFalse();
	}

	@Test
	void giveCards() {
		player.giveCards(Arrays.asList(aceSpade, sixDiamond));
		assertThat(player.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond));

		player.giveCards(Arrays.asList(tenClub, jackHeart));
		assertThat(player.getHand())
				.isEqualTo(Arrays.asList(aceSpade, sixDiamond, tenClub, jackHeart));
	}

	@Test
	void getScore() {
		player.giveCard(aceSpade);
		assertThat(player.getScore()).isEqualTo(Score.of(11));

		player.giveCard(jackHeart);
		assertThat(player.getScore()).isEqualTo(Score.of(21));

		player.giveCard(sixDiamond);
		assertThat(player.getScore()).isEqualTo(Score.of(17));

		player.giveCard(aceSpade);
		assertThat(player.getScore()).isEqualTo(Score.of(18));

		player.giveCard(sixDiamond);
		assertThat(player.getScore()).isEqualTo(Score.of(24));
	}

	@Test
	void isBust() {
		player.giveCards(Arrays.asList(tenClub, aceSpade));
		assertThat(player.isBust()).isFalse();

		player.giveCard(tenClub);
		assertThat(player.isBust()).isFalse();

		player.giveCard(aceSpade);
		assertThat(player.isBust()).isTrue();
	}

	@Test
	void getCards() {
		// given
		List<Card> expected = new ArrayList<>();

		// when-then
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCard(aceSpade);
		expected.add(aceSpade);
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCard(sixDiamond);
		expected.add(sixDiamond);
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCard(tenClub);
		expected.add(tenClub);
		assertThat(player.getHand()).isEqualTo(expected);

		player.giveCard(jackHeart);
		expected.add(jackHeart);
		assertThat(player.getHand()).isEqualTo(expected);
	}

	@Test
	void countCards() {
		player.giveCard(aceSpade);
		assertThat(player.countCards()).isEqualTo(1);

		player.giveCard(tenClub);
		assertThat(player.countCards()).isEqualTo(2);

		player.giveCard(sixDiamond);
		assertThat(player.countCards()).isEqualTo(3);

		player.giveCards(Arrays.asList(jackHeart, jackHeart));
		assertThat(player.countCards()).isEqualTo(5);
	}

	@Test
	void getName() {
		assertThat(player.getName()).isEqualTo("그니");
	}

	@Test
	void canReceiveCard_ReturnTrue() {
		player.giveCard(tenClub);
		player.giveCard(aceSpade);
		assertThat(player.canReceiveCard()).isTrue();
	}

	@Test
	void canReceiveCard_ReturnFalse() {
		player.giveCard(tenClub);
		player.giveCard(sixDiamond);
		player.giveCard(sixDiamond);
		assertThat(player.canReceiveCard()).isFalse();
	}
}
