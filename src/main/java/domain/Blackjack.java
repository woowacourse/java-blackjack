package domain;

import java.util.List;

public class Blackjack {
    private final Players players;
    private final Dealer dealer;

    public Blackjack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        initGame();
    }

    private void initGame() {
        initPlayers();
        initDealer();
    }

    private void initPlayers() {
        for (final Player player : players.getPlayers()) {
            player.dealCards(dealer.drawCards(2));
        }
    }

    private void initDealer() {
        dealer.dealCards(dealer.drawCards(2));
    }

    public void dealCard(final Player player) {
        player.dealCard(dealer.drawSingleCard());
    }


    public BlackjackResult finishGame() {
        final BlackjackRule blackjackRule = new BlackjackRule();
        return blackjackRule.calculateResult(players.getPlayers(), dealer);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Player getDealer() {
        return dealer;
    }
}
