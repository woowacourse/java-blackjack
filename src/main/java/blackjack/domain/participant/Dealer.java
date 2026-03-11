package blackjack.domain.participant;

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

    public DealerGameResult calculateDealerProfitResult(List<PlayerGameResult> playerGameResults) {
        long totalPlayerProfit = playerGameResults.stream()
            .mapToLong(PlayerGameResult::profit)
            .sum();
        return DealerGameResult.from(-totalPlayerProfit);
    }

    public ParticipantResult getInitialResult() {
        return ParticipantResult.of(
            getDealerNickname(),
            getFirstCard(),
            getTotalScore()
        );
    }
}
