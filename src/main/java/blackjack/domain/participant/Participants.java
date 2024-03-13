package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.result.*;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, HandGenerator handGenerator) {
        this.players = new Players(playersName, handGenerator);
        this.dealer = new Dealer(handGenerator);
    }

    public int executeAndCountDealerHit(Deck deck) {
        return dealer.hitAndCountCards(deck);
    }

    public InitialHands getParticipantsInitialHand() {
        List<InitialHand> playersHand = players.getInitialHands();
        InitialHand dealerHand = new InitialHand(dealer);
        return new InitialHands(dealerHand, playersHand);
    }

    public PlayerTurnSelector createPlayerTurnSelector() {
        return new PlayerTurnSelector(players);
    }

    public BetResultGenerator createBetResultGenerator() {
        return new BetResultGenerator(dealer, Referee.getInstance());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
