package blackjack.model;

import blackjack.model.player.Entry;

public final class Result {
    private Entry entry;
    private Boolean win;

    public Result(Entry entry, Boolean win) {
        this.entry = entry;
        this.win = win;
    }

    public Entry getEntry() {
        return entry;
    }

    public Boolean isWin() {
        return win;
    }
}
