package controller;

import domain.card.Deck;
import domain.game.Game;
import domain.game.GameAction;
import domain.game.GameBet;
import domain.money.Bet;
import domain.result.ProfitResult;
import domain.user.GameMember;
import domain.user.Playable;
import domain.user.Player;
import java.util.List;
import java.util.function.Function;
import view.InputView;
import view.OutputView;
import view.ProfitView;

public class GameController {
    
    public void run() {
        Game game = new Game(this.generateGameMemberUntilValid(), this.getShuffledDeck());
        GameBet gameBet = this.collectBets(game);
        this.startGame(game);
        this.runGame(game);
        this.endGameWithProfit(game, gameBet);
    }
    
    private GameBet collectBets(final Game game) {
        GameBet gameBet = new GameBet();
        for (Playable player : game.getPlayers()) {
            gameBet.accumulate(player, this.generateBetUntilValid(player.getName()));
        }
        return gameBet;
    }
    
    private Bet generateBetUntilValid(final String name) {
        try {
            return new Bet(InputView.readBet(name));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this.generateBetUntilValid(name);
        }
    }
    
    private GameMember generateGameMemberUntilValid() {
        try {
            return GameMember.of(InputView.readPlayerNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this.generateGameMemberUntilValid();
        }
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
        GameAction action = this.generateGameActionUntilValid(player.getName());
        if (action.isHit()) {
            game.deal(player);
            OutputView.printNameAndCards(player.getName(), player.getCards());
            return true;
        }
        return false;
    }
    
    private GameAction generateGameActionUntilValid(String name) {
        try {
            return GameAction.from(InputView.readAnswerForMoreCard(name));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this.generateGameActionUntilValid(name);
        }
    }
    
    private boolean dealDealerMoreCard(Game game, Playable dealer) {
        game.deal(dealer);
        OutputView.printDealerReceivedCard();
        return true;
    }
    
    
    private void endGameWithProfit(Game game, GameBet gameBet) {
        OutputView.printParticipantsNameCardsAndScore(game.getParticipants());
        ProfitResult profitResult = ProfitResult.create(gameBet, game.generateGameResult());
        ProfitView.printProfitResult();
        ProfitView.printDealerProfitResult(game.getDealer().getName(), profitResult.getDealerProfit());
        ProfitView.printGameProfit(profitResult.getProfitMap());
    }
}
