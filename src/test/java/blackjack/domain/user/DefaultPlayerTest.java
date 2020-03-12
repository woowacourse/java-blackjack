package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
		player.giveCards(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART));

		assertThat(player.getCards())
				.isEqualTo(Arrays.asList(Card.of(Symbol.ACE, Type.CLUB),
						Card.of(Symbol.EIGHT, Type.HEART)));
	}

	@Test
	void calculateScore() {
		player.giveCards(Card.of(Symbol.TWO, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART));

		assertThat(player.calculateScore()).isEqualTo(Score.of(10));
	}

	@Test
	void calculateScore_WhenAceShouldBeEleven() {
		player.giveCards(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART));

		assertThat(player.calculateScore()).isEqualTo(Score.of(19));
	}

	@Test
	void calculateScore_WhenAceShouldBeOne() {
		player.giveCards(Card.of(Symbol.ACE, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.THREE, Type.SPADE));

		assertThat(player.calculateScore()).isEqualTo(Score.of(12));
	}

	@Test
	void isBust() {
		player.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.FOUR, Type.SPADE));

		assertThat(player.isBust()).isTrue();
	}

	@Test
	void isNotBust() {
		player.giveCards(Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.EIGHT, Type.HEART),
				Card.of(Symbol.THREE, Type.SPADE));

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
		assertThat(player.getCards()).isEqualTo(Arrays.asList(cards));
	}

	@Test
	void isName() {
	    assertThat(player.isName("그니")).isTrue();
        assertThat(player.isName("그니2")).isFalse();
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
