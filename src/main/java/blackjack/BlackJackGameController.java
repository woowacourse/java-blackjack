package blackjack;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.Money;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.PlayerResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
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
        Betting betting = bettingAllPlayer(game);
        drawStartCards(game);
        drawAdditionalCard(game);
        printResult(game.getDealer(), game.getPlayers());
        printPrize(betting, game);
    }

    private void printPrize(Betting betting, BlackJackGame game) {
        List<PlayerResult> allGameResults = game.getAllGameResults();
        outputView.printPrizeTitle();
        Money dealerPrize = calculateDealerPrize(allGameResults, betting);
        outputView.printDealerPrize(dealerPrize);
        for (PlayerResult gameResult : allGameResults) {
            Money prize = betting.getPrize(gameResult);
            outputView.printPlayerPrize(gameResult.getName(), prize);
        }
    }

    private Money calculateDealerPrize(List<PlayerResult> playerResults, Betting betting) {
        Money dealerPrize = Money.ZERO;
        for (PlayerResult gameResult : playerResults) {
            Money prize = betting.getPrize(gameResult);
            dealerPrize = dealerPrize.subtract(prize);
        }
        return dealerPrize;
    }

    //TODO 여기도 String VS Name
    private Betting bettingAllPlayer(BlackJackGame game) {
        Betting betting = new Betting();
        List<String> playerNames = game.getPlayerNames();
        for (String playerName : playerNames) {
            int bettingAmount = inputView.inputBettingAmount(playerName);
            betting.bet(new Name(playerName), new Money(bettingAmount));
        }
        return betting;
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
        while (game.isDealerDrawable()) {
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
    }
}
