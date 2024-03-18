package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("성공: 패에 카드를 한 장 추가")
    void add_NoException() {
        Hand hand = new Hand();
        hand.add(Card.of(Rank.KING, Symbol.DIAMOND));
        assertThat(hand.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("성공: 패에 카드를 여러 장 추가")
    void add_NoException_SeveralCards() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.KING, Symbol.DIAMOND),
            Card.of(Rank.KING, Symbol.HEART)
        ));
        assertThat(hand.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("성공: Ace 여부 반환(Ace 있음)")
    void hasAce_True() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.ACE, Symbol.HEART),
            Card.of(Rank.KING, Symbol.CLUB)
        ));
        assertThat(hand.hasAce()).isTrue();
    }

    @Test
    @DisplayName("성공: Ace 여부 반환(Ace 없음)")
    void hasAce_False() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.TWO, Symbol.HEART),
            Card.of(Rank.KING, Symbol.CLUB)
        ));
        assertThat(hand.hasAce()).isFalse();
    }

    @Test
    @DisplayName("성공: 패에 2장이 있고 21점이면 블랙잭이다.")
    void isBlackjack_True() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.KING, Symbol.HEART),
            Card.of(Rank.ACE, Symbol.HEART)
        ));
        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("성공: 패에 3장이 있고 21점이면 블랙잭이 아니다.")
    void isBlackjack_False() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.KING, Symbol.HEART),
            Card.of(Rank.NINE, Symbol.HEART),
            Card.of(Rank.TWO, Symbol.HEART)
        ));
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("성공: 카드 점수의 합을 계산(경계값 21점)")
    void calculateScore_NoException_OneKingOneSevenOneFour() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.KING, Symbol.DIAMOND),
            Card.of(Rank.SEVEN, Symbol.HEART),
            Card.of(Rank.FOUR, Symbol.CLUB)
        ));
        assertThat(hand.totalScore()).isEqualTo(Score.valueOf(21));
    }

    @Test
    @DisplayName("성공: 카드 점수의 합을 계산(경계값 22점)")
    void calculateScore_NoException_OneKingOneSevenOneFive() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.KING, Symbol.DIAMOND),
            Card.of(Rank.SEVEN, Symbol.HEART),
            Card.of(Rank.FIVE, Symbol.CLUB)
        ));
        assertThat(hand.totalScore()).isEqualTo(Score.valueOf(22));
    }
    // 12, 22로 계산될 수 있으나, 21이하 점수 중 최고점인 12을 반환한다.

    @Test
    @DisplayName("성공: 점수 합 계산(ACE 2장 -> 12점)")
    void calculateScore_NoException_TwoAces() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.ACE, Symbol.DIAMOND),
            Card.of(Rank.ACE, Symbol.HEART)
        ));
        assertThat(hand.totalScore()).isEqualTo(Score.valueOf(12));
    }
    // 11, 21, 31으로 계산될 수 있으나, 21이하 점수 중 최고점인 21을 반환한다.

    @Test
    @DisplayName("성공: 점수 합 계산(ACE 2장, NINE 1장 -> 21점)")
    void calculateScore_NoException_TwoAcesOneNine() {
        Hand hand = new Hand();
        hand.add(List.of(
            Card.of(Rank.ACE, Symbol.DIAMOND),
            Card.of(Rank.ACE, Symbol.HEART),
            Card.of(Rank.NINE, Symbol.CLUB)
        ));
        assertThat(hand.totalScore()).isEqualTo(Score.valueOf(21));
    }
}
