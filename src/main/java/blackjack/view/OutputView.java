package blackjack.view;

import blackjack.domain.Dealer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private final static String PLAYER_NAME_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private final static String INITIAL_STATUS_INFO_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private final static String CARD_INFO_MESSAGE_FORMAT = "%s카드: %s";

    private final static String DELIMITER = ", ";

    public void printPlayerNameRequestMessage() {
        System.out.printf(PLAYER_NAME_REQUEST_MESSAGE);
    }

    public void printInitialStatus(Map<String, List<String>> initialStatus) {
        List<String> playerNames = initialStatus.keySet().stream()
                .filter(name -> !name.equals(Dealer.DEALER_NAME))
                .collect(Collectors.toUnmodifiableList());

        printInitialStatusInfoMessage(playerNames);
        printCards(Dealer.DEALER_NAME, initialStatus.get(Dealer.DEALER_NAME));
        playerNames.stream()
                .forEach(name -> printCards(name, initialStatus.get(name)));
    }

    private void printCards(String name, List<String> cardNames) {
        System.out.println(String.format(CARD_INFO_MESSAGE_FORMAT, name
                , String.join(DELIMITER, cardNames)));
    }

    private void printInitialStatusInfoMessage(List<String> playerNames) {
        System.out.println(String.format(INITIAL_STATUS_INFO_MESSAGE_FORMAT, Dealer.DEALER_NAME
                , String.join(DELIMITER, playerNames)));
    }

}
