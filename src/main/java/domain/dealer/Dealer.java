package domain.dealer;

import static domain.config.BlackjackGameConstant.*;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.player.Player;

import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    private final CardDeck cardDeck;
    private final CardBundle cardBundle;

    private Dealer(CardDeck cardDeck) {
        this.cardBundle = CardBundle.empty();
        this.cardDeck = cardDeck;
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public CardBundle handOutInitialCardToPlayer(Player player) {
        return handOutCardToPlayer(player, INITIAL_CARD_DRAW_COUNT);
    }

    public CardBundle handOutCard(int tryCount) {
        try {
            List<Card> cardList = Stream.generate(this::drawCard)
                    .limit(tryCount)
                    .toList();
            return CardBundle.of(cardList);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("딜러가 카드를 나눠줄 수 없습니다.");
        }
    }

    public CardBundle handOutCardToPlayer(Player player, int tryCount) {
        CardBundle cardBundle = handOutCard(tryCount);
        return player.addCardBundle(cardBundle);
    }

    public boolean hitIfRequired() {
        if (canHit()) {
            drawMySelf(DEFAULT_CARD_DRAW_COUNT);
            return true;
        }
        return false;
    }

    private boolean canHit() {
        return cardBundle.getBasicScore() <= DEALER_ADDITIONAL_DRAW_CONDITION;
    }

    public CardBundle drawMySelf(int tryCount) {
        return cardBundle.addUp(handOutCard(tryCount));
    }

    public Card drawCard() {
        return cardDeck.getCard();
    }

    public int getResultScore() {
        return cardBundle.getResultScore();
    }

    public String toDisplayMyName() {
        return DEALER_DISPLAY_NAME;
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

    public boolean isBusted() {
        return cardBundle.getBasicScore() > BUSTED_CONDITION;
    }

}
