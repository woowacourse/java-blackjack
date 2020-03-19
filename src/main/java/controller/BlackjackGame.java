package controller;

import java.util.List;

import domain.card.CardDeck;
import domain.card.ShuffleCardDeck;
import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;
import domain.user.PlayerFactory;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	public void play() {
		final Gamers gamers = initGamers();
		gamers.drawFirstTime(OutputView::printInitialDrawResult);
		gamers.drawPlayersAdditional(InputView::inputAdditionalDraw, OutputView::printPlayerCard);
		gamers.drawDealerAdditional(OutputView::printDealerDraw);
		OutputView.printUsersCardsAndScore(gamers.calculateUserScoresDto());
		OutputView.printGameResult(gamers.calculateUserPrizesDto());
	}

	private Gamers initGamers() {
		final List<Player> players = PlayerFactory.create(InputView.inputNames(), InputView::inputBettingMoney);
		final Dealer dealer = new Dealer();
		final CardDeck cardDeck = new ShuffleCardDeck();
		return new Gamers(players, dealer, cardDeck);
	}
}
