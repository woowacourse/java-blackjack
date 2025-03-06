package controller;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Card;
import domain.CardHand;
import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		final List<Player> players = inputPlayers();
		final Dealer dealer = new Dealer();
		final Deck deck = Deck.createShuffledDeck();
		handOutCards(players, dealer, deck);
		playerPickCard(players, deck);
	}

	private List<Player> inputPlayers() {
		final List<String> playerNames = inputView.readPlayerNames();
		return playerNames.stream()
			.map(Player::new)
			.collect(Collectors.toList());
	}

	private void handOutCards(final List<Player> players, final Dealer dealer, final Deck deck) {
		players.forEach(player -> {
			player.pickCard(deck);
			player.pickCard(deck);
		});
		dealer.pickCard(deck);
		dealer.pickCard(deck);
		outputHandOut(players, dealer);
	}

	private void outputHandOut(final List<Player> players, final Dealer dealer) {
		final List<String> names = players.stream()
			.map(Player::getName)
			.collect(Collectors.toList());
		outputView.printHandOutIntroduce(names);

		final Participant dealerParticipant = dealer.getParticipant();
		outputView.printDealerHandOutResult(getConvertedDealerCardText(dealerParticipant));

		final List<Map.Entry<String, List<String>>> convertedPlayers = players.stream()
			.map(this::getConvertPlayerToEntry)
			.collect(Collectors.toList());
		outputView.printPlayersCard(convertedPlayers);
	}

	private Map.Entry<String, List<String>> getConvertPlayerToEntry(final Player player) {
		return new AbstractMap.SimpleEntry<>(
			player.getName(),
			getConvertedPlayerCardText(player.getParticipant())
		);
	}

	private String getConvertedDealerCardText(final Participant dealerParticipant) {
		final CardHand dealerHand = dealerParticipant.getHand();
		final Card dealerFirstCard = dealerHand.hand().getFirst();
		return getConvertedCardText(dealerFirstCard);
	}

	private String getConvertedCardText(final Card dealerFirstCard) {
		final String cardNumberText = CardNumberToTextConverter.convert(dealerFirstCard.number());
		final String cardEmblemText = dealerFirstCard.emblem().getName();
		return cardNumberText + cardEmblemText;
	}

	private List<String> getConvertedPlayerCardText(final Participant dealerParticipant) {
		return dealerParticipant.getHand().hand().stream()
			.map(this::getConvertedCardText)
			.toList();
	}

	private void playerPickCard(final List<Player> players, final Deck deck) {
		for (final Player p : players) {
			boolean isDone = false;

			while (!isDone) {
				isDone = true;

				if (p.isPickCard()) {
					final String name = p.getName();
					if (inputView.readPlayerAnswer(name)) {
						isDone = false;
						p.pickCard(deck);
						outputView.printPlayerCards(name, getConvertedPlayerCardText(p.getParticipant()));
					}
				}
			}

		}

	}
}
