package blackjack;

import blackjack.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    private List<Card> cards;
    private CardDeck cardDeck;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.EIGHT),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.TEN)
                )
        );
        cardDeck = new CardDeck(cards);
        dealer = Dealer.getDealer();
    }

    @DisplayName("딜러의 점수가 16이하인지 확인")
    @Test
    void isUnderCriticalScore() {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(cardDeck.getOneCard());
        }
        assertThat(dealer.isUnderCriticalScore()).isFalse();
    }
}
