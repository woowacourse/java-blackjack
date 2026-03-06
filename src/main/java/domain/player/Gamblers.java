package domain.player;

import domain.MatchResult;
import domain.deck.CardDeck;
import dto.BlackjackResult;
import dto.CardInfo;
import dto.GamblerResultLog;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Gamblers {
    private final List<Gambler> gamblers;

    public Gamblers(List<String> names) {
        validateNonDuplicate(names);

        gamblers = names.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
    }

    private void validateNonDuplicate(List<String> names) {
        Set<String> namesSet = new HashSet<>(names);
        if (namesSet.size() != names.size()) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public void dealAll(CardDeck cardDeck) {
        gamblers.forEach(gambler -> gambler.deal(cardDeck));
    }

    public BlackjackResult getResult(int dealerScore) {
        List<GamblerResultLog> logs = gamblers.stream()
                .map(g -> new GamblerResultLog(g.getName(), g.getResult(dealerScore)))
                .toList();

        int winCount = count(logs, MatchResult.LOSE);
        int loseCount = count(logs, MatchResult.WIN);
        int drawCount = logs.size() - winCount - loseCount;

        return new BlackjackResult(winCount, loseCount, drawCount, logs);
    }

    private int count(List<GamblerResultLog> logs, MatchResult result) {
        return (int) logs.stream()
                .filter(log -> log.matchResult() == result)
                .count();
    }

    public List<String> getNames() {
        return gamblers.stream()
                .map(Gambler::getName)
                .toList();
    }

    public List<CardInfo> gamblerCardInfos() {
        return gamblers.stream()
                .map(Gambler::getCardInfo)
                .toList();
    }
}
