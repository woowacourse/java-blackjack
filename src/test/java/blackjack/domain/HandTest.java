package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @DisplayName("생성되면 손에는 아무 카드도 없다.")
    @Test
    void Should_Create_When_NewDealer() {
        assertThat(hand.getAllCards().size()).isEqualTo(0);
    }

    @DisplayName("카드를 추가한다.")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        hand.add(card);

        assertThat(hand.getAllCards()).contains(card);
    }

    @DisplayName("손에 있는 모든 카드를 확인한다.")
    @Test
    void Should_Success_When_GetAllCards() {
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        assertThat(hand.getAllCards()).containsAll(List.of(card, card2, card3));
    }

    @DisplayName("들고 있는 카드에 ACE가 있는지 확인한다.")
    @Nested
    class hasACE {
        @DisplayName("ACE가 있으면 true을 리턴한다.")
        @Test
        void Should_True_When_hasACE() {
            Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
            hand.add(card);

            assertThat(hand.hasACE()).isTrue();
        }

        @DisplayName("ACE가 없으면 false를 리턴한다.")
        @Test
        void Should_False_When_hasACE() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            hand.add(card);

            assertThat(hand.hasACE()).isFalse();
        }
    }

    @DisplayName("손에 있는 모든 카드의 점수를 계산한다.")
    @Nested
    class totalScoreTest {
        @DisplayName("Jack, ACE 카드를 가지고 있을 때 점수의 합은 21점이다.")
        @Test
        void Should_TotalScore_When_HaveCard1() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
            hand.add(card);
            hand.add(card2);

            assertThat(hand.getTotalScore()).isEqualTo(21);
        }

        @DisplayName("NINE, TWO, ACE 가지고 있을 때 점수의 합은 22점이다.")
        @Test
        void Should_TotalScore_When_HaveCard2() {
            Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
            Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
            hand.add(card);
            hand.add(card2);
            hand.add(card3);

            assertThat(hand.getTotalScore()).isEqualTo(22);
        }
    }
}
