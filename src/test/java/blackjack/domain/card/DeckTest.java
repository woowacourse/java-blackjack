package blackjack.domain.card;

import blackjack.domain.card.deck.Deck;
import blackjack.domain.card.deck.ShuffledDeckCardGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    Deque<Card> cards;

    @BeforeEach
    void init() {
        cards = ShuffledDeckCardGenerator.getInstance().generate();
    }

    @DisplayName("카드가 중복되면 예외를 발생시킨다.")
    @Test
    void cardDuplicateTest() {
        cards.pop();
        Card card = cards.pop();

        cards.add(card);
        cards.add(card);

        Assertions.assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드는 중복될 수 없습니다.");
    }

    @DisplayName("카드가 52장이 아니라면 예외를 발생시킨다.")
    @Test
    void cardInitSizeTest() {
        cards.pop();
        System.out.println(cards.size());

        Assertions.assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱의 사이즈가 52장이 아닙니다.");
    }

    @DisplayName("카드 1장을 드로우한다.")
    @Test
    void dealCardTest() {
        // given
        Deck deck = new Deck(cards);

        // when
        Card card = deck.drawCard();

        // then
        assertThat(deck)
                .extracting("cards")
                .isNotIn(card);
    }

    @DisplayName("초기 카드 2장을 드로우한다.")
    @Test
    void dealCardsTest() {
        // given
        Deck deck = new Deck(cards);

        // when
        List<Card> cards = deck.drawInitCards();

        // then
        // CONCERN POINT: contains 메서드를 만들 지, Extract 방식을 사용할 지 고민해보기
        assertThat(deck)
                .extracting("cards")
                .isNotIn(cards);
    }

    @DisplayName("남은 카드가 없을 때 드로우하면 예외를 발생시킨다.")
    @Test
    void emptyDeckDrawTest() {
        // given
        Deck deck = new Deck(cards);
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        // when & then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("덱에 카드가 존재하지 않습니다.");
    }
}