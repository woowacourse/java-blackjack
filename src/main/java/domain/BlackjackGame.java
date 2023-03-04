package domain;

import domain.user.User;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final int INITIAL_CARDS_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(List<String> nameValues) {
        this.dealer = new Dealer();
        this.players = new Players(nameValues);
    }

    public void initGame(Deck deck) {
        addInitialCards(dealer, deck);
        players.getPlayers().forEach(player -> addInitialCards(player, deck));
    }

    private void addInitialCards(User user, Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            user.addCard(deck.draw());
        }
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    public void addCardToDealerIfPossible(Deck deck) {
        this.dealer.addCard(deck.draw());
    }

    public Map<Player, Result> calculateAllResults() {
        Map<Player, Result> resultsOfPlayers = new HashMap<>();
        this.players.getPlayers().forEach(player -> resultsOfPlayers.put(player, calculateResult(player)));
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
}
