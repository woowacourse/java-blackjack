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
import java.util.function.Consumer;
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
    
    private void loopUntilDealStop(Playable participant, Consumer<Playable> consumer) {
        do {
            consumer.accept(participant);
        } while (participant.isAbleToDraw());
    }
    
    private void dealPlayerMoreCard(Game game, Playable player) {
        boolean isHit = InputView.readAnswerForMoreCard(player.getName());
        if (BlackJackAction.of(isHit) == BlackJackAction.HIT) {
            game.deal(player);
            OutputView.printNameAndCards(player.getName(), player.getCards());
        }
    }
    
    private void dealDealerMoreCard(Game game, Playable dealer) {
        game.deal(dealer);
        OutputView.printDealerReceivedCard();
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
