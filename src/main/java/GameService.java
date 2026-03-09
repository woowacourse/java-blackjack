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
        boolean dealerBurst = dealer.isBurst();
        users.forEach(user -> user.setGameResult(decideResult(user,dealer)));
    }

    private GameResult decideResult(User user, Dealer dealer) {
        user.calculateScore();
        boolean dealerBurst = dealer.isBurst();
        boolean userBurst = user.isBurst();

        if (dealerBurst && userBurst) {
            dealer.setRounds(GameResult.DRAW);
            return GameResult.DRAW;
        }
        if (dealerBurst) {
            dealer.setRounds(GameResult.LOSE);
            return GameResult.WIN;
        }
        if (userBurst) {
            dealer.setRounds(GameResult.WIN);
            return GameResult.LOSE;
        }

        if (user.getScore() > dealer.getScore()) {
            dealer.setRounds(GameResult.LOSE);
            return GameResult.WIN;
        }
        if (user.getScore() < dealer.getScore()) {
            dealer.setRounds(GameResult.WIN);
            return GameResult.LOSE;
        }
        dealer.setRounds(GameResult.DRAW);
        return GameResult.DRAW;
    }
}
