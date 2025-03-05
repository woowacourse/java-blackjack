package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import java.util.List;

public class Round {
    private final CardDeck cardDeck;
    private final List<Player> players;

    public Round(final CardDeck cardDeck, final List<Name> playerNames) {
        this.cardDeck = cardDeck;
        this.players = registerPlayers(playerNames);
    }

    private List<Player> registerPlayers(final List<Name> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public void distributeCards(final Name playerName, final int cardCount) {
        Player foundPlayer = findPlayer(playerName);
        for (int i = 0; i < cardCount; i++) {
            Card card = cardDeck.getCard();
            foundPlayer.addCard(card);
        }
    }

    public int getScoreByPlayer(Name name) {
        return findPlayer(name).calculateSum();
    }

    private Player findPlayer(Name name) {
        return players.stream()
                .filter(player -> player.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어 입니다:" + name));
    }
}
