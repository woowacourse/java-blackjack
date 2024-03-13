package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        BlackJackGame game = createBlackjackGame();
        drawStartCards(game);
        drawAdditionalCard(game);
        printResult(game.getDealer(), game.getPlayers());
    }

    private BlackJackGame createBlackjackGame() {
        List<String> playerNames = inputView.inputPlayerNames();
        BlackJackGame game = new BlackJackGame(playerNames);
        return game;
    }

    private void drawStartCards(BlackJackGame game) {
        game.drawStartCards();
        outputView.printStartStatus(game);
    }

    //TODO 아래 두 로직을 합칠 수 있을지 고민해보기 (요구사항)
    private void drawAdditionalCard(BlackJackGame game) {
        game.drawAdditionalCard(this::playTurn);
        while(game.isDealerDrawable()) {
            game.drawDealerCard();
            outputView.printDealerDraw();
        }
    }

    private void playTurn(Player player, Deck deck) {
        while (player.isDrawable() && inputView.isPlayerWantDraw(player.getName())) {
            player.add(deck.draw());
            outputView.printPlayerCards(player);
        }
    }

    //TODO 사용 파라미터 수정
    private void printResult(Dealer dealer, Players players) {
        outputView.printEndingStatus(dealer, players);
        int winCount = dealer.calculateWinCount(players);
        int loseCount = dealer.calculateLoseCount(players);
        outputView.printDealerMatchResult(winCount, loseCount);
        for (Player player : players.getPlayers()) {
            outputView.printPlayerMatchResult(player.getName(), player.isWin(dealer));
        }
    }
}
