package domain.user;

import domain.Deck;
import domain.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final int INITIAL_CARDS_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public Participants(List<String> nameValues) {
        this.dealer = new Dealer();
        this.players = new Players(nameValues);
    }

    public void drawInitialCardsEachParticipant(Deck deck) {
        addInitialCards(dealer, deck);
        players.getPlayers().forEach(player -> addInitialCards(player, deck));
    }

    private void addInitialCards(User user, Deck deck) {
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

    public void addCardToDealerIfPossible(Deck deck) {
        if (this.dealer.canAdd()) {
            this.dealer.addCard(deck.draw());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
