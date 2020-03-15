package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactory {
    private PlayerFactory() {
    }

    public static Players create(Cards cards, List<String> playerNames) {
        if (cards == null || (playerNames == null || playerNames.size() == 0)) {
            throw new NullPointerException("플레이어를 생성할 수 없습니다.");
        }

        List<Player> players = playerNames.stream()
                .map(name -> new User(name, getInitialCards(cards)))
                .collect(Collectors.toList());
        players.add(new Dealer(getInitialCards(cards)));
        return new Players(players);
    }

    private static Card[] getInitialCards(Cards cards) {
        Card[] initialCards = new Card[2];
        for (int i = 0; i < 2; i++) {
            initialCards[i] = cards.hit();
        }
        return initialCards;
    }
}
