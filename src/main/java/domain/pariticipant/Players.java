package domain.pariticipant;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

import static constant.BlackjackConstant.MAXIMUM_PLAYER_BOUND;
import static constant.BlackjackConstant.MINIMUM_PLAYER_BOUND;
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
}
