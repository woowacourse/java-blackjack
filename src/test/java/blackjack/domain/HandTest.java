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

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewDealer() {
        assertThat(hand.getAllCards().size()).isEqualTo(0);
    }

    @DisplayName("카드 추가 테스트")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        hand.add(card);

        assertThat(hand.getAllCards()).contains(card);
    }

    @DisplayName("카드 리스트에 ACE 존재 여부 확인 테스트")
    @Nested
    class hasACE {
        @DisplayName("ACE가 있을 때")
        @Test
        void Should_True_When_hasACE() {
            Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
            hand.add(card);

            assertThat(hand.hasACE()).isTrue();
        }

        @DisplayName("ACE가 없을 때")
        @Test
        void Should_False_When_hasACE() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            hand.add(card);

            assertThat(hand.hasACE()).isFalse();
        }
    }

    @DisplayName("카드 점수 합 테스트")
    @Nested
    class totalScoreTest {
        @DisplayName("Jack, ACE 카드를 가지고 있을 때")
        @Test
        void Should_TotalScore_When_HaveCard1() {
            Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
            Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);
            hand.add(card);
            hand.add(card2);

            assertThat(hand.getTotalScore()).isEqualTo(21);
        }
        @DisplayName("NINE, TWO, ACE 가지고 있을 대")
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

    @DisplayName("카드 Get 테스트")
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
}
