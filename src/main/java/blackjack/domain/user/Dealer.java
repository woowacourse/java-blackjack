package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.game.GameRule;
import java.util.List;

public class Dealer {

    private static String DEALER_NAME = "딜러";

    private final Player player;
    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        this.player = new Player(new Nickname(DEALER_NAME));
        this.cardDeck = cardDeck;
    }

    public void drawSelfInitialCard() {
        List<Card> cards = cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue());
        player.addInitialCards(cards);
    }

    public List<Card> distributePlayerInitialCard() {
        return cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    public Card distirbuteHitCard() {
        return cardDeck.drawCard(1).getFirst();
    }

    public int drawSelfCardUntilLimit() {
        int count = 0;
        while (GameRule.checkPossibilityOfDealerDrawing(player.getPoint())) {
            Card drawnCard = cardDeck.drawCard(1).getFirst();
            player.hit(drawnCard);
            count++;
        }
        return count;
    }

    public Card findFirstCard() {
        List<Card> cards = player.getHand();
        return cards.getFirst();
    }

    public String getDealerName() {
        return player.getNickname();
    }

    public List<Card> getHand() {
        return player.getHand();
    }

    public int getPoint() {
        return player.getPoint();
    }
}
