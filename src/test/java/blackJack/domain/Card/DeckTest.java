package blackJack.domain.Card;

import blackJack.utils.ExeptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("덱에서 가장 앞에 있는 카드를 뽑아 반환합니다.")
    void getCard_returnFirstCard() {
        Card firstCard = new Card(Suit.CLOVER,Denomination.ACE);
        Card secondCard = new Card(Suit.CLOVER,Denomination.JACK);
        LinkedList<Card> deckCards = new LinkedList<>(Arrays.asList(firstCard,secondCard));
        Deck deck = new Deck(deckCards);
        Card actual = deck.getCard();
        Card expected = firstCard;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("덱에 남아있는 카드가 없으면 예외를 발생시킵니다.")
    void getCard_noMoreCard() {
        LinkedList<Card> deckCards = new LinkedList<>(Arrays.asList());
        Deck deck = new Deck(deckCards);
        assertThatThrownBy(() -> deck.getCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.NO_MORE_CARD);
    }

}