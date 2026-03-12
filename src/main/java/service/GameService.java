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

    public void initDeal(List<Player> players, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.receiveCard(deal());
            }
            dealer.receiveCard(deal());
        }
    }

    public void determineResult(List<Player> players, Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        boolean dealerBurst = dealer.isBurst(dealerScore);

        for (Player player : players) {
            int userScore = player.calculateScore();
            boolean userBurst = player.isBurst(userScore);
            GameResult userResult = judge(player.isBlackjack(), dealer.isBlackjack(), userScore, dealerScore, userBurst, dealerBurst);
            player.setGameResult(userResult);
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
