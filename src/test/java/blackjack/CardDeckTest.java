package blackjack;

import blackjack.domain.Card;
import blackjack.domain.CardDeck;
import blackjack.domain.Symbol;
import blackjack.domain.Type;
import blackjack.exception.CardDeckEmptyException;
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
                        new Card(Symbol.CLOVER, Type.EIGHT),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.HEART, Type.TEN)
                )
        );
        cardDeck = new CardDeck(cards);
    }

    @DisplayName("카드 덱에서 카드 한 장 추출 확인")
    @Test
    void getOneCardFromCardDeck() {
        Card card = cardDeck.getOneCard();
        assertThat(card).isEqualTo(new Card(Symbol.CLOVER, Type.EIGHT));
    }

    @DisplayName("카드 덱이 빈 경우 한 장을 뽑았을 때 예외가 발생하는지 확인")
    @Test
    void isEmptyTest() {
        CardDeck emptyCardDeck = new CardDeck(new ArrayList<>());
        assertThatThrownBy(emptyCardDeck::getOneCard)
                .isInstanceOf(CardDeckEmptyException.class)
                .hasMessage("카드 덱이 비었습니다.");
    }
}
