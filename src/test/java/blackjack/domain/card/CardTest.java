package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static blackjack.domain.testAssistant.TestAssistant.createCard;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

	@DisplayName("of()가 인스턴스를 반환하는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"ACE,CLUB", "TWO,HEART", "THREE,DIAMOND", "KING,SPADE"})
	void of_ValidInput_IsNotNull(String input) {
		Card card = createCard(input);
		assertThat(card).isNotNull();
	}

	@DisplayName("isAce()가 ace일 경우 true인지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"ACE,CLUB", "ACE,HEART", "ACE,DIAMOND", "ACE,SPADE"})
	void isAce_AceClub_IsTrue(String input) {
		Card card = createCard(input);
		assertThat(card.isAce()).isTrue();
	}

	@DisplayName("isAce()가 ace가 아닐 경우 false인지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"TWO,HEART", "THREE,DIAMOND", "KING,SPADE", "JACK,CLUB"})
	void isAce_NotAce_IsFalse(String input) {
		Card card = createCard(input);
		assertThat(card.isAce()).isFalse();
	}
}
