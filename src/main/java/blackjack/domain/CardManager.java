package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CardManager {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int LIMIT_POINT = 21;

    private final CardDeck cardDeck;
    private final Map<Nickname, Cards> nicknameToCards = new HashMap<>();

    public CardManager(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
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
                        .add(cardDeck.drawCard(INITIAL_CARD_COUNT))
        );
    }

    public Cards findCardsByNickname(Nickname nickname) {
        return nicknameToCards.get(nickname);
    }

    public int calculateSumByNickname(Nickname nickname) {
        Cards cards = findCardsByNickname(nickname);
        return cards.calculateSum();
    }

    public boolean isOverLimitSumByNickname(Nickname nickname) {
        return calculateSumByNickname(nickname) > LIMIT_POINT;
    }

    public void addCardByNickname(Nickname nickname) {
        List<Card> drawnCard = cardDeck.drawCard(1);
        findCardsByNickname(nickname).add(drawnCard);
    }

}
