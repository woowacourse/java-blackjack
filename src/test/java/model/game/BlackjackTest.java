package model.game;

import java.util.List;
import model.card.Card;
import model.card.Deck;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Participants;
import model.participant.Player;
import org.junit.jupiter.api.BeforeEach;

public class BlackjackTest {
    Blackjack blackjack;
    Participants participants;
    Deck simpleDeck = () -> Card.of(Suit.SPADE, Rank.ACE);

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of(Player.of("pobi", 1000), Player.of("jason", 1000)));
        Dealer dealer = participants.getDealer();
        dealer.receive(Card.of(Suit.SPADE, Rank.TWO));
        dealer.receive(Card.of(Suit.SPADE, Rank.FOUR));

        Participant player1 = participants.getPlayers().getFirst();
        player1.receive(Card.of(Suit.HEART, Rank.TWO));
        player1.receive(Card.of(Suit.HEART, Rank.THREE));
        Participant player2 = participants.getPlayers().get(1);
        player2.receive(Card.of(Suit.HEART, Rank.FOUR));
        player2.receive(Card.of(Suit.HEART, Rank.SIX));

        blackjack = Blackjack.of(participants, simpleDeck);
    }
}
