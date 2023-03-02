package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 카드를 뽑으면 그 카드가 뽑은 카드에 포함된다.")
    @Test
    void drawn_cards_contains_when_player_pick() {
        // given
        Card cardA = new Card(Type.CLUB, Value.KING);
        Card cardB = new Card(Type.SPADE, Value.QUEEN);

        Name name = new Name("pobi");
        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Player player = new Player(name, drawnCards);

        // when
        player.pickCard(cardA);
        player.pickCard(cardB);

        // then
        assertThat(drawnCards.getCards()).containsExactly(cardA, cardB);
    }

    @DisplayName("플레이어가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_players_drawn_cards_score() {
        // given
        Value king = Value.KING;
        Value queen = Value.QUEEN;

        Card cardA = new Card(Type.CLUB, king);
        Card cardB = new Card(Type.SPADE, queen);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(name, drawnCards);

        // when
        int expectedCardScore = player.calculateCardScore();

        // then
        assertThat(expectedCardScore).isEqualTo(king.getScore() + queen.getScore());
    }

    @DisplayName("플레이어가 뽑은 카드들을 반환하다.")
    @Test
    void returns_player_drawn_cards() {
        // given
        Card cardA = new Card(Type.CLUB, Value.KING);
        Card cardB = new Card(Type.SPADE, Value.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(name, drawnCards);

        // when
        List<Card> expectedDrawnCards = player.openDrawnCards();

        // then
        assertThat(expectedDrawnCards).containsExactly(cardA, cardB);
    }
}
