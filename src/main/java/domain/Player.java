package domain;

public class Player extends Participant {

    public static final int STANDARD_SUM_OF_BLACKJACK = 21;

    public Player(Name name, Cards cards, Money money) {
        super(name, cards, money);
    }

    @Override
    public boolean canReceiveOneMoreCard() {
        return cards.calculateSum() < STANDARD_SUM_OF_BLACKJACK;
    }

    //
//    public static final int MAX_NAME_LENGTH = 5;
//
//    public Player(String name, Cards cards) {
//        super(name, cards);
//        validateNameLength(name);
//    }
//
//    private void validateNameLength(String name) {
//        if (name.length() > MAX_NAME_LENGTH) {
//            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
//        }
//    }
//
//    @Override
//    public boolean canAddCard() {
//        return cards.getScore() < Cards.BLACKJACK_NUMBER;
//    }
}
