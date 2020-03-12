package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;

public class Participant {
    List<User> participants;

    public Participant(String names) {
        List<User> users = new ArrayList<>();
        String[] userNames = names.split(",");
        for (String name : userNames) {
            users.add(new User(name));
        }
        this.participants = users;
    }

    public List<String> getUserNames() {
        return participants.stream().map(Player::getName).collect(Collectors.toList());
    }

    public void firstDraw(CardDeck cardDeck) {
        for (User user : participants) {
            user.firstDraw(cardDeck);
        }
    }

    public Map<String, Result> putResultIntoMap(Dealer dealer) {
        Map<String, Result> userResultMap = new HashMap<>();

        for (User user : participants) {
            userResultMap.put(user.getName(), user.beatDealer(dealer));
        }
        return userResultMap;
    }

    public List<User> getParticipants() {
        return participants;
    }


}
