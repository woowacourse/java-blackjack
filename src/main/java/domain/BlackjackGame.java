package domain;

import dto.GameResult;

import java.util.List;

public class BlackjackGame {
    public static final int INITIAL_HAND_SIZE = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final Players players, final Dealer dealer) {
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
            player.drawCards(dealer.drawCards(INITIAL_HAND_SIZE));
        }
    }

    private void initDealer() {
        dealer.drawCards(dealer.drawCards(INITIAL_HAND_SIZE));
    }

    public void dealCard(final Participant participant) {
        participant.drawCard(dealer.drawSingleCard());
    }

    public GameResult finishGame() {
        final Rule rule = new Rule();
        return rule.calculateProfits(players, dealer);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
