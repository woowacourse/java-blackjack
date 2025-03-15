package blackjack.domain.game;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Names;
import blackjack.domain.gambler.Player;
import java.util.List;

public class Round {
    public static final int MIN_PLAYER_COUNT = 1;
    public static final int MAX_PLAYER_COUNT = 6;
    public static final int BLACKJACK = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final String NOT_EXISTS_PLAYER = "존재하지 않는 플레이어입니다: %s";

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    public Round(final CardDeck cardDeck, final Names playerNames) {
        this.dealer = new Dealer();
        this.players = registerPlayers(playerNames);
        this.cardDeck = cardDeck;
    }

    private List<Player> registerPlayers(final Names playerNames) {
        return playerNames.getNames()
                .stream()
                .map(Player::new)
                .toList();
    }

    /**
     * TODO
     */
    public void distributeCards(final Name name, final int cardCount) {
        if (dealer.isNameEquals(name)) {
            for (int i = 0; i < cardCount; i++) {
                Card card = cardDeck.getCard();
                dealer.addCard(card);
            }
            return;
        }
        Player player = findPlayer(name);
        for (int i = 0; i < cardCount; i++) {
            Card card = cardDeck.getCard();
            player.addCard(card);
        }
    }

    public int getScore(final Name name) {
        if (dealer.isNameEquals(name)) {
            return dealer.calculateScore();
        }
        return findPlayer(name).calculateScore();
    }

    public void distributeInitialCards() {
        distributeCards(DEALER_NAME, 2);
        for (final Player player : players) {
            player.addCard(cardDeck.getCard());
            player.addCard(cardDeck.getCard());
        }
    }

    public boolean dealerMustDraw() {
        return dealer.mustDraw();
    }

    public boolean isBust(final Name name) {
        if (dealer.isNameEquals(name)) {
            return dealer.isBust();
        }
        return findPlayer(name).isBust();
    }

    public List<Card> getCards(final Name name) {
        if (dealer.isNameEquals(name)) {
            return dealer.getCards();
        }
        return findPlayer(name).getCards();
    }

    public List<Card> getInitialCards(final Name name) {
        if (dealer.isNameEquals(name)) {
            return dealer.getInitialCards();
        }
        Player player = findPlayer(name);
        return player.getInitialCards();
    }

    public WinningDiscriminator getWinningDiscriminator() {
        return new WinningDiscriminator(dealer, players);
    }

    private Player findPlayer(final Name name) {
        return players.stream()
                .filter(player -> player.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTS_PLAYER.formatted(name)));
    }
}
