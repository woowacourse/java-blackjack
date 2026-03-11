package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Role;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerBettingRequest;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.PlayersBettingRequest;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public static Players makePlayers(PlayersBettingRequest playersBettingRequest) {
        List<Player> result = new ArrayList<>();
        for (PlayerBettingRequest playerBettingRequest : playersBettingRequest.value()) {
            String nickname = playerBettingRequest.playerNickname();
            long amount = playerBettingRequest.amount();
            result.add(new Player(nickname, Role.PLAYER, amount));
        }
        return from(result);
    }

    public List<String> getAllPlayerNickname() {
        List<String> result = new ArrayList<>();
        for (Player player : players) {
            result.add(player.getNickname());
        }
        return result;
    }

    public List<Player> getAllPlayers() {
        return List.copyOf(players);
    }

    public String findDrawablePlayerNickname() {
        Player player = findDrawablePlayer();
        if (player == null) {
            return null;
        }
        return player.getNickname();
    }

    public PlayingCards addCardToAvailablePlayer(PlayingCards card) throws IllegalArgumentException {
        Player player = findDrawablePlayer();
        return player.receiveCard(card);
    }

    public Player findDrawablePlayer() {
        return players.stream()
            .filter(Player::isDrawable)
            .findFirst()
            .orElse(null);
    }

    public void dontWantDraw() {
        Player player = findDrawablePlayer();
        player.stop();
    }

    public List<PlayerGameResult> getWinningResultsWithDealer(Dealer dealer) {
        List<PlayerGameResult> result = new ArrayList<>();
        for (Player player : players) {
            PlayerGameResult playerGameResult = player.determinePlayerResult(dealer);
            result.add(playerGameResult);
        }
        return result;
    }

    public List<ParticipantResult> getInitialResult() {
        List<ParticipantResult> initialResults = new ArrayList<>();
        for (Player player : players) {
            initialResults.add(ParticipantResult.from(player));
        }
        return initialResults;
    }
}
