package domain.player;

import domain.betting.BettingAmount;
import domain.card.GameCards;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gamblers {

    private List<Gambler> gamblers;

    public Gamblers(Map<String, BettingAmount> gamblerNameAndBettingInfo) {
        this.gamblers = gamblerNameAndBettingInfo.entrySet()
                .stream()
                .map(entry -> new Gambler(entry.getKey(), entry.getValue()))
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
        return gamblers;
    }

    public int getGamblersSize() {
        return gamblers.size();
    }
}
