package blackjack.domain.person;

import blackjack.domain.card.Card;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Dealer extends Person {
    private static final int DRAW_CARD_BOUNDARY = 16;

    public Dealer() {
        super(new Name("딜러"));
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
