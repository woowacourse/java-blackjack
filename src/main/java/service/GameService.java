package service;

import domain.*;

import java.util.List;

public class GameService {

    private final int BLACKJACK_NUM = 21;
    private final int DEALER_HIT_NUM = 16;

    private final CardDeck cardDeck;

    public GameService() {
        this.cardDeck = new CardDeck();
    }

    public Card deal() {
        return cardDeck.deal();
    }

    public void initDeal(List<User> users, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            for (User user : users) {
                user.receiveCard(deal());
            }
            dealer.receiveCard(deal());
        }
    }

    public void determineResult(List<User> users, Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        boolean dealerBurst = dealer.isBurst(dealerScore);

        for (User user : users) {
            int userScore = user.calculateScore();
            boolean userBurst = user.isBurst(userScore);
            GameResult userResult = judge(userScore, dealerScore, userBurst, dealerBurst);
            user.setGameResult(userResult);
            dealer.setRounds(userResult.reverse());
        }
    }

    private GameResult judge(int userScore, int dealerScore, boolean userBurst, boolean dealerBurst) {
        if (dealerBurst && userBurst) return GameResult.DRAW;
        if (dealerBurst) return GameResult.WIN;
        if (userBurst) return GameResult.LOSE;
        if (userScore > dealerScore) return GameResult.WIN;
        if (userScore < dealerScore) return GameResult.LOSE;
        return GameResult.DRAW;
    }
}
