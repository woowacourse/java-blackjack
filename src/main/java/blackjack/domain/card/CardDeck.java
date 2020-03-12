package blackjack.domain.card;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.Set;

public class CardDeck {
    private static final String NULL_ERROR_MSG = "카드를 찾을 수 없습니다.";
    private final Set<Card> cards;

    public CardDeck() {
        cards = CardFactory.getInstance();
    }

    public void dealFirstCards(Dealer dealer) {
        dealer.addCard(giveCard());
        dealer.addCard(giveCard());
    }

    public void dealFirstCards(Player player) {
        player.addCard(giveCard());
        player.addCard(giveCard());
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
        Card card = cards.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NULL_ERROR_MSG));
        cards.remove(card);
        return card;
    }

    public int getSize() {
        return cards.size();
    }
}
