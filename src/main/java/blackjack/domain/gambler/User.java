package blackjack.domain.gambler;

import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserCards;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.Outcome;

import java.util.List;
import java.util.Objects;

public abstract class User {

    private static final int DEFAULT_DRAW_COUNT = 1;
    private static final String PLAYER_NAME_IS_NULL_EXCEPTION_MESSAGE =
            "참여인원의 이름은 null이어선 안됩니다.";

    private final Name name;
    protected UserCards userCards = new UserCards();

    public User(Name name) {
        validNotBlankOrNull(name);
        this.name = name;
    }

    private void validNotBlankOrNull(Name name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException(PLAYER_NAME_IS_NULL_EXCEPTION_MESSAGE);
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

    public Outcome calculateOutcome(User user) {
        return Outcome.of(getScore(), user.getScore());
    }

    public final Name getName() {
        return name;
    }

    public final CardsResult getScore() {
        return userCards.getScore();
    }

    public final List<String> getCardsInfos() {
        return userCards.getInfos();
    }
}
