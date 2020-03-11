package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class CardTest {
	@Test
	void 점수_계산() {
		assertThat(new Card(Symbol.ACE, Type.HEART).score()).isEqualTo(11);
		assertThat(new Card(Symbol.KING, Type.HEART).score()).isEqualTo(10);
	}

	@Test
	void Ace_유무() {
		Card card1 = new Card(Symbol.ACE, Type.HEART);
		assertThat(card1.isAce()).isTrue();

		Card card2 = new Card(Symbol.TWO, Type.HEART);
		assertThat(card2.isAce()).isFalse();
	}
}
