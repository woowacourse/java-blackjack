package domain.user;

import common.PlayerDto;
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.List;

public class Player extends User {
    private final Money bettingMoney;

    private Player(String name, Money bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    Money getBettingMoney() {
        return bettingMoney;
    }

    private Player(String name, PlayingCards playingCards, Money bettingMoney) {
        super(name, playingCards);
        this.bettingMoney = bettingMoney;
    }

    private Player(Player player, PlayingCards playingCards) {
        this(player.getName(), playingCards, player.bettingMoney);
    }

    public static Player join(PlayerDto playerDto) {
        int bettingMoney = playerDto.getBettingMoney();
        return new Player(playerDto.getName(), Money.of(bettingMoney));
    }

    public Player receiveInitCards(PlayingCards playingCards) {
        return new Player(this, playingCards);
    }

    static Player of(String name, PlayingCards playingCards, Money bettingMoney) {
        return new Player(name, playingCards, bettingMoney);
    }

    void calculateProfit(Result result) {
        if (playerLose(result)) {
            profit = result.calculateProfit(bettingMoney).multiply(LOSE_PENALTY_RATE);
        }
        profit = result.calculateProfit(bettingMoney);
    }

    private boolean playerLose(Result result) {
        return result.equals(Result.DEALER_WIN);
    }

    public boolean wantToHit(String value) {
        WantToHit wantToHit = WantToHit.findByValue(value);
        return wantToHit.equals(WantToHit.YES);
    }

    public PlayerDto serialize(PlayerDto playerDto) {
        playerDto.setName(name);
        playerDto.setBettingMoney(bettingMoney.serialize());
        playerDto.setCards(playingCards.serialize());
        playerDto.setScore(calculateScore());
        return playerDto;
    }

    @Override
    public Result match(User user, MatchRule matchRule) {
        return Result.judge(matchRule, this, user);
    }
}
