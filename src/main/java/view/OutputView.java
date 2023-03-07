package view;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Value;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningStatus;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    private static final Map<Shape, String> shapeDisplay = new EnumMap<>(Shape.class);
    private static final Map<Value, String> valueDisplay = new EnumMap<>(Value.class);

    static {
        initializeShapeDisplay();
        initializeValueDisplay();
    }

    private static void initializeShapeDisplay() {
        shapeDisplay.put(Shape.SPADE, "스페이드");
        shapeDisplay.put(Shape.CLOVER, "클로버");
        shapeDisplay.put(Shape.DIAMOND, "클로버");
        shapeDisplay.put(Shape.HEART, "하트");
    }

    private static void initializeValueDisplay() {
        valueDisplay.put(Value.ACE, "A");
        valueDisplay.put(Value.TWO, "2");
        valueDisplay.put(Value.THREE, "3");
        valueDisplay.put(Value.FOUR, "4");
        valueDisplay.put(Value.FIVE, "5");
        valueDisplay.put(Value.SIX, "6");
        valueDisplay.put(Value.SEVEN, "7");
        valueDisplay.put(Value.EIGHT, "8");
        valueDisplay.put(Value.NINE, "9");
        valueDisplay.put(Value.TEN, "10");
        valueDisplay.put(Value.JACK, "J");
        valueDisplay.put(Value.QUEEN, "Q");
        valueDisplay.put(Value.KING, "K");
    }

    public void printInitialMessage(final List<Player> players) {
        String playerNames = players.stream().map(Participant::getName).collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), playerNames);
    }

    public void printAllState(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        Card dealerCard = dealer.getCards().get(0);
        String dealerCardDisplay = String.format("%s: %s%s", dealer.getName(), valueDisplay.get(dealerCard.getValue()),
                shapeDisplay.get(dealerCard.getShape()));
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
                .map(card -> String.format("%s%s", valueDisplay.get(card.getValue()),
                        shapeDisplay.get(card.getShape())))
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
}
