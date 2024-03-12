package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.result.*;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private static final int DEALER_INDEX = 0;

    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, HandGenerator handGenerator) {
        this.players = new Players(playersName, handGenerator);
        this.dealer = new Dealer(handGenerator);
    }

    public int executeAndCountDealerHit(Deck deck) {
        return dealer.hitAndCountCards(deck);
    }

    public List<InitialHand> getParticipantsInitialHand() {
        List<InitialHand> participantsInitialHand = new ArrayList<>(players.getInitialHands());
        InitialHand dealerHand = new InitialHand(dealer);
        participantsInitialHand.add(DEALER_INDEX, dealerHand);
        return participantsInitialHand;
    }

    public PlayerTurnSelector createPlayerTurnSelector() {
        return new PlayerTurnSelector(players);
    }

    public Referee createRefereeByDealer() {
        return new Referee(dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
