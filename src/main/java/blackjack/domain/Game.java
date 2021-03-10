package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.Participants;
import blackjack.utils.RandomCardDeck;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GameTable gameTable;
    private final Dealer dealer;
    private final Players players;
    private final Participants participants;

    public Game(String namesValue) {
        gameTable = new GameTable(new RandomCardDeck());
        dealer = new Dealer(gameTable.initCards());

        List<Player> playersValue = getPlayerList(namesValue);

        players = new Players(playersValue);
        participants = new Participants(players, dealer);
    }

    private List<Player> getPlayerList(String namesValue) {
        List<Player> playersValue = new ArrayList<>();
        String[] names = namesValue.split(",");
        for(String name : names){
            playersValue.add(new Player(new Name(name), gameTable));
        }
        return playersValue;
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Player> getPlayers() {
        return players.getUnmodifiableList();
    }

    public Player turnForPlayer(Player player) {
        gameTable.giveCard(player);
        return player;
    }

    public void turnForDealer() {
        gameTable.giveCard(dealer);
    }

}