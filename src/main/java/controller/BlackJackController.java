package controller;

import domain.BlackJackGame;
import domain.Dealer;
import domain.Decks;
import domain.Gamer;
import domain.Gamers;
import domain.Name;
import domain.Player;
import domain.PlayerResults;
import domain.Players;
import domain.strategy.ShuffledDecksGenerator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public BlackJackController() {
    }

    public void start() {
        List<String> names = InputView.readPlayerNames();
        Players players = createPlayers(names);
        Dealer dealer = new Dealer();
        Gamers gamers = Gamers.of(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(Decks.createByStrategy(new ShuffledDecksGenerator()));
        blackJackGame.prepareCards(gamers);
        OutputView.printHandOutCardMessage(dealer, players);

        for (Player player : players.getPlayers()) {
            boolean retry = false;
            do {
                String selection = InputView.readSelectionOf(player);
                if(selection.equals("y")){
                    retry = blackJackGame.succeededGiving(player);
                    OutputView.printAllCards(player);
                }
                if (selection.equals("n") ) {
                    retry = false;
                }
            } while (retry && !player.isBust());
        }

        do {
            boolean retry = blackJackGame.succeededGiving(dealer);
            if(!retry){
                break;
            }
            OutputView.printDealerHit(dealer);
        } while (true);
        for (Gamer gamer : gamers.getGamers()) {
            OutputView.printCardsAndResult(gamer);
        }
        PlayerResults playerResults = blackJackGame.findPlayerResult(gamers);

        OutputView.printFinalGameResult(playerResults);
    }

    private static Players createPlayers(final List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(players);
    }

}
