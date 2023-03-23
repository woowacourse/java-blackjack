package domain.game;

import domain.user.PlayerBets;
import domain.card.DeckGenerator;
import domain.card.GameDeck;
import domain.dto.UserDto;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Players;

import java.util.List;

public final class BlackjackGame {
    private final Players players;
    private final Dealer dealer;
    private final GameDeck gameDeck;

    public BlackjackGame(Players players, DeckGenerator deckGenerator) {
        this.players = players;
        this.dealer = new Dealer();
        this.gameDeck = new GameDeck(deckGenerator);

        setUp();
    }

    private void setUp() {
        dealer.receiveCards(gameDeck.drawForFirstTurn());
        players.setUpGame(gameDeck);
    }

    public void drawOneMoreCardForPlayer(Name playerName) {
        players.drawOneMoreCard(playerName, gameDeck.drawCard());
    }

    public void drawCardUntilDealerFinished() {
        boolean flag = dealer.hasResult();
        while (!flag) {
            dealer.receiveCard(gameDeck.drawCard());
            flag = dealer.hasResult();
        }
    }

    public void doStay(Name playerName) {
        players.doStay(playerName);
    }

    public boolean hasReadyPlayer() {
        return players.hasReadyPlayer();
    }

    public boolean hasPlayerResult(Name playerName) {
        return players.hasPlayerResult(playerName);
    }

    public GameResult calculateGameResult(PlayerBets playerBets) {
        GameResult gameResult = new GameResult(getDealerName(), getAllPlayerNames(), playerBets);
        gameResult.saveResults(dealer, players);
        return gameResult;
    }

    public List<Name> getAllPlayerNames() {
        return players.getAllNames();
    }

    public int getDealerDrawCount() {
        return dealer.drawCount();
    }

    private Name getDealerName() {
        return dealer.getName();
    }

    public UserDto getDealerSetUpDto() {
        return dealer.getDealerSetUpDto();
    }

    public UserDto getDealerDto() {
        return dealer.getUserDto();
    }

    public UserDto getReadyPlayerDto() {
        return players.getReadyPlayerDto();
    }

    public UserDto getPlayerDtoByName(Name playerName) {
        return players.getPlayerDtoByName(playerName);
    }

    public List<UserDto> getAllPlayerDtos() {
        return players.getAllPlayerDtos();
    }


}
