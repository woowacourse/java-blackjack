package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Participants {
    private final int INITIAL_CARDS_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants from(List<String> nameValues) {
        return new Participants(Players.from(nameValues), new Dealer());
    }

    public void drawInitialCardsEachParticipant(Deck deck) {
        drawInitialCardsBy(dealer, deck);
        players.getPlayers().forEach(player -> drawInitialCardsBy(player, deck));
    }

    private void drawInitialCardsBy(User user, Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            user.addCard(deck.draw());
        }
    }

    public Map<Player, Result> calculateAllResults() {
        Map<Player, Result> resultsOfPlayers = new HashMap<>();
        this.getPlayers().forEach(player -> resultsOfPlayers.put(player, calculateResult(player)));
        return resultsOfPlayers;
    }

    private Result calculateResult(Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() || player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public boolean hitOrStayByDealer(Deck deck) {
        if (this.dealer.canAdd()) {
            this.dealer.addCard(deck.draw());
            return true;
        }
        return false;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
