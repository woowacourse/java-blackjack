package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "딜러";
    private static final int MINIMUM_SCORE = 17;

    private final Player player;

    public Dealer() {
        this.player = new Player(DEALER_NAME);
    }

    public void draw(Deck deck) {
        player.draw(deck);
    }

    public void drawUntilExceedMinimum(Deck deck) {
        while (getScore() < MINIMUM_SCORE) {
            draw(deck);
        }
    }

    public boolean isBusted() {
        return player.isBusted();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public int getScore() {
        return player.getScore();
    }

    public Player getPlayer() {
        return player;
    }

    public Card getFirstCard() {
        // TODO: 메시지 보내기
        List<Card> cards = getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("아무 카드도 뽑지 않았습니다.");
        }
        return cards.get(0);
    }

    public int getCardsCount() {
        // TODO: 메시지 보내기
        return player.getCards().size();
    }
}
