package view;

import domain.Name;
import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.Score;
import domain.score.ScoreBoard;
import domain.score.Status;

import java.util.List;
import java.util.Map;

import static domain.score.Status.*;

public class OutputView {

    public void printInitialCards(DealerCards dealerCards, List<PlayerCards> playerCards) {
        List<String> names = playerCards.stream()
                .map(playerCard -> playerCard.getPlayerName().toString())
                .toList();

        String joinNames = String.join(", ", names);
        System.out.println("딜러와 " + joinNames + "에게 2장을 나누었습니다.");

        String firstCard = dealerCards.getCards().get(0);
        System.out.print("딜러: " + firstCard);

        for (PlayerCards playerCard : playerCards) {
            System.out.println();
            printPlayerCards(playerCard);
        }
    }

    public void printPlayerCards(PlayerCards cards) {
        Name playerName = cards.getPlayerName();
        System.out.print(playerName + "카드: ");
        System.out.print(formatCards(cards));
    }

    private String formatCards(Cards playerCard) {
        List<String> cards = playerCard.getCards();
        return String.join(", ", cards);
    }

    public void printResults(DealerCards dealerCards, List<PlayerCards> playerCards) {
        System.out.println();
        printDealerCards(dealerCards);
        printSumOfCards(dealerCards);
        for (PlayerCards playerCard : playerCards) {
            printPlayerCards(playerCard);
            printSumOfCards(playerCard);
        }
    }

    private void printDealerCards(DealerCards cards) {
        System.out.print("딜러 카드: ");
        System.out.print(formatCards(cards));
    }

    private void printSumOfCards(Cards cards) {
        System.out.println(" - 결과: " + cards.bestSum());
    }

    public void printScores(ScoreBoard scoreBoard) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        Score dealerScore = scoreBoard.getDealerScore();
        String winScore = dealerScore.getWinScore() + WIN.getStatus();
        String tieScore = dealerScore.getTieScore() + TIE.getStatus();
        String loseScore = dealerScore.getLoseScore() + LOSE.getStatus();

        System.out.println(String.join(" ", winScore, tieScore, loseScore));

        Map<Name, Status> playerStatus = scoreBoard.getPlayerScore();
        playerStatus.forEach((name, status) -> System.out.println(name + " : " + status.getStatus()));
    }

    public void printDealerGivenCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
