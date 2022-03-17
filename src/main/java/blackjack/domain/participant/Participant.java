package blackjack.domain.participant;

import static blackjack.domain.PlayStatus.*;

import java.util.Set;

import blackjack.domain.PlayStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

public abstract class Participant {

    protected final Cards cards;
    protected PlayStatus playStatus;

    Participant() {
        this.cards = new Cards(Set.of());
        this.playStatus = HIT;
    }

    /**
     * CardDeck 으로부터 2장의 카드를 뽑아 Cards 에 저장한다.
     * 2장의 카드에 관한 PlayStatus 를 업데이트 한다.
     * 두 장의 합이 21인 경우 PlayStatus 가 STAY 가 되어 더이상 외부에서 카드를 뽑지 않는다.
     * @param cardDeck 카드가 생성된 CardDeck 을 받는다.
     */
    public void init(CardDeck cardDeck) {
        hit(cardDeck.drawCard());
        hit(cardDeck.drawCard());
    }

    PlayStatus getStatus() {
        return playStatus;
    }

    public boolean isHit() {
        return playStatus == HIT;
    }

    public int getScore() {
        return cards.sum();
    }

    public void hit(Card card) {
        cards.add(card);
        playStatus = cards.getStatus();
    }

    public abstract Name getName();

    public Set<Card> getCards() {
        return cards.getValue();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }
}
