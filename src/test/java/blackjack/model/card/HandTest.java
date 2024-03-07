package blackjack.model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // when
        Hand hand = new Hand(() -> new Card(Suit.HEART, Denomination.TWO));

        // then
        assertThat(hand.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("카드 합 계산은 카드 숫자를 기본으로 한다")
    void calculateCardsTotalTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.KING)
        );
        Hand hand = new Hand(cards);

        // when
        int actualTotal = hand.calculateCardsTotal();

        // then
        int expectedTotal = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }


    @Test
    @DisplayName("BlackJack인지 확인한다")
    void checkBlackJackTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.JACK),
                new Card(Suit.HEART, Denomination.ACE)
        );
        Hand hand = new Hand(cards);

        // when & then
        assertThat(hand.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void addCardTest() {
        // when
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        );
        Hand hand = new Hand(cards);

        // when
        hand.addCard(() -> new Card(Suit.HEART, Denomination.TWO));

        // then
        assertThat(hand.getCards()).hasSize(3);
    }
}
