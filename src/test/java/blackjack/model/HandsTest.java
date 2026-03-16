package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @Test
    @DisplayName("빈 핸즈를 반환한다.")
    void empty() {
        assertThat(
                Hands.empty()
                        .getAllCard()
        ).isEmpty();
    }

    @Test
    @DisplayName("인자로 넘겨받은 카드를 핸즈에 추가한다.")
    void addCard() {
        // given
        Hands hands = Hands.empty();

        //when
        hands.addCard(Card.createOpenedCard(Rank.TEN, Suit.CLOVER));

        // then
        assertThat(hands.getAllCard().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("J/Q/K는 10점으로 숫자 카드는 해당되는 숫자로 점수를 계산한다.")
    void calculateTotalScore() {
        // given
        Hands hands = Hands.empty();

        hands.addCard(Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.J, Suit.CLOVER));

        // when & then
        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace 카드를 제외한 카다들의 총점이 11점 초과이면 에이스가 1점으로 계산된다.")
    void calculateTotalScoreWithAceOf1() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.TWO, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.ACE, Suit.CLOVER));

        // when
        int totalScore = hands.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace 카드를 제외한 카다들의 총점이 11점 이하이면 에이스가 11점으로 계산된다.")
    void calculateTotalScoreWithAceOf11() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.ACE, Suit.CLOVER));

        // when
        int totalScore = hands.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(21);
    }

    @Test
    @DisplayName("현재 총 점수가 인자로 넘겨받은 점수보다 크면 true를 반환한다.")
    void isTotalScoreOverTrue() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.createOpenedCard(Rank.SIX, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.ACE, Suit.CLOVER));

        assertThat(hands.isTotalScoreOver(16)).isTrue();
    }

    @Test
    @DisplayName("현재 총 점수가 인자로 넘겨받은 점수보다 크면 false를 반환한다.")
    void isTotalScoreOverFalse() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.createOpenedCard(Rank.SIX, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.ACE, Suit.CLOVER));

        assertThat(hands.isTotalScoreOver(17)).isFalse();
    }

    @Test
    @DisplayName("핸즈에서 오픈된 카드들만 반환한다")
    void getOpenedCards() {
        // given
        Hands hands = Hands.empty();
        hands.addCard(Card.createOpenedCard(Rank.SIX, Suit.CLOVER));

        Card opened = Card.createOpenedCard(Rank.ACE, Suit.CLOVER);
        Card closed = opened.close();

        hands.addCard(closed);

        // when & then
        assertThat(hands.getOpenedCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("핸즈에 있는 초기 2 장의 카드의 점수를 반환한다.")
    void calculateInitialCardScore() {
        //given
        Hands hands = Hands.empty();

        hands.addCard(Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.TWO, Suit.CLOVER));
        hands.addCard(Card.createOpenedCard(Rank.K, Suit.CLOVER));

        //when & then
        assertThat(hands.calculateInitialCardScore()).isEqualTo(12);
    }
}