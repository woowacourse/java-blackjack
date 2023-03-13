package domain.card;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.result.Score;

public class Card {

    private final String name;
    private final int value;

    public Card(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public boolean isAce() {
        return name.contains("A");
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Score comparePlayerDealer(Player player, Dealer dealer) {
        if (player.isBust() || player.getScoreSum() - dealer.getScoreSum() < 0) {
            return Score.LOSE;
        }
        if (dealer.isBust() || player.getScoreSum() - dealer.getScoreSum() > 0) {
            return Score.WIN;
        }
        return Score.DRAW;
    }
}
