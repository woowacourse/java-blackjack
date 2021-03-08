package blackjack.domain;

import blackjack.dto.Participants;
import blackjack.utils.CardDeck;
import blackjack.utils.RandomCardDeck;
import java.util.List;

public class Game {
    private final GameTable gameTable;
    private final Dealer dealer;
    private final Players players;
    private final Participants participants;

    public Game(String namesValue) {
        gameTable = new GameTable(new RandomCardDeck());
        dealer = new Dealer(gameTable.initCards());
        players = new Players(namesValue, gameTable);
        participants = new Participants(players, dealer);
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Player> getPlayers() {
        return players.getUnmodifiableList();
    }

    public Player turnForPlayer(Player player) {
            player.takeCard(gameTable.pop());
        return player;
    }

    public void turnForDealer() {
        if (dealer.isAvailableToTake()) {
            dealer.takeCard(gameTable.pop());
        }
    }

}