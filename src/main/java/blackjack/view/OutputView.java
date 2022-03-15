package blackjack.view;

import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;
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
        String names = players.stream()
                .map(GamerDto::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 %d장을 나누었습니다.\n", dealer.getName(), names, dealer.getCardSize());
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
        System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.\n");
    }

    public static void printFinalCards(GamerDto dealer, List<GamerDto> players) {
        printDealerFinalCards(dealer);
        printPlayerFinalCards(players);
    }

    private static void printDealerFinalCards(GamerDto dealer) {
        System.out.printf("%s카드: %s- 결과: %d\n",
                dealer.getName(), getCardNames(dealer), dealer.getCardNumberSum());
    }

    private static void printPlayerFinalCards(List<GamerDto> players) {
        for (GamerDto player : players) {
            System.out.printf("%s카드: %s- 결과: %d\n",
                    player.getName(), getCardNames(player), player.getCardNumberSum());
        }
        System.out.println();
    }

    public static void printFinalResult(GameResultDto gameResultDto) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + gameResultDto.getDealerEarning());
        gameResultDto.getPlayerEarnings()
            .forEach((name, earning) -> System.out.printf("%s: %s\n", name, earning));
    }
}
