package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printParticipantInitialCards(final List<Player> players, final Dealer dealer) {
        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(), getPlayerNames(players));
        printNewLine();

        printPlayerCardStatus(dealer.getName(), dealer.openFirstCards());
        for (Player player : players) {
            printPlayerCardStatus(player.getName(), player.openFirstCards());
        }
        printNewLine();
    }

    public String getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printPlayerCardStatus(final String name, final List<Card> cards) {
        System.out.printf("%s카드: %s%n", name, printCards(cards));
    }

    private String printCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumber().getType() + card.getCardPattern().getName())
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawOneMoreCard() {
        printNewLine();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        printNewLine();
    }

    public void printAllPlayerCardStatus(final List<Player> players, final Dealer dealer) {
        printPlayerCardStatusAndScore(dealer.getName(), dealer.getCards(), dealer.calculateScore());
        for (Player player : players) {
            printPlayerCardStatusAndScore(player.getName(), player.getCards(), player.calculateScore());
        }
        printNewLine();
    }

    private void printPlayerCardStatusAndScore(final String name, final List<Card> cards, final int cardScore) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, printCards(cards), cardScore);
    }

    public void printGameResult(final double dealerProfit,
            final Map<String, Double> playerProfit) {
        System.out.println("## 최종 수익");

        System.out.println("딜러:" + (int) dealerProfit);

        for (String playerName : playerProfit.keySet()) {
            System.out.println(playerName + ": " + (int) playerProfit.get(playerName).doubleValue());
        }
    }

    private void printNewLine() {
        System.out.println();
    }
}
