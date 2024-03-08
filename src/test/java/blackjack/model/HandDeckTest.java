package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.card.CardProperties;
import blackjack.model.deck.HandDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandDeckTest {

    @DisplayName("덱에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.EIGHT));

        //when
        handDeck.addCard(card);
        List<Card> cards = handDeck.getCards();

        //then
        assertThat(cards).containsExactly(card);
    }

    @DisplayName("덱에 카드를 추가할 때 동일한 카드가 있는 경우 예외를 발생시킨다.")
    @Test
    void addCard_duplicateCard() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.EIGHT));
        Card card2 = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.EIGHT));

        handDeck.addCard(card1);
        //when, then
        assertThatThrownBy(() -> handDeck.addCard(card2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("덱에 있는 모든 카드의 합을 계산한다.")
    @Test
    void calculateTotalScore() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.EIGHT));
        Card card2 = new Card(new CardProperties(CardPattern.SPADE, CardNumber.EIGHT));

        //when
        handDeck.addCard(card1);
        handDeck.addCard(card2);

        //then
        assertThat(handDeck.calculateTotalScore()).isEqualTo(16);
    }

    @DisplayName("덱에 11값으로 설정된 Ace 카드의 갯수를 반환한다.")
    @Test
    void countElevenAce() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.ACE));
        Card card2 = new Card(new CardProperties(CardPattern.SPADE, CardNumber.ACE));

        //when
        handDeck.addCard(card1);
        handDeck.addCard(card2);

        //then
        assertThat(handDeck.countElevenAce()).isEqualTo(2);
    }

    @DisplayName("덱에 11값으로 설정된 Ace 중 제일 첫번쨰 Ace의 값을 1로 변경한다.")
    @Test
    void switchAceValueInRow() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(new CardProperties(CardPattern.CLOVER, CardNumber.ACE));
        Card card2 = new Card(new CardProperties(CardPattern.SPADE, CardNumber.ACE));

        //when
        handDeck.addCard(card1);
        handDeck.addCard(card2);
        handDeck.switchAceValueInRow();

        //then
        assertThat(card1.getScore()).isEqualTo(1);
        assertThat(card2.getScore()).isEqualTo(11);
    }
}
