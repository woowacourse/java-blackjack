package domain.pariticipant;

import domain.card.Deck;
import domain.result.MatchCase;
import domain.result.PlayersMatchResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static constant.BlackjackConstant.MAXIMUM_PLAYER_BOUND;
import static constant.BlackjackConstant.MINIMUM_PLAYER_BOUND;
import static domain.result.MatchCase.WIN;
import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;

public record Players(List<Player> players) {
    public Players(List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_BOUND || players.size() > MAXIMUM_PLAYER_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
        this.players = new ArrayList<>(players);
    }

    @Override
    public List<Player> players() {
        return List.copyOf(players);
    }

    public void drawInitialCards(Deck deck) {
        for (Player player : players) {
            player.drawInitialCards(deck);
        }
    }

    // players -> player
    public PlayersMatchResult calculateMatchResult(Dealer dealer) {
        Map<Player, MatchCase> playerMatchResult = new LinkedHashMap<>();
        for (Player player : players) {
            if (player.isLose(dealer)) { // 딜러승, 플레이어 패배
                playerMatchResult.put(player, MatchCase.LOSE);
                continue;
            }
            if (player.isDraw(dealer)) { // 무승부
                playerMatchResult.put(player, MatchCase.DRAW);
                continue;
            }
            playerMatchResult.put(player, WIN);
        }
        return new PlayersMatchResult(playerMatchResult);
    }

}
