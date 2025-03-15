package blackjack.model.participant;

import blackjack.model.betting.MatchResult;
import blackjack.model.betting.BetAmount;
import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.view.BettingPlayerCreateDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final Hand hand;
    private final BetAmount betAmount;

    private Player(String name, Hand hand, BetAmount betAmount) {
        this.name = name;
        this.hand = hand;
        this.betAmount = betAmount;
    }

    public static Player of(BettingPlayerCreateDto bettingPlayerCreateDto) {
        String name = bettingPlayerCreateDto.name();
        BetAmount betAmount = new BetAmount(bettingPlayerCreateDto.stake());
        validateName(name);
        return new Player(name, new Hand(new ArrayList<>()), betAmount);
    }

    private static void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 한글자 이상이어야합니다.");
        }
    }

    public boolean canHit() {
        return !hand.isBust() && !hand.isBlackjack();
    }

    public void receiveHand(Card card) {
        hand.receiveHand(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int calculateHandTotal() {
        return hand.calculateHandTotal();
    }

    public Profit calculateProfit(MatchResult matchResult) {
        return Profit.of(betAmount, matchResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getHand());
    }
}
