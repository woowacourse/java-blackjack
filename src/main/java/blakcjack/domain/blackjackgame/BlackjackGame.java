package blakcjack.domain.blackjackgame;

import blakcjack.domain.card.Deck;
import blakcjack.domain.outcome.Outcome;
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
    public static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final Participant dealer;
    private final List<Participant> players = new ArrayList<>();

    public BlackjackGame(final Deck deck, final List<String> names) {
        validate(names);

        this.deck = deck;
        this.dealer = new Dealer();

        for (String name : names) {
            players.add(new Player(name));
        }
    }

    private void validate(final List<String> names) {
        Set<String> nameGroup = new HashSet<>(names);
        if (nameGroup.size() != names.size()) {
            throw new GameInitializationFailureException();
        }
    }

    public void initializeHands() {
        for (Participant player : players) {
            distributeInitialHand(player);
        }
        distributeInitialHand(dealer);
    }

    private void distributeInitialHand(final Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeOneCard(participant);
        }
    }

    public void distributeOneCard(final Participant participant) {
        participant.receiveCard(deck.drawCard());
    }

    public Map<String, Outcome> judgePlayersOutcome() {
        final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();

        for (final Participant player : players) {
            final Outcome playerOutcome = player.decideOutcome(dealer);
            playersOutcome.put(player.getNameValue(), playerOutcome);
        }
        return playersOutcome;
    }

    public Map<Outcome, Integer> judgeDealerOutcome(final Map<String, Outcome> playersOutcome) {
        final Map<Outcome, Integer> dealerOutcome = new LinkedHashMap<>();

        dealerOutcome.put(Outcome.WIN, Collections.frequency(playersOutcome.values(), Outcome.LOSE));
        dealerOutcome.put(Outcome.DRAW, Collections.frequency(playersOutcome.values(), Outcome.DRAW));
        dealerOutcome.put(Outcome.LOSE, Collections.frequency(playersOutcome.values(), Outcome.WIN));
        return dealerOutcome;
    }

    public List<Participant> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Participant getDealer() {
        return dealer;
    }
}
