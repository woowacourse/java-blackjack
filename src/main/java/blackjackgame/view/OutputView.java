package blackjackgame.view;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.user.User;
import blackjackgame.domain.user.dto.NameDto;
import blackjackgame.domain.user.dto.ProfitDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();
    private static final String INPUT_PLAYER_BET_AMOUNT_MESSAGE_FORMAT = "%n%s의 배팅 금액은?%n";
    private static final String ASK_ONE_MORE_CARD_MESSAGE_FORMAT = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n) %n";
    private static final String SETUP_COMPLETE_MESSAGE_FORMAT = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_DRAW_RESULT_MESSAGE_FORMAT = "%n딜러는 16이하라 %d장의 카드를 더 받았습니다.%n";

    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String FINAL_RESULT_NOTICE_MESSAGE = "## 최종 수익";

    private static final String DRAW_RESULT_DELIMITER = ", ";
    private static final String VALUE_DELIMITER = ": ";
    private static final String NAME_CARD_DELIMITER = "카드" + VALUE_DELIMITER;
    private static final String CARD_SCORE_DELIMITER = " - 결과" + VALUE_DELIMITER;

    public void printInputPlayerNamesMessage() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
    }

    public void printInputPlayerBetAmountMessage(String playerName) {
        System.out.printf(INPUT_PLAYER_BET_AMOUNT_MESSAGE_FORMAT, playerName);
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printSetUpResult(Map<NameDto, List<Card>> setUpResult) {
        printSetUpCompleteMessage(setUpResult);
        printUserCards(setUpResult);
    }

    private void printSetUpCompleteMessage(Map<NameDto, List<Card>> setUpResult) {
        List<String> userNames = new ArrayList<>(setUpResult.keySet()).stream()
                .map(NameDto::getName)
                .collect(Collectors.toList());
        List<String> playerNames = userNames.subList(1, setUpResult.size());
        String playerNamesPrintFormat = String.join(DRAW_RESULT_DELIMITER, playerNames);

        System.out.printf(SETUP_COMPLETE_MESSAGE_FORMAT, playerNamesPrintFormat);
    }

    private void printUserCards(Map<NameDto, List<Card>> setUpResult) {
        for (Entry<NameDto, List<Card>> cardsByUser : setUpResult.entrySet()) {
            String name = cardsByUser.getKey().getName();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(DRAW_RESULT_DELIMITER));

            System.out.println(name + NAME_CARD_DELIMITER + cards);
        }
        System.out.print(NEWLINE);
    }

    public void printAskOneMoreCardMessage(String name) {
        System.out.printf(ASK_ONE_MORE_CARD_MESSAGE_FORMAT, name);
    }

    public void printPlayerDrawResult(String name, List<Card> cards) {
        String cardSymbols = cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.joining(DRAW_RESULT_DELIMITER));

        System.out.println(name + NAME_CARD_DELIMITER + cardSymbols);
    }

    public void printDealerDrawResult(int dealerDrawCount) {
        System.out.printf(DEALER_DRAW_RESULT_MESSAGE_FORMAT, dealerDrawCount);
    }

    public void printUsersCardResult(Map<User, List<Card>> userResult) {
        System.out.print(NEWLINE);
        for (Entry<User, List<Card>> cardsByUser : userResult.entrySet()) {
            String name = cardsByUser.getKey().getName();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(DRAW_RESULT_DELIMITER));
            int score = cardsByUser.getKey().getScore();

            System.out.println(name + NAME_CARD_DELIMITER + cards + CARD_SCORE_DELIMITER + score);
        }
    }
    public void printFinalResult(Map<NameDto, ProfitDto> betResultByName) {
        System.out.println(NEWLINE + FINAL_RESULT_NOTICE_MESSAGE);
        for (Entry<NameDto, ProfitDto> betResultByNameEntry : betResultByName.entrySet()) {
            String name = betResultByNameEntry.getKey().getName();
            int profit = betResultByNameEntry.getValue().getProfit();
            System.out.println(name + VALUE_DELIMITER + profit);
        }
    }
}
