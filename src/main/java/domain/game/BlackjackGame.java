package domain.game;

import domain.card.DeckGenerator;
import domain.card.GameDeck;
import domain.dto.UserDto;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
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

    public Map<Name, Integer> calculatePlayerResult() {
        GameResult gameResult = new GameResult(getAllUserNames().subList(1, getAllUserNames().size()));
        gameResult.saveResults(dealer, players);
        return gameResult.getPlayerPrizes();
    }

    // Todo: calculatePlayerResult 리팩토링 후 없애도 될지도?
    private List<Name> getAllUserNames() {
        List<Name> allUserNames = new ArrayList<>(List.of(dealer.getName()));
        allUserNames.addAll(players.getAllNames());

        return allUserNames;
    }

    public int getDealerDrawCount() {
        return dealer.drawCount();
    }

    public UserDto getDealerSetUpDto() {
        return dealer.getDealerSetUpDto();
    }

    public UserDto getDealerDto() {
        return dealer.getUserDto();
    }

    // todo : 준비된 플레이어를 넘겨주는 것보다 이름을 넘겨받아서 외부에서 순회하도록 할까?
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
