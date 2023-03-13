package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;

import java.util.ArrayList;
import java.util.List;

public class Players {

    public static final int MAXIMUM_NUMBER_OF_PLAYERS = 4;

    private final List<Player> players;

    private Players(List<Player> players) {
        validateLimitNumberOfPlayers(players);
        validateNameDuplication(players);
        this.players = players;
    }

    public static  Players of(List<String> names, CardDeck cardDeck, List<Integer> moneys) {
        List<Player> players = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < names.size(); playerIndex++) {
            Name name = Name.generatePlayerName(names.get(playerIndex));
            Cards cards = makeInitialCards(cardDeck);
            Money money = new Money(moneys.get(playerIndex));
            players.add(new Player(name, cards, money));
        }
        return new Players(players);
    }

    private static Cards makeInitialCards(CardDeck cardDeck) {
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.pickCard());
        cards.add(cardDeck.pickCard());
        return new Cards(cards);
    }

    private void validateLimitNumberOfPlayers(List<Player> players) {
        if (players.size() > MAXIMUM_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException("[ERROR] 플레이어 수는 4명을 초과할 수 없습니다.");
        }
    }

    private void validateNameDuplication(List<Player> players) {
        int distinctNumberOfPlayers = (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (distinctNumberOfPlayers != players.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 간 이름은 중복될 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
