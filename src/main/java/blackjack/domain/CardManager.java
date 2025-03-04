package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CardManager {

    public static final int INITIAL_CARD_COUNT = 2;
    private final CardDeck cardDeck;
    private final Map<Nickname, List<Card>> nicknameToCards = new HashMap<>();
    private final List<Card> dealerCards = new ArrayList<>();

    public CardManager(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void initialize(List<Nickname> nicknames) {
        nicknames.forEach(
                nickname -> nicknameToCards.put(nickname, new ArrayList<>())
        );
    }

    public void distributeCard() {
        dealerCards.addAll(cardDeck.drawCard(INITIAL_CARD_COUNT));

        Set<Nickname> nicknames = nicknameToCards.keySet();
        nicknames.forEach(nickname ->
                nicknameToCards.get(nickname)
                        .addAll(cardDeck.drawCard(INITIAL_CARD_COUNT))
        );
    }

    public List<Card> findCardsByNickname(Nickname nickname) {
        return nicknameToCards.get(nickname);
    }

    public List<Card> findDealerCards() {
        return dealerCards;
    }
}
