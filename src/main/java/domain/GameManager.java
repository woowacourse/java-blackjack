package domain;

import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class GameManager {
    private final Deck deck;

    public GameManager(Deck deck) {
        this.deck = deck;
    }

    public void dealInitialCards(Participants participants) {
        dealCard(participants.getDealer());
        dealCard(participants.getDealer());
        dealCardTo(participants.getPlayers(), 2);
    }

    public void dealCard(Participant participant) {
        participant.addCard(deck.pop());
    }

    public void dealCardTo(Players players, int count) {
        for (int i = 0; i < count; i++) {
            for (Player player : players) {
                dealCard(player);
            }
        }
    }

    public List<Card> getCardsResult(Participant participant) {
        return participant.getCards();
    }

}
