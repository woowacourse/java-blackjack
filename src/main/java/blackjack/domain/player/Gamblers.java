package domain.player;

import domain.MatchResult;
import domain.deck.CardDeck;
import dto.BlackjackResult;
import dto.MatchResultLog;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Gamblers {

    private final List<domain.player.Gambler> gamblers;

    public Gamblers(List<String> names) {
        validateNonDuplicate(names);

        gamblers = names.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
    }

    private void validateNonDuplicate(List<String> names) {
        Set<String> namesSet = new HashSet<>(names);
        if (namesSet.size() != names.size()) {
            throw new BlackjackException(ExceptionMessage.NAME_DUPLICATE_ERROR);
        }
    }

    public void dealAll(CardDeck cardDeck) {
        gamblers.forEach(gambler -> gambler.deal(cardDeck));
    }

    public BlackjackResult getResult(int dealerScore) {
        List<MatchResultLog> logs = gamblers.stream()
                .map(g -> new MatchResultLog(g.getName(), g.getResult(dealerScore)))
                .toList();

        int dealerWinCount = count(logs, MatchResult.LOSE);
        int dealerLoseCount = count(logs, MatchResult.WIN);
        int drawCount = logs.size() - dealerWinCount - dealerLoseCount;

        return new BlackjackResult(dealerWinCount, dealerLoseCount, drawCount, logs);
    }

    private int count(List<MatchResultLog> logs, MatchResult result) {
        return (int) logs.stream()
                .filter(log -> log.matchResult() == result)
                .count();
    }

    public List<String> getNames() {
        return gamblers.stream()
                .map(Gambler::getName)
                .toList();
    }

    public List<domain.player.Gambler> getGamblers() {
        return List.copyOf(gamblers);
    }

    public void forEach(Consumer<domain.player.Gambler> consumer) {
        gamblers.forEach(consumer);
    }

}
