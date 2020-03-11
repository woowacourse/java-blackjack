package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardHandTest {
    Strategy strategy = new TempDeck(Arrays.asList(
            new Card(Symbol.EIGHT, Type.DIAMOND),
            new Card(Symbol.TWO, Type.DIAMOND),
            new Card(Symbol.FIVE, Type.DIAMOND)
    ));

    @Test
    @DisplayName("카드를 추가하는 지 테스트")
    void addCard_Test() {
        CardHand cardHand = new CardHand();

        cardHand.addCard(strategy);
        assertThat(cardHand.getCards().contains(new Card(Symbol.EIGHT, Type.DIAMOND))).isTrue();
    }

    @Test
    @DisplayName("ACE를 가지고 있는 지 테스트")
    void isAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.ACE, Type.CLUB));
        assertThat(cardHand.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드 점수 계산")
    void calculateScoreWithNoAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.THREE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.TWO, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithNoAce()).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace를 가지고 있을때 - Ace 11점일때")
    void calculateScoreWithElevenAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.THREE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.ACE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithAce()).isEqualTo(20);
    }
    @Test
    @DisplayName("Ace를 가지고 있을때 - Ace 1점일때")
    void calculateScoreWithOneAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.FIVE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.ACE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithAce()).isEqualTo(12);
    }
}
