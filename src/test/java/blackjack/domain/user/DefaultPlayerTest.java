package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DefaultPlayerTest {
	Player player;

	@BeforeEach
	void setUp() {
		player = DefaultPlayer.of("그니");
	}

	@Test
	void create() {
		assertThat(player).isNotNull();
	}

	@Test
	void giveCards() {
		// given
		player.giveCards(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART));

		// then
		assertThat(player.getHand())
				.isEqualTo(Arrays.asList(Card.of(Symbol.ACE, Type.CLUB),
						Card.of(Symbol.EIGHT, Type.HEART)));
	}

	@Test
	void calculateScore() {
		// given
		player.giveCards(Card.of(Symbol.TWO, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART));

		// then
		assertThat(player.getScore()).isEqualTo(Score.of(10));
	}

	@Test
	void calculateScore_WhenAceShouldBeEleven() {
		// given
		player.giveCards(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART));

		// then
		assertThat(player.getScore()).isEqualTo(Score.of(19));
	}

	@Test
	void calculateScore_WhenAceShouldBeOne() {
		// given
		player.giveCards(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.THREE, Type.SPADE));

		// then
		assertThat(player.getScore()).isEqualTo(Score.of(12));
	}

	@Test
	void isBust() {
		// given
		player.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.FOUR, Type.SPADE));

		// then
		assertThat(player.isBust()).isTrue();
	}

	@Test
	void isNotBust() {
		// given
		player.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.THREE, Type.SPADE));

		// then
		assertThat(player.isNotBust()).isTrue();
	}

	@Test
	void getCards() {
		// given
		Card[] cards = {
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.THREE, Type.SPADE)};
		player.giveCards(cards);

		// then
		assertThat(player.getHand()).isEqualTo(Arrays.asList(cards));
	}

	@Test
	void countCards() {
	    // given
        player.giveCards(Card.of(Symbol.TEN, Type.CLUB),
                Card.of(Symbol.EIGHT, Type.HEART),
                Card.of(Symbol.THREE, Type.SPADE));

        // then
        assertThat(player.countCards()).isEqualTo(3);
    }

	@Test
	void getName() {
	    assertThat(player.getName()).isEqualTo("그니");
	}

	@Test
	void isWinner() {
	    // given
        player.giveCards(Card.of(Symbol.TEN, Type.CLUB),
                Card.of(Symbol.EIGHT, Type.HEART),
                Card.of(Symbol.THREE, Type.SPADE));

        // then
        assertThat(player.isWinner(Score.of(20))).isTrue();
    }
}
