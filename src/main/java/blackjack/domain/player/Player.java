package blackjack.domain.player;

import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.GamblerCards;
import blackjack.domain.result.CardsResult;
import java.util.List;

public abstract class Player {

    private static final int DEFAULT_DRAW_COUNT = 1;

    private final Name name;
    private final GamblerCards gamblerCards;

    protected Player(Name name, GamblerCards gamblerCards) {
        this.name = name;
        this.gamblerCards = gamblerCards;
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            gamblerCards.add(cardDeck.draw());
        }
    }

    public Name getName() {
        return name;
    }

    public CardsResult getCardsResult() {
        return gamblerCards.getResult();
    }

    public List<String> getCardsInfos() {
        return gamblerCards.getInfos();
    }
}
