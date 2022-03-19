package blackjack.domain.card;

import static blackjack.domain.Fixture.*;
import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardTest {

    @Test
    @DisplayName("합이 21초과일 경우 버스트다")
    void over21_isBust() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(Card.valueOf(QUEEN, CLUBS), Card.valueOf(JACK, CLUBS), Card.valueOf(FIVE, CLUBS));
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("합이 21보다 작을 경우 버스트가 아니다.")
    void under21_isNotBust() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(Card.valueOf(EIGHT, CLUBS), Card.valueOf(SEVEN, CLUBS));
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("Ace를 포함한 합이 21보다 작을 경우 버스트가 아니다.")
    void under21withAce_isNotBust() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(Card.valueOf(JACK, CLUBS), Card.valueOf(KING, CLUBS), ACE_CLUBS);
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

    @Test
    @DisplayName("Ace를 포함한 합이 21보다 클 경우 버스트다.")
    void under21withAce_isBust() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(Card.valueOf(JACK, CLUBS), Card.valueOf(KING, CLUBS),
                ACE_CLUBS, ACE_SPADES);
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("A가 4장 존재할 때 14로 계산할 수 있는가?")
    void fourAce_calculate() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(ACE_CLUBS, ACE_SPADES, ACE_HEARTS, ACE_DIAMONDS);
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.computeTotalScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("A가 4개와 7이 있으면 21로 계산할 수 있는가?")
    void fourAce_Add7_calculate() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(ACE_CLUBS, ACE_SPADES,
                ACE_HEARTS, ACE_DIAMONDS, Card.valueOf(SEVEN, DIAMONDS));
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.computeTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("A가 4개와 7, 10이 있으면 21로 계산할 수 있는가?")
    void fourAce_Add17_calculate() {
        HoldingCard holdingCard = new HoldingCard();

        List<Card> cards = List.of(ACE_CLUBS, ACE_SPADES, ACE_HEARTS, ACE_DIAMONDS,
                Card.valueOf(SEVEN, DIAMONDS), Card.valueOf(TEN, DIAMONDS));
        cards.forEach(holdingCard::add);

        Assertions.assertThat(holdingCard.computeTotalScore()).isEqualTo(21);
    }
}
