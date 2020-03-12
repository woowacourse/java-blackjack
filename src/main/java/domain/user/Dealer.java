package domain.user;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import domain.WinningResult;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    private static final int PIVOT = 17;

    private static Map<WinningResult, Integer> winningResults;

    static {
        winningResults = new LinkedHashMap<>();

        Arrays.stream(WinningResult.values())
                .forEach(winningResult ->
                        winningResults.put(winningResult, 0));
    }

    private Dealer() {
        super(DEALER);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    public String getFirstDrawResult() {
        return DEALER + "카드: " + cards.get(0).getName();
    }

    @Override
    public boolean isAvailableToDraw() {
        return !isBust() && !isBlackJack() && !isBlackJackPoint() && calculatePointAccordingToHasAce() < PIVOT;
    }

    public void applyWinningResult(WinningResult winningResult) {
        winningResults.computeIfPresent(winningResult, (key, value) -> value + 1);
    }

    @Override
    public String getTotalWinningResult() {
        return DEALER + ": "
                + winningResults.entrySet()
                .stream()
                .map(winningResult -> winningResult.getValue() + winningResult.getKey().getResult())
                .collect(Collectors.joining(" "));
    }
}
