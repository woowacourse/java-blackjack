package domain;

import domain.box.BoxResult;
import domain.box.Boxes;
import domain.card.Deck;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private static final int DEALER_THRESHOLDS = 16;
    private static final int FIRST_PLAYER_INDEX = 1;
    private static final int DEALER_INDEX = 0;
    private final Deck deck = new Deck();
    private final Boxes boxes;

    public BlackJackGame(String participantNames) {
        this.boxes = Boxes.of(participantNames);
    }

    private void dealTo(Player participant) {
        participant.dealt(deck.draw());
    }

    public void dealInitialHand() {
        deck.shuffle();
        List<Player> playersAndDealerAtLast = boxes.getPlayersAndDealerAtLast();
        dealOnceTo(playersAndDealerAtLast);
        dealOnceTo(playersAndDealerAtLast);
        playersAndDealerAtLast.forEach(boxes::updatePlayerBox);
    }

    private void dealOnceTo(List<Player> targets) {
        targets.forEach(this::dealTo);
    }

    public void progressTurn(Player player, TurnAction turnAction) {
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

    public List<BoxResult> showResult() {
        List<Player> dealerAndPlayers = getPlayersAndDealerAtFirst();
        List<BoxResult> boxResults = new ArrayList<>();
        boxResults.add(new BoxResult(0, 0));
        for (int index = FIRST_PLAYER_INDEX; index < dealerAndPlayers.size(); index++) {
            BoxResult playerResult = boxes.getBoxResult(dealerAndPlayers.get(index));
            boxResults.add(playerResult);
            BoxResult updatedDealerResult = boxResults.get(DEALER_INDEX).addReversed(playerResult);
            boxResults.set(DEALER_INDEX, updatedDealerResult);
        }
        return boxResults;
    }

    public List<Player> getPlayersAndDealerAtFirst() {
        List<Player> playersAndDealer = boxes.getPlayersAndDealerAtLast();
        final int dealerIndex = playersAndDealer.size() - 1;
        Player dealer = playersAndDealer.remove(dealerIndex);
        playersAndDealer.add(0, dealer);
        return playersAndDealer;
    }
}
