package blackjack.domain.player;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
        checkNameDealer(name);
    }

    private void checkNameDealer(String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 딜러일 수 없습니다.");
        }
    }

    @Override
    public boolean canAddCard() {
        return !isBust();
    }
}
