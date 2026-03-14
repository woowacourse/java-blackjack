package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class HandTest {
    @Test
    @DisplayName("A(에이스)가 없는 경우, 일반적인 점수 계산을 한다.")
    void 에이스_없는_점수_계산_테스트() {
        // given & when
        int totalScore = calculateScoreOf(List.of(
                new Card(Suit.CLUB, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN)
        ));
        // then
        assertThat(totalScore).isEqualTo(20);
    }

    @Test
    @DisplayName("A(에이스)가 포함되어 있으나, 합이 21을 넘지 않는 경우, 일반적인 점수 계산을 한다.")
    void 에이스_포함_21_이하_점수_계산_테스트() {
        // given & when
        int totalScore = calculateScoreOf(List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.HEART, Rank.QUEEN)
        ));

        // then
        assertThat(totalScore).isEqualTo(21);
    }

    @Test
    @DisplayName("A(에이스)가 포함되어 있고, 합이 21을 넘는 경우, A는 1점으로 간주한다.")
    void 에이스_포함_21_초과_점수_계산_테스트() {
        // given & when
        int totalScore = calculateScoreOf(List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.HEART, Rank.QUEEN),
                new Card(Suit.DIAMOND, Rank.FOUR)
        ));

        // then
        assertThat(totalScore).isEqualTo(15);
    }

    @Test
    @DisplayName("A(에이스)가 여러 장이고, 합이 21을 넘는 경우, A는 1점으로 간주한다.")
    void 에이스_여러_장_21_초과_점수_계산_테스트() {
        // given & when
        int totalScore = calculateScoreOf(List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.JACK),
                new Card(Suit.HEART, Rank.FOUR)
        ));

        // then
        assertThat(totalScore).isEqualTo(16);
    }

    private int calculateScoreOf(List<Card> cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.saveCard(card);
        }
        hand.calculateHandScore();
        return hand.getHandTotalScore();
    }
}
