package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.result.UserResults;

public class BlackjackGame {

    public static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void giveInitialCardsToUsers() {
        Deck deck = Deck.getInstance();
        deck.shuffleCards();
        for (Player player : players.getPlayers()) {
            giveInitialCards(player);
        }
        giveInitialCards(dealer);
    }

    private void giveInitialCards(User user) {
        Deck deck = Deck.getInstance();
        for (int cardIndex = 0; cardIndex < NUMBER_OF_INITIAL_CARDS; cardIndex++) {
            user.updateCardScore(deck.drawCard());
        }
    }

    public void updateCard(User user) {
        Deck deck = Deck.getInstance();
        user.updateCardScore(deck.drawCard());
    }

    public UserResults getResults() {
        return UserResults.of(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
