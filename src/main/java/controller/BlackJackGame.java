package controller;

import common.PlayerDto;
import domain.PlayersResult;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackGame {
    public void play() {
        Deck deck = Deck.create();
        List<String> playerNames = InputView.inputPlayerNames();
        List<Integer> playerMoneys = InputView.inputPlayerMoney(playerNames);
        Players players = new Players(playerNames, playerMoneys, deck);
        Dealer dealer = new Dealer(deck.getInitCards());
        PlayersResult playersResult = new PlayersResult(players, dealer);

        OutputView.printInitGamersState(PlayerDto.of(dealer),players);

        players.receivePlayerCards(deck);
        dealer.receiveDealerCard(deck);

        OutputView.printDealerHit();
        OutputView.printAllPlayerCardsStateWithScore(players,dealer);
        OutputView.printGameResult(playersResult,players);
    }
}
