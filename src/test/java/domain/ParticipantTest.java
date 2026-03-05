package domain;

import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ParticipantTest {
    Hand pobiHand;
    int startSize;
    Participant pobi;

    @BeforeEach
    void setUp() {
        pobiHand = new Hand(new ArrayList<>());

        startSize = pobiHand.size();
        pobi = new Player("pobi", pobiHand);
    }

    @Test
    void 참가자들은_카드를_받는다() {
        List<Card> cards = Deck.prepareCards();
        Deck deck = new Deck(cards);
        pobi.receive(deck);

        assertThat(pobi.handSize()).isEqualTo(startSize + 1);
    }

    @Test
    void 참가자들은_시작_시_카드_두장을_받는다() {
        List<Card> cards = Deck.prepareCards();
        Deck deck = new Deck(cards);
        pobi.receiveInitialCards(deck);

        assertThat(pobi.handSize()).isEqualTo(startSize + 2);
    }
}