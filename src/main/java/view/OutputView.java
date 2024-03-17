package view;

import java.util.List;
import model.result.ParticipantCard;
import model.result.ParticipantCards;
import model.result.ParticipantProfit;
import model.result.ParticipantProfits;
import model.result.ParticipantScore;
import model.result.ParticipantScores;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DEALER_NAME = "딜러";
    private static final String CARDS_DELIMITER = ", ";
    private static final String INIT_CARDS_INTRO = NEWLINE + "%s와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String CARDS_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + "%s는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE;
    private static final String SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";
    private static final String PROFIT_RESULT_INTRO = NEWLINE + NEWLINE + "## 최종 수익";
    private static final String PROFIT_RESULT_FORMAT = NEWLINE + "%s: %d";
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

    public static void printProfits(ParticipantProfits participantProfits) {
        ParticipantProfit dealerProfit = participantProfits.getDealerProfit();
        List<ParticipantProfit> playerProfits = participantProfits.getPlayerProfits();
        System.out.print(PROFIT_RESULT_INTRO);
        printProfit(dealerProfit);
        playerProfits.forEach(OutputView::printProfit);
    }

    private static void printProfit(ParticipantProfit dealerProfit) {
        System.out.printf(PROFIT_RESULT_FORMAT, dealerProfit.getName(), dealerProfit.getProfit().intValue());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }
}
