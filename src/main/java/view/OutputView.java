package view;

import domain.blackjackgame.ParticipantGameResult;
import domain.blackjackgame.TrumpCard;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String OPEN_CARD_FORMAT = "%s카드: %s\n";
    private static final String TRUMP_CARD_FORMAT = "%s%s";
    private static final String OPEN_CARD_WITH_SUM_FORMAT = "%s카드: %s - 결과: %d\n";
    private static final String INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String SPLITTER = ", ";
    private static final String INITIATE_DRAW = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String INPUT_ASK_DRAW_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DRAW_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PLAYER_WIN_STATUS_FORMAT = "%s: %s\n";
    private static final String DEALER_WIN_STATUS_FORMAT = "%s: %d승 %d패\n";
    private static final String RESULT_HEADER = "## 최종 승패";
    private static final String PRINT_INPUT_BETS = "%s의 배팅 금액은?";
    private static final String BET_RESULT_FORMAT = "%s: %d\n";
    private static final String BET_BLACKJACK_HEADER = "## 최종 수익\n";
    ;

    public void printError(String message) {
        System.out.println(message);
    }

    public void printInitiateDraw(List<String> names) {
        String nicknames = String.join(SPLITTER, names);
        System.out.printf(INITIATE_DRAW, nicknames);
    }

    public void inputNames() {
        System.out.println(INPUT_NAMES);
    }

    public void openCards(String name, List<TrumpCard> trumpCards) {
        String formattedTrumpCard = formattingTrumpCards(trumpCards);
        System.out.printf(OPEN_CARD_FORMAT, name, formattedTrumpCard);
    }

    public void openCardsWithSum(String name, List<TrumpCard> trumpCards, int sum) {
        String formattedTrumpCard = formattingTrumpCards(trumpCards);
        System.out.printf(OPEN_CARD_WITH_SUM_FORMAT, name, formattedTrumpCard, sum);
    }

    private String formattingTrumpCards(List<TrumpCard> trumpCards) {
        String formattedTrumpCard = trumpCards.stream()
                .map((trumpCard) -> {
                    String rank = trumpCard.getRank();
                    String suit = trumpCard.getSuit();
                    return String.format(TRUMP_CARD_FORMAT, rank, suit);
                }).collect(Collectors.joining(SPLITTER));
        return formattedTrumpCard;
    }

    public void askDraw(String name) {
        System.out.printf(INPUT_ASK_DRAW_FORMAT, name);
    }

    public void resultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public void dealerHit() {
        System.out.println(DRAW_DEALER);
    }

    public void dealerWinStatus(int win, int lose, String name) {
        System.out.printf(DEALER_WIN_STATUS_FORMAT, name, win, lose);
    }

    public void playerWinStatus(String name, ParticipantGameResult status) {
        System.out.printf(PLAYER_WIN_STATUS_FORMAT, name, status.getStatus());
    }

    public void inputBets(String name) {
        System.out.printf(PRINT_INPUT_BETS, name);
    }

    public void printBettingBlackjackGameResult(Map<String, Double> blackjackBettingResult) {
        StringBuilder stringBuilder = new StringBuilder(BET_BLACKJACK_HEADER);
        for (Entry<String, Double> entry : blackjackBettingResult.entrySet()) {
            Double value = entry.getValue();
            stringBuilder.append(String.format(BET_RESULT_FORMAT, entry.getKey(), value.intValue()));
        }
        System.out.println(stringBuilder.toString());
    }
}
