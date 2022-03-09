package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardTest {

    @Test
    @DisplayName("합이 21초과일 경우 버스트다")
    void over21_isBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.QUEEN), new Card(Symbol.CLOVER, CardNumber.JACK)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(Symbol.CLOVER, CardNumber.FIVE));
        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("합이 21보다 작을 경우 버스트가 아니다.")
    void under21_isNotBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.EIGHT), new Card(Symbol.CLOVER, CardNumber.SEVEN)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(Symbol.CLOVER, CardNumber.SIX));
        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("A가 4장 존재할 때 14로 계산할 수 있는가?")
    void fourAce_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.ACE), new Card(Symbol.SPADE, CardNumber.ACE),
                        new Card(Symbol.HEART, CardNumber.ACE), new Card(Symbol.DIAMOND, CardNumber.ACE)));
        HoldingCard holdingCard = new HoldingCard(cards);
        int result = holdingCard.calculateTotal();
        Assertions.assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("A가 4개 존재하고, 7이 추가되면 21로 계산할 수 있는가?")
    void fourAce_Add7_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.ACE), new Card(Symbol.SPADE, CardNumber.ACE),
                        new Card(Symbol.HEART, CardNumber.ACE), new Card(Symbol.DIAMOND, CardNumber.ACE)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(Symbol.DIAMOND, CardNumber.SEVEN));
        int result = holdingCard.calculateTotal();
        Assertions.assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("A가 4개 존재하고, 7과 10이 추가되면 21로 계산할 수 있는가?")
    void fourAce_Add17_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.ACE), new Card(Symbol.SPADE, CardNumber.ACE),
                        new Card(Symbol.HEART, CardNumber.ACE), new Card(Symbol.DIAMOND, CardNumber.ACE)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(Symbol.DIAMOND, CardNumber.SEVEN));
        holdingCard.add(new Card(Symbol.DIAMOND, CardNumber.TEN));
        int result = holdingCard.calculateTotal();
        Assertions.assertThat(result).isEqualTo(21);
    }
}
