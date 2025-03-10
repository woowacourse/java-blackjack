package model.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackStateException;
import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에서 카드를 뽑는다.")
    @Test
    void getCardTest() {
        Deck deck = new Deck(new ArrayList<>(List.of(new Card(CardNumber.SEVEN, CardShape.DIAMOND))));
        assertThat(deck.getCard()).isEqualTo(new Card(CardNumber.SEVEN, CardShape.DIAMOND));
    }

    @DisplayName("덱에 카드가 남아있지 않다면 예외가 발생한다.")
    @Test
    void getNotExistCardTest() {
        Deck deck = new Deck(new ArrayList<>(List.of()));
        assertThatThrownBy(deck::getCard)
                .isInstanceOf(IllegalBlackjackStateException.class);
    }

}
