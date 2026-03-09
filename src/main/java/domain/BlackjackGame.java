package domain;

import static domain.Constant.DEALER_NAME;
import static domain.Constant.DEFAULT_HAND_NUMBER;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import java.util.List;
import java.util.function.Function;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;
    private final Function<Integer, List<Card>> getCardsWithQuantityFunc;

    public BlackjackGame(List<String> players) {
        this.players = new Players(players);
        this.dealer = new Dealer(DEALER_NAME);
        this.deck = new Deck();
        this.getCardsWithQuantityFunc = (quantity) -> deck.pullCards(quantity);
    }

    public void giveHand() {
        players.giveCardsToEachPlayers(getCardsWithQuantityFunc, DEFAULT_HAND_NUMBER);
        dealer.addCards(getCardsWithQuantityFunc, DEFAULT_HAND_NUMBER);
    }

    public void giveCard(Participant participant) {
        participant.add(deck.pull());
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
