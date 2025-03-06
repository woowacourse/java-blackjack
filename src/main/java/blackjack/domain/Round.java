package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import java.util.List;

public class Round {
    private final CardDeck cardDeck;
    private final List<Player> players;
    private final Dealer dealer;

    public Round(final CardDeck cardDeck, final List<Name> playerNames) {
        this.cardDeck = cardDeck;
        this.players = registerPlayers(playerNames);
        this.dealer = new Dealer();
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

    public int getScoreByDealer() {
        return dealer.calculateSum();
    }

    private Player findPlayer(Name name) {
        return players.stream()
                .filter(player -> player.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어 입니다:" + name));
    }

    public void distributeInitialCards() {
        dealer.addCard(cardDeck.getCard());
        dealer.addCard(cardDeck.getCard());
        for (Player player : players) {
            player.addCard(cardDeck.getCard());
            player.addCard(cardDeck.getCard());
        }
    }

    public List<Card> getCardsByPlayer(Name name) {
        return findPlayer(name).getCards();
    }

    public List<Card> getCardsByDealer() {
        return dealer.getCards();
    }

    public List<Card> getInitialCardsByDealer() {
        return dealer.getInitialCards();
    }

    public boolean isPlayerBusted(Name name) {
        return findPlayer(name).isBust();
    }
}
