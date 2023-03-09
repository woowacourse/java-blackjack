package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardsTest {
    @DisplayName("생성한다.")
    @Test
    void create() {
        final CardDeck deck = new CardDeck();
        final List<Card> initCards = List.of(deck.draw(), deck.draw());
        assertThatNoException().isThrownBy(() -> new Cards(initCards));
    }

    @DisplayName("카드 리스트가 비어있을 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenCardsIsEmpty() {
        assertThatThrownBy(() -> new Cards(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 받아올 시에 중복이면 예외가 발생한다.")
    @Test
    void throwExceptionWhenCardIsDuplicated() {
        // given
        final Card card1 = new Card(CardShape.HEART, CardNumber.KING);
        final Card card2 = new Card(CardShape.SPADE, CardNumber.THREE);
        final Cards cards = new Cards(List.of(card1, card2));

        // when & then
        assertThatThrownBy(() -> cards.receive(card1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("요청한 수 만큼 카드를 오픈한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void open(final int openCount) {
        // given
        final Deck deck = new CardDeck();
        final Cards cards = new Cards(List.of(deck.draw(), deck.draw()));

        // when
        final List<Card> openCards = cards.open(openCount);

        // then
        assertThat(openCards).hasSize(openCount);
    }

    @DisplayName("모든 카드를 오픈한다.")
    @Test
    void openAll() {
        // given
        final CardDeck deck = new CardDeck();
        final List<Card> drawCards = List.of(deck.draw(), deck.draw());
        final Cards cards = new Cards(drawCards);

        // when
        final List<Card> openCards = cards.openAll();

        // then
        assertThat(openCards).hasSize(drawCards.size());
    }

    @DisplayName("카드 번호 리스트를 가져온다.")
    @Test
    void getCardNumbers() {
        // given
        final Card card1 = new Card(CardShape.SPADE, CardNumber.ACE);
        final Card card2 = new Card(CardShape.CLOVER, CardNumber.TWO);
        final Card card3 = new Card(CardShape.HEART, CardNumber.KING);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        // when
        final List<CardNumber> cardNumbers = cards.getCardNumbers();

        // then
        assertThat(cardNumbers).containsExactly(card1.getNumber(), card2.getNumber(), card3.getNumber());
    }
}
