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

    public int calculateScore(List<Card> cards) {
        int score = 0;
        int aceCount = 0;
        for (Card card: cards) {
            if (!card.isAce()) {
                score += card.getValue();
                continue;
            }
            aceCount++;
        }
        for(int i = 0; i< aceCount; i++){
            score += calculateOptimalAceScore(score);
        }
        return score;
    }

    public int calculateOptimalAceScore(int sum) {
        if (sum > 10) {
         return 1;
        }
        return 11;
    }

    public void determineResult(List<User> users, Dealer dealer) {
        int dealerScore = calculateScore(dealer.getHand());
        boolean dealerBurst = dealer.isBurst(dealerScore);

        for (User user : users) {
            user.setGameResult(judge(user, dealer, dealerBurst));
        }
    }

    private GameResult judge(User user,  Dealer dealer, boolean dealerBurst) {
        int userScore = calculateScore(user.getHand());
        int dealerScore = calculateScore(dealer.getHand());
        boolean userBurst = user.isBurst(userScore);

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

        if (userScore > dealerScore) {
            dealer.setRounds(GameResult.LOSE);
            return GameResult.WIN;
        }
        if (userScore < dealerScore) {
            dealer.setRounds(GameResult.WIN);
            return GameResult.LOSE;
        }
        dealer.setRounds(GameResult.DRAW);
        return GameResult.DRAW;
    }
}
