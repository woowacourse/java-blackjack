package controller;

import assembler.OutputDtoAssembler;
import domain.Deck;
import domain.Game;
import domain.Judge;
import domain.Player;
import factory.CardFactory;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackJackController {

    private final Supplier<Deck> deckSupplier;

    public BlackJackController(Supplier<Deck> deckSupplier){
        this.deckSupplier = deckSupplier;
    }

    public void run() {
        List<String> playerNames = InputView.askPlayerNames();
        Game game = new Game(playerNames, deckSupplier.get());
        OutputView.printInitMessage(OutputDtoAssembler
                .toBlackJackInitStatusDto(game.getDealer(),game.getPlayers()));

        playPlayers(game);
        playDealer(game);

        Judge judge = new Judge(game.getDealer(), game.getPlayers());
        OutputView.printFinalResult(OutputDtoAssembler
                .toFinalResultDto(game.getDealer(),game.getPlayers(), judge));
    }

    private void playPlayers(Game game){
        for(Player player : game.getPlayers()){
            playPlayer(game, player);
        }
    }

    private void playPlayer(Game game, Player player){
        while(player.canHit()){
            String yesNoInput = InputView.askPlayerCommand(player.getName());
            if(yesNoInput.equals("n")){
                return;
            }
            game.hitPlayer(player);
            OutputView.printHandOutput(OutputDtoAssembler.toPlayerHandDto(player));
        }
    }

    private void playDealer(Game game){
        while(game.dealerShouldHit()){
            OutputView.printDealerHitMessage();
            game.hitDealer();
        }
    }
}
