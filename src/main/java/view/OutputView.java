package view;

import dto.Card;
import dto.ParticipantWinning;
import dto.PlayerResult;
import dto.PlayerProfit;
import java.util.List;

public class OutputView {
    private static final String JOIN_DELIMITER = ", ";
    private static final String RESULT_DELIMITER = ": ";

    private static final String CARD_TEXT = "카드: ";
    private static final String DEALER_DRAW_ONE_CARD_TEXT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_TEXT = "## 최종 수익";
    private static final String DEALER_INIT_DECK_TEXT = "딜러와 ";
    private static final String INIT_DECK_TEXT = "에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_TEXT = "딜러카드: ";
    private static final String SCORE_TEXT = " - 결과: ";
    private static final String DEALER_TEXT = "딜러";

    public static void printInitDeck(List<PlayerResult> players, PlayerResult dealer) {
        List<String> playerNames = players.stream().map(PlayerResult::name).toList();
        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealer.hand().getFirst());
        printPlayersCurrentDeck(players);
        printNewLine();
    }

    public static void printPlayerCurrentDeck(PlayerResult playerResult) {
        List<String> cardString = playerResult.hand().stream().map(Card::getString).toList();
        System.out.println(playerResult.name() + CARD_TEXT + String.join(JOIN_DELIMITER, cardString));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println(DEALER_DRAW_ONE_CARD_TEXT);
        printNewLine();
    }


    public static void printPlayersScore(List<PlayerResult> players) {
        for (PlayerResult playerResult : players) {
            printPlayerScore(playerResult);
        }
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printResult(ParticipantWinning participantWinning) {
        System.out.println(FINAL_RESULT_TEXT);
        printDealerResult(participantWinning.dealerProfit());
        printPlayersResult(participantWinning.playersWinning());
    }


    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println(DEALER_INIT_DECK_TEXT + String.join(JOIN_DELIMITER, players) + INIT_DECK_TEXT);
    }

    private static void printDealerInitDeck(Card card) {
        System.out.println(DEALER_CARD_TEXT + card.getString());
    }

    private static void printPlayersCurrentDeck(List<PlayerResult> players) {
        for (PlayerResult playerResult : players) {
            printPlayerCurrentDeck(playerResult);
        }
    }

    private static void printPlayerScore(PlayerResult playerResult) {
        List<String> cardString = playerResult.hand().stream().map(Card::getString).toList();
        System.out.println(playerResult.name() + CARD_TEXT + String.join(JOIN_DELIMITER, cardString) + SCORE_TEXT
                + playerResult.score());
    }

    private static void printDealerResult(double dealerProfit) {
        System.out.println(DEALER_TEXT + RESULT_DELIMITER + formatProfit(dealerProfit));
    }

    private static void printPlayersResult(List<PlayerProfit> playersWinning) {
        for (PlayerProfit playerProfit : playersWinning) {
            printPlayerResult(playerProfit);
        }
    }

    private static void printPlayerResult(PlayerProfit playerProfit) {
        System.out.println(playerProfit.name() + RESULT_DELIMITER + formatProfit(playerProfit.profit()));
    }

    private static String formatProfit(double profit) {
        if (profit == Math.floor(profit)) {
            return String.valueOf((long) profit);
        }
        return String.valueOf(profit);
    }

}
