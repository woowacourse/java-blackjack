package view;

import domain.WinningStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static final String DEALER_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_WIN_OR_LOSE_MESSAGE = "## 최종 승패";

    private static final String COMMA = ", ";
    private static final String COLON = ": ";
    private static final String SCORE_MESSAGE = " - 결과: %s";

    private static final String DISTRIBUTION_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_PREFIX = "딜러카드: ";
    private static final String CARD_MESSAGE = "%s카드: %s";
    private static final String DEALER_RESULT = "딜러: ";
    private static final String WIN_MESSAGE = "승 ";
    private static final String TIE_MESSAGE = "무 ";
    private static final String LOSE_MESSAGE = "패 ";

    public void printInitialDistribution(Players players, Dealer dealer) {
        printDistributionMessage(players);
        printDealerInitialCard(dealer);
        printPlayersInitialCards(players);
    }

    public void printGameResult(DealerResultInfo dealerResult, List<PlayerResultInfo> playersResult) {
        printWinOrLoseMessage();
        printDealerResult(dealerResult);
        printPlayersResult(playersResult);
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(formatParticipantCards(participant));
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
        String PossessedCards = String.join(COMMA, cards);

        return String.format(CARD_MESSAGE, participant.name(), PossessedCards);
    }

    public void printWinOrLoseMessage() {
        System.out.println(FINAL_WIN_OR_LOSE_MESSAGE);
    }

    public void printDealerResult(DealerResultInfo dealerResult) {
        StringBuilder stringBuilder = new StringBuilder(DEALER_RESULT);

        if (dealerResult.winCount() > 0) {
            stringBuilder.append(dealerResult.winCount()).append(WIN_MESSAGE);
        }
        if (dealerResult.tieCount() > 0) {
            stringBuilder.append(dealerResult.tieCount()).append(TIE_MESSAGE);
        }
        if (dealerResult.loseCount() > 0) {
            stringBuilder.append(dealerResult.loseCount()).append(LOSE_MESSAGE);
        }

        System.out.println(stringBuilder.toString().trim());
    }

    public void printPlayersResult(List<PlayerResultInfo> playersResult) {
        for (PlayerResultInfo playerResult : playersResult) {
            System.out.println(playerResult.name() + COLON + toKorean(playerResult.status()));
        }
    }

    private String toKorean(WinningStatus status) {
        return switch (status) {
            case WinningStatus.WIN -> WIN_MESSAGE;
            case WinningStatus.TIE -> TIE_MESSAGE;
            case WinningStatus.LOSE -> LOSE_MESSAGE;
        };
    }
}
