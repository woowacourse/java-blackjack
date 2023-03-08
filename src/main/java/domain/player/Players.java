package domain.player;

import domain.deck.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<String> playerNames) {
        this.players = new ArrayList<>();
        addPlayers(playerNames);
    }

    private void addPlayers(final List<String> playerNames) {
        this.players.add(new Dealer());
        playerNames.forEach(name ->
                this.players.add(new Player(name))
        );
    }

    public void drawTwoCardsAtFirstTime(Deck deck) {
        for (Player player : players) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public boolean isLargerThanBlackJackNumber(final String playerName) {
        if (findPlayer(playerName).isLargerThanBlackJackNumber()) {
            return true;
        }
        return false;
    }

    public Player findPlayer(final String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]: 해당 이름을 가진 플레이어가 존재하지 않습니다."));
    }

    public Dealer findDealer() {
        return (Dealer) players.get(0);
    }

    public List<Player> getPlayersWithDealer() {
        return players.stream()
                .map(player -> {
                    Player copyPlayer = new Player(player.getName());
                    player.getCards().forEach(copyPlayer::drawCard);
                    return copyPlayer;
                }).collect(Collectors.toList());
    }

    public List<Player> getPlayersWithOutDealer() {
        return players.stream()
                .filter(player -> !(player instanceof Dealer))
                .map(player -> {
                    Player copyPlayer = new Player(player.getName());
                    player.getCards().forEach(copyPlayer::drawCard);
                    return copyPlayer;
                }).collect(Collectors.toList());
    }
}
