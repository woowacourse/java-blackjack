package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.gametable.GameTable;
import blackjack.dto.Results;
import blackjack.utils.RandomCardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final GameTable gameTable;
    private final Participant dealer;

    public Game(String namesValue) {
        List<Player> playersValue = getPlayerList(namesValue);
        this.dealer = new Dealer(new Cards(Collections.emptyList()));
        final Players players = new Players(playersValue);

        gameTable = new GameTable(dealer, players, new RandomCardDeck());
    }

    private List<Player> getPlayerList(String namesValue) {
        List<Player> playersValue = new ArrayList<>();
        String[] names = namesValue.split(",");
        for(String name : names){
            playersValue.add(new Player(new Name(name), new Cards(Collections.emptyList())));
        }
        return playersValue;
    }

    public Results getParticipants() {
        return gameTable.getParticipants();
    }

    public List<Player> getPlayers() {
        return gameTable.getPlayers();
    }

    public Player turnForPlayer(Player player) {
        gameTable.giveCard(player);
        return player;
    }

    public void turnForDealer() {
        gameTable.giveCard(dealer);
    }

}