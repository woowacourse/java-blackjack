package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.Nickname;
import blackjack.dto.DealerGameResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerGameResult;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_SCORE = 16;

    private Dealer(Role role) {
        super(Nickname.makeDealerNickname(), Hand.createEmptyHands(), role);
    }

    public static Dealer from() {
        return new Dealer(Role.DEALER);
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

    public DealerGameResult calculateDealerProfitResult(List<PlayerGameResult> playerGameResults) {
        long totalPlayerProfit = playerGameResults.stream()
            .mapToLong(PlayerGameResult::profit)
            .sum();
        return DealerGameResult.from(-totalPlayerProfit);
    }

    public ParticipantResult getInitialResult() {
        return ParticipantResult.of(
            getNickname(),
            getFirstCard(),
            getTotalScore()
        );
    }
}
