package domain;

import static org.assertj.core.api.Assertions.*;
import static util.ExceptionConstants.ERROR_HEADER;

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

    @DisplayName("카드가 1장 미만일 시 예외가 발생한다")
    @Test
    void test5() {
        // given
        List<Card> emptyCards = CardFixture.createEmptyCards();
        Deck deck = new Deck(new ArrayList<>(emptyCards));

        //when & then
        assertThatThrownBy(
                deck::drawCard
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_HEADER + "카드가 충분하지 않습니다.");
    }

    @DisplayName("배분할 카드의 개수가 요청한 수보다 적다면 예외가 발생한다")
    @Test
    void test19() {
        // given
        List<Card> emptyCards = CardFixture.createEmptyCards();
        emptyCards.add(new Card(CardNumberType.ACE, CardType.CLOVER));
        Deck deck = new Deck(new ArrayList<>(emptyCards));

        //when & then
        assertThatThrownBy(
                () -> deck.drawCards(2)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_HEADER + "카드가 충분하지 않습니다.");
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
