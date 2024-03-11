package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";
    private static final String ASK_DRAW_MESSAGE = "는/은 한장의 카드를 더 받겠습니까?(예는 "
            + ACCEPT_DRAW_MESSAGE + ", 아니오는 " + REJECT_DRAW_MESSAGE + ")";

    private static final String FINAL_HANDS_AND_SCORE_FORMAT = "%s - 결과: %d";
    private static final String FINAL_RESULT_FORMAT = "%s: %s" + System.lineSeparator();
    private static final String FINAL_RESULT_MESSAGE = System.lineSeparator() + "## 최종 승패";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private OutputView() {
    }

    public static void printAskNameMessage() {
        System.out.println("게임에 참여 할사람 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void printDrawInitialHandsMessage(String dealerName, List<Player> players) {
        System.out.println(System.lineSeparator()
                + dealerName
                + "와 "
                + resolvePlayerNamesMessage(players)
                + "에게 2장을 나누었습니다");
    }

    public static void printParticipantsInitialHands(String dealerName, String dealerFirstCardName, List<Player> players) {
        printDealerFirstCard(dealerName, dealerFirstCardName);
        for (Player player : players) {
            printParticipantHands(player.getName(), player.getHandsCards());
        }
        System.out.println();
    }

    public static void printParticipantHands(String participantName, List<Card> participantHands) {
        System.out.println(resolveParticipantHandsMessage(participantName, participantHands));
    }

    public static void printAskDrawMessage(String playerName) {
        System.out.println(playerName + ASK_DRAW_MESSAGE);
    }

    public static void printDealerDrawMessage(Dealer dealer) {
        System.out.println();
        System.out.println(dealer.getName() + "는 "
                + DEALER_DRAW_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHandsAndScoreMessage(Dealer dealer, Players players) {
        System.out.println(resolveFinalHandsAndScoreMessage(dealer.getName(),
                dealer.getHandsCards(), dealer.getHandsScore()));

        for (Player player : players.getPlayers()) {
            System.out.println(resolveFinalHandsAndScoreMessage(player.getName(),
                    player.getHandsCards(), player.getHandsScore()));
        }
    }

    public static void printGameResult(String dealerName, GameResult gameResult) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(dealerName, gameResult);
        printPlayerGameResult(gameResult.getGameResult());
    }

    private static String resolvePlayerNamesMessage(List<Player> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(","));
    }

    private static void printDealerFirstCard(String dealerName, String dealerFirstCardName) {
        System.out.println(dealerName
                + ": "
                + dealerFirstCardName);
    }

    private static String resolveFinalHandsAndScoreMessage(String participantName
            , List<Card> participantHands, int participantHandsScore) {
        return String.format(FINAL_HANDS_AND_SCORE_FORMAT
                , resolveParticipantHandsMessage(participantName, participantHands)
                , participantHandsScore);
    }

    private static void printDealerGameResult(String dealerName, GameResult gameResult) {
        String dealerResultMessage = resolveDealerResultMessage(gameResult, Result.WIN)
                + resolveDealerResultMessage(gameResult, Result.LOSE)
                + resolveDealerResultMessage(gameResult, Result.DRAW);

        System.out.printf(FINAL_RESULT_FORMAT, dealerName, dealerResultMessage);
    }

    private static void printPlayerGameResult(Map<Player, Result> gameResult) {
        for (Player player : gameResult.keySet()) {
            System.out.printf(FINAL_RESULT_FORMAT, player.getName()
                    , gameResult.get(player).getResult());
        }
    }

    private static String resolveDealerResultMessage(GameResult gameResult, Result result) {
        long cnt = gameResult.getTargetResultCount(Result.reverseResult(result));
        if (cnt == 0) {
            return "";
        }
        return cnt + result.getResult() + " ";
    }

    private static String resolveParticipantHandsMessage(String participantName, List<Card> participantHands) {
        return participantName
                + "카드: "
                + resolveHandsMessage(participantHands);
    }

    private static String resolveHandsMessage(List<Card> hands) {
        return hands.stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }
}
