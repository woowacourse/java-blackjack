package domain.card;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FIVE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {


    private Hand hand;

    @BeforeEach
    void setCards() {
        hand = new Hand();
    }

    @DisplayName("card를 추가한다")
    @Test
    void addCard() {
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        hand.addCard(card1);
        hand.addCard(card2);

        assertThat(hand.getCards()).containsExactly(card1, card2);
    }

    @DisplayName("card의 점수 합을 구한다")
    @Test
    void getSumOfScores() {
        addCards(List.of(TWO, THREE));

        assertThat(hand.getSumOfScores()).isEqualTo(new Score(5));
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21 이하인 경우 ACE를 11점으로 계산한다")
    @Test
    void aceIs11_IfSumOfScores21OrLess() {
        addCards(List.of(FOUR, SIX, ACE));

        assertThat(hand.getSumOfScores()).isEqualTo(new Score(21));
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21을 넘으면 ACE를 1점으로 계산한다")
    @Test
    void aceIs1_IfSumOfScoresOver21() {
        addCards(List.of(FIVE, SIX, ACE));

        assertThat(hand.getSumOfScores()).isEqualTo(new Score(12));
    }

    @DisplayName("카드의 점수 합이 n점 이상인지 확인한다")
    @Test
    void checkScoreMoreThanN() {
        addCards(List.of(TWO, THREE));

        assertThat(hand.isUnder(new Score(5))).isFalse();
        assertThat(hand.isUnder(new Score(6))).isTrue();
    }

    private void addCards(List<Denomination> denominations) {
        for (Denomination denomination : denominations) {
            hand.addCard(new Card(denomination, Suits.HEART));
        }
    }
}
