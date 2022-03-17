package blackjack.controller;

import blackjack.domain.Bank;
import blackjack.domain.Money;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Betting {

    public Bank play(Players players) {
        Bank bank = new Bank();
        for (Player player : players.getPlayers()) {
            bank = bets(bank, player);
        }
        return bank;
    }

    private Bank bets(Bank bank, Player player) {
        try {
            OutputView.printBettingInstruction(player.getName());
            bank.bet(player, Money.of(InputView.inputBettingMoney()));
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return bets(bank, player);
        }
        return bank;
    }
}
