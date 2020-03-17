package blackjack;

import blackjack.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    private CardDeck cardDeck;
    private Dealer dealer;

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
        dealer = Dealer.getDealer();
    }

    @DisplayName("딜러의 점수가 16이하인지 확인")
    @Test
    void isUnderCriticalScore() {
        dealer.receiveDistributedCards(cardDeck);
        assertThat(dealer.isReceivableOneMoreCard()).isFalse();
    }

    @DisplayName("딜러의 이름을 반환하는지 확인")
    @Test
    void getNameTest() {
        User dealer = Dealer.getDealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @DisplayName("딜러의 초기 카드 오픈상태 확인")
    @Test
    void getInitialCardsTest() {
        dealer.receiveDistributedCards(cardDeck);
        assertThat(dealer.getInitialCards()).containsExactly(new Card(Symbol.CLOVER, Type.EIGHT));
    }

    @AfterEach
    void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field dealer_instance = Dealer.class.getDeclaredField("dealerInstance");
        dealer_instance.setAccessible(true);
        dealer_instance.set(null, null);
    }
}
