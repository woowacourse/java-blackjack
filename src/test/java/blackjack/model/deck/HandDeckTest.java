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

class HandDeckTest {

    HandDeck handDeck = new HandDeck();

    @DisplayName("덱에 카드를 추가한다.")
    @Test
    void add() {
        //given
        Card card = new Card(CardPattern.CLOVER, CardNumber.EIGHT);

        //when
        handDeck.add(card);
        List<Card> cards = handDeck.cards();

        //then
        assertThat(cards).containsExactly(card);
    }

    @DisplayName("덱에 카드를 추가할 때 동일한 카드가 있는 경우 예외를 발생시킨다.")
    @Test
    void add_duplicateCard() {
        //given
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.ACE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.ACE);

        handDeck.add(card1);
        //when, then
        assertThatThrownBy(() -> handDeck.add(card2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("덱에 있는 모든 카드의 합을 계산한다.")
    @Test
    void calculateCardScore() {
        //given
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.EIGHT);
        Card card2 = new Card(CardPattern.SPADE, CardNumber.EIGHT);

        //when
        handDeck.add(card1);
        handDeck.add(card2);

        //then
        assertThat(handDeck.calculateCardScore()).isEqualTo(16);
    }

    @DisplayName("덱에 11값으로 설정된 Ace 카드의 갯수를 반환한다.")
    @Test
    void countElevenAce() {
        //given
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.ACE);
        Card card2 = new Card(CardPattern.SPADE, CardNumber.ACE);

        //when
        handDeck.add(card1);
        handDeck.add(card2);

        //then
        assertThat(handDeck.countElevenAce()).isEqualTo(2);
    }

    @DisplayName("덱에 11값으로 설정된 Ace 중 제일 첫번쨰 Ace의 값을 1로 변경한다.")
    @Test
    void switchAceValueInRow() {
        //given
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.ACE);
        Card card2 = new Card(CardPattern.SPADE, CardNumber.ACE);

        //when
        handDeck.add(card1);
        handDeck.add(card2);
        handDeck.switchAceValueInRow();

        //then
        assertAll(
                () -> assertThat(card1.getScore()).isEqualTo(1),
                () -> assertThat(card2.getScore()).isEqualTo(11)
        );
    }
}
