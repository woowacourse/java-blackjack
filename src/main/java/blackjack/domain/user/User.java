package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserCards;
import blackjack.domain.result.CardsResult;
import java.util.List;

public abstract class User {

    private static final int DEFAULT_DRAW_COUNT = 1;
    private static final String PLAYER_NAME_IS_BLANK_EXCEPTION_MESSAGE =
        "참여인원의 이름은 공백이어선 안됩니다.";

    private final String name;
    protected UserCards userCards = new UserCards();

    public User(String name) {
        validNotBlankOrNull(name);
        this.name = name.trim();
    }

    private void validNotBlankOrNull(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_IS_BLANK_EXCEPTION_MESSAGE);
        }
    }

    public final void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public final void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            userCards.add(cardDeck.draw());
        }
    }

    public abstract List<String> getFirstCardInfo();

    public abstract boolean canDrawCard();

    public final String getName() {
        return name;
    }

    public final CardsResult getScore() {
        return userCards.getScore();
    }

    public final List<String> getCardsInfos() {
        return userCards.getInfos();
    }
}
