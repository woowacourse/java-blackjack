package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;

class HandTest {

    private Hand hand;

    private static Stream<Arguments> cardProvider() {
        return Stream.of(
            Arguments.of(Card.from(Suit.HEART, Rank.ACE), 13),
            Arguments.of(Card.from(Suit.HEART, Rank.NINE), 21)
        );
    }

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(
            List.of(
                Card.from(Suit.HEART, Rank.TWO),
                Card.from(Suit.DIAMOND, Rank.KING)
            ))
        );
    }

    @Test
    @DisplayName("패에 카드를 추가한다.")
    void addCard() {
        hand.addCard(Card.from(Suit.CLOVER, Rank.FIVE));
        assertThat(hand.getCardNames().size()).isEqualTo(3);
    }

    @ParameterizedTest(name = "handValue를 계산한다.")
    @MethodSource("cardProvider")
    void calculateValue(Card card, int handValue) {
        hand.addCard(card);
        assertThat(hand.calculateValue()).isEqualTo(handValue);
    }
}
