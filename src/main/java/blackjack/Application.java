package blackjack;

import static blackjack.util.StringUtil.*;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.controller.BlackjackController;
import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.result.BettingMoney;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerFactory;
import blackjack.view.InputView;

public class Application {
    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.create());
        Dealer dealer = new Dealer(Dealer.NAME);
        List<String> playerNames = parsingPlayerNames(InputView.inputPlayerNames());
        List<Player> players = PlayerFactory.create(playerNames, generatePlayersBettingMoney(playerNames));
        BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);

        BlackjackController blackjackController = new BlackjackController(blackjackTable);
        blackjackController.run();
    }

    private static List<BettingMoney> generatePlayersBettingMoney(List<String> playerNames) {
        return playerNames.stream().map(
            playerName -> BettingMoney.valueOf(InputView.inputBettingMoneyFrom(playerName)))
            .collect(Collectors.toList());
    }
}
