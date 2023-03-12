package view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.hand.Hand;
import domain.Result;
import dto.PlayerDto;

public class OutputView {

    public void printDealStatus(List<PlayerDto> users) {
        System.out.println("딜러와 " + joinNamesOf(users) + "에게 2장을 나누었습니다.");
    }

    public void printUsersStatus(List<PlayerDto> players) {
        for (PlayerDto player : players) {
            String name = player.getName();
            printPlayerHand(name, player.getHand());
        }
    }

    public void printPlayerHand(String playerName, Hand hand) {
        System.out.println(playerName + "카드: " + joinDisplaysOf(hand.getCards()));
    }

    public void printFirstCardOfDealer(Hand hand) {
        Iterator<Card> cardIterator = hand.getCards().iterator();
        if (cardIterator.hasNext()) {
            Card firstCard = cardIterator.next();
            System.out.println("딜러: " + Display.of(firstCard));
            return;
        }
        System.out.println("딜러의 카드가 없습니다");
    }

    public void noticeDealerHitOrStand(int hitCount) {
        if (hitCount > 0) {
            System.out.println("딜러는 16이하라 " + hitCount + "장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 16초과라 카드를 받지 않았습니다.");
        System.out.println();
    }

    public void printCardsAndScores(List<PlayerDto> players) {
        for (PlayerDto player : players) {
            printCardsAndScore(player.getName(), player.getHand());
        }
    }

    private void printCardsAndScore(String name, Hand hand) {
        System.out.println(name + "카드: " + joinDisplaysOf(hand.getCards()) + " - 결과: " + hand.score().getScore());
    }

    public void printResult(String userName, Result result) {
        System.out.println(userName + ": " + Display.of(result));
    }

    public void printDealerResults(List<Result> results) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        String renderedResults = Arrays.stream(Result.values())
                .map(targetResult -> getDisplay(targetResult, results))
                .collect(Collectors.joining(" "));
        System.out.println("딜러: " + renderedResults);
    }

    public void printProfit(String name, int profit) {
        System.out.println(name + ": " + profit);
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    private long countResults(Result targetResult, List<Result> results) {
        return results.stream()
                .filter(result -> result.equals(targetResult))
                .count();
    }

    private String joinDisplaysOf(Set<Card> cards) {
        return cards.stream()
                .map(Display::of)
                .collect(Collectors.joining(", "));
    }

    private String joinNamesOf(List<PlayerDto> users) {
        return users.stream()
                .map(PlayerDto::getName)
                .collect(Collectors.joining(", "));
    }

    private String getDisplay(Result targetResult, List<Result> results) {
        return countResults(targetResult, results) + Display.of(targetResult);
    }
}
