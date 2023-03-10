package model.card;

import static model.card.CardFixture.DIAMOND_ACE;
import static model.card.CardFixture.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private ShuffleStrategy testShuffleStrategy;

    @BeforeEach
    void init() {
        testShuffleStrategy = cards -> {
            cards.clear();
            cards.add(0, DIAMOND_ACE);
            cards.add(1, HEART_ACE);
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
                () -> assertThat(firstCard).isEqualTo(DIAMOND_ACE),
                () -> assertThat(secondCard).isEqualTo(HEART_ACE)
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
