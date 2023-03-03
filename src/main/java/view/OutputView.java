package view;

import domain.WinningStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WIN_KEYWORD = "승";
    private static final String TIE_KEYWORD = "무";
    private static final String LOSE_KEYWORD = "패";
    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printInitialState(Participants participants) {
        printInitialMessage(participants.getPlayerNames());
        printAllState(participants);
    }

    private void printInitialMessage(final List<String> names) {
        String dealerName = names.get(0);
        String playerNames = names.stream().skip(1).collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장을 나누었습니다." + System.lineSeparator(), dealerName, playerNames);
    }

    private void printAllState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printInitialDealerState(dealer);
        for (Player player : players) {
            printSingleState(player);
        }
    }

    private void printInitialDealerState(Dealer dealer) {
        Card dealerCard = dealer.getCards().get(0);
        String dealerCardDisplay = String.format("%s: %s%s", dealer.getName(), dealerCard.getValue(),
                dealerCard.getShape());
        System.out.println(dealerCardDisplay);
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
    }

    private String formatCardState(final Participant participant) {
        String cards = participant.getCards().stream()
                .map(playerCard -> String.format("%s%s", playerCard.getValue(), playerCard.getShape()))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", participant.getName(), cards);
    }

    public void printFillDealerCards() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalResultMessage() {
        System.out.println("## 최종 승패");
    }

    public void printDealerResult(final Map<WinningStatus, Integer> dealerResult) {
        StringBuilder dealerDisplay = new StringBuilder();
        calculateWinCount(dealerResult, dealerDisplay);
        calculateTieCount(dealerResult, dealerDisplay);
        calculateLoseCount(dealerResult, dealerDisplay);
        System.out.printf("딜러: %s" + System.lineSeparator(), dealerDisplay);
    }

    private void calculateWinCount(Map<WinningStatus, Integer> dealerResult, StringBuilder dealerDisplay) {
        int winCount = dealerResult.getOrDefault(WinningStatus.WIN, 0);
        if (winCount >= 1) {
            dealerDisplay.append(winCount).append(WIN_KEYWORD);
        }
    }

    private void calculateTieCount(Map<WinningStatus, Integer> dealerResult, StringBuilder dealerDisplay) {
        int tieCount = dealerResult.getOrDefault(WinningStatus.TIE, 0);
        if (tieCount >= 1) {
            dealerDisplay.append(tieCount).append(TIE_KEYWORD);
        }
    }

    private void calculateLoseCount(Map<WinningStatus, Integer> dealerResult, StringBuilder dealerDisplay) {
        int loseCount = dealerResult.getOrDefault(WinningStatus.LOSE, 0);
        if (loseCount >= 1) {
            dealerDisplay.append(loseCount).append(LOSE_KEYWORD);
        }
    }

    public void printPlayerResult(final Map<Player, WinningStatus> playersResult) {
        for (Player player : playersResult.keySet()) {
            WinningStatus winningStatus = playersResult.get(player);
            System.out.printf("%s: %s" + System.lineSeparator(), player.getName(), printWinningStatus(winningStatus));
        }
    }

    private String printWinningStatus(WinningStatus winningStatus) {
        if (winningStatus.equals(WinningStatus.WIN)) {
            return WIN_KEYWORD;
        }
        if (winningStatus.equals(WinningStatus.TIE)) {
            return TIE_KEYWORD;
        }
        return LOSE_KEYWORD;
    }
}
