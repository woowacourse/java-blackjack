package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserCards;
import blackjack.domain.result.Score;
import java.util.List;

public abstract class User {

    private static final int DEFAULT_DRAW_COUNT = 1;
    private static final String PLAYER_NAME_IS_BLANK_EXCEPTION_MESSAGE =
        "참여인원의 이름은 공백이어선 안됩니다.";

    private final String name;
    private final Score basesScoreCanDraw;
    protected UserCards userCards = new UserCards();

    public User(String name, int basesScoreCanDraw) {
        validNotBlankOrNull(name);
        this.name = name;
        this.basesScoreCanDraw = new Score(basesScoreCanDraw);
    }

    private void validNotBlankOrNull(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_IS_BLANK_EXCEPTION_MESSAGE);
        }
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            userCards.add(cardDeck.draw());
        }
    }

    public boolean canDrawCard() {
        return basesScoreCanDraw.isEqualOrOverScore(userCards.getScore());
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return userCards.getScore();
    }

    public List<String> getCardsInfos() {
        return userCards.getInfos();
    }
}
