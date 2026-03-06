package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {


    @Test
    @DisplayName("인자로 넘겨받은 카드를 핸즈에 추가한다.")
    void addCards() {
        // given
        Hands hands = Hands.empty();
        Card card = Card.opened(Rank.ACE, Suit.CLOVER);

        // when && then
        assertThatCode(() -> hands.addCard(card))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("현재 핸즈가 가지고 있는 카드들의 총점을 계산한다.")
    void calculateTotalScore() {
        // given
        Hands hands = Hands.empty();

        hands.addCard(Card.opened(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.opened(Rank.J, Suit.CLOVER));

        // when & then
        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("에이스 점수 판별 - 에이스가 1점으로 계산되는 경우")
    void aceScoreTest1() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.opened(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.opened(Rank.TWO, Suit.CLOVER));
        hands.addCard(Card.opened(Rank.ACE, Suit.CLOVER));

        // when
        int totalScore = hands.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(13);
    }

    @Test
    @DisplayName("에이스 점수 판별 - 에이스가 11점으로 계산되는 경우")
    void aceScoreTest2() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.opened(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.opened(Rank.ACE, Suit.CLOVER));

        // when
        int totalScore = hands.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(21);
    }

    @Test
    @DisplayName("현재 총 점수가 인자로 넘겨받은 점수보다 크면 true를 반환한다.")
    void isTotalScoreOver() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.opened(Rank.SIX, Suit.CLOVER));
        hands.addCard(Card.opened(Rank.ACE, Suit.CLOVER));

        assertThat(hands.isTotalScoreOver(16)).isTrue();
    }
}