package controller;

import domain.AnswerCommand;
import domain.GameManager;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.GamerGenerator;
import domain.gamer.Player;
import java.util.List;

import util.LoopTemplate;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void gameStart() {
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Dealer dealer = GamerGenerator.generateDealer(randomCardGenerator);
        final List<Player> players = requestPlayers(randomCardGenerator);
        final GameManager gameManager = GameManager.create(dealer, players);

        gameManager.giveCardToAllGamer();
        requestHit(dealer, players,gameManager);
        printDealerReceiveCardCount(gameManager);

        outputView.printGamerCardsAndScore(dealer, players);
        outputView.printGameResult(gameManager.calculateDealerGameResult(), gameManager.calculatePlayerGameResult());
    }

    private List<Player> requestPlayers(RandomCardGenerator randomCardGenerator) {
        return LoopTemplate.tryCatchLoop(() ->
        {
            final List<String> playerNames = LoopTemplate.tryCatchLoop(inputView::readPlayers);
            return GamerGenerator.generatePlayer(playerNames, randomCardGenerator);
        });
    }

    private void requestHit(Dealer dealer, List<Player> players, GameManager gameManager) {
        outputView.printDealerAndPlayersCards(dealer, players);
        for (int index = 0; index < players.size(); index++) {
            final Player player = gameManager.findPlayerByIndex(index);
            if(!player.isBust()){
                receiveCardIsAbleToGetCard(player);
            }
        }
    }

    private void receiveCardIsAbleToGetCard(Player player){
        if(requestAnswerCommand(player) == AnswerCommand.NO){
            outputView.printPlayerCards(player);
            return;
        }
        do {
            player.receiveCard();
            outputView.printPlayerCards(player);
        }while(!player.isBust() && requestAnswerCommand(player) == AnswerCommand.YES);
    }

    private AnswerCommand requestAnswerCommand(Player player) {
        return LoopTemplate.tryCatchLoop(() -> inputView.readAnswer(player.getName()));
    }

    private void printDealerReceiveCardCount(GameManager gameManager){
        final int count = gameManager.giveCardsToDealer();
        if (count > 0) {
            outputView.printDealerReceivedCardCount(count);
        }
    }
}
