package domain.user;

import domain.result.WinningResult;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    private static final int PIVOT = 17;

    private Map<WinningResult, Integer> winningResults = new LinkedHashMap<>();

    private Dealer() {
        super(DEALER);
        Arrays.stream(WinningResult.values())
                .forEach(winningResult ->
                        winningResults.put(winningResult, 0));
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    public String getFirstCard() {
        return cards.get(0).getName();
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
