package view;

import static domain.Result.LOSE;
import static domain.Result.WIN;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Result;
import domain.User;

public class OutputView {

    public void printDealStatus(List<User> users) {
        System.out.println("딜러와 " + joinNamesOf(users) + "에게 2장을 나누었습니다.");
    }

    public void printUsersStatus(List<User> users) {
        for (User user : users) {
            String name = user.getName();
            printPlayerCards(name, user.getCards());
        }
    }

    public void printPlayerCards(String playerName, List<Card> cards) {
        System.out.println(playerName + "카드: " + joinCardDisplays(cards));
    }

    public void printFirstCardOf(Dealer dealer) {
        String name = dealer.getName();
        Card card = dealer.getCards().get(0);

        System.out.println(name + ": " + getCardDisplay(card));
    }

    public void noticeDealerHitOrStand(int hitCount) {
        if (hitCount > 0) {
            System.out.println("딜러는 16이하라 " + hitCount + "장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 16초과라 카드를 받지 않았습니다.");
        System.out.println();
    }

    public void printCardsAndScores(List<Player> players) {
        for (Player player : players) {
            printCardsAndScore(player);
        }
    }

    public void printCardsAndScore(Player player) {
        String name = player.getName();
        String cardDisplays = joinCardDisplays(player.getCards());

        System.out.println(name + "카드: " + cardDisplays + " - 결과: " + player.getScore());
    }

    public void printResult(User user, Result result) {
        System.out.println(user.getName() + ": " + getDisplay(result));
    }

    public void printDealerResults(List<Result> results) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        String renderedResults = Arrays.stream(Result.values())
                .map(targetResult -> getDisplay(targetResult, results))
                .collect(Collectors.joining(" "));
        System.out.println("딜러: " + renderedResults);
    }

    private long countResults(Result targetResult, List<Result> results) {
        return results.stream()
                .filter(result -> result.equals(targetResult))
                .count();
    }

    private String joinCardDisplays(List<Card> cards) {
        return cards.stream()
                .map(this::getCardDisplay)
                .collect(Collectors.joining(", "));
    }

    private String joinNamesOf(List<User> users) {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));
    }

    private String getDisplay(Result targetResult, List<Result> results) {
        return countResults(targetResult, results) + getDisplay(targetResult);
    }

    private String getDisplay(Result result) {
        if (result == WIN) {
            return "승";
        }
        if (result == LOSE) {
            return "패";
        }
        return "무";
    }

    private String getCardDisplay(Card card) {
        return card.getLetter().getLetter() + card.face().getName();
    }
}
