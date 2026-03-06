package domain;

import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ParticipantTest {
    int startSize;
    Participant pobi;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
    }

    @Test
    void 참가자들은_카드를_받는다() {
        List<Card> cards = Deck.createDeck();
        Deck deck = new Deck(cards);
        pobi.receive(deck.draw());

        assertThat(pobi.handSize()).isEqualTo(startSize + 1);
    }

    @Test
    void 참가자들은_시작_시_카드_두장을_받는다() {
        List<Card> cards = Deck.createDeck();
        Deck deck = new Deck(cards);
        pobi.receiveInitialCards(List.of(deck.draw(), deck.draw()));

        assertThat(pobi.handSize()).isEqualTo(startSize + 2);
    }
}