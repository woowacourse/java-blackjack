package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
	private Card aceClub;
	private Card twoHeart;
	private Card threeDiamond;
	private Card kingSpade;

	@BeforeEach
	void setUp() {
		aceClub = Card.of(Symbol.ACE, Type.CLUB);
		twoHeart = Card.of(Symbol.TWO, Type.HEART);
		threeDiamond = Card.of(Symbol.THREE, Type.DIAMOND);
		kingSpade = Card.of(Symbol.KING, Type.SPADE);
	}

	@Test
	void Card() {
		assertThat(aceClub).isNotNull();
		assertThat(twoHeart).isNotNull();
		assertThat(threeDiamond).isNotNull();
		assertThat(kingSpade).isNotNull();
	}

	@Test
	void isAce() {
		assertThat(aceClub.isAce()).isTrue();
		assertThat(twoHeart.isAce()).isFalse();
		assertThat(threeDiamond.isAce()).isFalse();
		assertThat(kingSpade.isAce()).isFalse();
	}

	@Test
	void getScore() {
		assertThat(aceClub.getScore()).isEqualTo(Score.of(1));
		assertThat(twoHeart.getScore()).isEqualTo(Score.of(2));
		assertThat(threeDiamond.getScore()).isEqualTo(Score.of(3));
		assertThat(kingSpade.getScore()).isEqualTo(Score.of(10));
	}

	@Test
	void equals() {
		assertThat(aceClub).isEqualTo(Card.of(Symbol.ACE, Type.CLUB));
		assertThat(twoHeart).isEqualTo(Card.of(Symbol.TWO, Type.HEART));
		assertThat(threeDiamond).isEqualTo(Card.of(Symbol.THREE, Type.DIAMOND));
		assertThat(kingSpade).isEqualTo(Card.of(Symbol.KING, Type.SPADE));
	}

	@Test
	void getName() {
		assertThat(aceClub.getName()).isEqualTo("A 클로버");
		assertThat(twoHeart.getName()).isEqualTo("2 하트");
		assertThat(threeDiamond.getName()).isEqualTo("3 다이아몬드");
		assertThat(kingSpade.getName()).isEqualTo("K 스페이드");
	}
}
