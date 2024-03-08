package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "딜러";

    private final Player player;

    public Dealer() {
        this.player = Player.fromName(DEALER_NAME);
    }

    public void draw(Deck deck) {
        player.draw(deck);
    }

    public void drawUntilExceedMinimum(Deck deck) {
        while (getScore().isLessThanDealerMinimumScore()) {
            draw(deck);
        }
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public Score getScore() {
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
