package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitDraw(Players players, Dealer dealer) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n",
                String.join(", ", players.getNames()));

        List<Card> dealerCards = dealer.getCards();

        System.out.printf("딜러카드: %s%n", dealerCards.getFirst().getName());

        //플레이어 카드 출력
        for (Player player : players.getPlayers()) {
            String displayPlayerCards = displayPlayerCards(player.getCards());
            System.out.printf("%s카드: %s%n", player.getName(), displayPlayerCards);
        }

    }

    private static String displayPlayerCards(List<Card> playerCards) {
        return playerCards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), displayPlayerCards(player.getCards()));
    }

    public void printDealerDraw() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardResult(Dealer dealer, Players players) {
        System.out.printf("%n딜러카드: %s - 결과: %d%n",
                String.join(", ", displayPlayerCards(dealer.getCards())), dealer.getTotalPoint());
        for (Player player : players.getPlayers()) {
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), displayPlayerCards(player.getCards()),
                    player.getTotalPoint());
        }


    }

    public void printFinalGameResult(HashMap<Player, GameResult> result) {
        System.out.println("\n## 최종 승패");

        int winCount = 0;
        int tieCount = 0;
        int loseCount = 0;
        for (Map.Entry<Player, GameResult> entry : result.entrySet()) {
            if (entry.getValue() == GameResult.WIN) {
                loseCount++;
            }
            if (entry.getValue() == GameResult.LOSE) {
                winCount++;
            }
            if (entry.getValue() == GameResult.TIE) {
                tieCount++;
            }
        }
        System.out.printf("딜러: %d승 %d무 %d패%n", winCount, tieCount, loseCount);
        for (Map.Entry<Player, GameResult> entry : result.entrySet()) {
            System.out.printf("%s: %s%n", entry.getKey().getName(), entry.getValue().getName());
        }
    }

}
