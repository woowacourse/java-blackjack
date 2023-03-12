package view;

import domain.BlackJackGame;
import domain.card.Card;
import domain.money.Money;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningResult;
import domain.result.WinningStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.display.ShapeDisplay;
import view.display.ValueDisplay;

public final class OutputView {

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    private static final ShapeDisplay shapeDisplay = new ShapeDisplay();
    private static final ValueDisplay valueDisplay = new ValueDisplay();

    public void printInitialMessage(final List<Player> players) {
        String playerNames = players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), playerNames);
    }

    public void printAllState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        Card dealerCard = dealer.getCards().get(0);
        System.out.printf("%s: %s%s" + System.lineSeparator(),
                dealer.getName(),
                valueDisplay.findDisplayOf(dealerCard.getValue()),
                shapeDisplay.findDisplayOf(dealerCard.getShape()));

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
        System.out.printf(finalFormat, formatCardState(dealer), dealer.getScore());
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            System.out.printf(finalFormat, formatCardState(player), player.getScore());
        }
        System.out.println();
    }

    private String formatCardState(final Participant participant) {
        String cards = participant.getCards().stream()
                .map(card -> String.format("%s%s", valueDisplay.findDisplayOf(card.getValue()),
                        shapeDisplay.findDisplayOf(card.getShape())))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", participant.getName(), cards);
    }

    public void printFillDealerCards() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
    }

    public void printResult(final WinningResult winningResult) {
        printFinalResult();
        printDealerResult(winningResult.getDealerResult());
        printPlayerResult(winningResult.getPlayersResult());
    }

    private void printFinalResult() {
        System.out.println("## 최종 승패");
    }

    private void printDealerResult(final Map<WinningStatus, Integer> dealerResult) {
        StringBuilder dealerDisplay = new StringBuilder();
        dealerDisplay.append(formatCountDisplay(dealerResult));
        System.out.printf("딜러: %s" + System.lineSeparator(), dealerDisplay);
    }

    private static String formatCountDisplay(final Map<WinningStatus, Integer> dealerResult) {
        StringBuilder countDisplay = new StringBuilder();
        int winCount = dealerResult.getOrDefault(WinningStatus.WIN, 0);
        int tieCount = dealerResult.getOrDefault(WinningStatus.DRAW, 0);
        int loseCount = dealerResult.getOrDefault(WinningStatus.LOSE, 0);
        if (winCount >= 1) {
            countDisplay.append(winCount).append("승");
        }
        if (tieCount >= 1) {
            countDisplay.append(tieCount).append("무");
        }
        if (loseCount >= 1) {
            countDisplay.append(loseCount).append("패");
        }
        return countDisplay.toString();
    }

    private void printPlayerResult(final Map<Player, WinningStatus> playersResult) {
        for (Player player : playersResult.keySet()) {
            WinningStatus winningStatus = playersResult.get(player);
            System.out.printf("%s: %s" + System.lineSeparator(), player.getName(),
                    formatWinningStatusDisplay(winningStatus));
        }
    }

    private static String formatWinningStatusDisplay(final WinningStatus winningStatus) {
        if (winningStatus.equals(WinningStatus.WIN)) {
            return "승";
        }
        if (winningStatus.equals(WinningStatus.DRAW)) {
            return "무";
        }
        return "패";
    }

    public void printBets(final BlackJackGame blackJackGame) {
        System.out.println("## 최종 수익");

        Dealer dealer = blackJackGame.getDealer();
        Money dealerProfit = blackJackGame.dealerProfit();
        System.out.printf("%s: %d" + System.lineSeparator(),
                dealer.getName(),
                dealerProfit.getAmount());

        Map<Player, Money> finalBets = blackJackGame.playersProfit();
        for (Player player : blackJackGame.getPlayers()) {
            Money playerBet = finalBets.get(player);
            System.out.printf("%s: %d" + System.lineSeparator(),
                    player.getName(),
                    playerBet.getAmount());
        }
    }
}
