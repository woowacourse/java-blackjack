package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    private List<Card> cards;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.HEART, Type.TEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.CLOVER, Type.EIGHT)
                )
        );
        cardDeck = new CardDeck(cards);
    }

    @DisplayName("카드 덱에서 랜덤으로 카드 한 장 추출 확인")
    @Test
    void getOneCardFromCardDeck() {
        Card card = cardDeck.pop();
        assertThat(card).isEqualTo(new Card(Symbol.CLOVER, Type.EIGHT));
    }

    @DisplayName("카드 덱이 빈 경우 한 장을 뽑았을 때 예외가 발생하는지 확인")
    @Test
    void isEmptyTest() {
        CardDeck emptyCardDeck = new CardDeck(new ArrayList<>());
        assertThatThrownBy(emptyCardDeck::pop)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 덱이 비었습니다.");
    }
}
