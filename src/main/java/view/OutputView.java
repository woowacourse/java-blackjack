package view;

import model.Card;
import dto.ParticipantWinningResponse;
import dto.PlayerProfitResponse;
import dto.PlayerResultResponse;
import java.math.BigDecimal;
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

    public static void printInitDeck(List<PlayerResultResponse> players, PlayerResultResponse dealer) {
        List<String> playerNames = players.stream().map(PlayerResultResponse::name).toList();
        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealer.hand().getFirst());
        printPlayersCurrentDeck(players);
        printNewLine();
    }

    public static void printPlayerCurrentDeck(PlayerResultResponse playerResultResponse) {
        List<String> cardString = playerResultResponse.hand().stream().map(Card::getString).toList();
        System.out.println(playerResultResponse.name() + CARD_TEXT + String.join(JOIN_DELIMITER, cardString));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println(DEALER_DRAW_ONE_CARD_TEXT);
        printNewLine();
    }


    public static void printPlayersScore(List<PlayerResultResponse> players) {
        for (PlayerResultResponse playerResultResponse : players) {
            printPlayerScore(playerResultResponse);
        }
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printResult(ParticipantWinningResponse participantWinningResponse) {
        System.out.println(FINAL_RESULT_TEXT);
        printDealerResult(participantWinningResponse.dealerProfit());
        printPlayersResult(participantWinningResponse.playersWinning());
    }


    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println(DEALER_INIT_DECK_TEXT + String.join(JOIN_DELIMITER, players) + INIT_DECK_TEXT);
    }

    private static void printDealerInitDeck(Card card) {
        System.out.println(DEALER_CARD_TEXT + card.getString());
    }

    private static void printPlayersCurrentDeck(List<PlayerResultResponse> players) {
        for (PlayerResultResponse playerResultResponse : players) {
            printPlayerCurrentDeck(playerResultResponse);
        }
    }

    private static void printPlayerScore(PlayerResultResponse playerResultResponse) {
        List<String> cardString = playerResultResponse.hand().stream().map(Card::getString).toList();
        System.out.println(playerResultResponse.name() + CARD_TEXT + String.join(JOIN_DELIMITER, cardString) + SCORE_TEXT
                + playerResultResponse.score());
    }

    private static void printDealerResult(BigDecimal dealerProfit) {
        System.out.println(DEALER_TEXT + RESULT_DELIMITER + formatProfit(dealerProfit));
    }

    private static void printPlayersResult(List<PlayerProfitResponse> playersWinning) {
        for (PlayerProfitResponse playerProfitResponse : playersWinning) {
            printPlayerResult(playerProfitResponse);
        }
    }

    private static void printPlayerResult(PlayerProfitResponse playerProfitResponse) {
        System.out.println(playerProfitResponse.name() + RESULT_DELIMITER + formatProfit(playerProfitResponse.profit()));
    }

    private static String formatProfit(BigDecimal profit) {
        return profit.stripTrailingZeros().toPlainString();
    }

}
