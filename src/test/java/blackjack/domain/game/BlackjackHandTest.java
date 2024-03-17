package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackHandTest {

    @Test
    @DisplayName("성공: 카드를 한 장 받을 수 있다")
    void receive_NoException() {
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(new Card(Rank.KING, Symbol.DIAMOND));
        assertThat(blackjackHand.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("성공: 카드를 여러장 받을 수 있다")
    void receive_NoException_SeveralCards() {
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(List.of(
                new Card(Rank.KING, Symbol.DIAMOND),
                new Card(Rank.KING, Symbol.HEART)
        ));
        assertThat(blackjackHand.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("성공: 카드 점수의 합을 계산(경계값 21점)")
    void calculateScore_NoException_OneKingOneSevenOneFour() {
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(List.of(
                new Card(Rank.KING, Symbol.DIAMOND),
                new Card(Rank.SEVEN, Symbol.HEART),
                new Card(Rank.FOUR, Symbol.CLUB)
        ));
        assertThat(blackjackHand.calculateScore())
                .isEqualTo(21);
    }

    @Test
    @DisplayName("성공: 카드 점수의 합을 계산(경계값 22점)")
    void calculateScore_NoException_OneKingOneSevenOneFive() {
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(List.of(
                new Card(Rank.KING, Symbol.DIAMOND),
                new Card(Rank.SEVEN, Symbol.HEART),
                new Card(Rank.FIVE, Symbol.CLUB)
        ));
        assertThat(blackjackHand.calculateScore())
                .isEqualTo(22);
    }

    // 12, 22로 계산될 수 있으나, 21이하 점수 중 최고점인 12을 반환한다.
    @Test
    @DisplayName("성공: 점수 합 계산(ACE 2장 -> 12점)")
    void calculateScore_NoException_TwoAces() {
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(List.of(
                new Card(Rank.ACE, Symbol.DIAMOND),
                new Card(Rank.ACE, Symbol.HEART)
        ));
        assertThat(blackjackHand.calculateScore())
                .isEqualTo(12);
    }

    // 11, 21, 31으로 계산될 수 있으나, 21이하 점수 중 최고점인 21을 반환한다.
    @Test
    @DisplayName("성공: 점수 합 계산(ACE 2장, NINE 1장 -> 21점)")
    void calculateScore_NoException_TwoAcesOneNine() {
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(List.of(
                new Card(Rank.ACE, Symbol.DIAMOND),
                new Card(Rank.ACE, Symbol.HEART),
                new Card(Rank.NINE, Symbol.CLUB)
        ));
        assertThat(blackjackHand.calculateScore())
                .isEqualTo(21);
    }

    @Test
    @DisplayName("성공: 블랙잭 여부 반환")
    void isBlackjack() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackHand blackjackHand = new BlackjackHand();
        blackjackHand.receive(List.of(
                new Card(Rank.ACE, Symbol.DIAMOND),
                new Card(Rank.KING, Symbol.HEART)
        ));

        assertThat(hand.isBlackjack()).isFalse();
        assertThat(blackjackHand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("성공: 블랙잭 점수를 넘는지 여부 반환")
    void isOverBlackjack() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackHand overBlackjackHand = new BlackjackHand();
        overBlackjackHand.receive(List.of(
                new Card(Rank.KING, Symbol.DIAMOND),
                new Card(Rank.KING, Symbol.HEART),
                new Card(Rank.KING, Symbol.CLUB)
        ));

        assertThat(hand.isOverBlackjackScore()).isFalse();
        assertThat(overBlackjackHand.isOverBlackjackScore()).isTrue();
    }
}
