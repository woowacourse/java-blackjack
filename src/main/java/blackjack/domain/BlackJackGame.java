package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.ParticipatingPlayer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerInfo;
import blackjack.dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Player dealer;
    private final Players players;

    private BlackJackGame(final CardDeck cardDeck, final Player dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame init(final List<String> playerNames) {
        Objects.requireNonNull(playerNames, "blackjackgame은 null이 들어올 수 없습니다.");
        final List<String> copyNames = new ArrayList<>(playerNames);
        final CardDeck cardDeck = CardDeck.init();
        final Player dealer = Dealer.init(cardDeck.provideInitCards());
        final List<Player> players = provideInitCardsToPlayers(copyNames, cardDeck);
        return new BlackJackGame(cardDeck, dealer, new Players(players));
    }

    private static List<Player> provideInitCardsToPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> ParticipatingPlayer.init(name, cardDeck.provideInitCards()))
                .collect(Collectors.toList());
    }

    public boolean isPlayersTurnEnd() {
        return players.isAllTurnEnd();
    }

    public PlayerInfo drawCurrentPlayer(final String command) {
        final DrawCommand drawCommand = DrawCommand.from(command);
        if (drawCommand.isNo()) {
            final PlayerInfo currentPlayer = players.getCurrentTurnPlayerInfo();
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

    public PlayerInfo getInitDealerInfo() {
        return PlayerInfo.toPlayerInitInfo(dealer);
    }

    public List<PlayerInfo> getInitPlayerInfo() {
        return players.getInitPlayerInfo();
    }

    public PlayerInfo getCurrentTurnPlayerInfo() {
        return players.getCurrentTurnPlayerInfo();
    }

    public List<PlayerResultInfo> getPlayerResultInfos() {
        final List<PlayerResultInfo> resultInfos =
                new ArrayList<>(Collections.singletonList(PlayerResultInfo.from(dealer)));
        resultInfos.addAll(players.getResultPlayerInfo());
        return List.copyOf(resultInfos);
    }

    public OutComeResult calculateAllResults() {
        return players.outcomeResult(dealer);
    }
}
