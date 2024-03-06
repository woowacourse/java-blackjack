package domain;

import java.util.List;

public class Participants {

    public static final int DEALER_COUNT = 1;
    private final Player dealer;
    private final List<Player> players;


    public Participants(Player dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int count() {
        return players.size() + DEALER_COUNT;
    }

    public void receiveInitialCards(List<Deck> decks) {
        if (decks.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
        Deck dealerDeck = decks.remove(decks.size() - 1);
        dealer.receiveDeck(dealerDeck);

        for (int index = 0; index < players.size(); index++) {
            Deck currentDeck = decks.get(index);
            players.get(index).receiveDeck(currentDeck);
        }
    }
}
