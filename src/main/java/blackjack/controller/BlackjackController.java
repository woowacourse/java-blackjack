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

        distributeCardToPlayers(players, cardMachine);
//         TODO: 딜러 카드 분배
//        distributeCardToDealer(dealer, cardMachine);
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
        OutputView.printCard(Dealer.getName(), dealer.getInitCard());
        OutputView.printNewLine();
        for (Player player : players.getPlayers()) {
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void distributeCardToPlayers(final Players players, final CardMachine cardMachine) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(player, cardMachine);
        }
    }

    private void distributeCardToPlayer(final Player player, final CardMachine cardMachine) {
        while (player.isReceived() && isReceived(player)) {
            player.receiveCard(cardMachine.giveCard());
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
    }

    private boolean isReceived(final Player player) {
        try {
            OutputView.printTakeCardInstruction(player.getName());
            String input = InputView.inputTakeCardAnswer();
            return player.answer(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isReceived(player);
        }
    }
}