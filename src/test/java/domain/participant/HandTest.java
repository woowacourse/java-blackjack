package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("Ace 카드가 없을 때 단순 점수 합을 정상적으로 계산한다.")
    @Test
    void calculateScoreWithoutAce() {
        Hand hand = new Hand();
        hand.add(new Card(10));
        hand.add(new Card(8));
        assertThat(hand.calculateScore().getGameScore()).isEqualTo(19);
    }

    @DisplayName("Ace 카드가 1장일 때, 21을 초과하지 않으면 11점으로 계산한다.")
    @Test
    void calculateScoreWithOneAceAsEleven() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(8));
        assertThat(hand.calculateScore().getGameScore()).isEqualTo(20);
    }

    @DisplayName("Ace 카드가 1장일 때, 11점으로 계산 시 21을 초과하면 1점으로 계산한다.")
    @Test
    void calculateScoreWithOneAceAsOne() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(10));
        hand.add(new Card(11));
        assertThat(hand.calculateScore().getGameScore()).isEqualTo(21);
    }
}
