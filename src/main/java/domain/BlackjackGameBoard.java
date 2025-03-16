package domain;

import domain.card.Cards;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlackjackGameBoard {

    private final int INITIAL_DRAW_COUNT = 2;
    private final int INITIAL_USER_OPEN_COUNT = 2;
    private final int INITIAL_DEALER_OPEN_COUNT = 1;

    private final Deck deck;

    public BlackjackGameBoard(Deck deck) {
        this.deck = deck;
    }

    public void distributeInitialCards(Player player) {
        player.drawCards(deck, INITIAL_DRAW_COUNT);
    }

    public void openInitialCards(Dealer dealer) {
        dealer.openCards(INITIAL_DEALER_OPEN_COUNT);
    }

    public void openInitialCards(User user) {
        user.openCards(INITIAL_USER_OPEN_COUNT);
    }

    public void hitUntilStay(User user,
                             Function<User, Boolean> wantHit, BiConsumer<User, Cards> onHit) {
        while (user.isRunning() && wantHit.apply(user)) {
            user.hit(deck);
            onHit.accept(user, user.cards());
        }
        if (user.isRunning()) {
            user.stay();
        }
    }

    public void hitUntilUnder16(Dealer dealer) {
        while (dealer.isRunning()) {
            dealer.hit(deck);
        }
    }

    public Cards playerCards(Player player) {
        return player.cards();
    }

    public int playerCardsSum(Player player) {
        return player.computeOptimalSum();
    }
}
