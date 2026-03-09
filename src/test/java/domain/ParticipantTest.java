package domain;

import domain.participant.Participant;
import domain.participant.Player;
import domain.strategy.RandomShuffleStrategy;
import domain.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ParticipantTest {
    public static final int INITIAL_CARDS_COUNT = 2;
    int startSize;
    Participant pobi;
    ShuffleStrategy noShuffleStrategy = new RandomShuffleStrategy();

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
    }

    @Test
    void 참가자들은_카드를_받는다() {
        Deck deck = Deck.createDeck(noShuffleStrategy);
        pobi.receive(deck.draw());

        assertThat(pobi.handSize()).isEqualTo(startSize + 1);
    }

    @Test
    void 참가자들은_시작_시_카드_두장을_받는다() {
        Deck deck = Deck.createDeck(noShuffleStrategy);
        pobi.receiveInitialCards(deck.drawInitialCards(INITIAL_CARDS_COUNT));

        assertThat(pobi.handSize()).isEqualTo(startSize + 2);
    }
}