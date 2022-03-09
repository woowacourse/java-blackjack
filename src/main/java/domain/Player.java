package domain;

import java.util.List;

public class Player extends Human {

    private final String GIVEN_SYMBOL = "y";
    private final String NOT_GIVEN_SYMBOL = "n";

    Cards cards = new Cards();

    public Player(List<Card> initCards) {
        cards.add(initCards);
    }

    public boolean isReceived(final String givenFlag) {
        if (givenFlag.equalsIgnoreCase(GIVEN_SYMBOL)) {
            return true;
        }
        if (givenFlag.equalsIgnoreCase(NOT_GIVEN_SYMBOL)) {
            return false;
        }
        throw new IllegalArgumentException("y, n 중에서 입력해주세요.");
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }


}
