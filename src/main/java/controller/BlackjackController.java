package controller;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Card;
import domain.CardHand;
import domain.Dealer;
import domain.Deck;
import domain.DuelHistory;
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
		dealerPickCard(dealer, deck);

		outputDealerHandResult(dealer.getParticipant());
		outputPlayersHandResult(players);

		duel(dealer, players);
		outputDuelResult(dealer, players);
	}

	private void duel(final Dealer dealer, final List<Player> players) {
		players.forEach(player -> dealer.duel(player));
	}

	private void outputDuelResult(final Dealer dealer, final List<Player> players) {
		outputView.printBlackjackDuelResultIntroduce();
		final DuelHistory dealerHistory = dealer.getParticipant().getDuelHistory();
		outputView.printBlackjackDealerDuelResult(dealerHistory.getWinCount() + dealerHistory.getDrawCount(),
			dealerHistory.getLoseCount());
		players.forEach(this::outputPlayersDuelResult);

	}

	private void outputPlayersDuelResult(final Player player) {
		final String name = player.getName();
		final DuelHistory duelHistory = player.getParticipant().getDuelHistory();
		outputView.printBlackjackPlayerDuelResult(name, duelHistory.getWinCount() > duelHistory.getLoseCount());
	}

	private List<Player> inputPlayers() {
		final List<String> playerNames = inputView.readPlayerNames();
		return playerNames.stream().map(Player::new).collect(Collectors.toList());
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
		final List<String> names = players.stream().map(Player::getName).collect(Collectors.toList());
		outputView.printHandOutIntroduce(names);

		final Participant dealerParticipant = dealer.getParticipant();
		outputView.printDealerHandOutResult(convertedDealerCardText(dealerParticipant));

		final List<Map.Entry<String, List<String>>> convertedPlayers = players.stream()
			.map(this::convertPlayerToEntry)
			.collect(Collectors.toList());
		outputView.printPlayersCard(convertedPlayers);
	}

	private Map.Entry<String, List<String>> convertPlayerToEntry(final Player player) {
		return new AbstractMap.SimpleEntry<>(player.getName(), convertParticipantCardText(player.getParticipant()));
	}

	private String convertedDealerCardText(final Participant dealerParticipant) {
		final CardHand dealerHand = dealerParticipant.getHand();
		final Card dealerFirstCard = dealerHand.hand().getFirst();
		return convertedCardText(dealerFirstCard);
	}

	private String convertedCardText(final Card dealerFirstCard) {
		final String cardNumberText = CardNumberToTextConverter.convert(dealerFirstCard.number());
		final String cardEmblemText = dealerFirstCard.emblem().getName();
		return cardNumberText + cardEmblemText;
	}

	private List<String> convertParticipantCardText(final Participant dealerParticipant) {
		return dealerParticipant.getHand().hand().stream().map(this::convertedCardText).toList();
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
						outputView.printPlayerCards(name, convertParticipantCardText(p.getParticipant()));
					}
				}
			}
		}
	}

	private void dealerPickCard(final Dealer dealer, final Deck deck) {
		while (dealer.isPickCard()) {
			dealer.pickCard(deck);
			outputView.printDealerPickCard();
		}
	}

	private void outputDealerHandResult(final Participant participant) {
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = participant.calculateAllScore();
		outputView.printDealerHandResult(convertedCards, score);
	}

	private void outputPlayersHandResult(List<Player> players) {
		for (Player player : players) {
			outputPlayerHandResult(player.getName(), player.getParticipant());
		}
	}

	private void outputPlayerHandResult(final String name, final Participant participant) {
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = participant.calculateAllScore();
		outputView.printPlayerHandResult(name, convertedCards, score);
	}

}
