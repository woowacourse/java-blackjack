package model;

public class Player extends Participator {

    public Player(PlayerName playerName) {
        super(playerName);
    }

    public Player(String playerName) {
        super(new PlayerName(playerName));
    }

    public Result matchWith(Dealer dealer) {
        Result result = this.cards.getResult(dealer.cards);
        if (result.equals(Result.DRAW) && this.cards.isBusted()) {
            return Result.LOSE;
        }
        return result;
    }
}
