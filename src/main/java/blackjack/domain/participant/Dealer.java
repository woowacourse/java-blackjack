package blackjack.domain.participant;

import blackjack.domain.MatchResult;
import blackjack.domain.PlayingCards;
import blackjack.dto.DealerGameResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerGameResult;
import java.util.List;

public class Dealer extends Participant {

    private final int DEALER_SCORE = 16;

    private final static String DEALER_NICKNAME = "딜러";

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

    public DealerGameResult getDealerWinningResult(List<PlayerGameResult> winningResultsWithDealer) {
        int dealerWin = (int) winningResultsWithDealer
            .stream()
            .filter(result -> result.matchResult() == MatchResult.LOSE)
            .count();
        int dealerTie = (int) winningResultsWithDealer
            .stream()
            .filter(result -> result.matchResult() == MatchResult.TIE)
            .count();
        int dealerLose = (int) winningResultsWithDealer
            .stream()
            .filter(result -> result.matchResult() == MatchResult.WIN)
            .count();
        return new DealerGameResult(dealerWin, dealerTie, dealerLose);
    }

    public ParticipantResult getInitialResult() {
        return new ParticipantResult(
            getDealerNickname(),
            getFirstCard(),
            getTotalScore()
        );
    }
}
