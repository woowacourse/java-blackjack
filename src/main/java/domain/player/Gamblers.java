package domain.player;

import domain.card.GameCards;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Gamblers {

    private List<Gambler> gamblers;

    public Gamblers(List<Gambler> gamblers) {
        validateNameDuplication(gamblers);
        this.gamblers = gamblers;
    }

    private void validateNameDuplication(List<Gambler> gamblers) {
        Set<String> names = new HashSet<>();
        for (Gambler gambler : gamblers) {
            names.add(gambler.getName());
        }
        if (names.size() != gamblers.size()) {
            throw new IllegalArgumentException("중복된 이름이 입력됩니다.");
        }
    }

    public Map<String, List<String>> getHandsInfo() {
        return gamblers.stream()
                .collect(Collectors.toMap(Gambler::getName,
                        Gambler::getHandInfo,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
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
        return gamblers;
    }

    public int getGamblersSize() {
        return gamblers.size();
    }

    public void receiveInitialCards(GameCards gameCards) {
        for (Gambler gambler : gamblers) {
            gambler.receiveInitialCards(gameCards);
        }
    }
}
