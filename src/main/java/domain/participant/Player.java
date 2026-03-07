package domain.participant;

import domain.MatchResult;

import java.util.Map;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public void compareWithDealer(Dealer dealer, Map<String, MatchResult> playersResult) {
        if (isBustResult(dealer, playersResult)) return;
        if (isHigherScoreThanDealer(dealer, playersResult)) return;
        if (isDrawResult(dealer, playersResult)) return;

        playersResult.put(getName(), MatchResult.LOSE);
    }

    private boolean isBustResult(Dealer dealer, Map<String, MatchResult> playersResult) {
        if (hand.isBust()) {
            playersResult.put(name, MatchResult.LOSE);
            return true;
        }

        if (dealer.isBust()) {
            playersResult.put(name, MatchResult.WIN);
            return true;
        }

        return false;
    }

    private boolean isHigherScoreThanDealer(Dealer dealer, Map<String, MatchResult> playersResult) {
        if (hand.calculateScore() > dealer.getScore()) {
            playersResult.put(name, MatchResult.WIN);
            return true;
        }

        return false;
    }

    private boolean isDrawResult(Dealer dealer, Map<String, MatchResult> playersResult) {
        if (hand.calculateScore() == dealer.getScore()) {
            return isDrawWithBlackJack(dealer, playersResult);
        }

        return false;
    }

    private boolean isDrawWithBlackJack(Dealer dealer, Map<String, MatchResult> playersResult) {
        if (hand.isBlackJack() && !dealer.isBlackJack()) {
            playersResult.put(name, MatchResult.WIN);
            return true;
        }
        if (!hand.isBlackJack() && dealer.isBlackJack()) {
            playersResult.put(name, MatchResult.LOSE);
            return true;
        }
        if (!hand.isBlackJack() && !dealer.isBlackJack()) {
            playersResult.put(name, MatchResult.DRAW);
            return true;
        }

        return false;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > 8) {
            throw new IllegalArgumentException("플레이어 이름은 1자 이상 8자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
