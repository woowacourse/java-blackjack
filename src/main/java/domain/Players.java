package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.result.Result;

public class Players {
    private List<Player> players;

    public Players(String names) {
        List<Player> players = new ArrayList<>();
        String[] userNames = names.split(",");
        for (String name : userNames) {
            players.add(new Player(name));
        }
        this.players = players;
    }

    public List<String> getUserNames() {
        return players.stream().map(Participant::getName).collect(Collectors.toList());
    }

    public void firstDraw(CardDeck cardDeck) {
        for (Player player : players) {
            player.firstDraw(cardDeck);
        }
    }

    public Map<String, Result> putResultIntoMap(Dealer dealer) {
        Map<String, Result> userResultMap = new HashMap<>();
        for (Player player : players) {
            userResultMap.put(player.getName(), player.beatDealer(dealer));
        }
        return userResultMap;
    }

    public List<Player> getPlayers() {
        return players;
    }


}
