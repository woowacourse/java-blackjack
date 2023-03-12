package blackjack.view;

import blackjack.domain.user.Dealer;
import blackjack.dto.view.CardAndScoreResult;
import blackjack.dto.view.HoldingCards;
import blackjack.dto.view.ProfitResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NAME_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String PLAYER_BETTING_AMOUNT_REQUEST_MESSAGE = "%s의 배팅 금액은?";
    private static final String INITIAL_STATUS_INFO_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_INFO_MESSAGE_FORMAT = "%s카드: %s";
    private static final String DRAW_CARD_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_DRAW_INFO_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_RESULT_MESSAGE_FORMAT = CARD_INFO_MESSAGE_FORMAT + " - 결과: %d";
    private static final String PROFIT_INFO_MESSAGE = "## 최종 수익";
    private static final String PROFIT_RESULT_MESSAGE_FORMAT = "%s: %d";
    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ", ";

    public void printPlayerNameRequestMessage() {
        System.out.println(PLAYER_NAME_REQUEST_MESSAGE);
    }

    public void printPlayerBettingAmountRequestMessage(final String name) {
        System.out.println(String.format(PLAYER_BETTING_AMOUNT_REQUEST_MESSAGE, name));
    }

    public void printInitialHoldingCards(final List<HoldingCards> initialHoldingCards) {
        List<String> playerNames = initialHoldingCards.stream()
                .map(HoldingCards::getName)
                .collect(Collectors.toUnmodifiableList());

        printInitialStatusInfoMessage(playerNames.stream()
                .filter(name -> !name.equals(Dealer.DEALER_NAME_CODE))
                .collect(Collectors.toUnmodifiableList()));

        for (HoldingCards cards : initialHoldingCards) {
            printCards(cards);
        }
    }

    public void printCards(HoldingCards holdingCards) {
        System.out.println(String.format(CARD_INFO_MESSAGE_FORMAT, dealerNameConvertor(holdingCards.getName())
                , String.join(DELIMITER, ViewRenderer.renderCardsToString(holdingCards.getCards()))));
    }

    private String dealerNameConvertor(final String name) {
        if (name.equals(Dealer.DEALER_NAME_CODE)) {
            return DEALER_NAME;
        }
        return name;
    }

    private void printInitialStatusInfoMessage(List<String> playerNames) {
        System.out.println(String.format(INITIAL_STATUS_INFO_MESSAGE_FORMAT, DEALER_NAME
                , String.join(DELIMITER, playerNames)));
    }

    public void printDrawCardRequestMessage(final String name) {
        System.out.println(String.format(DRAW_CARD_REQUEST_MESSAGE, name));
    }

    public void printDealerDrawInfoMessage() {
        System.out.println(DEALER_DRAW_INFO_MESSAGE);
    }

    public void printCardAndScoreResult(final List<CardAndScoreResult> cardAndScoreResult) {
        for (CardAndScoreResult result : cardAndScoreResult) {
            System.out.println(String.format(CARD_RESULT_MESSAGE_FORMAT,
                    dealerNameConvertor(result.getName()), String.join(DELIMITER, ViewRenderer.renderCardsToString(result.getCards())),
                    result.getScoreValue()));
        }
    }

    public void printProfitResult(final List<ProfitResult> profitResults) {
        System.out.println(PROFIT_INFO_MESSAGE);
        for (ProfitResult result : profitResults) {
            System.out.println(String.format(PROFIT_RESULT_MESSAGE_FORMAT,
                    dealerNameConvertor(result.getName()), result.getProfitAmount()));
        }
    }

    public void printLineBreak() {
        System.out.println();
    }
}
