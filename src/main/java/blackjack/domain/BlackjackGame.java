package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.*;
import blackjack.domain.result.UserResults;

public class BlackjackGame {

    private final Users users;
    private final Deck deck;

    public BlackjackGame(Players players) {
        users = new Users(new Dealer(), players);
        deck = Deck.getInstance();
    }

    public void giveInitialCardsToUsers() {
        deck.shuffleCards();
        users.giveInitialCards();
    }

    public void updateCard(User user) {
        user.updateCardScore(deck.drawCard());
    }

    public UserResults getResults() {
        return UserResults.of(users);
    }

    public void calculatePlayersBetAmount(UserResults results) {
        users.calculateReceivingAmount(results);
    }

    public Dealer getDealer() {
        return users.getDealer();
    }

    public Players getPlayers() {
        return users.getPlayers();
    }
}
