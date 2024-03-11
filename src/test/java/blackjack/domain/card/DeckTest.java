package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck mockDeck;
    private Deck realDeck;

    @BeforeEach
    void setUP() {
        LinkedList<Card> cardList = new LinkedList<>(List.of(
                new Card(CardShape.HEART, CardNumber.KING), new Card(CardShape.HEART, CardNumber.QUEEN),
                new Card(CardShape.HEART, CardNumber.ACE), new Card(CardShape.HEART, CardNumber.TWO),
                new Card(CardShape.DIAMOND, CardNumber.KING), new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                new Card(CardShape.DIAMOND, CardNumber.ACE), new Card(CardShape.DIAMOND, CardNumber.TWO)));

        mockDeck = new Deck(cardList);
        realDeck = new Deck();
    }

    @Test
    @DisplayName("카드 덱은 52장의 카드로 이루어져 있다.")
    void deckSizeTest() {
        assertThat(realDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("맨 위의 카드를 한 장 뽑는다.")
    void drawTest() {
        assertThat(mockDeck.draw()).isEqualTo(new Card(CardShape.HEART, CardNumber.KING));
        assertThat(mockDeck.draw()).isEqualTo(new Card(CardShape.HEART, CardNumber.QUEEN));
        assertThat(mockDeck.draw()).isEqualTo(new Card(CardShape.HEART, CardNumber.ACE));
    }
}
