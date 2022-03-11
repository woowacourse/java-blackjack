package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.HoldingCard;
import blackjack.domain.card.Symbol;
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
                List.of(new Card(CardNumber.QUEEN, Symbol.CLOVER), new Card(CardNumber.JACK, Symbol.CLOVER)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(CardNumber.FIVE, Symbol.CLOVER));
        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("합이 21보다 작을 경우 버스트가 아니다.")
    void under21_isNotBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.EIGHT, Symbol.CLOVER), new Card(CardNumber.SEVEN, Symbol.CLOVER)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(CardNumber.SIX, Symbol.CLOVER));
        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("Ace를 포함한 합이 21보다 작을 경우 버스트가 아니다.")
    void under21withAce_isNotBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.JACK, Symbol.CLOVER), new Card(CardNumber.KING, Symbol.CLOVER)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(CardNumber.ACE, Symbol.CLOVER));
        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("Ace를 포함한 합이 21보다 클 경우 버스트다.")
    void under21withAce_isBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.JACK, Symbol.CLOVER), new Card(CardNumber.KING, Symbol.CLOVER)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(CardNumber.ACE, Symbol.CLOVER));
        holdingCard.add(new Card(CardNumber.ACE, Symbol.SPADE));
        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("A가 4장 존재할 때 14로 계산할 수 있는가?")
    void fourAce_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.ACE, Symbol.CLOVER), new Card(CardNumber.ACE, Symbol.SPADE),
                        new Card(CardNumber.ACE, Symbol.HEART), new Card(CardNumber.ACE, Symbol.DIAMOND)));
        HoldingCard holdingCard = new HoldingCard(cards);
        int result = holdingCard.calculateTotal();
        Assertions.assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("A가 4개 존재하고, 7이 추가되면 21로 계산할 수 있는가?")
    void fourAce_Add7_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.ACE, Symbol.CLOVER), new Card(CardNumber.ACE, Symbol.SPADE),
                        new Card(CardNumber.ACE, Symbol.HEART), new Card(CardNumber.ACE, Symbol.DIAMOND)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(CardNumber.SEVEN, Symbol.DIAMOND));
        int result = holdingCard.calculateTotal();
        Assertions.assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("A가 4개 존재하고, 7과 10이 추가되면 21로 계산할 수 있는가?")
    void fourAce_Add17_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.ACE, Symbol.CLOVER), new Card(CardNumber.ACE, Symbol.SPADE),
                        new Card(CardNumber.ACE, Symbol.HEART), new Card(CardNumber.ACE, Symbol.DIAMOND)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(CardNumber.SEVEN, Symbol.DIAMOND));
        holdingCard.add(new Card(CardNumber.TEN, Symbol.DIAMOND));
        int result = holdingCard.calculateTotal();
        Assertions.assertThat(result).isEqualTo(21);
    }
}
