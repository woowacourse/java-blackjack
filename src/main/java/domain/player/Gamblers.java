package domain.player;

import domain.MatchResult;
import domain.deck.Deck;
import dto.BlackjackResult;
import exception.BlackjackException;
import exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gamblers {
    private final List<Gambler> gamblers;

    public Gamblers(List<Gambler> gamblers) {
        validateNonDuplicateNames(gamblers);
        this.gamblers = gamblers;
    }

    private void validateNonDuplicateNames(List<Gambler> gamblers) {
        List<String> names = gamblers.stream()
                .map(Gambler::getName)
                .toList();

        Set<String> targetNames = new HashSet<>(names);
        if (targetNames.size() != names.size()) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public void dealAll(Deck deck) {
        gamblers.forEach(gambler -> gambler.deal(deck));
    }

    public BlackjackResult getResult(Dealer dealer) {
        int winCount = 0;
        int loseCount = 0;
        List<String> logs = new ArrayList<>();
        for (Gambler gambler : gamblers) {
            MatchResult result = gambler.getResult(dealer);
            if (result == MatchResult.WIN) {
                loseCount++;
            }
            if (result == MatchResult.LOSE) {
                winCount++;
            }
            logs.add(gambler.showResult(result));
        }
        return new BlackjackResult(winCount, loseCount, gamblers.size() - winCount - loseCount, logs);
    }

    public List<String> getNames() {
        return gamblers.stream().map(Gambler::getName).toList();
    }
}
