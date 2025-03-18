package domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.TrumpCard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Nested
    class ValidCase {

        @DisplayName("덱에서 여러장의 카드를 줘야한다")
        @Test
        void drawMultiple() {
            // given
            List<TrumpCard> cards = Arrays.stream(TrumpCard.values())
                    .toList();
            Deck deck = new Deck(cards, new NoShuffle());

            // when
            List<TrumpCard> drawnCards = deck.drawMultiple(5);

            // when & then
            assertThat(drawnCards.size()).isEqualTo(5);
        }

        @DisplayName("덱은 마지막 카드를 줘야한다")
        @Test
        void draw() {
            // given
            List<TrumpCard> cards = Arrays.stream(TrumpCard.values())
                    .toList();
            Deck deck = new Deck(cards, new NoShuffle());

            // when & then
            assertThat(deck.draw()).isEqualTo(cards.getLast());
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("덱은 카드를 가지고 있어야한다.")
        @Test
        void validateCardsNotNull() {
            // given
            List<TrumpCard> nullCards = null;

            // when & then
            assertThatThrownBy(() -> new Deck(nullCards, new NoShuffle()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱은 카드와 섞기 전략 가지고 있어야합니다.");
        }

        @DisplayName("덱은 섞기 전략을 가지고 있어야한다.")
        @Test
        void validateShuffleStrategyNotNull() {
            // given
            ShuffleStrategy nullStrategy = null;

            // when & then
            assertThatThrownBy(() -> new Deck(Arrays.asList(TrumpCard.values()), nullStrategy))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱은 카드와 섞기 전략 가지고 있어야합니다.");
        }

        @DisplayName("덱의 카드는 52장 이어야한다.")
        @Test
        void validateSize() {
            // given
            List<TrumpCard> cards = new ArrayList<>();

            // when
            cards.add(TrumpCard.ACE_OF_CLUBS);

            // then
            assertThatThrownBy(() -> new Deck(cards, new NoShuffle()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱의 크기는 " + TrumpCard.TOTAL_COUNT + "여야 합니다.");
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
            assertThatThrownBy(() -> new Deck(cards, new NoShuffle()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("덱에 중복된 카드가 있습니다.");
        }

        @DisplayName("덱은 카드가 없다면 카드를 줄 수 없다")
        @Test
        void nonCardTest() {
            // given
            List<TrumpCard> cards = Arrays.stream(TrumpCard.values())
                    .toList();
            Deck deck = new Deck(cards, new NoShuffle());

            // when
            deck.drawMultiple(52);

            // then
            assertThatThrownBy(deck::draw)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("덱에 카드가 없습니다.");
        }
    }

}
