package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Money;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Referee;
import blackjack.strategy.CardPicker;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run(CardPicker cardPicker) {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames,
            OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final List<Money> moneys = inputMoneys(players);

        initializeGame(cardPicker, players, dealer);
        playGame(cardPicker, players, dealer);
        final Referee referee = Referee.createJudged(dealer.getCardDeck(), players.getPlayersDeck(),
            moneys);
        reportGame(players, dealer, referee);
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private List<Money> inputMoneys(Players players) {
        return players.getPlayerNames().stream()
            .map((name) -> Repeater.repeatIfError(() -> generateMoney(name),
                OutputView::printErrorMessage))
            .collect(Collectors.toList());
    }

    private Money generateMoney(String name) {
        return new Money(InputView.inputMoneys(name));
    }

    private void initializeGame(CardPicker cardPicker, Players players, Dealer dealer) {
        dealer.distributeCards(cardPicker);
        players.initHit(cardPicker);

        OutputView.printInitCardDeck(dealer, players);
    }

    private void playGame(CardPicker cardPicker, Players players, Dealer dealer) {
        tryPlayersTurn(cardPicker, players);
        tryDealerTurn(cardPicker, dealer);
    }

    private void tryPlayersTurn(CardPicker cardPicker, Players players) {
        for (Player player : players.getPlayers()) {
            if (player.isBlackJack()) {
                continue;
            }
            tryPlayerTurn(player, cardPicker);
        }
    }

    private void tryPlayerTurn(Player player, CardPicker cardPicker) {
        if (isContinueHit(player, cardPicker) && !isBust(player)) {
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

    private boolean isBust(Player player) {
        if (player.isBust()) {
            OutputView.printBustMessage();
            return true;
        }
        return false;
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

    private void reportGame(Players players, Dealer dealer, Referee referee) {
        OutputView.printFinalCardDeck(dealer, players, referee);
        OutputView.printResult(referee.calculateDealerWinningMoney(),
            referee.calculateWinningMoneys(), dealer, players);
    }
}
