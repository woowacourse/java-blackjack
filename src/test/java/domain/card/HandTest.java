package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(
                List.of(
                        new Card(Suit.CLOVER, Rank.FOUR),
                        new Card(Suit.CLOVER, Rank.ACE)
                ))
        );
    }

    @Test
    @DisplayName("처음 2장을 받은 hand에 카드를 한 장 받으면 hand는 카드 3장을 가지고 있다.")
    void addCard() {
        hand.addCard(new Card(Suit.CLOVER, Rank.FIVE));
        assertThat(hand.getCardNames().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("isAceProvider")
    @DisplayName("현재 카드가 ace인지에 따라 boolean값을 반환한다.")
    void isAceTest(Card card, boolean isAce) {
        assertThat(card.isAce()).isEqualTo(isAce);
    }

    @ParameterizedTest
    @MethodSource("cardProvider")
    @DisplayName("현재 hand에 있는 카드들의 합을 반환한다.")
    void calculateValue(Card card, int handValue) {
        hand.addCard(card);
        assertThat(hand.calculateValue()).isEqualTo(handValue);
    }

    @Test
    @DisplayName("처음 받은 2장의 합이 21이면 블랙잭이다.")
    void blackjackTest() {
        Hand newHand = new Hand(List.of(
                new Card(Suit.CLOVER, Rank.KING),
                new Card(Suit.CLOVER, Rank.ACE))
        );
        assertThat(newHand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드 합이 21이어도, 2장 초과면 블랙잭이 아니다.")
    void notBlackjackTest() {
        Hand newHand = new Hand(List.of(
                new Card(Suit.CLOVER, Rank.KING),
                new Card(Suit.CLOVER, Rank.FIVE),
                new Card(Suit.SPADE, Rank.SIX))
        );
        assertThat(newHand.isBlackjack()).isFalse();
    }

    private static Stream<Arguments> cardProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.ACE), 16),
                Arguments.of(new Card(Suit.CLOVER, Rank.SIX), 21)
        );
    }

    private static Stream<Arguments> isAceProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.ACE), true),
                Arguments.of(new Card(Suit.CLOVER, Rank.NINE), false)
        );
    }
}
