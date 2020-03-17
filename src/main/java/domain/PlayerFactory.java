package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import domain.player.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerFactory {
    private PlayerFactory() {
    }

    public static Users create(CardDeck cardDeck, List<String> playerNames) {
        if (cardDeck == null || (playerNames == null || playerNames.isEmpty())) {
            throw new IllegalArgumentException("플레이어를 생성할 수 없습니다.");
        }

        List<User> users = new ArrayList<>();
        users.add(new Dealer(cardDeck.initialDraw()));
        users.addAll(playerNames.stream()
                .map(name -> new Player(name, cardDeck.initialDraw()))
                .collect(Collectors.toList()));
        return new Users(users);
    }

    public static Users create(CardDeck cardDeck, Map<String, Integer> playerInformations) {
        if (cardDeck == null || playerInformations == null) {
            throw new IllegalArgumentException("플레이어를 생성할 수 없습니다.");
        }

        List<User> users = new ArrayList<>();
        users.add(new Dealer(cardDeck.initialDraw()));
        users.addAll(playerInformations.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), cardDeck.initialDraw(), entry.getValue()))
                .collect(Collectors.toList()));
        return new Users(users);
    }
}
