package domain.player;

import domain.MatchResult;
import domain.deck.CardDeck;
import dto.BlackjackResult;
import dto.GamblerInfoDto;
import dto.MatchResultLog;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Gamblers {

    private final List<Gambler> gamblers;

    public Gamblers(List<GamblerInfoDto> gamblerInfoDtos) {
        validateNonDuplicate(gamblerInfoDtos);

        gamblers = gamblerInfoDtos.stream()
                .map(Gambler::new)
                .toList();
    }
    private void validateNonDuplicate(List<GamblerInfoDto> gamblerInfoDtos) {
        Set<String> names = gamblerInfoDtos.stream()
                .map(GamblerInfoDto::name)
                .collect(Collectors.toSet());

        if (names.size() != gamblerInfoDtos.size()) {
            throw new BlackjackException(ExceptionMessage.NAME_DUPLICATE_ERROR);
        }
    }
    public void dealAll(CardDeck cardDeck) {
        gamblers.forEach(gambler -> gambler.deal(cardDeck));
    }

    public BlackjackResult getResult(int dealerScore) {
        List<MatchResultLog> matchResultLogs = gamblers.stream()
                .map(g -> new MatchResultLog(g.getName(), g.getResult(dealerScore)))
                .toList();

        int dealerWinCount = count(matchResultLogs, MatchResult.LOSE);
        int dealerLoseCount = count(matchResultLogs, MatchResult.WIN);
        int drawCount = matchResultLogs.size() - dealerWinCount - dealerLoseCount;

        return new BlackjackResult(dealerWinCount, dealerLoseCount, drawCount, matchResultLogs);
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

    public void forEach(Consumer<Gambler> consumer) {
        gamblers.forEach(consumer);
    }

}
