package blackjack.domain.card;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        cards = CardFactory.getInstance();
    }

    public void dealFirstCards(Participant participant) {
        participant.addCard(giveCard());
        participant.addCard(giveCard());
    }

    public boolean dealAdditionalCard(Dealer dealer) {
        dealer.addCard(giveCard());
        return true;
    }

    public boolean dealAdditionalCard(Player player, String reply) {
        if ("y".equals(reply)) {
            player.addCard(giveCard());
            return true;
        }
        if ("n".equals(reply)) {
            return false;
        }
        throw new IllegalArgumentException("응답은 y 혹은 n만 가능합니다.");
    }

    private Card giveCard() {
        return cards.remove(0);
    }

    public int getSize() {
        return cards.size();
    }
}
