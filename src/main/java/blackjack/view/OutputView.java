package blackjack.view;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.Outcome;
import blackjack.domain.result.ParticipantsResult;
import blackjack.domain.gambler.Participants;

import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Participants participants) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다."
            , participants.getDealerName()
            , String.join(", ", participants.getPlayerNames()));
        System.out.println();
    }

    public static void printUsersCards(Participants participants) {
        Dealer dealer = participants.getDealer();
        System.out.printf("%s: %s", dealer.getName(), String.join(",", dealer.getFirstCardInfo()));
        System.out.println();
        for (Player player : participants.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        System.out.println(getPlayerCards(player));
    }

    private static String getPlayerCards(Player player) {
        return String.format("%s: %s", player.getName()
                , String.join(", ", player.getCardsInfos()));
    }

    private static String getDealerCards(Dealer dealer) {
        return String.format("%s: %s", dealer.getName()
                , String.join(", ", dealer.getCardsInfos()));
    }

    public static void printDealerOneMoreCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println();
    }

    public static void printUsersCardsAndScore(Participants participants) {
        System.out.println();
        CardsResult dealerCardsResult = participants.getDealer().getScore();
        System.out.printf("%s - 결과: %s", getDealerCards(participants.getDealer()), dealerCardsResult.getResult());
        System.out.println();
        for (Player player : participants.getPlayers()) {
            CardsResult playerCardsResult = player.getScore();
            System.out.printf("%s - 결과: %s", getPlayerCards(player), playerCardsResult.getResult());
            System.out.println();
        }
    }

    public static void printFinalResult(Participants participants,
        ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerFinalResult(participants.getDealer(),
            participantsResult.getDealerResultsNoZero());
        printPlayerFinalResult(participantsResult.getPlayersResult());
    }

    private static void printDealerFinalResult(Dealer dealer, Map<Outcome, Integer> gameResult) {
        System.out.printf("%s: ", dealer.getName());
        for (Outcome outcome : gameResult.keySet()) {
            System.out.print(gameResult.get(outcome) + outcome.getConverseName() + " ");
        }
        System.out.println();
    }

    private static void printPlayerFinalResult(Map<Player, Outcome> playersResult) {
        for (Player player : playersResult.keySet()) {
            Outcome outcome = playersResult.get(player);
            System.out.printf("%s: %s", player.getName(), outcome.getName());
            System.out.println();
        }
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
