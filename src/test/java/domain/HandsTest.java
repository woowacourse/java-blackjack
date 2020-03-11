package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class HandsTest {
	@Test
	void 총_점수_계산() {
		Hands hands = new Hands();
		hands.add(new Card(Type.HEART, Symbol.ACE));
		hands.add(new Card(Type.DIAMOND, Symbol.KING));
		assertThat(hands.calculateTotalScore()).isEqualTo(21);
	}

	@Test
	void ACE_유무() {
		Hands hands1 = new Hands();
		hands1.add(new Card(Type.HEART, Symbol.ACE));
		hands1.add(new Card(Type.DIAMOND, Symbol.KING));
		assertThat(hands1.hasAce()).isTrue();

		Hands hands2 = new Hands();
		hands2.add(new Card(Type.HEART, Symbol.TWO));
		hands2.add(new Card(Type.DIAMOND, Symbol.KING));
		assertThat(hands2.hasAce()).isFalse();
	}

	@Test
	void 카드_추가() {
		List<Card> cards = new ArrayList<>(
			Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)));
		Hands hands = new Hands();
		for (Card card : cards) {
			hands.add(card);
		}
		hands.add(new Card(Type.CLUBS, Symbol.ACE));
		assertThat(hands.getSize()).isEqualTo(3);
	}
}
