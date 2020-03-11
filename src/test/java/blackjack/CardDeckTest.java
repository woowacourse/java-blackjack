package blackjack;

import blackjack.domain.Card;
import blackjack.domain.CardDeck;
import blackjack.domain.Symbol;
import blackjack.domain.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {
    private List<Card> cards;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.EIGHT),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.HEART, Type.TEN)
                        )
        );
    }

    @DisplayName("카드 덱에서 랜덤으로 카드 한 장 추출 확인")
    @Test
    void getOneCardFromCardDeck() {
        CardDeck cardDeck = new CardDeck(cards);
        Card card = cardDeck.getOneCard();
        assertThat(card).isEqualTo(new Card(Symbol.CLOVER, Type.EIGHT));
    }
}
