package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.utils.FixedCardDeck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    @DisplayName("카드 뽑기")
    void pop() {
        final FixedCardDeck fixedCardDeck = new FixedCardDeck();
        Card card = fixedCardDeck.pop();

        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.ACE));
    }

    @Test
    @DisplayName("연속 카드 뽑기")
    void pop2() {
        final FixedCardDeck fixedCardDeck = new FixedCardDeck();
        Card card = fixedCardDeck.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.ACE));
        card = fixedCardDeck.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.TWO));
    }

    @Test
    @DisplayName("52번 pop empty 확인")
    void size() {
        final FixedCardDeck fixedCardDeck = new FixedCardDeck();

        for (int i = 0; i < 52; i++) {
            fixedCardDeck.pop();
        }

        assertThat(fixedCardDeck.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("51번 pop notEmpty 확인")
    void size2() {
        final FixedCardDeck fixedCardDeck = new FixedCardDeck();


        for (int i = 0; i < 51; i++) {
            fixedCardDeck.pop();
        }

        assertThat(fixedCardDeck.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("53번 pop exception 확인")
    void size3() {
        final FixedCardDeck fixedCardDeck = new FixedCardDeck();

        for (int i = 0; i < 52; i++) {
            fixedCardDeck.pop();
        }

        assertThatThrownBy(fixedCardDeck::pop).isInstanceOf(IllegalArgumentException.class);
    }

}
