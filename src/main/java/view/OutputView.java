package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printInitialMessage(final List<Player> players) {
        String playerNames = players.stream().map(Participant::getName).collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), playerNames);
    }

    public void printAllState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        Card dealerCard = dealer.getCards().get(0);
        String dealerCardDisplay = String.format("%s: %s%s", dealer.getName(), dealerCard.getValue(),
                dealerCard.getShape());
        System.out.println(dealerCardDisplay);

        for (Player player : players) {
            printSingleState(player);
        }
        System.out.println();
    }

    public void printSingleState(final Participant participant) {
        System.out.println(formatCardState(participant));
    }

    public void printFinalState(final Participants participants) {
        String finalFormat = "%s - 결과: %d" + System.lineSeparator();
        Dealer dealer = participants.getDealer();
        System.out.printf(finalFormat, formatCardState(dealer), dealer.calculateScore());
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            System.out.printf(finalFormat, formatCardState(player), player.calculateScore());
        }
        System.out.println();
    }

    private String formatCardState(final Participant participant) {
        String cards = participant.getCards().stream()
                .map(playerCard -> String.format("%s%s", playerCard.getValue(), playerCard.getShape()))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", participant.getName(), cards);
    }

    public void printFillDealerCards() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
    }

    public void printFinalResult() {
        System.out.println("## 최종 승패");
    }

    public void printDealerResult(final Map<WinningStatus, Integer> dealerResult) {
        StringBuilder dealerDisplay = new StringBuilder();
        int winCount = dealerResult.getOrDefault(WinningStatus.WIN, 0);
        int tieCount = dealerResult.getOrDefault(WinningStatus.DRAW, 0);
        int loseCount = dealerResult.getOrDefault(WinningStatus.LOSE, 0);
        if (winCount >= 1) {
            dealerDisplay.append(winCount).append("승");
        }
        if (tieCount >= 1) {
            dealerDisplay.append(tieCount).append("무");
        }
        if (loseCount >= 1) {
            dealerDisplay.append(loseCount).append("패");
        }
        System.out.printf("딜러: %s" + System.lineSeparator(), dealerDisplay);
    }

    public void printPlayerResult(final Map<Player, WinningStatus> playersResult) {
        for (Player player : playersResult.keySet()) {
            String playerDisplay = "";
            WinningStatus winningStatus = playersResult.get(player);
            if (winningStatus.equals(WinningStatus.WIN)) {
                playerDisplay = "승";
            }
            if (winningStatus.equals(WinningStatus.DRAW)) {
                playerDisplay = "무";
            }
            if (winningStatus.equals(WinningStatus.LOSE)) {
                playerDisplay = "패";
            }
            System.out.printf("%s: %s" + System.lineSeparator(), player.getName(), playerDisplay);
        }
    }
}
