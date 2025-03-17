package view;

import domain.card.Card;
import domain.game.GamblingMoney;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    
    private final PrintStream printStream;

    public OutputView(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printSetUpCardDeck(Card dealerOpenCard, List<GamblerDto> players) {
        String playerNames = players.stream()
            .map(GamblerDto::name)
            .collect(Collectors.joining(","));

        printStream.printf("딜러와 %s에게 2장을 나누었습니다.%n", playerNames);
        printStream.println("딜러카드: " + dealerOpenCard);
        players.forEach(player -> printStream.printf("%s카드: %s%n",
            player.name(), formatCards(player.cards())));
    }

    public void printTakenMoreCards(String name, List<Card> cards) {
        printStream.printf("%s카드: %s%n", name, formatCards(cards));
    }

    public void printDealerHasTaken() {
        printStream.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardDeck(List<GamblerDto> gamblers) {
        gamblers.forEach(gambler ->
            printStream.printf("%s카드: %s - 결과: %d\n",
                gambler.name(), formatCards(gambler.cards()), gambler.score())
        );
    }

    public void printGamblerProfits(Map<GamblerDto, GamblingMoney> gamblerProfits) {
        printStream.println("## 최종 수익");
        gamblerProfits.forEach((gambler, profit) ->
            printStream.printf("%s: %d%n", gambler.name(), profit.getAmount()));
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "));
    }
}
