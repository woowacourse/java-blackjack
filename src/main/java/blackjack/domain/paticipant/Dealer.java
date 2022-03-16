package blackjack.domain.paticipant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.DealerRunning;
import java.util.List;
import java.util.Set;

public class Dealer extends AbstractParticipant {

    private static final String DEALER_NAME = "딜러";

    private Dealer(final String name, final BlackjackGameState gameState) {
        super(name, gameState);
    }

    public Dealer(final Cards cards) {
        this(DEALER_NAME, DealerRunning.createDealerGameState(cards));
    }

    public Dealer(final CardDeck cardDeck) {
        this(new Cards(Set.of(cardDeck.provideCard(), cardDeck.provideCard())));
    }

    public Card firstCard() {
        return super.cards().get(0);
    }

    @Override
    public List<Card> cards() {
        if (!isFinishied()) {
            throw new IllegalStateException("딜러는 완료되지 않으면 카드리스트를 반환하지 않습니다.");
        }
        return super.cards();
    }
}
