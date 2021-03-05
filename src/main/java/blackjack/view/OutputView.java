package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.scoreboard.result.Resultable;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String COMMA_AND_BLANK = ", ";
    private static final String BLANK = " ";
    private static final String COLON = ": ";
    private static final String FIRST_DRAW_MESSAGE_PREFIX = "딜러와 ";
    private static final String FIRST_DRAW_MESSAGE_SUFFIX = "에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_CARD_LIST_MSG_FORMAT = "%s 카드: %s%n";
    private static final String PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT = "%s 카드: %s - 점수 : %d%n";
    private static final String FINAL_WIN_OR_LOSE_MSG = "##최종 승패";
    private static final String DEALER_MORE_DRAW_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final long DEFAULT_COUNT = 0L;

    public static void printDrawMessage(List<Name> userNames) {
        String names = userNames.stream()
                .map(Object::toString)
                .collect(joining(COMMA_AND_BLANK));
        String firstDrawMessage = FIRST_DRAW_MESSAGE_PREFIX + names + FIRST_DRAW_MESSAGE_SUFFIX;
        System.out.println(firstDrawMessage);
    }

    public static void printDealerFirstCard(Card card) {
        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, Dealer.DEALER_NAME, card.getSuitLetter() + card.getValueLetter());
    }

    public static void printCardList(Participant participant) {
        String cards = participant.getCards()
                .stream()
                .map(card -> card.getValueLetter() + card.getSuitLetter())
                .collect(joining(COMMA_AND_BLANK));

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, participant.getName(), cards);
    }

    public static void printCardListAndScore(Resultable gameResult) {
        String cards = gameResult.getCards()
                .stream()
                .map(card -> card.getValueLetter() + card.getSuitLetter())
                .collect(joining(COMMA_AND_BLANK));

        System.out.printf(PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT, gameResult.getName(), cards, gameResult.getScore());
    }

    private static void printCardListAndScore(List<Resultable> gameResults) {
        gameResults.forEach(OutputView::printCardListAndScore);
    }

    public static void printDealerMoreDrawMessage() {
        System.out.println(DEALER_MORE_DRAW_CARD_MSG);
        println();
    }

    public static void printScoreBoard(ScoreBoard scoreBoard) {
        printCardListAndScore(scoreBoard.getDealerGameResult());
        printCardListAndScore(new ArrayList<>(scoreBoard.getUserResults().values()));
        println();
        printFinalWinOrLose(scoreBoard);
    }

    private static void printFinalWinOrLose(ScoreBoard scoreBoard) {
        System.out.println(FINAL_WIN_OR_LOSE_MSG);
        System.out.println(createDealerResultMessage(scoreBoard));

        scoreBoard.getUserResults().forEach(
                (name, userGameResult) ->
                        System.out.println(name + COLON + userGameResult.getWinOrLose().getCharacter())
        );
    }

    private static String createDealerResultMessage(ScoreBoard scoreBoard) {
        return Dealer.DEALER_NAME + COLON + Arrays.stream(WinOrLose.values())
                .map(winOrLose -> scoreBoard.dealerWinOrLoseCounts()
                        .getOrDefault(winOrLose, DEFAULT_COUNT) + winOrLose.getCharacter())
                .collect(joining(BLANK));
    }

    public static void println() {
        System.out.println();
    }
}
