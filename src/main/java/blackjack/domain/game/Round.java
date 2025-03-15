package blackjack.domain.game;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Names;
import blackjack.domain.gambler.Player;
import java.util.List;
import java.util.Map;

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

    public void hit(final Name name) {
        if (dealer.isNameEquals(name)) {
            dealer.hit(cardDeck.getCard());
            return;
        }
        Player player = findPlayer(name);
        player.hit(cardDeck.getCard());
    }

    public int getScore(final Name name) {
        if (dealer.isNameEquals(name)) {
            return dealer.calculateScore();
        }
        return findPlayer(name).calculateScore();
    }

    public void distributeInitialCards() {
        dealer.hit(cardDeck.getCard());
        dealer.hit(cardDeck.getCard());
        for (final Player player : players) {
            player.hit(cardDeck.getCard());
            player.hit(cardDeck.getCard());
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

    public void bet(final Name name, final BettingAmount bettingAmount) {
        findPlayer(name).bet(bettingAmount);
    }

    public WinningResult getWinningResult() {
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator();
        Map<Player, WinningType> winningResult = players.stream()
                .collect(toMap(identity(), player -> winningDiscriminator.judgePlayerResult(dealer, player)));
        return new WinningResult(winningResult);
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

    private Player findPlayer(final Name name) {
        return players.stream()
                .filter(player -> player.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTS_PLAYER.formatted(name)));
    }
}
