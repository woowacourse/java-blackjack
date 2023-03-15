package blackjackgame.view;

import java.util.List;
import java.util.Map;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.player.Player;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void printStartingCards(Map<String, List<Card>> startingCards) {
        for (String name : startingCards.keySet()) {
            printCards(name, startingCards.get(name));
        }
    }

    public void printCards(final String playerName, final List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.printf("%s%s: ", System.lineSeparator(), playerName);

        cards.forEach(card -> stringBuilder.append(card)
            .append(DELIMITER));

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(DELIMITER));
        System.out.print(stringBuilder);
    }

    public void dealerAddCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(final int score) {
        System.out.print(" - 결과: " + score);
    }

    public void printProfit(Map<Player, Double> profitResult) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder("## 최종 수익" + System.lineSeparator());
        for (Player player : profitResult.keySet()) {
            stringBuilder.append(player.getName())
                .append(": ")
                .append(Math.round(profitResult.get(player)))
                .append(System.lineSeparator());
        }
        System.out.println(System.lineSeparator() + stringBuilder);
    }

}
