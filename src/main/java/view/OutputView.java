package view;

import domain.deck.Card;
import domain.game.Outcome;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public final class OutputView {

    public void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printHitTwice(final Players players) {
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

    public void printGameResult(final EnumMap<Outcome, Integer> dealerResult, final Map<String, Outcome> playerResults) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerResult);
        printPlayerResults(playerResults);
    }

    private void printDealerResult(final EnumMap<Outcome, Integer> dealerResult) {
        printGameEachResult(
                "딜러",
                dealerResult.get(Outcome.WIN),
                dealerResult.get(Outcome.DRAW),
                dealerResult.get(Outcome.LOSE)
        );
    }

    private void printPlayerResults(final Map<String, Outcome> playerResults) {
        playerResults.keySet().forEach(name ->
                printPlayerResult(playerResults, name)
        );
    }

    private void printPlayerResult(final Map<String, Outcome> playerResults, final String name) {
        if (playerResults.get(name).equals(Outcome.WIN)) {
            printGameEachResult(name, 1, 0, 0);
        }
        if (playerResults.get(name).equals(Outcome.DRAW)) {
            printGameEachResult(name, 0, 1, 0);
        }
        if (playerResults.get(name).equals(Outcome.LOSE)) {
            printGameEachResult(name, 0, 0, 1);
        }
    }

    private void printGameEachResult(final String name, final int win, final int draw, final int lose) {
        System.out.printf("%s: %s승 %s무 %s패%n", name, win, draw, lose);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
