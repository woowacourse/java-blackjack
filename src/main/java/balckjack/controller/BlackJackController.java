package balckjack.controller;

import balckjack.domain.Dealer;
import balckjack.domain.Money;
import balckjack.domain.Player;
import balckjack.domain.Players;
import balckjack.domain.Referee;
import balckjack.strategy.CardPicker;
import balckjack.util.Repeater;
import balckjack.view.InputView;
import balckjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run(CardPicker cardPicker) {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames,
            OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();

        initializeGame(cardPicker, players, dealer);
        tryPlayersTurn(cardPicker, players);
        tryDealerTurn(cardPicker, dealer);
        final Referee referee = new Referee(dealer.getCardDeck(), players.getPlayersDeck(),
            List.of(new Money(1000), new Money(1000), new Money(1500), new Money(2000)));
        reportResult(players, dealer, referee);
    }

    private void initializeGame(CardPicker cardPicker, Players players, Dealer dealer) {
        dealer.distributeCards(cardPicker);
        players.initHit(cardPicker);

        OutputView.printInitCardDeck(dealer, players);
    }

    private void tryPlayersTurn(CardPicker cardPicker, Players players) {
        for (Player player : players.getPlayers()) {
            tryPlayerTurn(player, cardPicker);
        }
    }

    private void tryPlayerTurn(Player player, CardPicker cardPicker) {
        if (isContinueHit(player, cardPicker) && !isBurst(player)) {
            tryPlayerTurn(player, cardPicker);
        }
    }

    private boolean isContinueHit(Player player, CardPicker cardPicker) {
        if (askIfContinue(player) == Command.NO) {
            return false;
        }
        player.hit(cardPicker);
        OutputView.printParticipantCardDeck(player);
        return true;
    }

    private Command askIfContinue(Player player) {
        return Repeater.repeatIfError(() -> inputCommand(player),
            OutputView::printErrorMessage);
    }

    private boolean isBurst(Player player) {
        if (player.getCardDeck().calculateScore().isBurst()) {
            OutputView.printBurstMessage();
            return true;
        }
        return false;
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }

    private void tryDealerTurn(CardPicker cardPicker, Dealer dealer) {
        while (dealer.isContinueDealerTurn()) {
            dealer.hit(cardPicker);
            OutputView.printDealerPickMessage(dealer);
        }
    }

    private void reportResult(Players players, Dealer dealer, Referee referee) {
        OutputView.printFinalCardDeck(dealer, players, referee);
        OutputView.printResult(referee.calculateDealerWinningMoney(),
            referee.calculateWinningMoneys(), dealer, players);
    }
}
