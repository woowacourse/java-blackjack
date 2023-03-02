package view;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printDealCards(List<Player> players) {
        String joinedPlayerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.println("딜러와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");
    }

    public void printPlayerCards(List<Player> players) {
        for (Player player : players) {
            String name = player.getName();
            String cardDisplays = getCardDisplays(player.getCards());

            System.out.println(name + "카드: " + cardDisplays);
        }
    }

    public void printFirstPlayerCard(Player player) {
        String name = player.getName();
        Card card = player.getCards().get(0);

        System.out.println(name + ": " + getCardDisplay(card));
    }

    public void noticeDealerAccept() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void noticeDealerDecline() {
        System.out.println("딜러는 16초과라 카드를 받지 않았습니다.");
    }

    public void printCardsAndScores(List<Player> players) {
        for (Player player : players) {
            printCardsAndScore(player);
        }
    }

    public void printCardsAndScore(Player player) {
        String name = player.getName();
        String cardDisplays = getCardDisplays(player.getCards());

        System.out.println(name + "카드: " + cardDisplays + " - 결과: " + player.getScore());
    }

    public void printResult(String name, Result result) {
        System.out.println(name + ": " + result.getResult());
    }

    public void printDealerResults(List<Result> results) {
        int winCount = 0;
        int loseCount = 0;
        int drawCount = 0;
        for (Result result : results) {
            if (result == Result.WIN) {
                ++winCount;
            }
            if (result == Result.LOSE) {
                ++loseCount;
            }
            if (result == Result.DRAW) {
                ++drawCount;
            }
        }
        String result = winCount + "승 " + drawCount + "무 " + loseCount + "패";
        System.out.println("딜러: " + result);
    }

    private String getCardDisplays(List<Card> cards) {
        return cards.stream()
                .map(this::getCardDisplay)
                .collect(Collectors.joining(", "));
    }

    private String getCardDisplay(Card card) {
        return card.getLetter() + card.getFace().getName();
    }

}
