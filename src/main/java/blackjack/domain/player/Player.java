package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.Drawable;
import blackjack.domain.score.Score;

import java.util.Collections;
import java.util.List;

public abstract class Player {
    protected final PlayerInfo playerInfo;
    protected final CardBundle cardBundle;

    public Player(CardBundle cardBundle, PlayerInfo playerInfo) {
        validate(cardBundle, playerInfo);
        this.cardBundle = cardBundle;
        this.playerInfo = playerInfo;
    }

    private void validate(CardBundle cardBundle, PlayerInfo playerInfo) {
        if (cardBundle == null) {
            throw new IllegalArgumentException("CardBundle이 비었습니다.");
        }
        if (playerInfo == null) {
            throw new IllegalArgumentException("PlayerInfo가 비었습니다.");
        }
    }

    public boolean hasCard() {
        return cardBundle.isNotEmpty();
    }

    public void drawCard(Drawable drawable) {
        cardBundle.addCard(drawable.draw());
    }

    public int getScoreValue() {
        return new Score(cardBundle).getScore();
    }

    public Score getScore() {
        return new Score(cardBundle);
    }

    public boolean isBurst() {
        Score score = new Score(cardBundle);
        return score.isBurst();
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBlackjack() {
        Score score = new Score(cardBundle);
        return score.isBlackjack();
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public List<Card> getCardBundle() {
        return Collections.unmodifiableList(this.cardBundle.getCards());
    }

    public String getName() {
        return this.playerInfo.getName();
    }

    public abstract boolean isDrawable();
}
