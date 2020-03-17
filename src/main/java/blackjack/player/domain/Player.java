package blackjack.player.domain;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.Drawable;

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

    public int getScore() {
        return cardBundle.calculateScore();
    }

    public boolean isBurst() {
        return cardBundle.isBurst();
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBlackjack() {
        return cardBundle.isBlackjack();
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
