package io;

import bet.BetCenter;
import game.Card;
import participant.Participants;
import participant.Player;

import java.util.List;
import java.util.Map;

public class ConsoleOutput {

    public void printInitialGameSettings(Participants participants) {
        String joinedPlayers = String.join(", ", participants.getPlayerNames());
        System.out.println("\n딜러와 " + joinedPlayers + "에게 2장을 나누었습니다.");

        System.out.println("딜러카드: " + processCardInfo(participants.openOneCardWithDealer()));
        for (Player player : participants.getPlayers()) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()));
    }

    public void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printGameResults(Participants participants) {
        System.out.println("\n딜러카드: " + processCardsInfo(participants.openCardsWithDealer()) +
                " - 결과: " + participants.sumScoreWithDealer());
        for (Player player : participants.getPlayers()) {
            System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()) + " - 결과: "
                    + player.sumCardNumbers());
        }
    }

    private String processCardsInfo(List<Card> cards) {
        return String.join(", ", cards.stream()
                .map(this::processCardInfo)
                .toList());
    }

    private String processCardInfo(Card card) {
        return card.getNumber().getName() + card.getEmblem().getName();
    }

    public void printFinalProfit(BetCenter betCenter, Participants participants) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + betCenter.calculateDealerProfit(participants.getDealer()));
        Map<Player, Integer> deriveBetResults = betCenter.deriveBettingResults(participants.getDealer());
        for (Player player : deriveBetResults.keySet()) {
            System.out.println(player.getNickname() + ": " + deriveBetResults.get(player));
        }
    }
}
