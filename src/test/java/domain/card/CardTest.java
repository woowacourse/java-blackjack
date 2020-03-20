package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardTest {
    private Card aceCard;
    private Card kingCard;

    @BeforeEach
    void setUp() {
        aceCard = new Card(Symbol.valueOf("ACE"), Type.valueOf("HEART"));
        kingCard = new Card(Symbol.valueOf("KING"), Type.valueOf("HEART"));
    }

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
    @DisplayName(("#calculateExceptAce() :should throw IllegalStateException with Ace"))
    void calculateExceptAceFail() {
        Symbol symbol = Symbol.ACE;
        Card card = new Card(symbol, Type.SPADE);
        assertThatThrownBy(card::calculateExceptAce)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(String.format(Card.INVALID_ACE_MEESAGE, symbol.getPattern()));
    }
}
