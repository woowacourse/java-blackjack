package blackjack.model.player;

public class Record {

    private final Gamer gamer;
    private final Result result;

    public Record(Gamer gamer, Result result) {
        this.gamer = gamer;
        this.result = result;
    }

    public String name() {
        return gamer.name();
    }

    public Money profit() {
        return result.profit(gamer.bettingMoney());
    }

    public Result status() {
        return result;
    }
}
