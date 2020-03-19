package controller;

import common.PlayerDto;
import domain.PlayersResult;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    public void play() {
        Deck deck = Deck.create();
        Players players = new Players(InputView.inputParticipantPlayer(deck));
        Dealer dealer = new Dealer(deck.getInitCards());

        OutputView.printInitGamersState(PlayerDto.of(dealer),players);

        players.receivePlayerCards(deck);
        dealer.receiveDealerCard(deck);

        OutputView.printDealerHit();
        OutputView.printAllPlayerCardsStateWithScore(players,dealer);

        PlayersResult playersResult = new PlayersResult(players, dealer);
        OutputView.printGameResult(playersResult,playersResult.getPlayersResult());
    }
}
