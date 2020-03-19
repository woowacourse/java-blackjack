package domain.user;

import common.DealerDto;
import common.PlayerDto;
import domain.card.Cards;
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.List;

public class Player extends User {
    private final Money bettingMoney;

    public Player(PlayingCards playingCards, String name, Money bettingMoney) {
        super(playingCards, name);
        this.bettingMoney = bettingMoney;
    }

    public Money calculateProfit(Result result) {
        if (playerLose(result)) {
            return result.calculateProfit(bettingMoney).multiply(LOSE_PENALTY_RATE);
        }
        return result.calculateProfit(bettingMoney);
    }

    private boolean playerLose(Result result) {
        return result.equals(Result.DEALER_WIN);
    }

    public boolean wantToHit(String value) {
        WantToHit wantToHit = WantToHit.findByValue(value);
        return wantToHit.equals(WantToHit.YES);
    }

    void confirmCards(Cards cards) {
        this.playingCards.add(cards);
    }

    public PlayerDto serialize() {
        List<String> cards = playingCards.serialize();
        return PlayerDto.update(name, bettingMoney.serialize(), cards);
    }

    public PlayerDto serialize(Result result) {
        List<String> cards = playingCards.serialize();
        int score = calculateScore();
        Money profit = calculateProfit(result);
        return PlayerDto.complete(name, bettingMoney.serialize(), cards, score, profit.serialize());
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public Result match(User user, MatchRule matchRule) {
        return Result.judge(matchRule, this, user);
    }
}
