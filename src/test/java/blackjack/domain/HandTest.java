package blackjack.domain;

import static blackjack.domain.Number.JACK;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("손패는 카드를 받으면 마지막 위치에 저장한다.")
    @Test
    void should_addCard_At_LastIndex() {
        Hand hand = new Hand();
        Card card = new Card(SPADE, JACK);

        hand.add(card);

        List<Card> cards = hand.getCards();
        Card lastCard = cards.get(cards.size() - 1);
        assertThat(lastCard).isEqualTo(card);
    }

    @DisplayName("손패는 카드를 받으면 카드의 수가 1 증가한다.")
    @Test
    void should_HasSize_1Increased() {
        Hand hand = new Hand();
        int previousSize = hand.getCards().size();

        Card card = new Card(SPADE, JACK);
        hand.add(card);
        int currentSize = hand.getCards().size();

        assertThat(currentSize).isEqualTo(previousSize + 1);
    }
}
