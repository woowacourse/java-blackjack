package blackjack_statepattern;

public class Blackjack implements State{
    @Override
    public State draw(final Card card) {
        throw new IllegalArgumentException("더이상 진행할 수 없습니다.");
    }
}
