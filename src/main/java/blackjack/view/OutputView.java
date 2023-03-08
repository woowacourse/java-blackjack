package blackjack.view;

import blackjack.domain.user.Dealer;
import blackjack.dto.CardAndScoreResult;
import blackjack.dto.FinalResult;
import blackjack.dto.HoldingCards;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NAME_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String INITIAL_STATUS_INFO_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_INFO_MESSAGE_FORMAT = "%s카드: %s";
    private static final String DRAW_CARD_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_DRAW_INFO_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_RESULT_MESSAGE_FORMAT = CARD_INFO_MESSAGE_FORMAT + " - 결과: %d";
    private static final String WINNING_RESULT_MESSAGE_FORMAT = "%s: %s";
    private static final String WINNING_RESULT_INFO_MESSAGE = "## 최종 승패";
    private static final String DELIMITER = ", ";

    public void printPlayerNameRequestMessage() {
        System.out.println(PLAYER_NAME_REQUEST_MESSAGE);
    }

    public void printInitialHoldingCards(final List<HoldingCards> initialHoldingCards) {
        List<String> playerNames = initialHoldingCards.stream()
                .map(HoldingCards::getName)
                .collect(Collectors.toUnmodifiableList());
        printInitialStatusInfoMessage(playerNames);

        for (HoldingCards cards : initialHoldingCards) {
            printCards(cards);
        }
    }

    public void printCards(HoldingCards holdingCards) {
        System.out.println(String.format(CARD_INFO_MESSAGE_FORMAT, holdingCards.getName()
                , String.join(DELIMITER,ViewRenderer.renderCardsToString(holdingCards.getCards()))));
    }

    private void printInitialStatusInfoMessage(List<String> playerNames) {
        System.out.println(String.format(INITIAL_STATUS_INFO_MESSAGE_FORMAT, Dealer.DEALER_NAME_CODE
                , String.join(DELIMITER, playerNames)));
    }

    public void printDrawCardRequestMessage(final String name) {
        System.out.println(String.format(DRAW_CARD_REQUEST_MESSAGE, name));
    }

    public void printDealerDrawInfoMessage() {
        System.out.println(DEALER_DRAW_INFO_MESSAGE);
    }

    public void printCarAndScoreResult(final List<CardAndScoreResult> cardAndScoreResult) {
        for (CardAndScoreResult result : cardAndScoreResult) {
            System.out.println(String.format(CARD_RESULT_MESSAGE_FORMAT,
                    result.getName(), String.join(DELIMITER, ViewRenderer.renderCardsToString(result.getCards())),
                    result.getScoreValue()));
        }
    }

    public void printFinalResult(final List<FinalResult> finalResults) {
        System.out.println(WINNING_RESULT_INFO_MESSAGE);
        for (FinalResult finalResult : finalResults) {
            System.out.println(String.format(WINNING_RESULT_MESSAGE_FORMAT, finalResult.getName(),
                    ViewRenderer.renderFinalResult(finalResult)));
        }
    }

    public void printLineBreak() {
        System.out.println();
    }
}
