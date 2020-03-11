package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardHandTest {

    @Test
    @DisplayName("카드를 추가하는 지 테스트")
    void addCard_Test() {
        CardHand cardHand = new CardHand();
        Strategy strategy = new TempDeck();
        cardHand.addCard(strategy);
        assertThat(cardHand.getCards().contains(new Card(Symbol.ACE, Type.CLUB))).isTrue();
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
    void calculateScore_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.THREE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.TWO, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithNoAce()).isEqualTo(11);
    }
}
