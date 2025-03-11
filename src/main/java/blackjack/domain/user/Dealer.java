package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.game.GameRule;
import blackjack.exception.ExceptionMessage;
import java.util.List;

public class Dealer {

    private final static Nickname DEALER_NICKNAME = new Nickname("딜러");
    private final GameUser gameUser;
    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        this.gameUser = new GameUser(DEALER_NICKNAME);
        this.cardDeck = cardDeck;
    }

    public void drawSelfInitialCard() {
        List<Card> cards = cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue());
        gameUser.addCardInHand(cards);
    }

    public List<Card> distributePlayerInitialCard() {
        return cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    public Card distirbuteHitCard() {
        return cardDeck.drawCard(1).getFirst();
    }

    public int drawSelfCardUntilLimit() {
        int count = 0;
        while (GameRule.checkPossibilityOfDealerDrawing(gameUser.getPoint())) {
            Card drawnCard = cardDeck.drawCard(1).getFirst();
            gameUser.addCardInHand(drawnCard);
            count++;
        }
        return count;
    }

    public Card findFirstCard() {
        validateBeforeDrawInitialCards();
        List<Card> cards = gameUser.getHand();
        return cards.getFirst();
    }

    public String getDealerName() {
        return gameUser.getNickname();
    }

    public List<Card> getHand() {
        return gameUser.getHand();
    }

    public int getPoint() {
        return gameUser.getPoint();
    }

    private void validateBeforeDrawInitialCards() {
        if (gameUser.getHand().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.BEFORE_CARD_DISTRIBUTION.getContent());
        }
    }
}
