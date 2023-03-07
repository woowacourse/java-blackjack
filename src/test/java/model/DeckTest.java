package model;

import model.card.Card;
import model.card.Deck;
import model.card.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.card.Shape.DIAMOND;
import static model.card.Shape.HEART;
import static model.card.Value.ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeckTest {

    private ShuffleStrategy testShuffleStrategy;

    @BeforeEach
    void init() {
        testShuffleStrategy = cards -> {
            cards.clear();
            cards.add(0,new Card(DIAMOND, ACE));
            cards.add(1, new Card(HEART, ACE));
        };
    }

    @Test
    @DisplayName("덱이 섞였는지 테스트한다.")
    void cardShuffleTest() {
        // given
        final Deck deck = Deck.create(testShuffleStrategy);

        // when
        final Card firstCard = deck.pick();
        final Card secondCard = deck.pick();

        // then
        assertAll(
                () -> assertThat(firstCard).isEqualTo(new Card(DIAMOND, ACE)),
                () -> assertThat(secondCard).isEqualTo(new Card(HEART, ACE))
        );
    }
    
    @Test
    @DisplayName("덱이 비었을 때 카드를 꺼내려하면 오류를 던진다.")
    void whenCardPickEmptyDeck_throwException() {
        // given
        final Deck deck = Deck.create(testShuffleStrategy);

        // when, then
        deck.pick();
        deck.pick();

        assertThatThrownBy(deck::pick)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 덱이 비었습니다.");
    }
}
