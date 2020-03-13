package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

/**
 *
 *    @author AnHyungJu
 */
@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {
	private static Stream<Arguments> generateDrawInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), true),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), true));
	}

	@Test
	void 생성() {
		assertThat(new Dealer().getName()).isEqualTo("딜러");
	}

	@ParameterizedTest
	@MethodSource("generateDrawInput")
	void 카드_받기(List<Card> cards, boolean expected) {
		Dealer dealer = new Dealer();
		for (Card card : cards) {
			dealer.hit(card);
		}
		assertThat(dealer.canHit()).isEqualTo(expected);
	}
}
