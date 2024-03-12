package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    Deck deck = new Deck();

    @DisplayName("덱에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        Card card = Card.of(CardPattern.CLOVER, CardNumber.EIGHT);

        //when
        deck.add(card);
        List<Card> cards = deck.cards();

        //then
        assertThat(cards).containsExactly(card);
    }

    @DisplayName("덱에 카드를 추가할 때 동일한 카드가 있는 경우 예외를 발생시킨다.")
    @Test
    void addCard_duplicateCard() {
        //given
        Card card1 = Card.of(CardPattern.CLOVER, CardNumber.ACE);
        Card card2 = Card.of(CardPattern.CLOVER, CardNumber.ACE);

        deck.add(card1);
        //when, then
        assertThatThrownBy(() -> deck.add(card2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("덱에 있는 모든 카드의 합을 계산한다.")
    @Test
    void calculateTotalScore() {
        //given
        Card card1 = Card.of(CardPattern.CLOVER, CardNumber.EIGHT);
        Card card2 = Card.of(CardPattern.SPADE, CardNumber.EIGHT);

        //when
        deck.add(card1);
        deck.add(card2);

        //then
        assertThat(deck.calculateCardScore()).isEqualTo(16);
    }

    @DisplayName("덱에 11값으로 설정된 Ace 카드의 갯수를 반환한다.")
    @Test
    void countElevenAce() {
        //given
        Card card1 = Card.of(CardPattern.CLOVER, CardNumber.ACE);
        Card card2 = Card.of(CardPattern.SPADE, CardNumber.ACE);

        //when
        deck.add(card1);
        deck.add(card2);

        //then
        assertThat(deck.countElevenAce()).isEqualTo(2);
    }

    @DisplayName("덱에 11값으로 설정된 Ace 중 제일 첫번쨰 Ace의 값을 1로 변경한다.")
    @Test
    void switchAceValueInRow() {
        //given
        Card card1 = Card.of(CardPattern.CLOVER, CardNumber.ACE);
        Card card2 = Card.of(CardPattern.SPADE, CardNumber.ACE);

        //when
        deck.add(card1);
        deck.add(card2);
        deck.switchAceValueInRow();

        //then
        assertAll(
                () -> assertThat(card1.getScore()).isEqualTo(1),
                () -> assertThat(card2.getScore()).isEqualTo(11)
        );
    }
}
