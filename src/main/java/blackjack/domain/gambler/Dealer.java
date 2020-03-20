package blackjack.domain.gambler;

import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.GamblerCards;
import blackjack.domain.result.CardsResult;
import java.util.List;
import java.util.Objects;

public final class Dealer {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int BASES_SCORE_CAN_DRAW = 16;
    private static final int FIRST_FROM_INDEX = 0;
    private static final int FIRST_TO_INDEX = 1;
    private static final int DEFAULT_DRAW_COUNT = 1;
    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";

    private final Name name;
    private final GamblerCards gamblerCards;

    public Dealer() {
        this(new GamblerCards());
    }

    public Dealer(GamblerCards gamblerCards) {
        validateGamblerCards(gamblerCards);
        this.name = DEALER_NAME;
        this.gamblerCards = gamblerCards;
    }

    private void validateGamblerCards(GamblerCards gamblerCards) {
        if (Objects.isNull(gamblerCards)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            gamblerCards.add(cardDeck.draw());
        }
    }

    public List<String> getFirstCardInfo() {
        return gamblerCards.getInfos().subList(FIRST_FROM_INDEX, FIRST_TO_INDEX);
    }

    public boolean canDrawCard() {
        return getCardsResult().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
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
