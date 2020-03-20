package controller;

import java.util.ArrayList;
import java.util.List;

import domain.GameParticipant;
import domain.PlayerResult;
import domain.Players;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

	public static void run() {
		List<Name> names = inputNames();
		List<Money> bettingMoneys = inputBettingMoneys(names);
		Players players = new Players(names, bettingMoneys);
		GameParticipant participant = new GameParticipant(players);
		participant.initialDraw();
		OutputView.printInitialDraw(participant);

		Dealer dealer = participant.getDealer();
		CardDeck cardDeck = participant.getCardDeck();
		for (Player player : players.getPlayers()) {
			performPlayersHit(cardDeck, player);
		}
		OutputView.printDealerAdditionalCard(dealer.performHit(cardDeck));

		OutputView.printFinalScoreBoard(participant);
		PlayerResult playerResult = new PlayerResult();
		playerResult.deduceResult(participant);
		OutputView.printFinalResult(playerResult);
	}

	private static List<Name> inputNames() {
		try {
			List<Name> names = new ArrayList<>();
			String[] nameInput = InputView.inputUserNames();
			for (String name : nameInput) {
				names.add(Name.create(name.trim()));
			}
			return names;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputNames();
		}
	}

	private static List<Money> inputBettingMoneys(List<Name> names) {
		List<Money> bettingMoneys = new ArrayList<>();
		for (Name name : names) {
			bettingMoneys.add(inputMoney(name));
		}
		return bettingMoneys;
	}

	private static Money inputMoney(Name name) {
		try {
			return Money.create(InputView.inputBettingMoney(name));
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputMoney(name);
		}
	}

	private static void performPlayersHit(CardDeck cardDeck, Player player) {
		try {
			while (player.under21() && InputUtils.isAnswerHit(InputView.inputHitOrNot(player))) {
				player.receive(cardDeck.draw());
				OutputView.printCardStatus(player);
			}
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			performPlayersHit(cardDeck, player);
		}
	}

}
