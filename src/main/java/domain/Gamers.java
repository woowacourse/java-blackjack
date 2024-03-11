package domain;

import java.util.List;

public class Gamers {
    private final List<Gamer> gamers;

    public Gamers(final List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public List<Gamer> listOf() {
        return gamers;
    }

    public List<String> getGamerNames() {
        return gamers.stream()
                .map(Gamer::getName)
                .toList();
    }

    public Gamer getGamerByName(final String name) {
        return gamers.stream()
                .filter(gamer -> gamer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));
    }
}
