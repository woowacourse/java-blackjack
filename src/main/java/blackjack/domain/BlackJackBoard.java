package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.ParticipatingPlayer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerCards;
import blackjack.dto.PlayerScoreResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlackJackBoard {

    private final CardDeck cardDeck;
    private final Player dealer;
    private final Players players;

    private BlackJackBoard(final CardDeck cardDeck, final Player dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackBoard createGame(final List<String> playerNames) {
        Objects.requireNonNull(playerNames, "blackjackgame은 null이 들어올 수 없습니다.");
        final List<String> copyNames = new ArrayList<>(playerNames);
        final CardDeck cardDeck = CardDeck.createNewCardDek();
        final Player dealer = Dealer.createNewDealer(cardDeck.provideFirstDrawCards());
        final List<Player> players = createPlayers(copyNames, cardDeck);
        return new BlackJackBoard(cardDeck, dealer, new Players(players));
    }

    private static List<Player> createPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> createPlayer(cardDeck, name))
                .collect(Collectors.toList());
    }

    private static ParticipatingPlayer createPlayer(final CardDeck cardDeck, final String name) {
        return ParticipatingPlayer.createNewPlayer(name, cardDeck.provideFirstDrawCards());
    }

    public boolean isPlayersTurnEnd() {
        return players.isAllTurnEnd();
    }

    public PlayerCards drawCurrentPlayer(final DrawCommand command) {
        if (command.isNo()) {
            final PlayerCards currentPlayer = players.getCurrentTurnPlayerCards();
            players.turnToNextPlayer();
            return currentPlayer;
        }
        return players.drawCurrentPlayer(cardDeck.provideCard());
    }

    public boolean isDealerTurnEnd() {
        return !dealer.canDraw();
    }

    public void drawDealer() {
        dealer.draw(cardDeck.provideCard());
    }

    public PlayerCards getDealerFirstCard() {
        return PlayerCards.toPlayerFirstCards(dealer);
    }

    public List<PlayerCards> getPlayersFirstCards() {
        return players.getPlayerFirstCards();
    }

    public String getCurrentTurnPlayerName() {
        return players.getCurrentTurnPlayerName();
    }

    public List<PlayerScoreResult> getPlayerScoreResults() {
        final List<PlayerScoreResult> results = new ArrayList<>(Collections.singletonList(PlayerScoreResult.from(dealer)));
        results.addAll(players.getPlayerScoreResults());
        return List.copyOf(results);
    }

    public OutComeResult calculateAllResults() {
        return players.outcomeResult(dealer);
    }
}
