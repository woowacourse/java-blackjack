package blackjack;

import blackjack.model.game.BettingResult;
import blackjack.model.game.BlackJackGame;
import blackjack.model.game.Deck;
import blackjack.model.game.DeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.HitOrStand;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackApplication app = new BlackJackApplication();
        app.run();
    }

    private void run() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Players players = initializeGame(inputView);
        Deck deck = new DeckInitializer().generateDeck();
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame(players, deck, dealer);

        blackJackGame.initializeGame(players, dealer);
        outputView.outputFirstCardDistributionResult(players, dealer);

        playPlayersTurn(players, blackJackGame, inputView, outputView);
        playDealerTurn(dealer, blackJackGame, deck, outputView);

        printGameResult(dealer, players, outputView);
    }

    private Players initializeGame(InputView inputView) {
        List<String> names = inputView.inputParticipant();
        List<Player> playersList = new ArrayList<>();

        for (String name : names) {
            playersList.add(new Player(name, inputView.inputBetting(name)));
        }

        return new Players(playersList);
    }

    private void playPlayersTurn(Players players, BlackJackGame blackJackGame, InputView inputView,
                                 OutputView outputView) {
        players.getParticipants().forEach(player -> playTurnForPlayer(player, blackJackGame, inputView, outputView));
    }

    private void playTurnForPlayer(Player player, BlackJackGame blackJackGame, InputView inputView,
                                   OutputView outputView) {
        while (shouldHit(player, inputView)) {
            if (processHit(player, blackJackGame, outputView)) {
                return;
            }
        }
    }

    private boolean shouldHit(Player player, InputView inputView) {
        return HitOrStand.from(inputView.inputHitOrStand(player.getName())) == HitOrStand.HIT;
    }

    private boolean processHit(Player player, BlackJackGame blackJackGame, OutputView outputView) {
        if (blackJackGame.isBustAfterDraw(player)) {
            outputView.printPlayerCardStatus(player.getName(), player);
            outputView.printParticipantBust(player.getName());
            return true;
        }
        outputView.printPlayerCardStatus(player.getName(), player);
        return false;
    }


    private void playDealerTurn(Dealer dealer, BlackJackGame blackJackGame, Deck deck, OutputView outputView) {
        while (blackJackGame.isDealerUnderNumber(dealer)) {
            blackJackGame.giveDealerCard(dealer, deck);
            outputView.outputDealerGetCard();
            outputView.printPlayerCardStatus("딜러", dealer);
        }
        outputView.outputDealerCardFinish();
    }

    private void printGameResult(Dealer dealer, Players players, OutputView outputView) {
        BettingResult bettingResult = new BettingResult(dealer.calculateGameResult(players));

        outputView.outputFinalCardStatus(dealer, players);
        outputView.outputFinalProfit(bettingResult.getBettingResult(), bettingResult.getDealerResult());
    }
}
