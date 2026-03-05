package view;

import domain.CardList;
import domain.CardSuitMap;
import domain.Dealer;
import domain.Outcome;
import domain.Player;
import domain.Players;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import message.IOMessage;

public class ResultView {
    public void printGameStart(Players players, Dealer dealer) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNames(players));
        System.out.printf("딜러카드: %s%n", CardSuitMap.getCardName(dealer.getCardList().get(0)));
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(player -> System.out.printf(
                        "%s카드: %s%n",
                        player.getName(),
                        joinCardNames(player.getCardList())
                ));
    }

    public void printResult(Players players, Dealer dealer) {
        System.out.println("딜러카드: " + joinCardNames(dealer.getCardList()) + " - 결과: " + dealer.getResult());
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(player -> System.out.printf(
                        "%s카드: %s - 결과: %s%n",
                        player.getName(),
                        joinCardNames(player.getCardList()),
                        player.getResult()
                ));
    }

    public void printWinner(Players players, Dealer dealer) {
        System.out.println();
        System.out.println(IOMessage.WINNING_STATISTICS.message());
        printDealerOutcome(dealer);
        printPlayerOutcomes(players);
    }

    private String joinPlayerNames(Players players) {
        return IntStream.range(0, players.getSize())
                .mapToObj(index -> players.getPlayer(index).getName())
                .collect(Collectors.joining(", "));
    }

    public String joinCardNames(CardList cardList) {
        return IntStream.range(0, cardList.size())
                .map(cardList::get)
                .mapToObj(CardSuitMap::getCardName)
                .collect(Collectors.joining(", "));
    }

    private void printDealerOutcome(Dealer dealer) {
        System.out.print("딜러: ");
        for (Outcome outcome : Outcome.values()) {
            System.out.print(dealer.getCount(outcome) + outcome.toString() + " ");
        }
        System.out.println();
    }

    private void printPlayerOutcomes(Players players) {
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(this::printPlayerOutcome);
    }

    private void printPlayerOutcome(Player player) {
        System.out.println(player.getName() + ": " + player.getOutcome().toString());
    }
}
