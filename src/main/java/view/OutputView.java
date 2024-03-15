package view;

import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.game.BlackjackGame;
import domain.player.Name;
import domain.score.Revenue;
import domain.score.ScoreBoard;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitialCards(BlackjackGame game) {
        Map<Name, PlayerCards> players = game.players();
        printDrawNotice(players);

        String firstCard = game.dealer().getFirstCard();
        System.out.print("딜러: " + firstCard);

        players.forEach((name, player) -> {
            System.out.println();
            printPlayerCards(name, player);
        });
        System.out.println();
    }

    public void printPlayerCards(Name name, PlayerCards player) {
        System.out.print(name + "카드: " + formatCards(player));
    }

    private void printDrawNotice(Map<Name, PlayerCards> players) {
        List<String> names = players.keySet().stream()
                .map(Name::toString)
                .toList();
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    private String formatCards(Cards cards) {
        return String.join(", ", cards.getCards());
    }

    public void printResults(BlackjackGame game) {
        System.out.println();
        printDealerCards(game.dealer());
        printSumOfCards(game.dealer());
        game.players().forEach((name, player) -> {
            printPlayerCards(name, player);
            printSumOfCards(player);
        });
    }

    private void printDealerCards(DealerCards cards) {
        System.out.print("딜러 카드: ");
        System.out.print(formatCards(cards));
    }

    private void printSumOfCards(Cards cards) {
        System.out.println(" - 결과: " + cards.bestSum());
    }

    public void printScores(ScoreBoard scoreBoard) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        printDealerScore(scoreBoard);

        Map<Name, Revenue> playerScore = scoreBoard.getPlayersRevenues();
        playerScore.forEach((name, revenue) -> System.out.println(name + ": " + revenue.getAmount()));
    }

    private void printDealerScore(ScoreBoard scoreBoard) {
        Revenue dealerRevenue = scoreBoard.calculateDealerRevenue();
        System.out.println(dealerRevenue.getAmount());
    }

    public void printDealerGivenCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
