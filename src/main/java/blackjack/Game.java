package blackjack;

import java.util.List;

public class Game {
    private final Players players;
    private final TrumpCardPack trumpCardPack;

    public Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = Players.from(names);
    }

    public void start() {
        this.players.giveFirstCards(trumpCardPack);
    }

    public List<String> getEntryNames() {
        return this.players.getEntryNames();
    }

    public List<String> getNames() {
        return this.players.getNames();
    }

    public List<List<String>> getDecksToString() {
        return this.players.getCardsToString();
    }

    public boolean hasNextEntry() {
        return this.players.hasNextEntry();
    }

    public void toNextEntry() {
        this.players.toNextEntry();
    }

    public String getCurrentEntryName() {
        return this.players.getCurrentEntryName();
    }
}
