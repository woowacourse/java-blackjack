package domain.dealer;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.player.Player;

import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    private static final int ADDITIONAL_DRAW_CONDITION = 16;

    private CardDeck cardDeck;
    private CardBundle cardBundle;

    private Dealer(CardDeck cardDeck) {
        this.cardBundle = CardBundle.empty();
        this.cardDeck = cardDeck;
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    // TODO Method Name Refactor
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
            drawMySelf(1);
            return true;
        }
        return false;
    }

    private boolean canHit() {
        return cardBundle.getBasicScore() <= 16;
    }

    public CardBundle drawMySelf(int tryCount) {
        return cardBundle.addUp(handOutCard(tryCount));
    }

    public Card drawCard() {
        return cardDeck.giveCard();
    }

    public String toDisplayMyName() {
        return "딜러";
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

    public boolean isBusted() {
        return cardBundle.getBasicScore() > 21;
    }

}
