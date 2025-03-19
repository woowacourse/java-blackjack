package domain;

import exception.CustomException;
import java.util.List;

public class CardGiver {

    private static final int DEFAULT_CARD_GIVE_COUNT = 2;

    private final Deck deck;

    public CardGiver(Deck deck) {
        this.deck = deck;
    }

    public void dealingTo(List<Participant> participants) {
        participants.forEach(participant -> participant.addDefaultCards(deck.drawCards(DEFAULT_CARD_GIVE_COUNT)));
    }

    public void hit(Player player, boolean isRequestHit) {
        if (!isRequestHit) {
            return;
        }
        if (player.isImpossibleHit()) {
            throw new CustomException("카드의 합이 21을 초과하였습니다. 더이상 카드를 받을 수 없습니다.");
        }
        player.addCard(deck.drawCard());
    }

    public void draw(Dealer dealer) {
        if (dealer.isPossibleDraw()) {
            dealer.addCard(deck.drawCard());
        }
    }
}
