package view;

import static java.util.stream.Collectors.joining;

import dto.DealerResult;
import dto.ParticipantCard;
import dto.ParticipantCards;
import dto.ParticipantResults;
import dto.ParticipantScore;
import dto.ParticipantScores;
import dto.PlayerResult;
import java.util.List;
import model.game.ResultStatus;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DEALER_NAME = "딜러";
    private static final String CARDS_DELIMITER = ", ";
    private static final String GAME_RESULT_DELIMITER = " ";
    private static final String INIT_CARDS_INTRO = NEWLINE + "%s와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String CARDS_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + "%s는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE;
    private static final String SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_INTRO = NEWLINE + NEWLINE + "## 최종 승패";
    private static final String GAMER_RESULT_FORMAT = NEWLINE + "%s: %s";
    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printInitialCards(ParticipantCards participantCards) {
        ParticipantCard dealerCard = participantCards.getDealerCard();
        List<ParticipantCard> playerCards = participantCards.getPlayerCards();
        printInitCardsIntro(participantCards);
        printCards(dealerCard);
        printAllPlayerCards(playerCards);
    }

    private static void printInitCardsIntro(ParticipantCards participantCards) {
        String playerNames = String.join(CARDS_DELIMITER, participantCards.playerNames());
        System.out.printf(INIT_CARDS_INTRO, participantCards.dealerName(), playerNames);
    }

    public static void printCards(ParticipantCard participantCard) {
        String cards = String.join(CARDS_DELIMITER, participantCard.getCards());
        System.out.printf(CARDS_FORMAT, participantCard.getName(), cards);
    }

    private static void printAllPlayerCards(List<ParticipantCard> playerCards) {
        playerCards.forEach(OutputView::printCards);
        System.out.println();
    }

    public static void printAfterDealerHit() {
        System.out.printf(DEALER_HIT, DEALER_NAME);
    }

    public static void printScores(ParticipantScores participantScores) {
        ParticipantScore dealerScore = participantScores.getDealerScore();
        List<ParticipantScore> playerScores = participantScores.getPlayerScores();
        printScore(dealerScore);
        playerScores.forEach(OutputView::printScore);
    }

    private static void printScore(ParticipantScore participantScore) {
        ParticipantCard participantCard = participantScore.getCard();
        String cards = String.join(CARDS_DELIMITER, participantCard.getCards());
        System.out.printf(SCORE_FORMAT, participantCard.getName(), cards, participantScore.getScore());
    }

    public static void printResult(ParticipantResults participantResults) {
        DealerResult dealerResult = participantResults.getDealerResult();
        List<PlayerResult> playerResults = participantResults.getPlayerResults();
        System.out.print(GAME_RESULT_INTRO);
        printDealerGameResult(dealerResult);
        playerResults.forEach(OutputView::printPlayerGameResult);
    }
    private static void printDealerGameResult(DealerResult dealerResult) {
        String result = dealerResult.allStatus()
            .stream()
            .map(resultStatus -> createResultMessage(resultStatus, dealerResult))
            .collect(joining(GAME_RESULT_DELIMITER));
        System.out.printf(GAMER_RESULT_FORMAT, DEALER_NAME, result);
    }

    private static String createResultMessage(ResultStatus resultStatus,
        DealerResult dealerResult) {
        return dealerResult.statusCount(resultStatus) + resultStatus.getDisplayName();
    }

    private static void printPlayerGameResult(PlayerResult playerResult) {
        System.out.printf(GAMER_RESULT_FORMAT, playerResult.getName(), playerResult.getStatus().getDisplayName());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }
}
