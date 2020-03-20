package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                        new Card(Symbol.HEART, Type.TEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.CLOVER, Type.EIGHT))
        ));
        dealer = new Dealer();
    }

    @DisplayName("딜러의 점수가 16이하인지 확인")
    @Test
    void isUnderCriticalScore() {
        dealer.receiveInitialCards(cardDeck);
        assertThat(dealer.isReceivableOneMoreCard()).isFalse();
    }

    @DisplayName("딜러의 이름을 반환하는지 확인")
    @Test
    void getNameTest() {
        User dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @DisplayName("딜러의 초기 카드 오픈상태 확인")
    @Test
    void getInitialCardsTest() {
        dealer.receiveInitialCards(cardDeck);
        assertThat(dealer.getInitialCards()).containsExactly(new Card(Symbol.CLOVER, Type.EIGHT));
    }
}
