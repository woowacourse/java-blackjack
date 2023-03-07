package model;

import model.card.Card;
import model.card.Deck;
import model.card.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.card.Shape.DIAMOND;
import static model.card.Shape.HEART;
import static model.card.Value.ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeckTest {

    @Test
    @DisplayName("덱이 섞였는지 테스트한다.")
    void cardShuffleTest() {
        // given
        final ShuffleStrategy shuffleStrategy = cards -> {
            cards.add(0,new Card(DIAMOND, ACE));
            cards.add(1, new Card(HEART, ACE));
        };

        final Deck deck = Deck.create(shuffleStrategy);

        // when
        final Card firstCard = deck.pick();
        final Card secondCard = deck.pick();

        // then
        assertAll(
                () -> assertThat(firstCard).isEqualTo(new Card(DIAMOND, ACE)),
                () -> assertThat(secondCard).isEqualTo(new Card(HEART, ACE))
        );
    }
}
