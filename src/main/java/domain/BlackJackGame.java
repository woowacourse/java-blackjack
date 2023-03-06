package domain;

import domain.box.GameBoxes;
import domain.card.Deck;
import domain.user.Player;
import dto.ParticipantDTO;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int DEALER_THRESHOLDS = 16;
    private final Deck deck = new Deck();
    private final GameBoxes gameBoxes;

    public BlackJackGame(String participantNames) {
        this.gameBoxes = GameBoxes.of(participantNames);
    }

    public void initializeHand() {
        deck.shuffle();
        List<Player> playersAndDealerAtLast = gameBoxes.getPlayersAndDealerAtLast();
        dealToAll(playersAndDealerAtLast);
        dealToAll(playersAndDealerAtLast);
    }

    private void dealToAll(List<Player> targets) {
        targets.forEach(this::dealTo);
        targets.forEach(gameBoxes::updatePlayerBox);
    }

    private void dealTo(Player participant) {
        participant.dealt(deck.draw());
    }

    public void playTurn(Player player, TurnAction turnAction) {
        if (turnAction == TurnAction.HIT) {
            dealTo(player);
        }
        gameBoxes.updatePlayerBox(player);
    }

    public Player getCurrentPlayer() {
        return gameBoxes.getCurrentTurnPlayer();
    }

    public boolean isDealerUnderThresholds(Player dealer) {
        return dealer.calculatePoint() <= DEALER_THRESHOLDS;
    }

    public void makeParticipants(ParticipantDTO participantDTO) {
        gameBoxes.setParticipantDTO(participantDTO);
    }

    public GameResult getDealerGameResult(List<GameResult> playerBoxResults) {
        GameResult dealerBoxResult = new GameResult(0, 0);
        for (GameResult playerBoxResult : playerBoxResults) {
            dealerBoxResult = dealerBoxResult.addReversed(playerBoxResult);
        }
        return dealerBoxResult;
    }

    public List<GameResult> getPlayerGameResults(List<Player> players) {
        return players.stream().map(gameBoxes::getGameResult).collect(Collectors.toList());
    }
}
