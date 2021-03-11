package blackjack.view;

import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.participant.BlackJackParticipant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void printInputNames() {
        System.out.println(LINE_SEPARATOR + "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printInputMoney(String name) {
        System.out.println(LINE_SEPARATOR + name + "의 베팅 금액은?");
    }

    public static void printGameInitializeMessage(List<BlackJackParticipant> participants, int startingCardCount) {
        String participantNames = participants.stream()
                .map(BlackJackParticipant::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
        System.out.println("\n" + participantNames + "에게 " + startingCardCount + "장의 카드를 나누었습니다.");
        participants.forEach(OutputView::printParticipantStatus);
        System.out.println();
    }

    public static void printParticipantStatus(BlackJackParticipant participant) {
        System.out.println(participant.getName() + "카드: " + getCardNameFormat(participant));
    }

    private static String getCardNameFormat(BlackJackParticipant participant) {
        if (participant instanceof Dealer) {
            return participant.getHand().unwrap().stream()
                    .map(Card::getCardName)
                    .findFirst()
                    .orElse(null);
        }
        return participant.getHand().unwrap().stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    public static void printParticipantsStatusWithScore(List<BlackJackParticipant> participants) {
        System.out.println(LINE_SEPARATOR);
        participants.forEach(OutputView::printParticipantStatusWithScore);
    }

    public static void printParticipantStatusWithScore(BlackJackParticipant participant) {
        System.out.println(participant.getName() + "카드: " + getCardNameFormat(participant) + getScoreMessage(participant));
    }

    private static String getScoreMessage(BlackJackParticipant participant) {
        String scoreMessage = " - 결과: " + participant.getScore();
        if (participant.isBust()) {
            scoreMessage = " - 결과: BUST";
        }
        return scoreMessage;
    }

    public static void willDrawCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerDrawCard(Dealer dealer) {
        System.out.println(LINE_SEPARATOR + dealer.getName() + "는 " + Dealer.DEALER_LIMIT + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(Players players, Dealer dealer) {
        System.out.println(LINE_SEPARATOR + "## 최종 승패");

        System.out.println(dealer.getName() + " : " + dealer.getProfit(players));

        players.unwrap()
                .forEach(player -> System.out.println(player.getName() + " : " + player.getProfit(dealer)));
    }
}
