package model;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import model.card.CardDeck;
import model.participator.Dealer;
import model.participator.Participator;
import model.participator.Player;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException("중복된 플레이어는 존재할 수 없습니다.");
        }
        this.players = players;
    }

    public static Players of(List<String> names) {
        return new Players(names.stream()
                .map(playerName -> new Player(playerName, 1))
                .collect(toList()));
    }

    private boolean isDuplicate(List<Player> player) {
        return player.stream().distinct().count() != player.size();
    }

    public void receiveCardsAll(CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveCard(cardDeck.drawCard());
        }
    }

    public void receiveCardTo(String name, CardDeck cardDeck) {
        findByName(name).receiveCard(cardDeck.drawCard());
    }

    public Player findByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 플레이어가 없습니다."));
    }

    public boolean canReceiveCard(String name) {
        return findByName(name).canReceiveCard();
    }

    public Map<String, Result> matchWith(Dealer dealer) {
        return players.stream()
                .collect(toMap(Participator::getPlayerName, player -> player.matchWith(dealer)));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
