package blackjack.domain;

public class Player extends Gamer {
    private String result;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return this.calculateJudgingPoint() < 21;
    }

    public void playEachPlayer() {
        receiveCard(Deck.dealCard());
    }

    @Override
    public Boolean continueDraw(String draw) {
        return isDrawCard(draw);
    }

    private Boolean isDrawCard(String draw) {
        String pattern = "[yn]";
        if (!draw.matches(pattern)) {
            throw new IllegalArgumentException("y 혹은 n 만 입력하여 주십시오.");
        }
        return draw.equals("y");
    }

    public void matchResult(String result) {
        this.result = result;
    }

    @Override
    public String getInfo() {
        return getName() + "카드: " + getCards();
    }

    public String getResult() {
        return result;
    }
}
