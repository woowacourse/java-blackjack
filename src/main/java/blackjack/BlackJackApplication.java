package blackjack;

import blackjack.controller.BlackJackGame;
import blackjack.model.game.BettingResult;
import blackjack.model.game.Deck;
import blackjack.model.game.DeckInitializer;
import blackjack.model.game.HitOrStand;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        List<String> names = inputView.inputParticipant();
        List<Player> playersList = new ArrayList<>();

        for (String name : names) {
            playersList.add(new Player(name, inputView.inputBetting(name)));
        }

        Players players = new Players(playersList);
        Deck deck = new DeckInitializer().generateDeck();
        Dealer dealer = new Dealer();

        BlackJackGame blackJackGame = new BlackJackGame(players, deck, dealer);
        blackJackGame.initializeGame(players, dealer);
        outputView.outputFirstCardDistributionResult(players, dealer);

        for (Player player : players.getParticipants()) {
            while (HitOrStand.from(inputView.inputHitOrStand(player.getName())) == HitOrStand.HIT) {
                if (blackJackGame.isBustAfterDraw(player)) {
                    outputView.printPlayerCardStatus(player.getName(), player);
                    outputView.printParticipantBust(player.getName());
                    break;
                }
                outputView.printPlayerCardStatus(player.getName(), player);

            }
        }
        while (blackJackGame.isDealerUnderNumber(dealer)) {
            blackJackGame.giveDealerCard(dealer, deck);
            outputView.outputDealerGetCard();
            outputView.printPlayerCardStatus("딜러", dealer);
        }
        outputView.outputDealerCardFinish();

        BettingResult bettingResult = new BettingResult(dealer.calculateGameResult(players));

        outputView.outputFinalCardStatus(dealer, players);
        outputView.outputFinalProfit(bettingResult.getBettingResult(), bettingResult.getDealerResult());

    }
}
