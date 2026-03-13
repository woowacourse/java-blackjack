package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    public static final int INITIAL_CARD_COUNT = 2;
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;


    public BlackjackGame() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer("딜러");
        this.deck = new Deck();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void registPlayers(List<String> names) {
        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public void giveHand() {
        for (Player player : players) {
            giveCards(player, INITIAL_CARD_COUNT);
        }
        giveCards(dealer, INITIAL_CARD_COUNT);
    }

    public void giveCards(Participant participant, int quantity) {
        for (int i = 0; i < quantity; i++) {
            giveCard(participant);
        }
    }

    public void giveCard(Participant participant) {
        Card card = deck.pull();
        participant.add(card);
    }

    public boolean dealerHitsStand() {
        if (dealer.decideHitStand()) {
            giveCard(dealer);
            return true;
        }
        return false;
    }
}
