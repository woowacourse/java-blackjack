package blackjack.domain;

import blackjack.dto.Participants;
import blackjack.utils.CardDeck;
import blackjack.utils.RandomCardDeck;
import blackjack.view.InputView;
import java.util.List;

public class Game {
    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;
    private final Participants participants;

    public Game(String namesValue) {
        cardDeck = new RandomCardDeck();
        dealer = new Dealer(cardDeck.initCards());
        players = new Players(namesValue, cardDeck);
        participants = new Participants(players, dealer);
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Player> getPlayers() {
        return players.getUnmodifiableList();
    }

    public Player turnForPlayer(Player player, boolean requestTaking) {
        while (player.isAvailableToTake() && requestTaking){
            player.takeCard(cardDeck.pop());
        }
        return player;
    }

    public void turnForDealer() {
        if (dealer.isAvailableToTake()) {
            dealer.takeCard(cardDeck.pop());
        }
    }

}