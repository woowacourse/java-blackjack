package blackjack_statepattern;

public class Bust implements State{
    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("버스트일 때 카드를 받을 수 없습니다.");
    }
}
