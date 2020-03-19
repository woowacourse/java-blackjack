package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.controller.dto.GamersResultDto;
import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BettingTable;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        Players players = Players.of(InputView.askPlayerNames().get());

        BettingTable bettingTable = BettingTable.of(players, InputView.askPlayerBettingMoney(players.getNames()).get());

        BlackJackController.initializeCard(dealer, players, deck);
        OutputView.printInitialCards(dealer, players);

        BlackJackController.drawMoreCard(dealer, players, deck);
        OutputView.printGamerScore(dealer, players);

        GamersResultDto gamersResultDto = bettingTable.calculateProfit(dealer, players);
        OutputView.printGamersProfit(gamersResultDto);
    }
}