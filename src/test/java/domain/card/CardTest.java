package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    @Test
    @DisplayName("Symbol과 Type을 인자로 갖는 Card가 생성된다.")
    void card() {
        assertThat(new Card(Symbol.valueOf("ACE"), Type.valueOf("HEART"))).isNotNull();
    }

    @Test
    @DisplayName("#calculateExceptAce() : should return value of symbol")
    void calculateExceptAceSucceed() {
        Symbol symbol = Symbol.QUEEN;
        Card card = new Card(symbol, Type.SPADE);

        int score = card.calculateExceptAce();
        assertThat(score).isEqualTo(symbol.getValue());
    }

    @Test
    @DisplayName("#calculateExceptAce() : should throw IllegalStateException with Ace")
    void calculateExceptAceFail() {
        Symbol symbol = Symbol.ACE;
        Card card = new Card(symbol, Type.SPADE);
        assertThatThrownBy(card::calculateExceptAce)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(String.format(Card.INVALID_ACE_MEESAGE, symbol.getPattern()));
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
}
