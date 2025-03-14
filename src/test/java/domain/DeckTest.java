package domain;

import static org.assertj.core.api.Assertions.*;

import fixture.CardFixture;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에 있는 1장의 카드를 뽑을 수 있다")
    @Test
    void test2() {
        // given
        List<Card> cards = CardFixture.createFixedCards();
        Deck deck = new Deck(new ArrayList<>(cards));

        // when
        Card card = deck.drawCard();

        // then
        assertThat(card).isEqualTo(cards.get(0));
    }

    @DisplayName("덱에 카드를 원하는 개수만큼 뽑을 수 있다")
    @Test
    void test3() {
        // given
        List<Card> cards = CardFixture.createFixedCards();
        Deck deck = new Deck(new ArrayList<>(cards));
        int count = 2;

        // when
        List<Card> drawCards = deck.drawCards(count);

        // then
        assertThat(drawCards).containsAll(List.of(cards.getFirst(), cards.get(1)));
    }
}
