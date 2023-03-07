package view;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

import static domain.Result.*;

public class OutputView {
    public void printCardsFrom(Users users) {
        printDealCardsNotice(users.getPlayers());
        printFirstPlayerCard(users.getDealer());
        printPlayersCards(users.getPlayers());
    }

    private void printDealCardsNotice(List<Player> players) {
        String joinedPlayerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.println("딜러와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");
    }

    private void printFirstPlayerCard(User user) {
        String name = user.getName();
        Card card = user.getCards().get(0);

        System.out.println(name + ": " + getCardDisplay(card));
    }

    private void printPlayersCards(List<Player> players) {
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getName() + "카드: " + getCardDisplays(player.getCards()));
    }

    public void noticeDealerAccept() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void noticeDealerDecline() {
        System.out.println("딜러는 16초과라 카드를 받지 않았습니다.");
    }

    public void printCardsAndScores(Users users) {
        printCardsAndScore(users.getDealer());

        for (Player player : users.getPlayers()) {
            printCardsAndScore(player);
        }
    }

    public void printCardsAndScore(User user) {
        String name = user.getName();
        String cardDisplays = getCardDisplays(user.getCards());

        System.out.println(name + "카드: " + cardDisplays + " - 결과: " + user.score().getValue());
    }

    public void printResult(String name, Result result) {
        System.out.println(name + ": " + result.getResult());
    }

    public void printDealerResults(List<Result> results) {
        long winCount = getCount(results, WIN);
        long loseCount = getCount(results, LOSE);
        long drawCount = getCount(results, DRAW);
        String result = winCount + "승 " + drawCount + "무 " + loseCount + "패";
        System.out.println("딜러: " + result);
    }

    private long getCount(List<Result> results, Result target) {
        return results.stream()
                .filter(result -> result.equals(target))
                .count();
    }

    private String getCardDisplays(List<Card> cards) {
        return cards.stream()
                .map(this::getCardDisplay)
                .collect(Collectors.joining(", "));
    }

    private String getCardDisplay(Card card) {
        return card.letter() + card.suit().getName();
    }

}
