package service;

import domain.*;

import java.util.List;

public class GameService {

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
            GameResult userResult = judge(user.isBlackjack(), dealer.isBlackjack(), userScore, dealerScore, userBurst, dealerBurst);
            user.setGameResult(userResult);
            dealer.setRounds(userResult.reverse());
        }
    }

    private GameResult judge(boolean userIsBlackjack, boolean dealerIsBlackjack, int userScore, int dealerScore, boolean userBurst, boolean dealerBurst) {
        if(userIsBlackjack && dealerIsBlackjack) return GameResult.DRAW;
        if(userIsBlackjack) return GameResult.WIN;
        if(dealerIsBlackjack) return GameResult.LOSE;
        if (userBurst) return GameResult.LOSE;
        if (dealerBurst) return GameResult.WIN;
        if (userScore > dealerScore) return GameResult.WIN;
        if (userScore < dealerScore) return GameResult.LOSE;
        return GameResult.DRAW;
    }
}
