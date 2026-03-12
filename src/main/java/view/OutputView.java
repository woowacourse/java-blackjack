package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static final String DEALER_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 수익";

    private static final String COMMA = ", ";
    private static final String SCORE_MESSAGE = " - 결과: %s";
    private static final String DISTRIBUTION_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_PREFIX = "딜러카드: ";
    private static final String CARD_MESSAGE = "%s카드: %s";
    private static final String DEALER_RESULT_FORMAT = "딜러: %s";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s";

    public void printInitialDistribution(Players players, Dealer dealer) {
        printDistributionMessage(players);
        printDealerInitialCard(dealer);
        printPlayersInitialCards(players);
    }

    public void printGameResult(int dealerProfit, List<PlayerResultInfo> playersResult) {
        printFinalResultMessage();
        printDealerResult(dealerProfit);
        printPlayersResult(playersResult);
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(formatParticipantCards(participant));
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printDealerReceiveMessage() {
        System.out.println(DEALER_ONE_MORE_CARD_MESSAGE);
    }

    public void printFinalResult(Participant participant) {
        String cardFormat = formatParticipantCards(participant);
        String scoreMessage = String.format(SCORE_MESSAGE, participant.score());

        System.out.println(cardFormat + scoreMessage);
    }

    private void printDistributionMessage(Players players) {
        List<String> playerNames = players.getPlayerNames();

        String names = String.join(COMMA, playerNames);
        String distributionMessage = String.format(DISTRIBUTION_MESSAGE, names);

        System.out.println(distributionMessage);
    }

    private void printDealerInitialCard(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        System.out.println(DEALER_CARD_PREFIX + CardFormatter.format(firstCard));
    }

    private void printPlayersInitialCards(Players players) {
        for (Player player : players.getPlayers()) {
            printParticipantCards(player);
        }
    }

    private String formatParticipantCards(Participant participant) {
        List<String> cards = new ArrayList<>();
        for (Card card : participant.getAllCards()) {
            cards.add(CardFormatter.format(card));
        }
        String possessedCards = String.join(COMMA, cards);

        return String.format(CARD_MESSAGE, participant.name(), possessedCards);
    }

    public void printFinalResultMessage() {
        System.out.println(FINAL_RESULT_MESSAGE);
    }

    public void printDealerResult(int dealerProfit) {
        System.out.println(String.format(DEALER_RESULT_FORMAT, dealerProfit));
    }

    public void printPlayersResult(List<PlayerResultInfo> playersResult) {
        for (PlayerResultInfo playerResult : playersResult) {
            System.out.println(String.format(
                    PLAYER_RESULT_FORMAT,
                    playerResult.name(),
                    playerResult.profit()
            ));
        }
    }
}
