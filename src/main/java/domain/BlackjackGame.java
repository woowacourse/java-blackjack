package domain;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import domain.result.GameResult;
import domain.result.GameResultStatus;

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
        dealer.shuffleCards();
        handOutCards(dealer, dealer, INITIAL_CARD_COUNT);
        players.forEach(player -> handOutCards(dealer, player, INITIAL_CARD_COUNT));
    }

    public void handOutCards(final Dealer dealer, final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            dealer.deal(participant);
        }
    }

    public GameResult resultsOfPlayerPosition() {
        GameResult gameResult = new GameResult();
        players.forEach(player -> gameResult.put(player, getResultOf(dealer, player)));
        return gameResult;
    }

    public GameResult resultsOfDealerPosition() {
        GameResult gameResult = new GameResult();
        players.forEach(player -> gameResult.put(player, getResultOf(player, dealer)));
        return gameResult;
    }

    private GameResultStatus getResultOf(Participant standardTarget, Participant comparisonTarget) {
        return GameResultStatus.comparedTo(standardTarget.score(), comparisonTarget.score());
    }

    public Dealer dealer() {
        return dealer;
    }

    public Players players() {
        return players;
    }
}
