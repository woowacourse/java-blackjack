package model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.participants.ParticipantType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("카드 핸드에 여러 장의 카드를 추가할 수 있다")
    @Test
    void testAddCards() {
        Hand hand = new Hand();
        hand.addCards(List.of(new Card("A", "스페이드"), new Card("K", "클로버")));

        assertThat(hand).isNotNull();
    }

    @DisplayName("딜러의 핸드 점수를 계산할 수 있다")
    @Test
    void testCheckScores_Dealer() {
        Hand hand = new Hand();
        hand.addCards(List.of(new Card("A", "스페이드"), new Card("K", "클로버"), new Card("J", "다이아몬드")));

        hand.calculateScore(ParticipantType.DEALER);
        assertThat(hand.getScore()).isEqualTo(31);
    }

    @DisplayName("딜러의 핸드 점수를 계산할 수 있다 (에이스 11 고정)")
    @Test
    void testCheckScores_Dealer_MultipleAce() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card("A", "스페이드"),
                new Card("A", "클로버"),
                new Card("A", "다이아몬드"))
        );

        hand.calculateScore(ParticipantType.DEALER);
        assertThat(hand.getScore()).isEqualTo(33);
    }

    @DisplayName("플레이어의 핸드 점수를 계산할 수 있다")
    @Test
    void testCheckScores_Player() {
        Hand hand = new Hand();
        hand.addCards(List.of(new Card("A", "스페이드"), new Card("K", "클로버"), new Card("J", "다이아몬드")));

        hand.calculateScore(ParticipantType.PLAYER);
        assertThat(hand.getScore()).isEqualTo(21);
    }

    @DisplayName("플레이어의 핸드 점수를 계산할 수 있다 (에이스 11, 1 전환 가능)")
    @Test
    void testCheckScores_Player_MultipleAce() {
        Hand hand = new Hand();
        hand.addCards(List.of(
                new Card("A", "스페이드"),
                new Card("A", "클로버"),
                new Card("A", "다이아몬드"))
        );

        hand.calculateScore(ParticipantType.PLAYER);
        assertThat(hand.getScore()).isEqualTo(13);
    }
}
