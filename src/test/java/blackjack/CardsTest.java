package blackjack;

import blackjack.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class CardsTest {
    private Cards cards;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.EIGHT),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.TEN)
                )
        ));
        cards = new Cards();
        cards.receiveDistributedCards(cardDeck);
    }

    @DisplayName("현재 보유 중인 카드의 총 점수 확인")
    @Test
    void currentScoreTest() {
        int score = cards.calculateScore();
        assertThat(score).isEqualTo(19);
    }
}
