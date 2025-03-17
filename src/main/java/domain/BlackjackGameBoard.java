package domain;

import domain.card.Cards;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import domain.player.Users;
import domain.profit.Profit;
import domain.profit.ProfitStrategy;
import domain.profit.UserBattleResult;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlackjackGameBoard {

    public static final int INITIAL_DRAW_COUNT = 2;
    public static final int INITIAL_USER_OPEN_COUNT = 2;
    public static final int INITIAL_DEALER_OPEN_COUNT = 1;

    private final Deck deck;

    public BlackjackGameBoard(Deck deck) {
        this.deck = deck;
    }

    public void distributeInitialCards(Player player) {
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            player.hit(deck.drawCard(), false);
        }
    }

    public void openInitialCards(Dealer dealer) {
        dealer.openCards(INITIAL_DEALER_OPEN_COUNT);
    }

    public void openInitialCards(User user) {
        user.openCards(INITIAL_USER_OPEN_COUNT);
    }

    public void hitUntilStay(User user,
                             Function<User, Boolean> wantHit, BiConsumer<String, Cards> onHitPrintNameAndCards) {
        while (user.isRunning() && wantHit.apply(user)) {
            user.hit(deck.drawCard(), true);
            onHitPrintNameAndCards.accept(user.getName(), user.cards());
        }
        if (user.isRunning()) {
            user.stay();
        }
    }

    public void hitUntilNotHittable(Dealer dealer, Runnable onHitPrintHitMessage) {
        while (dealer.isRunning()) {
            dealer.hit(deck.drawCard(), true);
            onHitPrintHitMessage.run();
        }
    }

    public Cards playerCards(Player player) {
        return player.cards();
    }

    public int playerCardsSum(Player player) {
        return player.computeOptimalSum();
    }


    public Profit computeUserProfit(User user, Dealer dealer, ProfitStrategy profitStrategy) {
        return profitStrategy.calculateProfit(user.getBet(),
                UserBattleResult.determineUserBattleResult(user.getState(), dealer.getState()));
    }

    public Profit computeDealerProfit(Dealer dealer, Users users, ProfitStrategy profitStrategy) {
        int usersProfitSum = 0;
        for (User user : users.getUsers()) {
            usersProfitSum += computeUserProfit(user, dealer, profitStrategy).value();
        }
        return new Profit(-usersProfitSum);
    }
}
