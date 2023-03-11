package domain;

import domain.card.Card;
import domain.card.Hand;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private final List<Card> cards = new ArrayList<>(List.of(
            new Card(HEART, "8"),
            new Card(HEART, "10"),
            new Card(HEART, "A")));

    private Hand hand;

    @BeforeEach
    public void setUp() {
        hand = new Hand(cards);
    }

    @Test
    @DisplayName("Hand를 생성한다.")
    void test_create() {
        assertDoesNotThrow(() -> new Hand(cards));
    }

    @Test
    @DisplayName("카드들을 통해 점수를 계산한다.")
    void test_score() {
        assertThat(hand.score()).isEqualTo(new Score(19));
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void test_addCard() {
        hand.add(new Card(HEART, "2"));

        assertThat(hand.getCards()).hasSize(4);
    }

}