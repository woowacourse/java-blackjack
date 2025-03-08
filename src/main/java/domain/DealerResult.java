package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DealerResult extends Dealer implements ParticipantResult {

    private final Map<GameResult, Integer> dealerResult;

    public DealerResult() {
        this.dealerResult = new HashMap<>();
    }

    @Override
    public void add(GameResult gameResult) {
        dealerResult.put(gameResult, dealerResult.getOrDefault(gameResult, 0) + 1);
    }


    @Override
    public Map<GameResult, Integer> get() {
        return Collections.unmodifiableMap(dealerResult);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DealerResult that = (DealerResult) o;
        return Objects.equals(dealerResult, that.dealerResult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dealerResult);
    }

    @Override
    public String toString() {
        return super.getName() + ": " + result();
        /*
        return super.getName() + ": " + dealerResult.get(WIN) + "승 " + dealerResult.get(LOSE) + "패 "
            + dealerResult.get(DRAW) + "무";

         */
    }

    private String result() {
        StringBuilder stringBuilder = new StringBuilder();
        for (GameResult gameResult : GameResult.values()) {
            String koreanName = gameResult.getKoreanName();
            if (dealerResult.get(gameResult) == null) {
                continue;
            }
            stringBuilder.append(dealerResult.get(gameResult));
            stringBuilder.append(koreanName + " ");
        }
        return stringBuilder.toString();
    }
}
