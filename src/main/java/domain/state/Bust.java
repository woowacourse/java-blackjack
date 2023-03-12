package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Bust extends State{
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("[ERROR] 게임이 종료되어 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 게임이 종료되어 상태를 조작할 수 없습니다.");
    }
}
