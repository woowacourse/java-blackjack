package blackjackgame.domain.gamers;

import blackjackgame.domain.blackjack.CardResult;
import blackjackgame.domain.blackjack.CardResultCalculator;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.blackjack.SummationCardPoint;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardDrawStrategy;
import blackjackgame.domain.card.Deck;
import java.util.List;

public class CardHolder {
    private final String name;
    private final HoldingCards holdingCards;

    public CardHolder(String name, HoldingCards holdingCards) {
        this.name = name;
        this.holdingCards = holdingCards;
    }

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy) {
        Card draw = deck.draw(cardDrawStrategy);
        holdingCards.add(draw);
    }

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy, int execution_count) {
        for(int count = 1; count <= execution_count; count++) {
            draw(deck, cardDrawStrategy);
        }
    }

    public SummationCardPoint getSummationCardPoint() {
        return holdingCards.calculateTotalPoint();
    }

    public String getRawName() {
        return name;
    }

    public List<Card> getRawHoldingCards() {
        return List.copyOf(holdingCards.getHoldingCards());
    }

    public int getRawSummationCardPoint() {
        return getSummationCardPoint().summationCardPoint();
    }

    public boolean isDead() {
        return getSummationCardPoint().isDeadPoint();
    }

    public CardResult getCardResult() {
        return CardResultCalculator.calculate(holdingCards);
    }
}
