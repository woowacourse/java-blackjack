package domain.gamer;

public class Player extends Gamer {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int BUST = 0;
    private final Name name;
    private final Money money;

    public Player(Name name, Money money) {
        this.name = name;
        this.money = money;
    }

    public boolean isDrawable() {
        return cards.getScoreByAceToOne() <= MAX_BLACK_JACK_SCORE && cards.countMaxScore() != BUST;
    }

    public int getProfit(PlayerResult playerResult) {
        return money.getResultMoney(playerResult);
    }

    public Name getName() {
        return name;
    }
}
