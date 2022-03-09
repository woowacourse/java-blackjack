package blackjack.controller;

import blackjack.domain.CardMachine;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        OutputView.printPlayerNameInstruction();

        CardMachine cardMachine = new CardMachine();
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        noticeInitCard(cardMachine, dealer, players);
        openInitCard(dealer, players);
    }

    private Players createPlayers() {
        try {
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    private void noticeInitCard(CardMachine cardMachine, Dealer dealer, Players players) {
        OutputView.printReceiveInitCardMessage(Dealer.getName(), players.getNames());
        dealer.receiveInitCard(cardMachine.giveInitCard());
        for (Player player : players.getPlayers()) {
            player.receiveInitCard(cardMachine.giveInitCard());
        }
    }

    private void openInitCard(Dealer dealer, Players players) {
        OutputView.printInitCard(Dealer.getName(), dealer.getInitCard());
        OutputView.printNewLine();
        for (Player player : players.getPlayers()) {
            OutputView.printInitCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
    }
}