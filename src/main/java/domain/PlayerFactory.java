package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactory {
    private PlayerFactory() {
    }

    public static Players create(CardDeck cardDeck, List<String> playerNames) {
        if (cardDeck == null || (playerNames == null || playerNames.isEmpty())) {
            throw new NullPointerException("플레이어를 생성할 수 없습니다.");
        }

        List<Player> players = playerNames.stream()
                .map(name -> new User(name, cardDeck.initialDraw()))
                .collect(Collectors.toList());
        players.add(new Dealer(cardDeck.initialDraw()));
        return new Players(players);
    }
}
