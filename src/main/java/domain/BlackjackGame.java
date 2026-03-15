package domain;

import java.util.List;


public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.cardDeck = new CardDeck();
        this.dealer = new Dealer();
        this.players = players;
    }

    public void initDeal() {
        for (int i = 0; i < 2; i++) {
            players.receiveCardAll(cardDeck::draw);
            dealer.receiveCard(cardDeck.draw());
        }
    }

    public void deal(Participant participant) {
        participant.receiveCard(cardDeck.draw());
    }

    public void processDealerTurn(Runnable onHit) {
        while (dealer.isHit()) {
            deal(dealer);
            onHit.run();
        }
    }

    public List<Player> getBlackjackPlayers() {
        return this.players.getBlackjackPlayers();
    }

    public void determineResult() {
        int dealerScore = dealer.calculateScore();
        boolean dealerBurst = dealer.isBurst(dealerScore);

        for (Player player : players.getPlayers()) {
            int userScore = player.calculateScore();
            boolean userBurst = player.isBurst(userScore);
            GameResult userResult = judge(player.isBlackjack(), dealer.isBlackjack(), userScore, dealerScore, userBurst, dealerBurst);
            player.setGameResult(userResult);
            dealer.setRounds(userResult.reverse());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
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
