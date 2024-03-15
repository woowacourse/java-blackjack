package domain;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.GameResult;
import domain.result.GameResultStatus;
import domain.result.ResultProfitRatio;

public class BlackjackGame {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int DEALER_HIT_THRESHOLD = 16;
    public static final int BLACKJACK_SCORE = 21;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void setUp() {
        handOutCards(dealer, INITIAL_CARD_COUNT);
        players.forEach(player -> handOutCards(player, INITIAL_CARD_COUNT));
        checkBlackjack();
    }

    private void checkBlackjack() {
        for (Player player : players.getPlayers()) {
            if (player.isBlackjack() && !dealer.isBlackjack()) {
                player.revenue(ResultProfitRatio.BLACKJACK);
            }
        }
    }

    public void handOutCards(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            dealer.deal(participant);
        }
    }

    public GameResult resultsOfParticipants() {
        GameResult gameResult = new GameResult();
        players.forEach(player -> player.revenue(getResultOf(player, dealer)));
        gameResult.put(dealer, dealer.calculateProfit(players));
        players.forEach(player -> gameResult.put(player, player.profit()));
        return gameResult;
    }

    private ResultProfitRatio getResultOf(Participant standardTarget, Participant comparisonTarget) {
        return GameResultStatus.comparedTo(standardTarget.score(), comparisonTarget.score())
                               .getResultProfitRatio();
    }

    public Dealer dealer() {
        return dealer;
    }

    public Players players() {
        return players;
    }
}
