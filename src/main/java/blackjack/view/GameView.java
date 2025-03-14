package blackjack.view;

import blackjack.card.CardDeck;
import blackjack.game.BlackjackGame;
import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import blackjack.game.betting.BetAmount;
import java.util.List;
import java.util.Map;

public class GameView {

    private final InputView inputView;
    private final OutputView outputView;

    public GameView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void displayToConsole() {
        BlackjackGame blackjackGame = enterParticipants();
        BlackjackGame blackjackBettingGame = collectBetAmountFromPlayers(blackjackGame);

        distributeInitialCards(blackjackBettingGame);
        distributeAdditionalCards(blackjackBettingGame);

        showFinalCards(blackjackBettingGame);
        showFinalProfits(blackjackBettingGame);
    }

    private BlackjackGame enterParticipants() {
        inputView.printMessage("게임에 참여할 사람의 이름을 영어/한글로 입력하세요. 최대 25명 참가 가능합니다.(쉼표 기준으로 분리)");
        List<PlayerName> names = inputView.readNames();

        return BlackjackGame.createWithEmptyBet(CardDeck.shuffleCardDeck(), names);
    }

    private BlackjackGame collectBetAmountFromPlayers(final BlackjackGame blackjackGame) {
        List<PlayerName> names = blackjackGame.getPlayerNames();
        Map<PlayerName, BetAmount> playerAmounts = inputView.readPlayerPrincipals(names);

        return BlackjackGame.createWithActiveBet(CardDeck.shuffleCardDeck(), playerAmounts);
    }

    private void distributeInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.initCardsToParticipants();

        outputView.printInitDistributionMessage(blackjackGame.getPlayerNames());
        outputView.printDealerCardResult(blackjackGame.getParticipants().getDealer());
        outputView.printPlayersCardResult(blackjackGame.getParticipants().getPlayers());
    }

    private void distributeAdditionalCards(final BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getParticipants().getPlayers()) {
            handleExtraCardError(() -> distributeAdditionalCardsToPlayer(player, blackjackGame));
        }
        handleExtraCardError(() -> distributeAdditionalCardsToDealer(blackjackGame.getParticipants().getDealer(), blackjackGame));
    }

    private void distributeAdditionalCardsToPlayer(final Player player, final BlackjackGame blackjackGame) {
        while (inputView.readGetOneMore(player)) {
            blackjackGame.addExtraCardToPlayer(player);
            outputView.printPlayerCardResult(player);
        }
    }

    private void distributeAdditionalCardsToDealer(final Dealer dealer, final BlackjackGame blackjackGame) {
        while (dealer.isPossibleToAdd()) {
            blackjackGame.addExtraCardToDealer(dealer);
            outputView.printAddExtraCardToDealer();
        }
    }

    private void handleExtraCardError(final Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            inputView.printMessage(e.getMessage());
        }
    }

    private void showFinalCards(final BlackjackGame blackjackGame) {
        outputView.printDealerFinalCardResult(blackjackGame.getParticipants().getDealer());
        outputView.printPlayersFinalCardResult(blackjackGame.getParticipants().getPlayers());
    }

    private void showFinalProfits(final BlackjackGame blackjackGame) {
        Map<PlayerName, BetAmount> playersProfit = blackjackGame.calculateProfitForPlayers();
        int dealerProfit = -playersProfit.values().stream()
            .mapToInt(BetAmount::getProfit)
            .sum();

        outputView.printProfitResultTitle();
        outputView.printDealerResult(dealerProfit);
        outputView.printPlayerResult(playersProfit);
    }
}
