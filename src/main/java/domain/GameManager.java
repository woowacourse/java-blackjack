package domain;

import domain.card.CardsSnapshot;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class GameManager {
    private static final int STARTING_CARD_COUNT = 2;
    private final Deck deck;

    private GameManager(Deck deck) {
        this.deck = deck;
    }

    public static GameManager createWith(Deck deck) {
        return new GameManager(deck);
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

    public CardsSnapshot getStartingCard(Dealer dealer) {
        return dealer.startingHandInfo();
    }

    public CardsSnapshot getCardsResult(Participant participant) {
        return participant.handInfo();
    }

    public void dealStartingCards(Participant participant) {
        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
            participant.addCard(deck.pop());
        }
    }
}
