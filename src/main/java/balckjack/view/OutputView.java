package balckjack.view;

import balckjack.domain.Participant;
import balckjack.domain.Player;
import balckjack.domain.Players;
import balckjack.domain.Referee;
import balckjack.domain.Result;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printInitCardDeck(Participant dealer, Players players) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final String names = String.join(", ", playerNames);
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", names));

        final List<String> dealerCards = dealer.getCardDeck().getCardsInfo();

        System.out.println(String.format("딜러: %s", dealerCards.get(0)));

        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index));
        }
        System.out.println();
    }

    public static void printParticipantCardDeck(Player player) {
        final String cards = String.join(", ", player.getCardDeck().getCardsInfo());
        System.out.println(player.getName().getValue() + "카드: " + cards);
    }

    public static void printParticipantCardDeck(Player player, int score) {
        final String cards = String.join(", ", player.getCardDeck().getCardsInfo());
        System.out.println(player.getName().getValue() + "카드: " + cards + " - 결과: " + score);
    }

    public static void printDealerPick() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalCardDeck(Participant dealer, Players players, Referee referee) {
        //플레이어 이름/deckInfo/총 점수
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final List<String> dealerCards = dealer.getCardDeck().getCardsInfo();
        System.out.println(
            String.format("딜러 카드: %s - 결과: %d", dealerCards.get(0), referee.calculateDeckScore(
                dealer.getCardDeck())));

        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index),
                referee.calculateDeckScore(players.getPlayers().get(index).getCardDeck()));
        }
        System.out.println();
    }

    public static void printResult(Map<String, Long> dealerResult, Players players,
        List<Result> results) {
        System.out.println("## 최종 승패");
        List<String> names = players.getPlayerNames();

        System.out.print("딜러: ");
        for (Result result : Result.values()) {
            if (dealerResult.containsKey(result.getResult())) {
                Long a = dealerResult.get(result.getResult());
                System.out.println(a + result.getResult() + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < names.size(); i++) {
            System.out.println(String.format("%s: %s", names.get(i), results.get(i).getResult()));
        }

    }

}
