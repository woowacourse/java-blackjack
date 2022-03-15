package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.dto.DealerDto;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitCardHandStatus(DealerDto dealerDto, Players players) {
        List<Player> readPlayers = players.getPlayers();

        System.out.println();
        System.out.println("딜러와 " + readPlayers.stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", ")) + "에게 2장을 나누었습니다.");

        System.out.println("딜러: " + dealerDto.showOneCard());

        for (Player player : readPlayers) {
            printCardHandStatus(player);
        }
        System.out.println();
    }

    public static String showCardHandStatus(Participant participant) {
        return String.format("%s 카드: %s",
                participant.getName().getValue(),
                participant.getCards().getCardHand().stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")));
    }

    public static void printDealerStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardHandStatus(Participant participant) {
        System.out.println(showCardHandStatus(participant));
    }

    public static void printFinalStatus(Dealer dealer, Players players) {
        System.out.println(showCardHandStatus(dealer) + " 결과 - " + dealer.calculateBestScore());
        for (Player readPlayer : players.getPlayers()) {
            System.out.println(showCardHandStatus(readPlayer) + " 결과 - " + readPlayer.calculateBestScore());
        }
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.println("## 최종 승패");
        Map<Player, Result> resultCounter = players.judgeResult(dealer.calculateBestScore());
        System.out.println(dealer.getName().getValue() + ": " +
                resultCounter.values().stream().filter(result -> result == Result.WIN).count() + "승" +
                resultCounter.values().stream().filter(result -> result == Result.TIE).count() + "무" +
                resultCounter.values().stream().filter(result -> result == Result.LOSE).count() + "패");
        resultCounter.forEach((player, result)
                -> System.out.println(player.getName().getValue() + ": " + result.getResult()));
    }
}
