package controller;

import domain.card.CardDeck;
import domain.card.ShuffleCardDeck;
import domain.result.GameResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerFactory;
import domain.user.Players;
import util.DrawResponse;
import view.InputView;
import view.OutputView;
import view.dto.UserPrizeDto;
import view.dto.UserScoreDto;

public class BlackjackGame {
	public void play() {
		final Players players = PlayerFactory.create(InputView.inputNames(), InputView::inputBettingMoney);
		final Dealer dealer = new Dealer();
		final CardDeck cardDeck = new ShuffleCardDeck();

		drawFirstTime(players, dealer, cardDeck);
		drawPlayersCardAdditional(players, cardDeck);
		drawDealerCardAdditional(dealer, cardDeck);

		OutputView.printUsersCardsAndScore(UserScoreDto.createAllUsersDto(players, dealer));
		OutputView.printGameResult(UserPrizeDto.createAllUsersDto(new GameResult(players, dealer)));
	}

	private void drawFirstTime(Players players, Dealer dealer, CardDeck cardDeck) {
		players.drawFirstTime(cardDeck);
		dealer.drawFirstTime(cardDeck);
		OutputView.printInitialDrawResult(players, dealer);
	}

	private void drawPlayersCardAdditional(Players players, CardDeck cardDeck) {
		for (Player player : players) {
			drawSinglePlayerCardAdditional(player, cardDeck);
		}
	}

	private void drawSinglePlayerCardAdditional(Player player, CardDeck cardDeck) {
		while (player.isDrawable() && DrawResponse.isYes(InputView.inputAdditionalDraw(player.getName()))) {
			player.draw(cardDeck);
			OutputView.printPlayerCard(player);
		}
	}

	private void drawDealerCardAdditional(Dealer dealer, CardDeck cardDeck) {
		if (dealer.isDrawable()) {
			dealer.draw(cardDeck);
			OutputView.printDealerDraw(dealer);
		}
	}
}
