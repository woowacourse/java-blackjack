package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.User;

public class Players {
    List<User> players;

    public Players(String names) {
        List<User> users = new ArrayList<>();
        String[] userNames = names.split(",");
        for (String name : userNames) {
            users.add(new User(name));
        }
        this.players = users;
    }

    public List<String> getUserNames() {
        return players.stream().map(Participant::getName).collect(Collectors.toList());
    }

    public void firstDraw(CardDeck cardDeck) {
        for (User user : players) {
            user.firstDraw(cardDeck);
        }
    }

    public Map<String, Result> putResultIntoMap(Dealer dealer) {
        Map<String, Result> userResultMap = new HashMap<>();

        for (User user : players) {
            userResultMap.put(user.getName(), user.beatDealer(dealer));
        }
        return userResultMap;
    }

    public List<User> getPlayers() {
        return players;
    }


}
