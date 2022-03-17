package blackjack.domain.human.humans;

import blackjack.domain.card.cards.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import java.util.List;

public final class Participant {
    private static final int INIT_CARD_NUMBER = 2;

    private final Dealer dealer;
    private final Players players;

    private Participant(Players players) {
        this.dealer = Dealer.newInstance();
        this.players = players;
    }

    public static Participant from(Players players) {
        return new Participant(players);
    }

    public void initCard(CardDeck cardDeck) {
        for (int i = 0; i < INIT_CARD_NUMBER; i++) {
            dealer.addCard(cardDeck.pop());
            players.giveCard(cardDeck);
        }
    }

    public List<Player> getRawPlayers() {
        return players.get();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
