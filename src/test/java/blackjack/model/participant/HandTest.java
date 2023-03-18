package blackjack.model.participant;

import blackjack.model.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.model.CardFixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandTest {

    @Test
    @DisplayName("Ace 점수를 11로 했을 때, 총 합이 21을 초과하지 않으면 Ace 카드가 11로 계산된다")
    void calculate_score_include_ace() {
        //given
        List<Card> cards = List.of(HEART_ACE, CLUB_THREE, HEART_SEVEN);
        Hand hand = new Hand(cards);

        //when
        int score = hand.getScore();

        //then
        assertThat(score).isEqualTo(11 + 3 + 7);
    }

    @Test
    @DisplayName("처음 받은 두 장의 Ace 카드가 두 장인 경우, 총 합은 11+1로 계산한다")
    void calculate_score_include_two_ace() {
        //given
        List<Card> cards = List.of(CLUB_ACE, HEART_ACE);
        Hand hand = new Hand(cards);

        //when
        int score = hand.getScore();

        //then
        assertThat(score).isEqualTo(11 + 1);
    }

    @Test
    @DisplayName("Ace 점수를 11로 했을 때, 총 합이 21을 초과하면 Ace 는 1로 계산된다")
    void calculate_score_include_ace_over_21() {
        //given
        List<Card> cards = List.of(CLUB_ACE, HEART_TEN, HEART_FIVE);
        Hand hand = new Hand(cards);

        //when
        int score = hand.getScore();

        //then
        assertThat(score).isEqualTo(1 + 10 + 5);
    }

}
