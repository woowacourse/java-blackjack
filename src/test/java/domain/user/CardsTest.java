package domain.user;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FIVE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
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
        Card card1 = Card.of(Denomination.TWO, Suits.HEART);
        Card card2 = Card.of(Denomination.THREE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);

        assertThat(cards.getCards()).containsExactly(card1, card2);
    }

    @DisplayName("card의 점수 합을 구한다")
    @Test
    void getSumOfScores() {
        addCards(List.of(TWO, THREE));

        assertThat(cards.getSumOfScores()).isEqualTo(5);
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21 이하인 경우 ACE를 11점으로 계산한다")
    @Test
    void aceIs11_IfSumOfScores21OrLess() {
        addCards(List.of(FOUR, SIX, ACE));

        assertThat(cards.getSumOfScores()).isEqualTo(21);
    }

    @DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21을 넘으면 ACE를 1점으로 계산한다")
    @Test
    void aceIs1_IfSumOfScoresOver21() {
        addCards(List.of(FIVE, SIX, ACE));

        assertThat(cards.getSumOfScores()).isEqualTo(12);
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
            cards.addCard(Card.of(denomination, Suits.HEART));
        }
    }
}
