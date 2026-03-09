import domain.*;

import java.util.List;

public class GameService {

    private final CardDeck cardDeck;

    public GameService() {
        this.cardDeck = new CardDeck();
    }

    public void initDeal(List<User> users, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            for (User user : users) {
                user.receiveCard(deal());
            }
            dealer.receiveCard(deal());
        }
    }


    public Card deal() {
        return cardDeck.deal();
    }

    public void settleResult(List<User> users, Dealer dealer) {
        dealer.calculateScore();
        users.forEach(user -> user.setGameResult(decideResult(user, dealer)));
    }

    private GameResult decideResult(User user, Dealer dealer) {
        user.calculateScore();

        if (user.isBlackjack() && dealer.isBlackjack()) {
            return applyResult(dealer, GameResult.DRAW);
        }
        if (user.isBlackjack()) {
            return applyResult(dealer, GameResult.WIN);
        }
        if (dealer.isBlackjack()) {
            return applyResult(dealer, GameResult.LOSE);
        }
        if (user.isBurst()) {
            return applyResult(dealer, GameResult.LOSE);
        }
        if (dealer.isBurst()) {
            return applyResult(dealer, GameResult.WIN);
        }

        return applyResult(dealer, compareScore(user, dealer));
    }

    private GameResult compareScore(User user, Dealer dealer) {
        if (user.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        if (user.getScore() < dealer.getScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private GameResult applyResult(Dealer dealer, GameResult userResult) {
        dealer.recordRounds(userResult.opposite());
        return userResult;
    }
}
