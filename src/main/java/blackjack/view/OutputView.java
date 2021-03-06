package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.participant.BlackJackParticipant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";

    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printGameInitializeMessage(List<BlackJackParticipant> participants, int startingCardCount) {
        String participantNames = participants.stream()
                .map(BlackJackParticipant::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
        System.out.println("\n" + participantNames + "에게 " + startingCardCount + "장의 카드를 나누었습니다.");
        participants.forEach(player -> printParticipantStatus(player, false));
        System.out.println();
    }

    public static void printParticipantsStatus(List<BlackJackParticipant> participants) {
        participants.forEach(participant -> printParticipantStatus(participant, true));
    }

    public static void printParticipantStatus(BlackJackParticipant participant, boolean withScore) {
        String cardNames = participant.getHand().unwrap().stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(NAME_DELIMITER));

        if (withScore) {
            System.out.println(participant.getName() + "카드: " + cardNames + getScoreMessage(participant));
            return;
        }
        System.out.println(participant.getName() + "카드: " + cardNames);
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
        System.out.println("\n" + dealer.getName() + "는 " + Dealer.DEALER_LIMIT + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(GameResult gameResult) {
        System.out.print("\n## 최종 승패\n딜러: ");
        printDealerResult(gameResult);
        printPlayersResult(gameResult);
    }

    private static void printDealerResult(GameResult gameResult) {
        Map<ResultType, Integer> dealerStatistics = gameResult.getDealerStatistics();
        for (ResultType resultType : dealerStatistics.keySet()) {
            System.out.print(dealerStatistics.get(resultType) + resultType.getName() + " ");
        }
        System.out.println();
    }

    private static void printPlayersResult(GameResult gameResult) {
        Map<Player, ResultType> unwrappedResult = gameResult.unwrap();
        unwrappedResult.keySet().forEach(player ->
                System.out.println(player.getName() + ": " + unwrappedResult.get(player).getName()));
    }
}
