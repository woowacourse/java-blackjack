package domain.user;

import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

public class Player extends User {
    private final Money bettingMoney;

    private Player(String name, Money bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    private Player(String name, PlayingCards playingCards, Money bettingMoney) {
        super(name, playingCards);
        this.bettingMoney = bettingMoney;
    }

    private Player(Player player, PlayingCards playingCards) {
        this(player.getName(), playingCards, player.bettingMoney);
    }

    static Player of(String name, PlayingCards playingCards, Money bettingMoney) {
        return new Player(name, playingCards, bettingMoney);
    }

    static Player join(String name, int bettingMoney) {
        return new Player(name, Money.of(bettingMoney));
    }

    @Override
    public Result match(User user, MatchRule matchRule) {
        return Result.judge(matchRule, this, user);
    }

    public String getName() {
        return name;
    }

    public int getBettingBoney() {
        return bettingMoney.serialize();
    }

    public PlayingCards getCards() {
        return playingCards;
    }

    public int getScore() {
        return calculateScore();
    }

    Player receiveInitCards(PlayingCards playingCards) {
        return new Player(this, playingCards);
    }

    boolean wantToHit(String value) {
        WantToHit wantToHit = WantToHit.findByValue(value);
        return wantToHit.equals(WantToHit.YES);
    }

    void calculateProfit(Result result) {
        if (playerLose(result)) {
            profit = result.calculateProfit(bettingMoney).multiply(LOSE_PENALTY_RATE);
        } else {
            profit = result.calculateProfit(bettingMoney);
        }

    }

    Money getBettingMoney() {
        return bettingMoney;
    }

    private boolean playerLose(Result result) {
        return result.equals(Result.DEALER_WIN);
    }
}
