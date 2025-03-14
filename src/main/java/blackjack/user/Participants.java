package blackjack.user;

import blackjack.card.CardDeck;
import java.util.Collections;
import java.util.List;

public class Participants {

    private static final int PARTICIPANTS_MIN_SIZE = 1;
    private static final int PARTICIPANTS_MAX_SIZE = 25;
    private static final int INITIAL_DISTRIBUTE_CARD_NUMBER = 2;
    private static final int EXTRA_DISTRIBUTE_CARD_NUMBER = 1;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<Player> players) {
        validatePlayerNumber(players);
        validateDuplicatePlayerNames(players);

        this.dealer = dealer;
        this.players = players;
    }

    private void validatePlayerNumber(final List<Player> players) {
        if (players.size() < PARTICIPANTS_MIN_SIZE) {
            throw new IllegalArgumentException("플레이어는 한 명 이상 참가해야 합니다.");
        }

        if (players.size() > PARTICIPANTS_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어는 25명까지만 참가 가능합니다.");
        }
    }

    private void validateDuplicatePlayerNames(final List<Player> players) {
        long uniqueNameSize = players.stream()
            .map(Player::getName)
            .distinct().count();

        if (players.size() != uniqueNameSize) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어가 있습니다.");
        }
    }

    public void addInitialCardsToDealer(CardDeck cardDeck) {
        dealer.addCards(cardDeck, INITIAL_DISTRIBUTE_CARD_NUMBER);
    }

    public void addInitialCardsToPlayers(CardDeck cardDeck) {
        for(Player player : players) {
            player.addCards(cardDeck, INITIAL_DISTRIBUTE_CARD_NUMBER);
        }
    }

    public void addExtraCardToDealer(CardDeck cardDeck) {
        dealer.addCards(cardDeck, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    public void addExtraCardToPlayer(CardDeck cardDeck, PlayerName name) {
        Player player = findPlayerByName(name);
        player.addCards(cardDeck, EXTRA_DISTRIBUTE_CARD_NUMBER);
    }

    private Player findPlayerByName(PlayerName name) {
        return players.stream()
            .filter(player -> player.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));
    }

    public List<PlayerName> getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
