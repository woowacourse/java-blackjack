package blackjack.view;

import blackjack.domain.*;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPlayerCardStatus(Player player, List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(Card::getCardName)
                .toList();
        System.out.println(player.getName() + "카드: " + String.join(", ", cardNames));
    }

    public void printFirstCardStatus(Dealer dealer, Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName).toList();
        System.out.println("\n딜러와 " + String.join(", ", playerNames) + "에게 " + "2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealer.getInitialCards());
        for (Player player : players.getPlayers()) {
            printPlayerCardStatus(player, player.getCards());
        }
        System.out.println();
    }

    public void printDealerReceiveCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResult(Dealer dealer, Players players) {
        printParticipantGameResult(dealer, "\n딜러");
        for (Player player : players.getPlayers()) {
            printParticipantGameResult(player, player.getName());
        }
    }

    public void printBurst(String name) {
        System.out.println(name + "의 점수가 버스트 되었습니다.");
    }

    public void printGameResultProfit(GameResults gameResults) {
        Map<Player, Integer> playersProfit = gameResults.getPlayersProfit();

        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + gameResults.getDealerProfit());
        for (Player player : playersProfit.keySet()) {
            System.out.println(player.getName() + ": " + playersProfit.get(player));
        }
    }

    private void printParticipantGameResult(Participant participant, String name) {
        List<String> cardNames = participant.getCards().stream()
                .map(Card::getCardName).toList();
        System.out.println(name + "카드: " +
                String.join(", ", cardNames) +
                " - 결과: " + participant.getScore());
    }
}
