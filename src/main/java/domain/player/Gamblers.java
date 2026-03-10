package domain.player;

import domain.card.GameCards;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gamblers {

    private List<Gambler> gamblers;

    public Gamblers(List<String> names) {
        validateDuplicateNames(names);
        this.gamblers = names.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
    }

    public boolean containGambler(String name) {
        return gamblers.stream()
                .anyMatch(gambler -> gambler.isEqualName(name));
    }

    public Map<String, List<String>> getHandsInfo() {
        return gamblers.stream()
                .collect(Collectors.toMap(Gambler::getName,
                        Gambler::getHandInfo,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Map<String, Integer> getParticipantTotalScore() {
        return gamblers.stream()
                .collect(Collectors.toMap(Gambler::getName, Gambler::getTotalScore));
    }

    public void receiveCards(GameCards gameCards) {
        for (Gambler gambler : gamblers) {
            gambler.addCard(gameCards.drawCard());
        }
    }

    public List<ParticipantGameInfo> getParticipantGameInfos() {
        return gamblers.stream()
                .map(Gambler::getParticipantGameInfo)
                .toList();
    }

    public List<Integer> getHandSize() {
        return gamblers.stream()
                .map(Gambler::getCardSize)
                .toList();
    }

    public List<Gambler> getGamblers() {
        return List.copyOf(gamblers);
    }

    public void validateDuplicateNames(List<String> names) {
        List<String> distinctNamesCount = names.stream().
                distinct().collect(Collectors.toList());
        if(distinctNamesCount.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 입력됩니다.");
        }
    }
}
