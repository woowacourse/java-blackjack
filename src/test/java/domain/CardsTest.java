package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("card를 추가한다")
    @Test
    void addCard() {
        Cards cards = new Cards();
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);

        assertThat(cards.getCards()).containsExactly(card1, card2);
    }

    @DisplayName("card의 점수 합을 구한다")
    @Test
    void getSumOfScores() {
        Cards cards = new Cards();
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);

        assertThat(cards.getSumOfScores()).isEqualTo(5);
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21 이하인 경우 ACE를 11점으로 계산한다")
    @Test
    void aceIs11_IfSumOfScores21OrLess() {
        Cards cards = new Cards();
        Card card1 = new Card(Denomination.FOUR, Suits.HEART);
        Card card2 = new Card(Denomination.SIX, Suits.DIAMOND);
        Card card3 = new Card(Denomination.ACE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        assertThat(cards.getSumOfScores()).isEqualTo(21);
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21을 넘으면 ACE를 1점으로 계산한다")
    @Test
    void aceIs1_IfSumOfScoresOver21() {
        Cards cards = new Cards();
        Card card1 = new Card(Denomination.FIVE, Suits.HEART);
        Card card2 = new Card(Denomination.SIX, Suits.DIAMOND);
        Card card3 = new Card(Denomination.ACE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        assertThat(cards.getSumOfScores()).isEqualTo(12);
    }

    @DisplayName("카드의 점수 합이 n점 이상인지 확인한다")
    @Test
    void checkScoreMoreThanN() {
        Cards cards = new Cards();
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);

        assertThat(cards.isUnder(5)).isFalse();
        assertThat(cards.isUnder(6)).isTrue();
    }
}
