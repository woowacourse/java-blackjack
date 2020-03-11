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

    public Card giveCard() {
        Card card = cards.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NULL_ERROR_MSG));
        cards.remove(card);
        return card;
    }

    public void giveCard(Player player) {
        player.addCard(giveCard());
    }

    public void giveCard(Dealer dealer) {
        dealer.addCard(giveCard());
    }

    public int getSize() {
        return cards.size();
    }
}
