package blackjack.view;

import blackjack.domain.GamerDto;
import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printFirstCards(GamerDto dealer, List<GamerDto> players) {
        StringBuilder builder = new StringBuilder();
        printGamers(dealer, players, builder);
        printCards(dealer, players, builder);
        System.out.println(builder);

    }

    private static void printGamers(GamerDto dealer, List<GamerDto> players, StringBuilder builder) {
        builder.append(dealer.getName() + "와 ");
        String names = players.stream()
                .map(GamerDto::getName)
                .collect(Collectors.joining(", "));
        builder.append(names + "에게 " + dealer.getCardSize() + "장을 나누었습니다.\n");
    }

    private static void printCards(GamerDto dealer, List<GamerDto> players, StringBuilder builder) {
        Card firstCard = dealer.getFirstCard();
        String dealerFirstCardName = firstCard.getNumber() + firstCard.getShape();
        builder.append(dealer.getName()+": " + dealerFirstCardName + "\n");

        for (GamerDto player : players) {
            builder.append(player.getName() + "카드: ");
            printPlayerCards(builder, player);
        }
    }

    private static void printPlayerCards(StringBuilder builder, GamerDto player) {
        String cardNames = player.getCards().stream()
                .map(card -> card.getNumber() + card.getShape())
                .collect(Collectors.joining(", "));
        builder.append(cardNames + "\n");
    }
}
