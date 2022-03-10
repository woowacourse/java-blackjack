package blackjack.domain.game;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerInfo;
import blackjack.dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackJackGame(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame init(final List<String> playerNames) {
        final CardDeck cardDeck = CardDeck.init();
        final Dealer dealer = new Dealer(cardDeck.provideInitCards());
        final List<Player> players = provideInitCardsToPlayers(playerNames, cardDeck);
        return new BlackJackGame(cardDeck, dealer, new Players(players));
    }

    private static List<Player> provideInitCardsToPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> Player.newInstance(name, cardDeck.provideInitCards()))
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
        return PlayerInfo.dealerToInitInfo(dealer);
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
        final Map<String, GameOutcome> playerResults = players.getValues().stream()
                .collect(Collectors.toUnmodifiableMap(Player::getName, dealer::judgeOutcomeOfPlayer));
        return OutComeResult.from(playerResults);
    }
}
