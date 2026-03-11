package blackjack.view;

import blackjack.dto.GameResultDto;
import blackjack.model.*;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printFirstCardStatus(Dealer dealer, Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName).toList();
        System.out.println("\n딜러와 " + String.join(", ", playerNames) + "에게 " + "2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealer.FirstCardOpen().getCardName());
        for (Player player : players.getPlayers()) {
            printPlayerCardStatus(player, player.getHandCards());
        }
        System.out.println();
    }

    public void printPlayerCardStatus(Player player, List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(Card::getCardName)
                .toList();
        System.out.println(player.getName() + "카드: " + String.join(", ", cardNames));
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

    private void printParticipantGameResult(Participant participant, String name) {
        List<String> cardNames = participant.getHandCards().stream()
                .map(Card::getCardName).toList();
        System.out.println(name + "카드: " +
                String.join(", ", cardNames) +
                " - 결과: " + participant.getScore().getScore());
    }

    public void printGameResult(GameResultDto gameResultDto, double dealerAmount) {
        Map<Player, GameResult> gameResult = gameResultDto.getGameResult();
        System.out.println("\n## 최종 수익");
        System.out.println("딜러 : " + (int) dealerAmount);
        for (Player player : gameResultDto.getGameResult().keySet()) {
            System.out.println(player.getName() + ": " + (int) player.getBettingAmount(gameResult.get(player)));
        }
    }
}
