package domain;

import static domain.Constant.DEALER_NAME;
import static domain.Constant.DEFAULT_HAND_NUMBER;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(List<String> players) {
        this.players = new Players(players);
        this.dealer = new Dealer(DEALER_NAME);
        this.deck = new Deck();
    }

    public void giveHand() {
        players.giveCardsToEachPlayers(deck.getCardsWithQuantityFunc, DEFAULT_HAND_NUMBER);
        dealer.addCards(deck.getCardsWithQuantityFunc, DEFAULT_HAND_NUMBER);
    }

    public void giveCard(Participant participant) {
        participant.add(deck.pull());
    }

    public void playerHitStand(Function<Player, Boolean> decideHitStandFunc, Consumer<Player> printResultFunc) {
        players.hitStandEachPlayers(decideHitStandFunc, () -> deck.pull(), printResultFunc);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
