package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    List<Card> cards;
    Card card1;
    Card card2;
    Hand hand;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        card1 = new Card(Rank.ACE, Shape.CLOVER);
        card2 = new Card(Rank.FIVE, Shape.CLOVER);
        hand = new Hand(cards);
    }

    @DisplayName("카드를 손에 추가한다.")
    @Test
    void 카드를_손에_추가한다() {

        // given

        // when & then
        assertThatCode(() -> {
            hand.add(card1);
            hand.add(card2);
        }).doesNotThrowAnyException();
    }

    @DisplayName("손에 있는 카드의 합을 가져온다.")
    @Test
    void 손에_있는_카드의_합을_가져온다() {

        // given

        // when
        hand.add(card1);
        hand.add(card2);

        // then
        assertThat(hand.getSumOfRank()).isEqualTo(6);
    }
}
