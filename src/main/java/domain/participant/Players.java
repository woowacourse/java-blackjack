package domain.participant;

import domain.BlackJackGame;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public void initDistribute() {
        for (Player player : players) {
            BlackJackGame.distributeCard(player, 2);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void askEachPlayers() {
        System.out.println();
        for (Player player : players) {
            askPlayerDistribute(player);
        }
    }

    private void askPlayerDistribute(Player player) {
        try {
            checkAdditionalDistribute(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            askPlayerDistribute(player);
        }
    }

    private void checkAdditionalDistribute(Player player) {
        do {
            OutputView.printInputReceiveYesOrNotMessage(player.getName());
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        } while (player.playerAbleToDraw() && isReceivable(player));
    }

    private boolean isReceivable(Player player) {
        if (InputView.inputReceiveOrNot()) {
            BlackJackGame.distributeCard(player, 1);
            return true;
        }
        return false;
    }

    public void printInitPlayerCards() {
        for (Player player : players) {
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        }
    }

    public void printFinalPlayerResults()  {
        for (Player player : players) {
            ResultView.printParticipantFinalResult(player.getName(), player.getCardNames(), player.calculateScore());
        }
    }
}
