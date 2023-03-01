package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(List.of(new Card("2하트", 2), new Card("K다이아", 10))));
    }

    @Test
    void addCard() {
        hand.addCard(new Card("5클로버", 5));
        assertThat(hand.getCards().size()).isEqualTo(3);
    }

    @Test
    void calculateValue() {
        assertThat(hand.calculateValue()).isEqualTo(12);
    }
}
