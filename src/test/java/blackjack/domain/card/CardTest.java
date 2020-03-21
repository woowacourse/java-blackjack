package blackjack.domain.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.Symbol.*;
import static blackjack.domain.card.Type.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
	private static Card aceClub;
	private static Card twoHeart;
	private static Card threeDiamond;
	private static Card kingSpade;

	@BeforeAll
	static void beforeAll() {
		aceClub = Card.of(ACE, CLUB);
		twoHeart = Card.of(TWO, HEART);
		threeDiamond = Card.of(THREE, DIAMOND);
		kingSpade = Card.of(KING, SPADE);
	}

	@DisplayName("of()가 인스턴스를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("of_ValidInput_IsNotNull")
	void of_ValidInput_IsNotNull(Card card) {
		assertThat(card).isNotNull();
	}

	static Stream<Card> of_ValidInput_IsNotNull() {
		return Stream.of(aceClub, twoHeart, threeDiamond, kingSpade);
	}

	@DisplayName("isAce()가 ace일 경우 true인지 테스트")
	@Test
	void isAce_AceClub_IsTrue() {
		assertThat(aceClub.isAce()).isTrue();
	}

	@DisplayName("isAce()가 ace가 아닐 경우 false인지 테스트")
	@ParameterizedTest
	@MethodSource("isAce_NotAce_IsFalse")
	void isAce_NotAce_IsFalse(Card card) {
		assertThat(card.isAce()).isFalse();
	}

	static Stream<Card> isAce_NotAce_IsFalse() {
		return Stream.of(twoHeart, threeDiamond, kingSpade);
	}

	@DisplayName("getName()이 적절한 이름을 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("getName_Card_ReturnCardName")
	void getName_Card_ReturnCardName(Card card, String cardName) {
		assertThat(card.getName()).isEqualTo(cardName);
	}

	static Stream<Arguments> getName_Card_ReturnCardName() {
		return Stream.of(Arguments.of(aceClub, "A 클로버"),
				Arguments.of(twoHeart, "2 하트"),
				Arguments.of(threeDiamond, "3 다이아몬드"),
				Arguments.of(kingSpade, "K 스페이드"));
	}
}
