package domain;

import domain.box.BoxResult;
import domain.box.Boxes;
import domain.card.Deck;
import domain.user.Player;
import dto.ParticipantDTO;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int DEALER_THRESHOLDS = 16;
    private final Deck deck = new Deck();
    private final Boxes boxes;

    public BlackJackGame(String participantNames) {
        this.boxes = Boxes.of(participantNames);
    }

    public void initializeHand() {
        deck.shuffle();
        List<Player> playersAndDealerAtLast = boxes.getPlayersAndDealerAtLast();
        dealToAll(playersAndDealerAtLast);
        dealToAll(playersAndDealerAtLast);
    }

    private void dealToAll(List<Player> targets) {
        targets.forEach(this::dealTo);
        targets.forEach(boxes::updatePlayerBox);
    }

    private void dealTo(Player participant) {
        participant.dealt(deck.draw());
    }

    public void playTurn(Player player, TurnAction turnAction) {
        if (turnAction == TurnAction.HIT) {
            dealTo(player);
        }
        boxes.updatePlayerBox(player);
    }

    public Player getCurrentPlayer() {
        return boxes.getCurrentTurnPlayer();
    }

    public boolean isDealerUnderThresholds(Player dealer) {
        return dealer.calculatePoint() <= DEALER_THRESHOLDS;
    }

    public void makeParticipants(ParticipantDTO participantDTO) {
        boxes.setParticipantDTO(participantDTO);
    }

    public BoxResult getDealerBoxResult(List<BoxResult> playerBoxResults) {
        BoxResult dealerBoxResult = new BoxResult(0, 0);
        for (BoxResult playerBoxResult : playerBoxResults) {
            dealerBoxResult = dealerBoxResult.addReversed(playerBoxResult);
        }
        return dealerBoxResult;
    }

    public List<BoxResult> getPlayerBoxResults(List<Player> players) {
        return players.stream().map(boxes::getBoxResult).collect(Collectors.toList());
    }
}
