package dto;

import domain.Dealer;
import domain.Hand;
import domain.Player;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ScoreResultDto(List<String> scoreResults) {

    public static final String DEALER_NAME = "딜러";
    public static final String SCORE_RESULT_FORMAT = "%s카드: %s - 결과: %s";

    public ScoreResultDto(Dealer dealer, List<Player> players) {
        this(new ArrayList<>());
        addDealerScoreResult(dealer);
        addPlayerScoreResults(players);
    }

    private void addPlayerScoreResults(List<Player> players) {
        for (Player player : players) {
            Hand playerHand = player.getHand();
            String scoreResult = String.format(
                    SCORE_RESULT_FORMAT,
                    player.getName(),
                    getHandString(playerHand),
                    playerHand.getSum());
            scoreResults.add(scoreResult);
        }
    }

    private void addDealerScoreResult(Dealer dealer) {
        Hand dealerHand = dealer.getHand();
        String scoreResult = String.format(
                SCORE_RESULT_FORMAT,
                DEALER_NAME,
                getHandString(dealerHand),
                dealerHand.getSum());
        scoreResults.add(scoreResult);
    }

    private String getHandString(Hand hand) {
        return hand.getCards().stream().
                map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }
}
