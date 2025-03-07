package blackjack.controller;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
//        List<Nickname> nicknames = inputView.readNicknames().stream().map(Nickname::new).collect(Collectors.toList());
//
//        CardManager cardManager = new CardManager();
//        GameManager gameManager = new GameManager(cardManager);
//        gameManager.registerPlayers(nicknames);
//        gameManager.distributeCards();
//
//        List<Player> players = gameManager.getPlayers();
//        Player dealer = new Player(Nickname.createDealerNickname());
//
//        outputView.printCardHeader(players);
//        outputView.printCard(dealer.getNickname(), List.of(gameManager.findCardsByPlayer(dealer).getFirst()));
//
//        for (Player player : players) {
//            List<Card> cards = gameManager.findCardsByPlayer(player);
//
//            outputView.printCard(player.getNickname(), cards);
//        }
//
//        for (Player player : players) {
//            while (!gameManager.isBustPlayer(player) && inputView.readWannaHit(player.getNickname())) {
//                gameManager.hit(player);
//                outputView.printCard(player.getNickname(), gameManager.findCardsByPlayer(player));
//            }
//        }
//
//        int count = gameManager.drawDealerCards();
//        outputView.printDealerHit(count);
//
//        List<DrawnCardResult> drawnCardResults = gameManager.calculateDrawnCardResults();
//        outputView.printDrawnCardResults(drawnCardResults);
//
//        PlayerWinningStatistics statistics = gameManager.calculateGameResult();
//        outputView.printPlayerWinningResults(statistics);
    }

}
