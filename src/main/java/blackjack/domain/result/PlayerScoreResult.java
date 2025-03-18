package blackjack.domain.result;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;
import static blackjack.domain.result.ResultStatus.BLACKJACK;
import static blackjack.domain.result.ResultStatus.LOSE;
import static blackjack.domain.result.ResultStatus.PUSH;
import static blackjack.domain.result.ResultStatus.WIN;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PlayerScoreResult {

    private final Map<Player, Hand> playerHands;

    public PlayerScoreResult(final Map<Player, Hand> playerHands) {
        this.playerHands = playerHands;
    }

    public Map<Player, ResultStatus> calculateScoreResult(final Dealer dealer) {
        return playerHands.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> determinePlayerResult(dealer.showAllCards(), entry.getValue()),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private ResultStatus determinePlayerResult(final Hand dealerHand, final Hand playerHand) {
        if (dealerHand.isBlackjack() && !playerHand.isBlackjack()) {
            return LOSE;
        }
        if (!dealerHand.isBlackjack() && playerHand.isBlackjack()) {
            return BLACKJACK;
        }
        return determineRegularGameResult(dealerHand, playerHand);
    }

    private ResultStatus determineRegularGameResult(final Hand dealerHand, final Hand playerHand) {
        int dealerScore = dealerHand.calculateResult();
        int playerScore = playerHand.calculateResult();
        if (dealerScore <= BURST_THRESHOLD) {
            return determineResultWhenDealerNotBusted(dealerScore, playerScore);
        }
        return determineResultWhenDealerBusted(playerScore);
    }

    private ResultStatus determineResultWhenDealerNotBusted(final int dealerScore, final int playerScore) {
        if (playerScore <= BURST_THRESHOLD) {
            return determineResultWhenPlayerNotBusted(dealerScore, playerScore);
        }
        return LOSE;
    }

    private ResultStatus determineResultWhenPlayerNotBusted(final int dealerScore, final int playerScore) {
        if (dealerScore > playerScore) {
            return LOSE;
        }
        if (dealerScore == playerScore) {
            return PUSH;
        }
        return WIN;
    }

    private ResultStatus determineResultWhenDealerBusted(final int playerScore) {
        if (playerScore > BURST_THRESHOLD) {
            return WIN;
        }
        return LOSE;
    }

}
