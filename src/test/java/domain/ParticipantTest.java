package domain;

import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {
    Participant pobi;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
    }

    @Test
    void 참가자는_덱에서_카드를_한_장_받는다() {
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        pobi.receive(deck.draw());

        int cardsSize = Math.toIntExact(pobi.cards().size());

        assertThat(cardsSize).isEqualTo(1);
    }

    @Test
    void 참가자는_게임_시작_시_카드_두_장을_받는다() {
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        pobi.receiveInitialCards(deck.drawInitialCards());

        int cardsSize = Math.toIntExact(pobi.cards().size());

        assertThat(cardsSize).isEqualTo(2);
    }
}
