package view;

import domain.ParticipantName;
import domain.Score;
import domain.TrumpCard;
import domain.WinStatus;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private final String OPEN_CARD_FORMAT = "%s카드: %s\n";
    private final String TRUMP_CARD_FORMAT = "%s%s";
    private final String OPEN_CARD_WITH_SUM_FORMAT = "%s카드: %s - 결과: %d\n";
    private final String INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String SPLITTER = ", ";
    private final String INITIATE_DRAW = "딜러와 %s에게 2장을 나누었습니다.\n";
    private final String INPUT_ASK_DRAW_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private final String DRAW_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private final String PLAYER_WIN_STATUS_FORMAT = "%s: %s\n";
    private final String DEALER_WIN_STATUS_FORMAT = "%s: %d승 %d패\n";
    private final String RESULT_HEADER = "## 최종 승패";

    public void printError(String message) {
        System.out.println(message);
    }

    public void inputNames() {
        System.out.println(INPUT_NAMES);
    }

    public void printInitiateDraw(List<ParticipantName> participantNames) {
        List<String> names = participantNames.stream()
                .map(ParticipantName::name)
                .toList();
        String nicknames = String.join(SPLITTER, names);
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

    public void resultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public void dealerHit() {
        System.out.println(DRAW_DEALER);
    }

    public void dealerWinStatus(int win, int lose, ParticipantName name) {
        System.out.printf(DEALER_WIN_STATUS_FORMAT, name, win, lose);
    }

    public void playerWinStatus(ParticipantName name, WinStatus status) {
        System.out.printf(PLAYER_WIN_STATUS_FORMAT, name, status.getStatus());
    }
}
