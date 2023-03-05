package view;

import static domain.Result.DRAW;
import static domain.Result.LOSE;
import static domain.Result.WIN;

import java.util.List;
import java.util.stream.Collectors;

import domain.Player;
import domain.Card;
import domain.Dealer;
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

    public void noticeDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void noticeDealerStand() {
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
        System.out.println(user.getName() + ": " + result.getResult());
    }

    public void printDealerResults(List<Result> results) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        long winCount = countResults(WIN, results);
        long loseCount = countResults(LOSE, results);
        long drawCount = countResults(DRAW, results);
        String result = winCount + "승 " + drawCount + "무 " + loseCount + "패";
        System.out.println("딜러: " + result);
    }

    private long countResults(Result target, List<Result> results) {
        return results.stream()
                .filter(result -> result.equals(target))
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

    private String getCardDisplay(Card card) {
        return card.getLetter().getLetter() + card.getFace().getName();
    }
}
