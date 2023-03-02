package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.dto.CardDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NAME_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String INITIAL_STATUS_INFO_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_INFO_MESSAGE_FORMAT = "%s카드: %s";
    private static final String DRAW_CARD_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_DRAW_INFO_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_RESULT_MESSAGE = CARD_INFO_MESSAGE_FORMAT + " - 결과: %d";
    private static final String WINNING_RESULT_MESSAGE_FORMAT = "%s: %s";
    private static final String DELIMITER = ", ";

    public void printPlayerNameRequestMessage() {
        System.out.printf(PLAYER_NAME_REQUEST_MESSAGE);
    }

    public void printInitialStatus(Map<String, List<String>> initialStatus) {
        List<String> playerNames = initialStatus.keySet().stream()
                .filter(name -> !name.equals(Dealer.DEALER_NAME))
                .collect(Collectors.toUnmodifiableList());

        printInitialStatusInfoMessage(playerNames);
        printCards(Dealer.DEALER_NAME, initialStatus.get(Dealer.DEALER_NAME));
        playerNames.forEach(name -> printCards(name, initialStatus.get(name)));
    }

    private void printCards(String name, List<String> cardNames) {
        System.out.println(String.format(CARD_INFO_MESSAGE_FORMAT, name
                , String.join(DELIMITER, cardNames)));
    }

    private void printInitialStatusInfoMessage(List<String> playerNames) {
        System.out.println(String.format(INITIAL_STATUS_INFO_MESSAGE_FORMAT, Dealer.DEALER_NAME
                , String.join(DELIMITER, playerNames)));
    }

    public void printDrawCardRequestMessage(final String name) {
        System.out.println(String.format(DRAW_CARD_REQUEST_MESSAGE, name));
    }

    public void printDealerDrawInfoMessage() {
        System.out.println(DEALER_DRAW_INFO_MESSAGE);
    }

    public void printCardResult(final Map<String, CardDto> result) {
        for (final String name : result.keySet()) {
            CardDto cardDto = result.get(name);
            System.out.println(String.format(CARD_RESULT_MESSAGE, name,
                    String.join(DELIMITER, cardDto.getCardNames()), cardDto.getScore()));
        }
    }

    public void printWinningResult(final Map<String, String> winningResults) {
        for (String name : winningResults.keySet()) {
            System.out.println(String.format(WINNING_RESULT_MESSAGE_FORMAT, name, winningResults.get(name)));
        }
    }
}
