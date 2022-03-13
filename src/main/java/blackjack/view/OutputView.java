package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackReferee;
import blackjack.domain.result.BlackJackResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printFirstCards(Dealer dealer, List<Player> players) {
        printGamers(dealer, players);
        printDealerFirstCard(dealer);
        printPlayerFirstCards(players);
    }

    private static void printGamers(Dealer dealer, List<Player> players) {
        String names = players.stream()
                .map(Gamer::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 %d장을 나누었습니다.\n", dealer.getName(), names, dealer.getCardsSize());
    }

    private static void printDealerFirstCard(Dealer dealer) {
        Card firstCard = dealer.getCards().get(0);
        String dealerFirstCardName = firstCard.getName() + firstCard.getShape();
        System.out.println(dealer.getName() + "카드: " + dealerFirstCardName);
    }

    private static void printPlayerFirstCards(List<Player> players) {
        for (Player player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        System.out.println(player.getName() + "카드: " + getCardNames(player));
    }

    private static String getCardNames(Gamer gamer) {
        return gamer.getCards().stream()
                .map(card -> card.getName() + card.getShape())
                .collect(Collectors.joining(", "));
    }

    public static void printAdditionalDrawDealer(int count) {
        System.out.println();
        if (count != 0) {
            System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", count);
            return;
        }
        System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.\n");
    }

    public static void printFinalCards(Dealer dealer, List<Player> players) {
        printDealerFinalCards(dealer);
        printPlayerFinalCards(players);
    }

    private static void printDealerFinalCards(Dealer dealer) {
        System.out.printf("%s카드: %s- 결과: %d\n",
                dealer.getName(), getCardNames(dealer), dealer.getCardsNumberSum());
    }

    private static void printPlayerFinalCards(List<Player> players) {
        for (Player player : players) {
            System.out.printf("%s카드: %s- 결과: %d\n",
                    player.getName(), getCardNames(player), player.getCardsNumberSum());
        }
        System.out.println();
    }

    public static void printFinalResult(BlackJackReferee blackJackReferee) {
        System.out.println("## 최종 승패");
        printFinalDealerResult(blackJackReferee.getDealerResult());
        printFinalPlayerResult(blackJackReferee.getPlayerResult());
    }

    private static void printFinalDealerResult(Map<BlackJackResult, Integer> dealerResult) {
        System.out.print("딜러: ");
        for (BlackJackResult dealer : dealerResult.keySet()) {
            getDealerSingleResult(dealerResult, dealer);
        }
        System.out.println();
    }

    private static void getDealerSingleResult(Map<BlackJackResult, Integer> dealerResult, BlackJackResult dealer) {
        if (dealerResult.get(dealer) > 0) {
            System.out.print(dealerResult.get(dealer) + dealer.getValue() + " ");
        }
    }

    private static void printFinalPlayerResult(Map<String, BlackJackResult> playerResult) {
        for (String name : playerResult.keySet()) {
            System.out.printf("%s: %s\n", name, playerResult.get(name).getValue());
        }
    }
}
