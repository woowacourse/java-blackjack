package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_THRESHOLD = 16;

    private final Deck deck;

    public Dealer() {
        super("딜러", new CardHand(new ArrayList<>()));
        this.deck = Deck.createShuffledDeck();
    }

    public void drawBaseCard() {
        cardHand.add(drawCard());
        cardHand.add(drawCard());
    }

    public void deal(Player player) {
        player.receiveCard(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.dealerSum() <= DEALER_THRESHOLD;
    }

    public void setPlayerBaseCard(List<Player> players) {
        for (Player player: players) {
            player.receiveCard(drawCard());
            player.receiveCard(drawCard());
        }
    }

    public void pickAnotherCard() {
        // 뽑은 카드를 자기 패에 놓는다
        cardHand.add(drawCard());
    }

    private Card drawCard() {
        // 덱에서 카드 뽑아서 리턴
        return deck.drawCard();
    }

    @Override
    public int getCardSum() {
        return cardHand.dealerSum();
    }

//### 딜러
//- [ ] 카드 패를 가짐
//- [ ] 시작하면 카드 두 장을 받고 한 장만 공개한다
//- [ ] 모든 플레이어의 턴이 끝나고 딜러의 턴이 진행된다
//- [ ] 딜러는 패의 합계가 16 이하면 계속해서 뽑는다
}
