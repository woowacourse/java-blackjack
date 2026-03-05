package domain.dealer;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;

import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    private CardDeck cardDeck;
    // TODO CardBundle 추가

    private Dealer(CardDeck cardDeck) {
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

    private Card drawCard() {
        return cardDeck.giveCard();
    }

}
