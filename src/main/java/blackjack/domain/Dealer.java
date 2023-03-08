package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Dealer extends Person {
    private static final int DRAW_CARD_BOUNDARY = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canDrawCard() {
        return getScore() <= DRAW_CARD_BOUNDARY;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public List<Card> getInitCards() {
        return getCards().stream()
                .limit(1)
                .collect(toList());
    }
}
