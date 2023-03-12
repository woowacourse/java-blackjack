package view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import domain.Result;
import dto.CardDto;
import dto.PlayerDto;

public class OutputView {

    public void printDealStatus(List<PlayerDto> users) {
        System.out.println("딜러와 " + joinNamesOf(users) + "에게 2장을 나누었습니다.");
    }

    public void printUsersStatus(List<PlayerDto> players) {
        for (PlayerDto player : players) {
            String name = player.getName();
            printPlayerCards(name, player.getCards());
        }
    }

    public void printPlayerCards(String name, List<CardDto> cards) {
        System.out.println(name + "카드: " + joinDisplaysOf(cards));
    }

    public void printFirstCardOfDealer(List<CardDto> cards) {
        Iterator<CardDto> cardIterator = cards.iterator();
        if (cardIterator.hasNext()) {
            CardDto firstCard = cardIterator.next();
            System.out.println("딜러: " + getDisplay(firstCard));
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
            printCardsAndScore(player.getName(), player.getCards(), player.getScore());
        }
    }

    private void printCardsAndScore(String name, List<CardDto> cards, int score) {
        System.out.println(name + "카드: " + joinDisplaysOf(cards) + " - 결과: " + score);
    }

    public void printResult(String name, Result result) {
        System.out.println(name + ": " + ResultCategory.of(result).getDisplay());
    }

    public void printDealerResults(List<Result> results) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        String renderedResults = Arrays.stream(ResultCategory.values())
                .map(resultCategory -> getDisplay(resultCategory, results))
                .collect(Collectors.joining(" "));
        System.out.println("딜러: " + renderedResults);
    }

    public void printProfit(String name, int profit) {
        System.out.println(name + ": " + profit);
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    private long countResults(ResultCategory resultCategory, List<Result> results) {
        return results.stream()
                .map(ResultCategory::of)
                .filter(result -> result.equals(resultCategory))
                .count();
    }

    private String joinDisplaysOf(List<CardDto> cards) {
        return cards.stream()
                .map(this::getDisplay)
                .collect(Collectors.joining(", "));
    }

    private String joinNamesOf(List<PlayerDto> users) {
        return users.stream()
                .map(PlayerDto::getName)
                .collect(Collectors.joining(", "));
    }

    private String getDisplay(ResultCategory resultCategory, List<Result> results) {
        return countResults(resultCategory, results) + resultCategory.getDisplay();
    }

    private String getDisplay(CardDto card) {
        return card.getLetter() + card.getName();
    }
}
