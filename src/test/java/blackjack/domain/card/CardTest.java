package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
	private Card card1;
	private Card card2;
	private Card card3;
	private Card card4;

	@BeforeEach
	void setUp() {
		card1 = Card.of(Symbol.ACE, Type.CLUB);
		card2 = Card.of(Symbol.TWO, Type.HEART);
		card3 = Card.of(Symbol.THREE, Type.DIAMOND);
		card4 = Card.of(Symbol.KING, Type.SPADE);
	}

	@Test
	void Card() {
		assertThat(card1).isNotNull();
		assertThat(card2).isNotNull();
		assertThat(card3).isNotNull();
		assertThat(card4).isNotNull();
	}

	@Test
	void isAce() {
		assertThat(card1.isAce()).isTrue();
		assertThat(card2.isAce()).isFalse();
        assertThat(card3.isAce()).isFalse();
        assertThat(card4.isAce()).isFalse();
    }

	@Test
	void getScore() {
		assertThat(card1.getScore()).isEqualTo(Score.of(1));
        assertThat(card2.getScore()).isEqualTo(Score.of(2));
        assertThat(card3.getScore()).isEqualTo(Score.of(3));
        assertThat(card4.getScore()).isEqualTo(Score.of(10));
    }

	@Test
	void equals() {
		assertThat(card1).isEqualTo(Card.of(Symbol.ACE, Type.CLUB));
        assertThat(card2).isEqualTo(Card.of(Symbol.TWO, Type.HEART));
        assertThat(card3).isEqualTo(Card.of(Symbol.THREE, Type.DIAMOND));
        assertThat(card4).isEqualTo(Card.of(Symbol.KING, Type.SPADE));
    }

	@Test
	void getName() {
		assertThat(card1.getName()).isEqualTo("A 클로버");
        assertThat(card2.getName()).isEqualTo("2 하트");
        assertThat(card3.getName()).isEqualTo("3 다이아몬드");
        assertThat(card4.getName()).isEqualTo("K 스페이드");
	}
}
