package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Nested
    class ValidCase {

        @DisplayName("덱은 마지막 카드를 줘야한다")
        @Test
        void drawCard() {
            // given
            List<TrumpCard> cards = Arrays.stream(TrumpCard.values())
                    .toList();
            Deck deck = new Deck(cards);

            // when & then
            assertThat(deck.draw()).isEqualTo(cards.getLast());
        }

        @DisplayName("덱은 카드를 섞을 수 있다")
        @Test
        void shuffle() {
            // given
            List<TrumpCard> cards = Arrays.stream(TrumpCard.values())
                    .toList();
            Deck originalDeck = new Deck(cards);
            Deck firstShuffledDeck = new Deck(cards);
            Deck secondShuffledDeck = new Deck(cards);

            // when
            firstShuffledDeck.shuffle();
            secondShuffledDeck.shuffle();

            // then
            assertSoftly(softly -> {
                softly.assertThat(firstShuffledDeck)
                        .isNotEqualTo(originalDeck);
                softly.assertThat(secondShuffledDeck)
                        .isNotEqualTo(firstShuffledDeck);
            });
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("덱은 카드를 가지고 있어야한다.")
        @Test
        void validateNotNull() {
            // given
            List<TrumpCard> nullCards = null;

            // when & then
            assertThatThrownBy(() -> new Deck(nullCards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱은 카드를 가지고 있어야합니다.");
        }

        @DisplayName("덱의 카드는 52장 이어야한다.")
        @Test
        void validateSize() {
            // given
            List<TrumpCard> cards = new ArrayList<>();

            // when
            cards.add(TrumpCard.ACE_OF_CLUBS);

            // then
            assertThatThrownBy(() -> new Deck(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱의 크기는 52여야 합니다.");
        }

        @DisplayName("덱의 카드는 중복되지 않아야한다.")
        @Test
        void validateNotDuplicate() {
            // given
            List<TrumpCard> cards = new ArrayList<>();

            // when
            for (int i = 0; i < 52; i++) {
                cards.add(TrumpCard.ACE_OF_CLUBS);
            }
            //then
            assertThatThrownBy(() -> new Deck(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱에 중복된 카드가 있습니다.");
        }

        @DisplayName("덱은 카드가 없다면 카드를 줄 수 없다")
        @Test
        void nonCardTest() {
            // given
            Deck deck = Deck.create();

            // when
            for (int i = 0; i < 52; i++) {
                deck.draw();
            }

            // then
            assertThatThrownBy(deck::draw)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("덱에 카드가 없습니다.");
        }
    }

}
