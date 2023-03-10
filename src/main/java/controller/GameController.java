package controller;

import domain.Card.Deck;
import domain.game.Game;
import domain.game.GameAction;
import domain.game.GameResult;
import domain.game.ResultStatus;
import domain.user.Participants;
import domain.user.Playable;
import domain.user.Player;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import view.InputView;
import view.OutputView;

public class GameController {
    
    public void run() {
        Game game = new Game(this.readPlayerNamesUntilValid(), this.getShuffledDeck());
        this.startGame(game);
        this.runGame(game);
        this.endGame(game);
    }
    
    private Deck getShuffledDeck() {
        Deck deck = new Deck();
        deck.shuffle();
        return deck;
    }
    
    private void startGame(Game game) {
        game.start();
        OutputView.printReadyMessage(game.getParticipants());
        OutputView.printReadyParticipantsNameAndCards(game.getParticipants());
    }
    
    private void runGame(Game game) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            this.loopUntilDealStop(player, p -> this.dealPlayerMoreCard(game, p));
        }
        this.loopUntilDealStop(game.getDealer(), d -> this.dealDealerMoreCard(game, d));
    }
    
    private void loopUntilDealStop(Playable participant, Function<Playable, Boolean> function) {
        boolean isDealContinued = true;
        while (participant.isAbleToDraw() && isDealContinued) {
            isDealContinued = function.apply(participant);
        }
    }
    
    private boolean dealPlayerMoreCard(Game game, Playable player) {
        boolean isHit = this.readAnswerMoreCardUntilValid(player.getName());
        if (GameAction.of(isHit) == GameAction.HIT) {
            game.deal(player);
            OutputView.printNameAndCards(player.getName(), player.getCards());
            return true;
        }
        return false;
    }
    
    private boolean readAnswerMoreCardUntilValid(String name) {
        try {
            return InputView.readAnswerForMoreCard(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this.readAnswerMoreCardUntilValid(name);
        }
    }
    
    private boolean dealDealerMoreCard(Game game, Playable dealer) {
        game.deal(dealer);
        OutputView.printDealerReceivedCard();
        return true;
    }
    
    
    private void endGame(Game game) {
        final Participants participants = game.getParticipants();
        OutputView.printParticipantsNameCardsAndScore(participants);
        GameResult gameResult = game.generateGameResult();
        Map<ResultStatus, Integer> dealerResult = gameResult.getDealerResult();
        OutputView.printDealerGameResult(dealerResult);
        OutputView.printPlayersGameResult(gameResult.getResultMap());
    }
    
    private String readPlayerNamesUntilValid() {
        try {
            return InputView.readPlayerNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this.readPlayerNamesUntilValid();
        }
    }
}
