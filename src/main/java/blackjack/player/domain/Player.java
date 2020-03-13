package blackjack.player.domain;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

import java.util.Collections;
import java.util.List;

public abstract class Player {
    protected final String name;
    protected final CardBundle cardBundle;

    public Player(CardBundle cardBundle, String name) {
        validate(cardBundle, name);
        this.cardBundle = cardBundle;
        this.name = name;
    }

    private void validate(CardBundle cardBundle, String name) {
        if (cardBundle == null) {
            throw new IllegalArgumentException("CardBundle이 비었습니다.");
        }
        if (name == null) {
            throw new IllegalArgumentException("이름이 비었습니다.");
        }
    }

    public String getName() {
        return this.name;
    }

    public void addCard(Card card) {
        cardBundle.addCard(card);
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

    public int getScore() {
        return cardBundle.calculateScore();
    }

    public abstract boolean isDrawable();

    public abstract GameReport createReport(Player player);

    public boolean hasCard() {
        return cardBundle.isNotEmpty();
    }
}
