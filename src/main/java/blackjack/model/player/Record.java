package blackjack.model.player;

public class Record {

    private final Gamer gamer;
    private final Result status;

    public Record(Gamer gamer, Result status) {
        this.gamer = gamer;
        this.status = status;
    }

    public String name() {
        return gamer.name();
    }

    public Money profit() {
        return status.profit(gamer.bettingMoney());
    }

    public Result status() {
        return status;
    }
}
