package view;

import domain.deck.Card;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public final class OutputView {

    public void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitBetMoney(final Player player) {
        System.out.printf("%n%s 배팅 금액은?%n", player.getName());
    }

    public void printHitTwice(final Players players) {
        printEmptyLine();
        List<String> names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        String joinedName = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinedName);
    }

    public void printDealerFirstCard(final Dealer dealer) {
        Card dealerOpenCard = dealer.getCards().get(0);
        String rank = dealerOpenCard.getRank().getRank();
        String suit = dealerOpenCard.getSuit().getSuit();
        System.out.printf("딜러: %s%s%n", rank, suit);
    }

    public void printPlayersCard(final Players players) {
        players.getPlayers()
                .forEach(this::printPlayerCard);
        printEmptyLine();
    }

    public void printPlayerCard(final Player player) {
        String toStringCards = toStringCards(player.getCards());
        String name = player.getName();

        System.out.printf("%s: %s%n", name, toStringCards);
    }

    private String toStringCards(final List<Card> cards) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        cards.forEach(card -> {
            String temp = card.getRank().getRank() + card.getSuit().getSuit();
            stringJoiner.add(temp);
        });
        return stringJoiner.toString();
    }

    public void printHitAgain(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
    }

    public void printDealerHitAgain() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        printEmptyLine();
    }

    public void printDealerCardAndScore(final Dealer dealer) {
        List<Card> cards = dealer.getCards();
        int score = dealer.getScore();

        System.out.printf("딜러 카드: %s - 결과: %s%n", toStringCards(cards), score);
    }

    public void printPlayerCardAndScore(final Player player) {
        String name = player.getName();
        List<Card> cards = player.getCards();
        int score = player.getScore();

        System.out.printf("%s 카드: %s - 결과: %s%n", name, toStringCards(cards), score);
    }

    public void printGameResult(final int dealerProfit, final Map<String, Integer> playerBetMoneyResults) {
        printEmptyLine();
        System.out.println("## 최종 수익");
        printDealerResult(dealerProfit);
        printPlayerResults(playerBetMoneyResults);
    }

    private void printDealerResult(final int dealerProfit) {
        System.out.printf("딜러: %s%n", dealerProfit);
    }

    private void printPlayerResults(final Map<String, Integer> playerResults) {
        playerResults.keySet()
                .forEach(name -> {
                    printEachPlayerResult(name, playerResults.get(name));
                });
    }

    private void printEachPlayerResult(final String name, final int profit) {
        System.out.printf("%s: %s%n", name, profit);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
