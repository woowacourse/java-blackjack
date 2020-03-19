package blackjack.domain.gambler;

import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserCards;
import blackjack.domain.result.CardsResult;
import java.util.List;

public final class Dealer implements Gambler {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int BASES_SCORE_CAN_DRAW = 16;
    private static final int FIRST_FROM_INDEX = 0;
    private static final int FIRST_TO_INDEX = 1;
    private static final int DEFAULT_DRAW_COUNT = 1;

    private final Name name;
    private UserCards userCards = new UserCards();

    public Dealer() {
        this.name = DEALER_NAME;
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            userCards.add(cardDeck.draw());
        }
    }

    public List<String> getFirstCardInfo() {
        return userCards.getInfos().subList(FIRST_FROM_INDEX, FIRST_TO_INDEX);
    }

    @Override
    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }

    public Name getName() {
        return name;
    }

    public CardsResult getScore() {
        return userCards.getScore();
    }

    public List<String> getCardsInfos() {
        return userCards.getInfos();
    }
}
