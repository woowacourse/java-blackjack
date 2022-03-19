package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private static final int MORE_CARD_STANDARD = 16;
    private static final String NAME = "딜러";

    public Dealer(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Card> openCard() {
        return Collections.singletonList(findFirstCard(getHoldCards()));
    }

    @Override
    public boolean canHit() {
        return calculateCardsSum() <= MORE_CARD_STANDARD;
    }

    private Card findFirstCard(HoldCards holdCards) {
        return holdCards.getFirstCard()
                .orElseThrow(() -> new IllegalArgumentException("카드가 존재하지 않습니다."));
    }

    public int calculateBettingMoney(Map<Participant, Integer> bettingResult) {
        return - bettingResult.values().stream()
                .mapToInt(money -> money)
                .sum();
    }
}
