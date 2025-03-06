package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CardManager {

    private final CardDeck cardDeck;
    private final Map<Nickname, Cards> nicknameToCards = new HashMap<>();

    public CardManager() {
        cardDeck = CardDeck.initialize();
    }

    public void initialize(List<Nickname> nicknames) {
        nicknames.forEach(
                nickname -> nicknameToCards.put(nickname, Cards.initialize())
        );
    }

    public void distributeCards() {
        Set<Nickname> nicknames = nicknameToCards.keySet();
        nicknames.forEach(nickname ->
                nicknameToCards.get(nickname)
                        .add(cardDeck.drawCard(GameRule.INITIAL_CARD_COUNT.getValue()))
        );
    }

    public Cards findCardsByNickname(Nickname nickname) {
        return nicknameToCards.get(nickname);
    }

    public int calculateSumByNickname(Nickname nickname) {
        Cards cards = findCardsByNickname(nickname);
        return cards.calculateSum();
    }

    public void addCardByNickname(Nickname nickname) {
        List<Card> drawnCard = cardDeck.drawCard(1);
        findCardsByNickname(nickname).add(drawnCard);
    }

}
