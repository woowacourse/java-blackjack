package service;

import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackService {
    private final Deck totalDeck;
    private final List<Participant> participants = new ArrayList<>();

    public BlackJackService(Deck totalDeck) {
        this.totalDeck = totalDeck;
    }

    public void createParticipants(List<String> playerNames) {
        createDealer();
        createPlayer(playerNames);
    }

    private void createDealer() {
        Dealer newDealer = new Dealer(
                Deck.createParticipantDeck(totalDeck)
        );
        participants.add(newDealer);
    }

    private void createPlayer(List<String> playerNames) {
        playerNames.forEach(
                name -> participants.add(
                        new Player(Deck.createParticipantDeck(totalDeck), name)
                )
        );
    }
}
