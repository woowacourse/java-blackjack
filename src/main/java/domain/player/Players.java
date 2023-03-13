package domain.player;

import domain.deck.Card;
import domain.game.Outcome;
import domain.game.Outcomes;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final int BLACKJACK_NUMBER = 21;

    private final List<Player> players;

    public Players(final List<Name> names, final List<Amount> amounts) {
        this.players = generatePlayers(names, amounts);
    }

    private Outcome decideOutcome(final Dealer dealer, final Player player) {
        if (isBlackjack(dealer, player)) {
            return Outcome.BLACKJACK;
        }
        if (isWin(dealer, player)) {
            return Outcome.WIN;
        }
        if (isLose(dealer, player)) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    private boolean isBlackjack(final Dealer dealer, final Player player) {
        return !dealer.isBlackjack() && player.isBlackjack();
    }

    private boolean isWin(final Dealer dealer, final Player player) {
        return !isBurst(player.score()) && (isBurst(dealer.score()) || dealer.score() < player.score());
    }

    private boolean isLose(final Dealer dealer, final Player player) {
        return isBurst(player.score()) || !isBurst(dealer.score()) && dealer.score() > player.score();
    }

    private boolean isBurst(final int score) {
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

    public Outcomes judgePlayersOutcome(final Dealer dealer) {
        final Outcomes outcomes = new Outcomes();

        for (final Player player : players) {
            outcomes.addOutcome(player.getName(), decideOutcome(dealer, player));
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
