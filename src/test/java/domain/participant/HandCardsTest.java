package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandCardsTest {

    @Test
    @DisplayName("카드를 받아서 손패에 추가한다.")
    void addHandCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.CLOVER, Denomination.JACK));
        cards.add(new Card(Suit.CLOVER, Denomination.TWO));
        HandCards handCards = new HandCards(cards);

        Card cardToAdd = new Card(Suit.SPADE, Denomination.ACE);
        handCards.addCard(cardToAdd);
        List<Card> afterAddCards = handCards.getCards();

        Assertions.assertThat(afterAddCards.size()).isEqualTo(3);
        Assertions.assertThat(afterAddCards).contains(cardToAdd);
    }
}
