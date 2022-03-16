package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;

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
                List.of(Card.valueOf(QUEEN, CLUBS),
                        Card.valueOf(JACK, CLUBS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(Card.valueOf(FIVE, CLUBS));
        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("합이 21보다 작을 경우 버스트가 아니다.")
    void under21_isNotBust() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.valueOf(EIGHT, CLUBS),
                        Card.valueOf(SEVEN, CLUBS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(Card.valueOf(SIX, CLUBS));
        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("Ace를 포함한 합이 21보다 작을 경우 버스트가 아니다.")
    void under21withAce_isNotBust() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.valueOf(JACK, CLUBS),
                        Card.valueOf(KING, CLUBS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(Card.valueOf(ACE, CLUBS));
        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("Ace를 포함한 합이 21보다 클 경우 버스트다.")
    void under21withAce_isBust() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.valueOf(JACK, CLUBS),
                        Card.valueOf(KING, CLUBS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(Card.valueOf(ACE, CLUBS));
        holdingCard.add(Card.valueOf(ACE, SPADES));
        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("A가 4장 존재할 때 14로 계산할 수 있는가?")
    void fourAce_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.valueOf(ACE, CLUBS), Card.valueOf(ACE, SPADES),
                        Card.valueOf(ACE, HEARTS),
                        Card.valueOf(ACE, DIAMONDS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        int result = holdingCard.computeTotalScore();
        Assertions.assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("A가 4개 존재하고, 7이 추가되면 21로 계산할 수 있는가?")
    void fourAce_Add7_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.valueOf(ACE, CLUBS), Card.valueOf(ACE, SPADES),
                        Card.valueOf(ACE, HEARTS),
                        Card.valueOf(ACE, DIAMONDS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(Card.valueOf(SEVEN, DIAMONDS));
        int result = holdingCard.computeTotalScore();
        Assertions.assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("A가 4개 존재하고, 7과 10이 추가되면 21로 계산할 수 있는가?")
    void fourAce_Add17_calculate() {
        List<Card> cards = new ArrayList<>(
                List.of(Card.valueOf(ACE, CLUBS), Card.valueOf(ACE, SPADES),
                        Card.valueOf(ACE, HEARTS),
                        Card.valueOf(ACE, DIAMONDS)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(Card.valueOf(SEVEN, DIAMONDS));
        holdingCard.add(Card.valueOf(TEN, DIAMONDS));
        int result = holdingCard.computeTotalScore();
        Assertions.assertThat(result).isEqualTo(21);
    }
}
