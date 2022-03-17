package blackjack.domain.paticipant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.state.DealerRunning;
import java.util.List;

public final class Dealer extends AbstractParticipant {

    private static final String DEALER_NAME = "딜러";

    private Dealer(final Cards cards) {
        super(new Name(DEALER_NAME), DealerRunning.createDealerGameState(cards));
    }

    public Dealer(final CardDeck cardDeck) {
        this(new Cards(List.of(cardDeck.provideCard(), cardDeck.provideCard())));
    }

    public Card firstCard() {
        return super.cards().get(0);
    }

    @Override
    public List<Card> cards() {
        checkDealerCanReturnCards();
        return super.cards();
    }

    private void checkDealerCanReturnCards() {
        if (!isFinished()) {
            throw new IllegalStateException("딜러는 완료되지 않으면 카드리스트를 반환하지 않습니다.");
        }
    }
}
