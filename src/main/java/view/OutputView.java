package view;

import java.util.List;
import model.DealerWinning;
import model.PlayersWinning;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;
import model.dto.PlayerWinning;

public class OutputView {
    private static final String JOIN_DELIMITER = ", ";
    private static final String RESULT_DELIMITER = ": ";

    private static final String CARD_TEXT = "카드: ";
    private static final String DEALER_DRAW_ONE_CARD_TEXT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_TEXT = "## 최종 승패";
    private static final String DEALER_INIT_DECK_TEXT = "딜러와 ";
    private static final String INIT_DECK_TEXT = "에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_TEXT = "딜러카드: ";
    private static final String SCORE_TEXT = " - 결과: ";
    private static final String DEALER_TEXT = "딜러: ";

    public static void printInitDeck(List<PlayerResult> players, String dealerFirstCard) {
        List<String> playerNames = players.stream().map(PlayerResult::name).toList();
        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealerFirstCard);
        printPlayersCurrentDeck(players);
        printNewLine();
    }

    public static void printPlayerCurrentDeck(PlayerResult playerResult) {
        System.out.println(playerResult.name() + CARD_TEXT + String.join(JOIN_DELIMITER, playerResult.deck()));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println(DEALER_DRAW_ONE_CARD_TEXT);
        printNewLine();
    }

    public static void printPlayersScore(PlayerResult dealerResult, List<PlayerResult> players) {
        printPlayerScore(dealerResult);
        for(PlayerResult playerResult : players) {
            printPlayerScore(playerResult);
        }
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printResult(ParticipantWinning participantWinning) {
        System.out.println(FINAL_RESULT_TEXT);
        printDealerResult(participantWinning.dealerWinning());
        printPlayersResult(participantWinning.playersWinning());
    }

    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println(DEALER_INIT_DECK_TEXT + String.join(JOIN_DELIMITER, players) + INIT_DECK_TEXT);
    }

    private static void printDealerInitDeck(String firstCard) {
        System.out.println(DEALER_CARD_TEXT + firstCard);
    }

    private static void printPlayersCurrentDeck(List<PlayerResult> players) {
        for(PlayerResult playerResult : players) {
            printPlayerCurrentDeck(playerResult);
        }
    }

    private static void printPlayerScore(PlayerResult playerResult) {
        System.out.println(playerResult.name() + CARD_TEXT + String.join(JOIN_DELIMITER, playerResult.deck()) + SCORE_TEXT + playerResult.score());
    }

    private static void printDealerResult(DealerWinning dealerWinning) {
        System.out.println(DEALER_TEXT + dealerWinning.getTotalBet());
    }

    private static void printPlayersResult(PlayersWinning playersWinning) {
        for(PlayerWinning playerWinning : playersWinning.getFormattedPlayersWinnings()) {
            printPlayerResult(playerWinning);
        }
    }

    private static void printPlayerResult(PlayerWinning playerWinning) {
        System.out.println(playerWinning.name() + RESULT_DELIMITER + playerWinning.profit());
    }

}
