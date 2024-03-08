package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
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

    public static void printDrawInitialHandsMessage(Dealer dealer, Players players) {
        System.out.println(System.lineSeparator()
                + dealer.getName()
                + "와 "
                + resolvePlayerNamesMessage(players)
                + "에게 2장을 나누었습니다");
    }

    public static void printParticipantsInitialHands(Dealer dealer, Players players) {
        printDealerFirstCard(dealer);
        for (Player player : players.getPlayers()) {
            printParticipantHands(player);
        }
        System.out.println();
    }

    public static void printParticipantHands(Participant participant) {
        System.out.println(resolveParticipantHandsMessage(participant));
    }

    public static void printAskDrawMessage(String playerName) {
        System.out.println(playerName + ASK_DRAW_MESSAGE);
    }

    public static void printDealerDrawMessage(Dealer dealer) {
        System.out.println(dealer.getName() + "는 "
                + DEALER_DRAW_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHandsAndScoreMessage(Dealer dealer, Players players) {
        System.out.println(resolveFinalHandsAndScoreMessage(dealer));
        for (Player player : players.getPlayers()) {
            System.out.println(resolveFinalHandsAndScoreMessage(player));
        }
    }

    public static void printGameResult(Dealer dealer, GameResult gameResult) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(dealer, gameResult);
        printPlayerGameResult(gameResult.getGameResult());
    }

    private static String resolvePlayerNamesMessage(Players players) {
        return players.getPlayers()
                .stream()
                .map(Participant::getName)
                .collect(Collectors.joining(","));
    }

    private static void printDealerFirstCard(Dealer dealer) {
        System.out.println(dealer.getName()
                + ": "
                + dealer.getFirstCardName());
    }

    private static String resolveFinalHandsAndScoreMessage(Participant participant) {
        return String.format(FINAL_HANDS_AND_SCORE_FORMAT
                , resolveParticipantHandsMessage(participant)
                , participant.getHandsScore());
    }

    private static void printDealerGameResult(Dealer dealer, GameResult gameResult) {
        String dealerResultMessage = resolveDealerResultMessage(gameResult, Result.WIN)
                + resolveDealerResultMessage(gameResult, Result.LOSE)
                + resolveDealerResultMessage(gameResult, Result.DRAW);

        System.out.printf(FINAL_RESULT_FORMAT, dealer.getName(), dealerResultMessage);
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

    private static String resolveParticipantHandsMessage(Participant participant) {
        return participant.getName()
                + "카드: "
                + resolveHandsMessage(participant.getHandsCards());
    }

    private static String resolveHandsMessage(List<Card> hands) {
        return hands.stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }
}
