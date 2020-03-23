package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    private Symbol ace = Symbol.ACE;
    private Symbol notAce = Symbol.KING;

    @Test
    @DisplayName("Symbol과 Type을 인자로 갖는 Card가 생성된다.")
    void card() {
        assertThat(new Card(Symbol.valueOf("ACE"), Type.valueOf("HEART"))).isNotNull();
    }

    @Test
    @DisplayName("#calculateExceptAce() : should return value of symbol")
    void calculateExceptAceSucceed() {
        Card card = new Card(notAce, Type.SPADE);

        int score = card.calculateExceptAce();
        assertThat(score).isEqualTo(notAce.getValue());
    }

    @Test
    @DisplayName("#calculateExceptAce() : should throw IllegalStateException with Ace")
    void calculateExceptAceFail() {
        Symbol symbol = Symbol.ACE;
        Card card = new Card(symbol, Type.SPADE);
        assertThatThrownBy(card::calculateExceptAce)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(Card.INVALID_ACE_ONLY_NOTACE_ARE_ALLOWED_MEESAGE);
    }

    @Test
    @DisplayName("#isAce() : should return true")
    void isAceTrue() {
        Card card = new Card(Symbol.ACE, Type.SPADE);
        assertTrue(card.isAce());
    }

    @Test
    @DisplayName("#isAce() : should return false")
    void isAceFalse() {
        Card card = new Card(Symbol.KING, Type.SPADE);
        assertFalse(card.isAce());
    }

    @Test
    @DisplayName("#isNotAce() : should return true")
    void isNotAceTrue() {
        Card card = new Card(Symbol.ACE, Type.SPADE);
        assertFalse(card.isNotAce());
    }

    @Test
    @DisplayName("#isNotAce() : should return false")
    void isNotAceFalse() {
        Card card = new Card(Symbol.ACE, Type.SPADE);
        assertFalse(card.isNotAce());
    }
}
