package domain;

import controller.dto.gamer.GamerHandScore;
import controller.dto.gamer.GamerHandStatus;
import java.util.ArrayList;
import java.util.List;
import view.CardName;

public class Gamers {
    private final List<Gamer> gamers;

    public Gamers(final List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public List<Gamer> listOf() {
        return gamers;
    }

    public List<GamerHandScore> getCurrentGamerHandScore() {
        List<GamerHandStatus> gamerHandStatuses = getGamerHandStatuses();
        List<Integer> scores = getGamerResultScore();

        List<GamerHandScore> gamerHandScores = new ArrayList<>();
        for (int i = 0; i < gamerHandStatuses.size(); i++) {
            gamerHandScores.add(new GamerHandScore(gamerHandStatuses.get(i), scores.get(i)));
        }

        return gamerHandScores;
    }

    private List<Integer> getGamerResultScore() {
        List<Integer> scores = new ArrayList<>();
        for (Gamer gamer : gamers) {
            scores.add(gamer.calculateResultScore());
        }
        return scores;
    }

    public List<GamerHandStatus> getGamerHandStatuses() {
        List<GamerHandStatus> gamerHandStatuses = new ArrayList<>();

        for (Gamer gamer : gamers) {
            gamerHandStatuses.add(
                    new GamerHandStatus(gamer.getName(), CardName.getHandStatusAsString(gamer.getHand()))
            );
        }
        return gamerHandStatuses;
    }

    public List<String> getNames() {
        return gamers.stream()
                .map(Gamer::getName)
                .toList();
    }
}
