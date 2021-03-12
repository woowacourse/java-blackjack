package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.results.ResultsDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printCardsOf(Player player) {
        System.out.println(cardsOf(player));
    }

    public static void printParticipantsCards(Dealer dealer, Players players) {
        System.out.printf("\n%s와 %s에게 2장의 나누었습니다.\n", dealer.getName(), playerNames(players));
        System.out.println(halfCardsOf(dealer));
        for (Player player : players.values()) {
            System.out.println(cardsOf(player));
        }
    }

    private static String halfCardsOf(Participant participant) {
        return participant.getName() + cardsToString(participant.getState().hand().getHalfUnmodifiableList());
    }

    private static String cardsOf(Participant participant) {
        return participant.getName() + cardsToString(participant.getState().hand().getUnmodifiableList());
    }

    private static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", ", "카드: ", ""));
    }

    private static String playerNames(Players players) {
        return players.values().stream()
                .map(Participant::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(Dealer dealer, Players players) {
        System.out.println("\n" + resultOf(dealer));
        for (Player player : players.values()) {
            System.out.println(resultOf(player));
        }
    }

    public static void printProfits(ResultsDto resultsDto) {
        System.out.println("\n## 최종 수익");
        System.out.println(resultsDto.getDealerName() + ": " + resultsDto.getDealerProfit());
        List<String> playersNames = resultsDto.getPlayersNames();
        List<Double> playersProfits = resultsDto.getPlayersProfits();
        for (int i = 0; i < resultsDto.getPlayersSize(); i++) {
            System.out.println(playersNames.get(i) + ": " + playersProfits.get(i));
        }
    }

    private static String resultOf(Participant participant) {
        return cardsOf(participant) + " - 결과: " + participant.getState().hand().softSum();
    }
}
