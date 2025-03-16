package blackjack.view;

import blackjack.card.CardDeck;
import blackjack.game.BlackjackGame;
import blackjack.user.player.Player;
import blackjack.user.player.BetAmount;
import blackjack.user.player.Players;

public class GameView {

    private final InputView inputView;
    private final OutputView outputView;

    public GameView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void displayToConsole() {
        BlackjackGame game = enterParticipants();

        distributeInitialCards(game);
        distributeAdditionalCards(game);

        showFinalCardsAndProfits(game);
    }

    private BlackjackGame enterParticipants() {
        outputView.printEnterPlayers();
        Players players = inputView.readPlayers();

        for (Player player : players.getJoinedPlayers()) {
            outputView.printBettingAmountQuestion(player.getName());
            BetAmount amountWithPrincipal = inputView.readPlayerPrincipal();
            player.updateAmount(amountWithPrincipal);
        }

        return BlackjackGame.createGameWith(CardDeck.shuffleCardDeck(), players);
    }

    private void distributeInitialCards(final BlackjackGame game) {
        game.initCardsToUsers();

        outputView.printInitDistributionMessage(game.getPlayerNames());
        outputView.printDealerInitialCardResult(game.getDealer());
        outputView.printPlayersInitialCardResult(game.getPlayers());
    }

    private void distributeAdditionalCards(final BlackjackGame game) {
        for (Player player : game.getPlayers().getJoinedPlayers()) {
            outputView.printAddExtraCardToPlayer(player.getName());
            handleExtraCardError(() -> distributeAdditionalCardsToPlayer(player, game));
        }

        while (game.getDealer().getCardHand().isPossibleToAdd()) {
            handleExtraCardError(game::addExtraCardToDealer);
            outputView.printAddExtraCardToDealer();
        }
    }

    private void distributeAdditionalCardsToPlayer(final Player player, final BlackjackGame game) {
        while (inputView.readGetOneMore()) {
            game.addExtraCardToPlayer(player.getName());
            outputView.printPlayerCardResult(player);
        }
    }

    private void handleExtraCardError(final Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e.getMessage());
        }
    }

    private void showFinalCardsAndProfits(final BlackjackGame game) {
        outputView.printDealerFinalCardResult(game.getDealer());
        outputView.printPlayersFinalCardResult(game.getPlayers());

        int playersProfit = game.calculateProfitForPlayers();
        int dealerProfit = -playersProfit;

        outputView.printProfitResultTitle();
        outputView.printDealerProfitResult(dealerProfit);
        outputView.printPlayerProfitResult(game.getPlayers());
    }
}
