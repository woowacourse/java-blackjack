package domain.participant;

import static config.BlackjackGameConstant.*;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;

import java.util.List;
import java.util.stream.Stream;

public class Dealer extends Participant {

    private final CardDeck cardDeck;

    private Dealer(CardDeck cardDeck) {
        super(ParticipantName.from(DEALER_DISPLAY_NAME));
        this.cardDeck = cardDeck;
    }

    public static Dealer from(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public void handOutInitialCardToPlayer(Player player) {
        handOutCardToPlayer(player, INITIAL_CARD_DRAW_COUNT);
    }

    public CardBundle handOutCard(int tryCount) {
        try {
            List<Card> cardList = Stream.generate(this::drawCard)
                    .limit(tryCount)
                    .toList();
            return CardBundle.from(cardList);
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

    public void drawMySelf(int tryCount) {
        cardBundle.addUp(handOutCard(tryCount));
    }

    public Card drawCard() {
        return cardDeck.getCard();
    }

    private boolean canHit() {
        return cardBundle.getBasicScore() <= DEALER_ADDITIONAL_DRAW_CONDITION;
    }

}
