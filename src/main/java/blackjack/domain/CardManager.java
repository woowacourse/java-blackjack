package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardManager {

    private final CardDeck cardDeck;
    private final Map<Nickname, Cards> nicknameToCards = new HashMap<>();

    public CardManager() {
        cardDeck = CardDeck.initialize();
    }

    public List<Card> findCardsByNickname(Player player) {
        if (nicknameToCards.containsKey(player.getNickname())) {
            return nicknameToCards.get(player.getNickname()).getCards();
        }
        return List.of();
    }

    public int calculateSumByNickname(Player player) {
        if (nicknameToCards.containsKey(player.getNickname())) {
            return nicknameToCards.get(player.getNickname()).calculateSum();
        }
        return 0;
    }

    public void addCardByNickname(Player player, int count) {
        List<Card> drawnCards = cardDeck.drawCard(count);

        if (!nicknameToCards.containsKey(player.getNickname())) {
            Cards newCards = Cards.initialize();
            newCards.add(drawnCards);
            nicknameToCards.put(player.getNickname(), newCards);
            return;
        }

        Cards cards = nicknameToCards.get(player.getNickname());
        cards.add(drawnCards);
    }
}
