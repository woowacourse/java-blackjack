package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawnCardsTest {

    @DisplayName("받은 카드의 점수를 계산한다.")
    @Test
    void calculate_drawn_cards_number() {
        // given
        List<Card> cards = new ArrayList<>();
        CardValue expectedA = CardValue.TWO;
        CardValue expectedB = CardValue.THREE;

        cards.add(new Card(CardType.SPADE, expectedA));
        cards.add(new Card(CardType.SPADE, expectedB));

        DrawnCards drawnCards = new DrawnCards(cards);

        int expected = expectedA.getScore() + expectedB.getScore();
        // when
        int actual = drawnCards.calculateScore();
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드의 점수 총합이 21이 넘지않는다면 에이스는 11로 계산된다")
    @Test
    void calculate_ace_when_under_burst_number() {
        // given
        List<Card> cards = new ArrayList<>();

        CardValue expectedA = CardValue.TWO;
        CardValue expectedB = CardValue.ACE;

        cards.add(new Card(CardType.SPADE, expectedA));
        cards.add(new Card(CardType.SPADE, expectedB));

        DrawnCards drawnCards = new DrawnCards(cards);

        int expected = expectedA.getScore() + expectedB.getScore();
        // when
        int actual = drawnCards.calculateScore();
        // then
        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("카드의 점수 총합이 21을 넘으면 에이스는 1로 계산된다")
    @Test
    void calculate_ace_when_over_burst_number() {
        // given
        List<Card> cards = new ArrayList<>();

        CardValue expectedA = CardValue.TWO;
        CardValue expectedB = CardValue.TEN;
        CardValue expectedC = CardValue.ACE;

        cards.add(new Card(CardType.SPADE, expectedA));
        cards.add(new Card(CardType.SPADE, expectedB));
        cards.add(new Card(CardType.SPADE, expectedC));

        DrawnCards drawnCards = new DrawnCards(cards);

        int expected = expectedA.getScore() + expectedB.getScore() + expectedC.getExtraScore();
        // when
        int actual = drawnCards.calculateScore();
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("BlackJack이라면 true를 반환한다.")
    @Test
    void is_blackJack() {
        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(CardType.SPADE, CardValue.ACE));
        cards.add(new Card(CardType.SPADE, CardValue.TEN));

        DrawnCards drawnCards = new DrawnCards(cards);
        // when
        boolean actual = drawnCards.isBlackJack();
        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점이 아니라면 블랙잭이 아니다.")
    @Test
    void is_not_blackJack_by_score() {

        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(CardType.SPADE, CardValue.ACE));
        cards.add(new Card(CardType.SPADE, CardValue.TWO));

        DrawnCards drawnCards = new DrawnCards(cards);
        // when
        boolean actual = drawnCards.isBlackJack();
        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드가 2장이 아니라면 블랙잭이 아니다.")
    @Test
    void is_not_blackJack_by_card_size() {
        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(CardType.SPADE, CardValue.TEN));
        cards.add(new Card(CardType.SPADE, CardValue.NINE));
        cards.add(new Card(CardType.SPADE, CardValue.TWO));

        DrawnCards drawnCards = new DrawnCards(cards);
        // when
        boolean actual = drawnCards.isBlackJack();
        // then
        assertThat(actual).isFalse();
    }
}
