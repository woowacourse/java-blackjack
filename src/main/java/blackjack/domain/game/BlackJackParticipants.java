package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;

public class BlackJackParticipants {
    private final Dealer dealer;
    private final BettingPlayers bettingPlayers;

    public BlackJackParticipants(final Deck deck, final List<String> nameValues, final List<Integer> moneyValues) {
        this.dealer = new Dealer(deck);
        this.bettingPlayers = new BettingPlayers(deck, nameValues, moneyValues);
    }

    public BettingResults createBettingResults() {
        final Map<Participant, Money> bettingResults = bettingPlayers.findBettingResultsBy(dealer);
        return new BettingResults(bettingResults, dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return bettingPlayers.getPlayers();
    }
}
