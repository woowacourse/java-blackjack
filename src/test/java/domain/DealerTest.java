package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.DrawnCards;
import domain.participant.Dealer;
import domain.participant.Name;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 카드를 뽑으면 그 카드가 뽑은 카드에 포함된다.")
    @Test
    void drawn_cards_contains_when_dealer_pick() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        dealer.drawCard(cardA);
        dealer.drawCard(cardB);

        // then
        assertThat(drawnCards.getCards()).containsExactly(cardA, cardB);
    }

    @DisplayName("딜러가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_dealer_drawn_cards_score() {
        // given
        CardValue king = CardValue.KING;
        CardValue queen = CardValue.QUEEN;

        Card cardA = new Card(CardType.CLUB, king);
        Card cardB = new Card(CardType.SPADE, queen);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        int expectedCardScore = dealer.calculateScore();

        // then
        assertThat(expectedCardScore).isEqualTo(king.getScore() + queen.getScore());
    }

    @DisplayName("딜가 뽑은 카드들을 반환하다.")
    @Test
    void returns_dealer_drawn_cards() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        List<Card> expectedDrawnCards = dealer.openDrawnCards();

        // then
        assertThat(expectedDrawnCards).containsExactly(cardB);
    }
}
