package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackGame {
    public static final int DEFAULT_HAND_NUMBER = 2;
    public static final String DEALER_NAME = "딜러";
    public static final Score DEALER_HIT_STAND_BOUNDARY = new Score(16);
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(List<String> players) {
        this.players = Players.from(players);
        this.dealer = new Dealer(DEALER_NAME);
        this.deck = new Deck();
    }

    public void giveHand() {
        players.giveCardsToEachPlayers(deck, DEFAULT_HAND_NUMBER);
        dealer.addCards(deck.drawWithAmount(DEFAULT_HAND_NUMBER));
    }

    public void playerHitStand(Function<Player, Boolean> decideHitStandFunc, Consumer<Player> printResultFunc) {
        players.hitStandEachPlayers(decideHitStandFunc, deck, printResultFunc);
    }

    public void dealerHitStand(Consumer<Boolean> printDecisionOutput) {
        while (true) {
            boolean dealerHitStand = dealer.decideHitStand(DEALER_HIT_STAND_BOUNDARY);
            if (!dealerHitStand) {
                printDecisionOutput.accept(dealerHitStand);
                break;
            }
            dealer.addCard(deck.draw());
            printDecisionOutput.accept(dealerHitStand);
        }
    }

    public List<RoundResult> getResult() {
        return players.getResults(dealer.getTotalSum());
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
