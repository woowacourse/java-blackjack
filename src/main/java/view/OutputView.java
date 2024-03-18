package view;

import java.util.List;
import model.result.CardDto;
import model.result.CardsDto;
import model.result.ProfitDto;
import model.result.ProfitsDto;
import model.result.ScoreDto;
import model.result.ScoresDto;

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

    public static void printInitialCards(CardsDto participantCards) {
        CardDto dealerCard = participantCards.dealerCard();
        List<CardDto> playerCards = participantCards.playerCards();
        printInitCardsIntro(participantCards);
        printCards(dealerCard);
        printAllPlayerCards(playerCards);
    }

    private static void printInitCardsIntro(CardsDto participantCards) {
        String playerNames = String.join(CARDS_DELIMITER, participantCards.playerNames());
        System.out.printf(INIT_CARDS_INTRO, participantCards.dealerName(), playerNames);
    }

    public static void printCards(CardDto participantCard) {
        String cards = String.join(CARDS_DELIMITER, participantCard.cards());
        System.out.printf(CARDS_FORMAT, participantCard.name(), cards);
    }

    private static void printAllPlayerCards(List<CardDto> playerCards) {
        playerCards.forEach(OutputView::printCards);
        System.out.println();
    }

    public static void printAfterDealerHit() {
        System.out.printf(DEALER_HIT, DEALER_NAME);
    }

    public static void printScores(ScoresDto participantScores) {
        ScoreDto dealerScore = participantScores.dealerScore();
        List<ScoreDto> playerScores = participantScores.playerScores();
        printScore(dealerScore);
        playerScores.forEach(OutputView::printScore);
    }

    private static void printScore(ScoreDto participantScore) {
        CardDto participantCard = participantScore.card();
        String cards = String.join(CARDS_DELIMITER, participantCard.cards());
        System.out.printf(SCORE_FORMAT, participantCard.name(), cards, participantScore.score());
    }

    public static void printProfits(ProfitsDto participantProfits) {
        ProfitDto dealerProfit = participantProfits.dealerProfit();
        List<ProfitDto> playerProfits = participantProfits.playerProfits();
        System.out.print(PROFIT_RESULT_INTRO);
        printProfit(dealerProfit);
        playerProfits.forEach(OutputView::printProfit);
    }

    private static void printProfit(ProfitDto dealerProfit) {
        System.out.printf(PROFIT_RESULT_FORMAT, dealerProfit.name(), dealerProfit.profit().intValue());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }
}
