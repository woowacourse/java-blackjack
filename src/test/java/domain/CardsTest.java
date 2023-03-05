package domain;

import static domain.Denomination.ACE;
import static domain.Denomination.FIVE;
import static domain.Denomination.FOUR;
import static domain.Denomination.SIX;
import static domain.Denomination.THREE;
import static domain.Denomination.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {


    private Cards cards;

    @BeforeEach
    void setCards() {
        cards = new Cards();
    }

    @DisplayName("card를 추가한다")
    @Test
    void addCard() {
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);

        assertThat(cards.getCards()).containsExactly(card1, card2);
    }

    @DisplayName("card의 점수 합을 구한다")
    @Test
    void getSumOfScores() {
        addCards(List.of(TWO, THREE));

        assertThat(cards.calculateScore()).isEqualTo(5);
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21 이하인 경우 ACE를 11점으로 계산한다")
    @Test
    void aceIs11_IfSumOfScores21OrLess() {
        addCards(List.of(FOUR, SIX, ACE));

        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21을 넘으면 ACE를 1점으로 계산한다")
    @Test
    void aceIs1_IfSumOfScoresOver21() {
        addCards(List.of(FIVE, SIX, ACE));

        assertThat(cards.calculateScore()).isEqualTo(12);
    }

    @DisplayName("카드의 점수 합이 n점 이상인지 확인한다")
    @Test
    void checkScoreMoreThanN() {
        addCards(List.of(TWO, THREE));

        assertThat(cards.isUnder(5)).isFalse();
        assertThat(cards.isUnder(6)).isTrue();
    }

    private void addCards(List<Denomination> denominations) {
        for (Denomination denomination : denominations) {
            cards.addCard(new Card(denomination, Suits.HEART));
        }
    }
}
