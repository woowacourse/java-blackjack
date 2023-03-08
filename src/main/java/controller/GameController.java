package controller;

import domain.BlackJackGame.BlackJackAction;
import domain.BlackJackGame.Game;
import domain.BlackJackGame.GameResult;
import domain.BlackJackGame.GameResult.Result;
import domain.Card.Deck;
import domain.user.Participants;
import domain.user.Playable;
import domain.user.Player;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import view.InputView;
import view.OutputView;

public class GameController {
    
    public void run(Deck deck) {
        deck.shuffle();
        Game game = new Game(this.readPlayerNamesUntilValid(), deck);
        this.startGame(game);
        this.runGame(game);
        this.endGame(game);
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
        boolean isDealContinued;
        do {
            isDealContinued = function.apply(participant);
        } while (participant.isAbleToDraw() && isDealContinued);
    }
    
    private boolean dealPlayerMoreCard(Game game, Playable player) {
        boolean isHit = InputView.readAnswerForMoreCard(player.getName());
        if (BlackJackAction.of(isHit) == BlackJackAction.HIT) {
            game.deal(player);
            OutputView.printNameAndCards(player.getName(), player.getCards());
            return true;
        }
        return false;
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
        HashMap<Result, Integer> dealerResult = gameResult.generateDealerResult();
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
