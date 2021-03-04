package blackjack.domain;

public class Player extends Gamer{
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return this.calculateJudgingPoint() < 21;
    }

    public Boolean continueDraw(Deck deck) {
        System.out.println(getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String draw = InputView.isContinueDraw();
        if (isDrawCard(draw)) {
            receiveCard(deck.dealCard());
            System.out.println(getInfo());
            return true;
        }
        return false;
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
