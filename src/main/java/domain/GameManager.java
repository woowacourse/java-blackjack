package domain;

import domain.player.Player;
import domain.player.Players;

public class GameManager {
    private final Deck deck;

    public GameManager(Deck deck) {
        this.deck = deck;
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

    public CardDto getStartingCard(Dealer dealer) {
        return dealer.startingHandInfo();

    }

    public CardDto getCardsResult(Participant participant) {
        return participant.handInfo();
    }

}
