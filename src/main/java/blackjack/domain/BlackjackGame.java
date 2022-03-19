package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.MatchResult;

public class BlackjackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    private BlackjackGame(final Deck deck, final Dealer dealer, final Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame initialize(final DeckGenerator deckGenerator, final List<String> playerNames) {
        final Deck deck = Deck.generate(deckGenerator);
        final Dealer dealer = Dealer.readyToPlay(deck.distributeInitialCards());
        final Players players = Players.readyToPlay(playerNames, deck);
        return new BlackjackGame(deck, dealer, players);
    }

    public void playerBet(final String playerName, final int amount) {
        final Player player = players.findByPlayerName(playerName);
        player.betAmount(amount);
    }

    public void playerDrawCard(final String playerName, final boolean needToDrawCard) {
        final Player player = players.findByPlayerName(playerName);
        if (needToDrawCard) {
            player.drawCard(deck.drawCard());
            return;
        }
        player.stay();
    }

    public boolean isPlayerPossibleToDrawCard(final String playerName) {
        final Player player = players.findByPlayerName(playerName);
        return player.isPossibleToDrawCard();
    }

    public void dealerDrawCard() {
        dealer.drawCard(deck.drawCard());
    }

    public boolean isDealerPossibleToDrawCard() {
        return dealer.isPossibleToDrawCard();
    }

    public MatchResult calculateMatchResult() {
        validateAllPlayersFinished();
        return players.judgeMatchStatusOfPlayers(dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Card getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players.getPlayers());
    }

    public List<String> getPlayerNames() {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> getPlayerCards(final String playerName) {
        return players.getPlayerCards(playerName);
    }

    private void validateAllPlayersFinished() {
        if (players.isAnyPlayerNotFinished()) {
            throw new IllegalStateException("턴이 종료되지 않은 플레이어가 존재합니다.");
        }
    }
}
