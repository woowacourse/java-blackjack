package blakcjack.domain.blackjackgame;

import blakcjack.domain.Outcome;
import blakcjack.domain.OutcomeStatistics;
import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlackjackGame {
    private final Deck deck;
    private final Participant dealer;
    private final List<Participant> players = new ArrayList<>();

    public BlackjackGame(final Deck deck, final List<String> names) {
        validate(names);

        this.deck = deck;
        this.dealer = new Dealer();

        for (String name : names) {
            players.add(new Player(new Name(name)));
        }
    }

    private void validate(final List<String> names) {
        Set<String> nameGroup = new HashSet<>(names);
        if (nameGroup.size() != names.size()) {
            throw new GameInitializationFailureException();
        }
    }

    public void distributeOneCard(final Participant player) {
        player.receiveCard(deck.drawCard());
    }

    public void initializeHands() {
        for (Participant player : players) {
            distributeOneCard(player);
            distributeOneCard(player);
        }
        distributeOneCard(dealer);
        distributeOneCard(dealer);
    }

    public List<Participant> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return (Dealer) dealer;
    }

    public OutcomeStatistics judgeOutcome() {
        final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();
        final Map<Outcome, Integer> dealerOutcome = new LinkedHashMap<>();
        initializeDealerOutcome(dealerOutcome);

        for (final Participant player : players) {
            final Outcome playerOutcome = Outcome.of((Player) player, (Dealer) dealer);
            playersOutcome.put(player.getName(), playerOutcome);
            updateDealerOutcome(dealerOutcome, playerOutcome);
        }
        return new OutcomeStatistics(dealerOutcome, playersOutcome);
    }

    private void updateDealerOutcome(final Map<Outcome, Integer> dealerOutcome, final Outcome playerOutcome) {
        dealerOutcome.computeIfPresent(playerOutcome.getDealerOutcome(), (outcome, count) -> count + 1);
    }

    private void initializeDealerOutcome(final Map<Outcome, Integer> dealerOutcome) {
        dealerOutcome.put(Outcome.WIN, 0);
        dealerOutcome.put(Outcome.DRAW, 0);
        dealerOutcome.put(Outcome.LOSE, 0);
    }
}
