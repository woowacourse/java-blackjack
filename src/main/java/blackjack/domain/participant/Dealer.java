package blackjack.domain.participant;

import blackjack.domain.MatchResult;
import blackjack.domain.PlayingCards;
import blackjack.dto.DealerGameResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerGameResult;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_SCORE = 16;

    private static final String DEALER_NICKNAME = "딜러";

    private Dealer(String nickname, Role role) {
        super(nickname, PlayingCards.createEmptyHands(), role);
    }

    public static Dealer from() {
        return new Dealer(DEALER_NICKNAME, Role.DEALER);
    }

    public String getDealerNickname() {
        return DEALER_NICKNAME;
    }

    public String getFirstCard() {
        return hand.getFirstCard().getDisplayName();
    }

    public boolean isDealerDraw() {
        return hand.calculateTotalScore() <= DEALER_SCORE;
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public DealerGameResult getDealerWinningResult(List<PlayerGameResult> playerGameResults) {
        int dealerWin = (int) playerGameResults
            .stream()
            .filter(result -> result.matchResult() == MatchResult.LOSE)
            .count();
        int dealerTie = (int) playerGameResults
            .stream()
            .filter(result -> result.matchResult() == MatchResult.TIE)
            .count();
        int dealerLose = (int) playerGameResults
            .stream()
            .filter(result -> result.matchResult() == MatchResult.WIN)
            .count();
        double profit = calculateDealerProfit(playerGameResults);
        return new DealerGameResult(dealerWin, dealerTie, dealerLose, profit);
    }

    private double calculateDealerProfit(List<PlayerGameResult> playerGameResults) {
        double totalPlayerProfit = playerGameResults.stream()
            .mapToDouble(PlayerGameResult::profit)
            .sum();
        return -totalPlayerProfit;
    }

    public ParticipantResult getInitialResult() {
        return new ParticipantResult(
            getDealerNickname(),
            getFirstCard(),
            getTotalScore()
        );
    }
}
