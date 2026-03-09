import domain.*;

import java.util.List;

public class GameService {

    private final CardDeck cardDeck;

    public GameService(ShuffleStrategy strategy) {
        this.cardDeck = new CardDeck(strategy);
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
        for (User user : users) {
            user.calculateScore();
            GameResult result = decideResult(user, dealer);
            user.setGameResult(result);
            dealer.recordRounds(result.opposite());
        }
    }

    private GameResult decideResult(User user, Dealer dealer) {
        if (user.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (user.isBlackjack()) {
            return GameResult.WIN;
        }
        if (dealer.isBlackjack()) {
            return GameResult.LOSE;
        }
        if (user.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst()) {
            return GameResult.WIN;
        }
        return compareScore(user, dealer);
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
}
