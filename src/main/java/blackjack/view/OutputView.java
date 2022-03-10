package blackjack.view;

import blackjack.controller.dto.GameResultDto;
import blackjack.controller.dto.GamerDto;
import blackjack.domain.card.Card;
import blackjack.domain.result.BlackJackResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printFirstCards(GamerDto dealer, List<GamerDto> players) {
        printGamers(dealer, players);
        printDealerFirstCard(dealer);
        printPlayerFirstCards(players);
    }

    private static void printGamers(GamerDto dealer, List<GamerDto> players) {
        StringBuilder builder = new StringBuilder();
        builder.append(dealer.getName() + "와 ");
        String names = players.stream()
                .map(GamerDto::getName)
                .collect(Collectors.joining(", "));
        builder.append(names + "에게 " + dealer.getCardSize() + "장을 나누었습니다.");
        System.out.println(builder);
    }

    private static void printDealerFirstCard(GamerDto dealer) {
        Card firstCard = dealer.getFirstCard();
        String dealerFirstCardName = firstCard.getName() + firstCard.getShape();
        System.out.println(dealer.getName() + "카드: " + dealerFirstCardName);
    }

    private static void printPlayerFirstCards(List<GamerDto> players) {
        for (GamerDto player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    public static void printPlayerCard(GamerDto player) {
        System.out.println(player.getName() + "카드: " + getCardNames(player));
    }

    private static String getCardNames(GamerDto gamer) {
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
        System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.\n\n");
    }

    public static void printFinalCards(GamerDto dealer, List<GamerDto> players) {
        printDealerFinalCards(dealer);
        printPlayerFinalCards(players);
    }

    private static void printDealerFinalCards(GamerDto dealer) {
        StringBuilder builder = new StringBuilder();
        builder.append(dealer.getName() + "카드: ");
        builder.append(getCardNames(dealer));
        builder.append("- 결과: " + dealer.getCardNumberSum() );
        System.out.println(builder);
    }

    private static void printPlayerFinalCards(List<GamerDto> players) {
        StringBuilder builder = new StringBuilder();
        for (GamerDto player : players) {
            builder.append(player.getName() + "카드: ");
            builder.append(getCardNames(player));
            builder.append("- 결과: " + player.getCardNumberSum() + "\n");
        }
        System.out.println(builder);
    }

    public static void printFinalResult(GameResultDto gameResultDto) {
        System.out.println("## 최종 승패");
        printFinalDealerResult(gameResultDto.getDealerResult());
        printFinalPlayerResult(gameResultDto.getPlayerResults());
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
            System.out.print(dealerResult.get(dealer) + dealer.getValue()+ " ");
        }
    }

    private static void printFinalPlayerResult(Map<String, BlackJackResult> playerResult) {
        StringBuilder builder = new StringBuilder();
        for (String name : playerResult.keySet()) {
            builder.append(name+": ");
            builder.append(playerResult.get(name).getValue() + "\n");
        }
        System.out.print(builder);
    }
}
