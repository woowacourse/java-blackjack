package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackParticipants {
    private final Dealer dealer;
    private final BettingPlayers bettingPlayers;

    public BlackJackParticipants(final Deck deck, final List<String> nameValues, final List<Integer> moneyValues) {
        this.dealer = new Dealer(initCards(deck));
        this.bettingPlayers = new BettingPlayers(createPlayers(deck, nameValues), createMoneys(moneyValues));
    }

    public BettingResults createBettingResults() {
        final Map<Participant, Money> bettingResults = bettingPlayers.findBettingResultsBy(dealer);
        return new BettingResults(bettingResults, dealer);
    }

    private List<Player> createPlayers(final Deck deck, final List<String> nameValues) {
        return nameValues.stream()
                .map(nameValue -> new Player(initCards(deck), nameValue))
                .collect(Collectors.toList());
    }

    private ParticipantCards initCards(final Deck deck) {
        return new ParticipantCards(deck);
    }

    private List<Money> createMoneys(final List<Integer> moneyValues) {
        return moneyValues.stream()
                .map(Money::new)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return bettingPlayers.getPlayers();
    }
}
