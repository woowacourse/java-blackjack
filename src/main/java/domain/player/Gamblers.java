package domain.player;

import domain.deck.Deck;
import exception.BlackjackException;
import exception.ExceptionMessage;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Integer> getResult(Dealer dealer) {
        Map<String, Integer> gamblersResult = new LinkedHashMap<>();
        for (Gambler gambler : gamblers) {
            String name = gambler.getName();
            int finalIncomeMoney = gambler.calculateFinalIncome(dealer);
            gamblersResult.put(name, finalIncomeMoney);
        }
        return gamblersResult;
    }

    public int dealerFinalIncome(Dealer dealer) {
        int dealerFinalIncome = 0;
        for (Gambler gambler : gamblers) {
            dealerFinalIncome += gambler.calculateFinalIncome(dealer);
        }
        return dealerFinalIncome * -1;
    }

    public List<String> getNames() {
        return gamblers.stream().map(Gambler::getName).toList();
    }
}
