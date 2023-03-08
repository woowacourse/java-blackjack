package blackjack.view;

import blackjack.domain.user.Dealer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NAME_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String INITIAL_STATUS_INFO_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_INFO_MESSAGE_FORMAT = "%s카드: %s";
    private static final String DRAW_CARD_REQUEST_MESSAGE_FORMAT = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_DRAW_INFO_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
            + System.lineSeparator();
    private static final String USER_NAME_AND_CARD_RESULT_MESSAGE_FORMAT = "%s카드: %s";
    private static final String DEALER_INFO = Dealer.DEALER_NAME + ": ";
    private static final String DEALER_WINNING_RESULT_MESSAGE_FORMAT = "%d%s";
    private static final String PLAYERS_WINNING_RESULT_MESSAGE_FORMAT = "%s: %s";
    private static final String WINNING_RESULT_INFO_MESSAGE = "## 최종 승패";
    private static final String DELIMITER = ", ";

    public void printPlayerNameRequestMessage() {
        System.out.println(PLAYER_NAME_REQUEST_MESSAGE);
    }

    public void printFirstOpenCardGroups(Map<String, List<String>> initialStatus) {
        printLineBreak();
        List<String> playerNames = initialStatus.keySet().stream()
                .filter(name -> !name.equals(Dealer.DEALER_NAME))
                .collect(Collectors.toUnmodifiableList());

        printInitialStatusInfoMessage(playerNames);
        printCardGroup(Dealer.DEALER_NAME, initialStatus.get(Dealer.DEALER_NAME));
        playerNames.forEach(name -> printCardGroup(name, initialStatus.get(name)));
        printLineBreak();
    }

    public void printCardGroup(String name, List<String> cardNames) {
        System.out.println(String.format(CARD_INFO_MESSAGE_FORMAT, name
                , String.join(DELIMITER, cardNames)));
    }

    private void printInitialStatusInfoMessage(List<String> playerNames) {
        System.out.println(String.format(INITIAL_STATUS_INFO_MESSAGE_FORMAT, Dealer.DEALER_NAME
                , String.join(DELIMITER, playerNames)));
    }

    public void printDrawCardRequestMessage(final String playerName) {
        System.out.println(String.format(DRAW_CARD_REQUEST_MESSAGE_FORMAT, playerName));
    }

    public void printDealerDrawInfoMessage() {
        System.out.println(DEALER_DRAW_INFO_MESSAGE);
    }

    public void printUserNameAndCardResults(final Map<String, String> renderedUserNameAndCardResults) {
        renderedUserNameAndCardResults.forEach((name, renderedCardResult) ->
                System.out.println(String.format(USER_NAME_AND_CARD_RESULT_MESSAGE_FORMAT, name, renderedCardResult)));
    }

    public void printDealerWinningResult(final Map<String, Long> dealerWinningResult) {
        printLineBreak();
        System.out.println(WINNING_RESULT_INFO_MESSAGE);
        System.out.print(DEALER_INFO);
        dealerWinningResult.forEach((winningStatus, count) ->
                System.out.print(String.format(DEALER_WINNING_RESULT_MESSAGE_FORMAT, count, winningStatus)));
        printLineBreak();
    }

    public void printPlayersWinningResults(final Map<String, String> playersWinningResults) {
        for (String name : playersWinningResults.keySet()) {
            System.out.println(
                    String.format(PLAYERS_WINNING_RESULT_MESSAGE_FORMAT, name, playersWinningResults.get(name)));
        }
    }

    public void printLineBreak() {
        System.out.println();
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
