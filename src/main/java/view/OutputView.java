package view;

import domain.card.TrumpCard;
import domain.participant.ParticipantName;
import domain.participant.Score;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private final static String OPEN_CARD_FORMAT = "%s카드: %s\n";
    private final static String TRUMP_CARD_FORMAT = "%s%s";
    private final static String OPEN_CARD_WITH_SUM_FORMAT = "%s카드: %s - 결과: %d\n";
    private final static String PLAYER_BET_INPUT_FORMAT = "%s의 배팅 금액은?\n";
    private final static String INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final static String SPLITTER = ", ";
    private final static String INITIATE_DRAW = "딜러와 %s에게 2장을 나누었습니다.\n";
    private final static String INPUT_ASK_DRAW_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private final static String DRAW_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private final static String RESULT_TITLE = "## 최종 수익\n";
    private final static String PROFIT_RESULT = "%s: %,d\n";

    public void printError(String message) {
        System.out.println(message);
    }

    public void inputNames() {
        System.out.println(INPUT_NAMES);
    }

    public void inputBetAmounts(ParticipantName name) {
        System.out.printf(PLAYER_BET_INPUT_FORMAT, name.name());
    }

    public void printInitiateDraw(List<ParticipantName> participantNames) {
        String nicknames = participantNames.stream()
                .map(ParticipantName::name)
                .collect(Collectors.joining(SPLITTER));
        System.out.printf(INITIATE_DRAW, nicknames);
    }

    public void openCards(ParticipantName name, List<TrumpCard> trumpCards) {
        String formattedTrumpCard = formattingTrumpCards(trumpCards);
        System.out.printf(OPEN_CARD_FORMAT, name.name(), formattedTrumpCard);
    }

    public void openCardsWithSum(ParticipantName name, List<TrumpCard> trumpCards, Score sum) {
        String formattedTrumpCard = formattingTrumpCards(trumpCards);
        System.out.printf(OPEN_CARD_WITH_SUM_FORMAT, name.name(), formattedTrumpCard, sum.value());
    }

    private String formattingTrumpCards(List<TrumpCard> trumpCards) {
        return trumpCards.stream()
                .map(trumpCard -> {
                    String rank = trumpCard.getRank();
                    String suit = trumpCard.getSuit();
                    return String.format(TRUMP_CARD_FORMAT, rank, suit);
                }).collect(Collectors.joining(SPLITTER));
    }

    public void askDraw(ParticipantName name) {
        System.out.printf(INPUT_ASK_DRAW_FORMAT, name.name());
    }


    public void dealerHit() {
        System.out.println(DRAW_DEALER);
    }

    public void printProfitResult(Map<String, Integer> playersProfit, Map<String, Integer> dealerProfit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(RESULT_TITLE);
        appendProfitResult(stringBuilder, dealerProfit);
        appendProfitResult(stringBuilder, playersProfit);
        System.out.println(stringBuilder);
    }

    private void appendProfitResult(StringBuilder stringBuilder, Map<String, Integer> profits) {
        for (Entry<String, Integer> entry : profits.entrySet()) {
            stringBuilder.append(String.format(PROFIT_RESULT, entry.getKey(), entry.getValue()));
        }
    }


}
