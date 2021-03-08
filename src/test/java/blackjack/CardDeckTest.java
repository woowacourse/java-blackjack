package blackjack;

import blackjack.domain.Card;
import blackjack.domain.Denominations;
import blackjack.domain.Suits;
import blackjack.utils.CardDeck;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    @DisplayName("카트 뽑기")
    void pop() {
        CardDeck cardDeck = new FixedCardDeck();
        Card card = cardDeck.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.ACE));
    }

    @Test
    @DisplayName("연속 카트 뽑기")
    void pop2() {
        CardDeck cardDeck = new FixedCardDeck();
        Card card = cardDeck.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.ACE));
        card = cardDeck.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.TWO));
    }

    @Test
    @DisplayName("52번 pop empty 확인")
    void size() {
        CardDeck cardDeck = new FixedCardDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.pop();
        }

        assertThat(cardDeck.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("51번 pop notEmpty 확인")
    void size2() {
        CardDeck cardDeck = new FixedCardDeck();

        for (int i = 0; i < 51; i++) {
            cardDeck.pop();
        }

        assertThat(cardDeck.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("53번 pop exception 확인")
    void size3() {
        CardDeck cardDeck = new FixedCardDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.pop();
        }

        assertThatThrownBy(cardDeck::pop).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("초기 2장 확인")
    void initCards() {
        CardDeck cardDeck = new FixedCardDeck();

        List<Card> cards = cardDeck.initCards();

        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 2장 확인 일치")
    void initCards2() {
        CardDeck cardDeck = new FixedCardDeck();

        List<Card> cards = cardDeck.initCards();

        assertThat(cards).contains(Card.from(Suits.CLOVER, Denominations.ACE), Card.from(Suits.CLOVER, Denominations.TWO));
    }
}
