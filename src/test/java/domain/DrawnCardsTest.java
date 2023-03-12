package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.DrawnCards;
import domain.card.Type;
import domain.card.Value;
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

        cards.add(new Card(Type.SPADE, Value.TWO));
        cards.add(new Card(Type.SPADE, Value.THREE));

        DrawnCards drawnCards = new DrawnCards(cards);

        // when
        int expectedScore = drawnCards.calculateScore();

        // then
        assertThat(expectedScore).isEqualTo(5);
    }

    @DisplayName("카드의 점수 총합이 21이 넘지않는다면 에이스는 11로 계산된다")
    @Test
    void calculate_ace_when_under_burst_number() {
        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Type.SPADE, Value.TWO));
        cards.add(new Card(Type.SPADE, Value.ACE));

        DrawnCards drawnCards = new DrawnCards(cards);

        // when
        int expectedScore = drawnCards.calculateScore();

        // then
        assertThat(expectedScore).isEqualTo(13);
    }


    @DisplayName("카드의 점수 총합이 21을 넘으면 에이스는 1로 계산된다")
    @Test
    void calculate_ace_when_over_burst_number() {
        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Type.SPADE, Value.QUEEN));
        cards.add(new Card(Type.SPADE, Value.FIVE));
        cards.add(new Card(Type.SPADE, Value.ACE));

        DrawnCards drawnCards = new DrawnCards(cards);

        // when
        int expectedScore = drawnCards.calculateScore();

        // then
        assertThat(expectedScore).isEqualTo(16);
    }
}
