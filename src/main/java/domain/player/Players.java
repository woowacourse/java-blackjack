package domain.player;

import domain.deck.Card;
import domain.game.Outcome;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private static final int BLACKJACK_NUMBER = 21;

    private final List<Player> players;

    public Players(final List<Name> names, final List<Amount> amounts) {
        this.players = generatePlayers(names, amounts);
    }

    private static Outcome decideOutcome(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return Outcome.DRAW;
        }
        if (player.isBlackjack()) {
            return Outcome.BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return Outcome.LOSE;
        }
        if (isBurst(player.score())) {
            return Outcome.LOSE;
        }
        if (isBurst(dealer.score())) {
            return Outcome.WIN;
        }
        if (dealer.score() < player.score()) {
            return Outcome.WIN;
        }
        if (dealer.score() > player.score()) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    private static boolean isBurst(final int score) {
        return score > BLACKJACK_NUMBER;
    }

    private List<Player> generatePlayers(final List<Name> names, final List<Amount> amounts) {
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < amounts.size(); i++) {
            players.add(new Player(names.get(i), amounts.get(i)));
        }

        return players;
    }

    public void drawCardPlayer(final Name name, final Card card) {
        getPlayer(name).drawCard(card);
    }

    public Map<Name, Outcome> judgePlayersOutcome(final Dealer dealer) {
        final Map<Name, Outcome> outcomes = new LinkedHashMap<>();

        for (final Player player : players) {
            outcomes.put(player.getName(), decideOutcome(dealer, player));
        }

        return outcomes;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    private Player getPlayer(final Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    public List<Card> getPlayerCards(final Name name) {
        return getPlayer(name).cards();
    }

    public int getPlayerScore(final Name name) {
        return getPlayer(name).score();
    }
}
