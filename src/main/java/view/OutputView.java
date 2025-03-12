package view;

import domain.card.Card;
import domain.game.Winning;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    
    private final PrintStream printStream;

    public OutputView(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printSetUpCardDeck(Dealer dealer, List<Player> players) {
        Card dealerOpenCard = dealer.getOpenCard();
        String playerNames = players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(","));

        printStream.printf("딜러와 %s에게 2장을 나누었습니다.%n", playerNames);
        printStream.println("딜러카드: " + dealerOpenCard);
        players.forEach(player -> printStream.printf("%s카드: %s%n", player.getName(),
            formatCards(player.getCards())));
    }

    public void printTakenMoreCards(String name, List<Card> cards) {
        printStream.printf("%s카드: %s%n", name, formatCards(cards));
    }

    public void printDealerHasTaken() {
        printStream.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardDeck(List<Gambler> gamblers) {
        gamblers.forEach(gambler ->
            printStream.printf("%s카드: %s - 결과: %d\n",
                gambler.getName(), formatCards(gambler.getCards()), gambler.calculateScore())
        );
    }

    public void printGameResult(Map<Winning, Long> dealerWinnings,
        Map<Player, Winning> playerWinnings) {
        printStream.println("## 최종 승패");
        printDealerWinnings(dealerWinnings);

        playerWinnings.forEach((player, winning) ->
            printStream.printf("%s: %s%n", player.getName(), winning.getName()));
    }

    private void printDealerWinnings(Map<Winning, Long> dealerWinnings) {
        printStream.print("딜러: ");
        dealerWinnings.entrySet()
            .stream()
            .sorted(Entry.comparingByKey())
            .forEach(
                entry -> printStream.printf("%d%s ", entry.getValue(), entry.getKey().getName()));
        printStream.println();
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "));
    }
}
