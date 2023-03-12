package blackjack.domain.participant;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    private static final int DRAW_CARD_BOUNDARY = 16;
    private static final int DEALER_START_SHOW_COUNT = 1;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean canDrawCard() {
        return getScore().getScore() <= DRAW_CARD_BOUNDARY;
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
    public List<Card> getStartCards() {
        return getCards().stream()
                .limit(DEALER_START_SHOW_COUNT)
                .collect(toList());
    }
}
