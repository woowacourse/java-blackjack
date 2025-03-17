package blackjack.view;

import blackjack.card.CardDeck;
import blackjack.game.BlackjackGame;
import blackjack.user.player.Player;
import blackjack.user.player.BetAmount;
import blackjack.user.player.Players;

public class GameView {

    private final InputView inputView;
    private final OutputView outputView;

    public GameView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public BlackjackGame enterParticipants() {
        outputView.printEnterPlayers();
        Players players = inputView.readPlayers();

        for (Player player : players.getJoinedPlayers()) {
            outputView.printBettingAmountQuestion(player.getName());
            BetAmount amountWithPrincipal = inputView.readPlayerPrincipal();
            player.updateAmount(amountWithPrincipal);
        }

        return BlackjackGame.createGameWith(CardDeck.shuffleCardDeck(), players);
    }

    public void distributeInitialCards(final BlackjackGame game) {
        game.initCardsToUsers();
        outputView.printInitDistributionResult(game.getDealer(), game.getPlayers());
    }

    public void distributeAdditionalCards(final BlackjackGame game) {
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
            System.out.println(e.getMessage());
        }
    }

    public void showFinalCardsAndProfits(final BlackjackGame game) {
        outputView.printFinalCardResult(game.getDealer(), game.getPlayers());

        int playersProfit = game.calculateProfitForPlayers();
        int dealerProfit = -playersProfit;
        outputView.printProfitResult(dealerProfit, game.getPlayers());
    }
}
