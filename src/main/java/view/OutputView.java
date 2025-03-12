package view;

import domain.card.Card;
import domain.game.Winning;
import domain.game.WinningCounts;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printSetUpCardDeck(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();
        String playerNames = players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(","));

        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playerNames);
        System.out.println("딜러카드: " + dealerOpenCard);
        players.forEach(player -> System.out.printf("%s카드: %s%n", player.getName(),
            formatCards(player.getCards())));
    }

    public void printTakenMoreCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name, formatCards(cards));
    }

    public void printDealerHasTaken() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardDeck(List<Gambler> gamblers) {
        gamblers.forEach(gambler ->
            System.out.printf("%s카드: %s - 결과: %d\n",
                gambler.getName(), formatCards(gambler.getCards()), gambler.calculateScore())
        );
    }

    public void printGameResult(WinningCounts winningCounts, Map<Player, Winning> playerWinnings) {
        System.out.println("## 최종 승패");
        printDealerWinnings(winningCounts);

        playerWinnings.forEach((player, winning) ->
            System.out.printf("%s: %s\n", player.getName(), winning.getName())
        );
    }

    private static void printDealerWinnings(WinningCounts winningCounts) {
        System.out.print("딜러: ");
        if (winningCounts.winCount() > 0) {
            System.out.print(winningCounts.winCount() + "승 ");
        }
        if (winningCounts.drawCount() > 0) {
            System.out.print(winningCounts.drawCount() + "무 ");
        }
        if (winningCounts.loseCount() > 0) {
            System.out.print(winningCounts.loseCount() + "패 ");
        }
        System.out.println();
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "));
    }
}
