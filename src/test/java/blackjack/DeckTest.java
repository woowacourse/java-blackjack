package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("생성되는 시점에 카드의 개수는 52장이어야 한다.")
    @Test
    void test6() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();

        // when & then
        assertAll(
                () -> assertThat(cards).hasSize(52),
                () -> assertThatCode(() -> new Deck(cards))
                        .doesNotThrowAnyException()
        );
    }

    @DisplayName("생성되는 시점에 카드의 개수가 52장이 아니면 예외를 던진다.")
    @Test
    void test1() {
        // given
        List<Card> cards = List.of(new Card(CardSuit.CLUB, CardRank.ACE));

        // when & then
        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DECK_SIZE.getMessage());
    }

    @DisplayName("중복되는 카드가 존재하면 예외를 던진다.")
    @Test
    void test2() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();

        ArrayList<Card> copiedCards = new ArrayList<>(cards);
        copiedCards.removeLast();
        Card card = copiedCards.getLast();
        copiedCards.add(card);

        // when & then
        assertThatThrownBy(() -> new Deck(copiedCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_CARD_EXISTED.getMessage());
    }

    @DisplayName("카드는 섞일 수 있다.")
    @Test
    void test3() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);

        // when
        deck.shuffle();

        // then
        assertThat(deck.getCards())
                .containsExactlyInAnyOrderElementsOf(cards);
    }

    @DisplayName("카드뽑을 때")
    @Nested
    class DrawCard {
        @DisplayName("원하는 개수만큼 카드를 뽑을 수 있다.")
        @Test
        void test4() {
            // given
            CardsGenerator cardsGenerator = new CardsGenerator();
            List<Card> cards = cardsGenerator.generate();
            Deck deck = new Deck(cards);
            int expect = 2;

            // when
            List<Card> takeCards = deck.takeCards(expect);

            // then
            assertThat(takeCards).hasSize(expect);
        }

        @DisplayName("카드가 소진되면 예외를 던진다.")
        @Test
        void test5() {
            CardsGenerator cardsGenerator = new CardsGenerator();
            List<Card> cards = cardsGenerator.generate();
            Deck deck = new Deck(cards);

            assertThatThrownBy(() -> deck.takeCards(53))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.EMPTY_DECK_SIZE.getMessage());
        }
    }


}
