package domain.player;

import domain.MatchResult;
import domain.deck.CardDeck;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Gamblers {

    private final List<Gambler> gamblers;

    public Gamblers(List<Gambler> gamblers) {
        validateNonDuplicate(gamblers);
        this.gamblers = gamblers.stream().toList();
    }

    private void validateNonDuplicate(List<Gambler> gamblers) {
        Set<String> names = gamblers.stream()
                .map(Gambler::getName)
                .collect(Collectors.toSet());

        if (names.size() != gamblers.size()) {
            throw new BlackjackException(ExceptionMessage.NAME_DUPLICATE_ERROR);
        }
    }

    public void dealAll(CardDeck cardDeck) {
        gamblers.forEach(gambler -> gambler.deal(cardDeck));
    }

    public Map<String, Integer> getResult(Dealer dealer) {
        Map<String, Integer> resultMap = new LinkedHashMap<>();

        for (Gambler gambler : gamblers) {
            MatchResult matchResult = MatchResult.of(gambler, dealer);
            int reward = gambler.calculateReward(matchResult);
            resultMap.put(gambler.getName(), reward);
        }

        return resultMap;
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
