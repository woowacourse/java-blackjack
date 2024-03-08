package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";
    private static final String ASK_DRAW_MESSAGE = "는/은 한장의 카드를 더 받겠습니까?(예는 "
            + ACCEPT_DRAW_MESSAGE + ", 아니오는 " + REJECT_DRAW_MESSAGE + ")";

    private static final String FINAL_HANDS_AND_SCORE_FORMAT = "%s - 결과: %d" + System.lineSeparator();
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private OutputView() {
    }

    public static void printAskNameMessage() {
        System.out.println("게임에 참여 할사람 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void printAskDrawMessage(String playerName) {
        System.out.println(playerName + ASK_DRAW_MESSAGE);
    }

    public static void printDealerDrawMessage(Dealer dealer) {
        System.out.println(dealer.getName() + "는 "
                + DEALER_DRAW_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDrawInitialHandsMessage(Dealer dealer, Players players) {
        String playerNames = players.getPlayers()
                .stream()
                .map(Participant::getName)
                .collect(Collectors.joining(","));

        System.out.println(dealer.getName()
                + "와 "
                + playerNames
                + "에게 2장을 나누었습니다");
    }

    public static void printParticipantHands(Participant participant) {
        System.out.println(resolveParticipantHandsMessage(participant));
    }

    public static void printFinalHandsAndScore(Participant participant) {
        System.out.printf((FINAL_HANDS_AND_SCORE_FORMAT)
                , resolveParticipantHandsMessage(participant)
                , participant.getHandsScore());
    }

    public static void printDealerFirstCard(Dealer dealer) {
        System.out.println(dealer.getName()
                + ": "
                + dealer.getFirstCardName());
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
