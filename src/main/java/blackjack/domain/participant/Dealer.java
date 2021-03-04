package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Deck;

import java.util.ArrayList;

public class Dealer extends Participant {
    
    private static final int DEALER_THRESHOLD = 16;
    private static final String NAME = "딜러";
    private static final String ERROR_CANNOT_RECEIVE = "최고 합이거나 버스트되어 카드를 받을 수 없습니다.";
    
    private final Deck deck;
    
    private Dealer(String name, CardHand cardHand, Deck deck) {
        super(name, cardHand);
        this.deck = deck;
    }
    
    public static Dealer create() {
        CardHand cardHand = new CardHand(new ArrayList<>());
        
        return new Dealer(NAME, cardHand, Deck.createShuffledDeck());
    }
    
    public void deal(Participant participant) {
        if (!participant.canReceive()) {
            throw new IllegalArgumentException(ERROR_CANNOT_RECEIVE);
        }
        
        final Card card = deck.drawCard();
        participant.receive(card);
    }
    
    public boolean shouldReceive() {
        return sumCardHand() <= DEALER_THRESHOLD;
    }
    
    //### 딜러
    //- [ ] 카드 패를 가짐
    //- [ ] 시작하면 카드 두 장을 받고 한 장만 공개한다
    //- [ ] 모든 플레이어의 턴이 끝나고 딜러의 턴이 진행된다
    //- [ ] 딜러는 패의 합계가 16 이하면 계속해서 뽑는다
}
