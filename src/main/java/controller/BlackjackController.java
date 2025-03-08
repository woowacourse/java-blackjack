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
		startGame(players, dealer, deck);
	}

	private List<Player> inputPlayers() {
		final List<String> playerNames = inputView.readPlayerNames();
		return playerNames.stream().map(Player::new).collect(Collectors.toList());
	}

	private void startGame(final List<Player> players, final Dealer dealer, final Deck deck) {
		handOutCards(players, dealer, deck);
		outputPickCard(players, dealer, deck);
		outputHandResult(dealer, players);
		duel(dealer, players);
		outputDuelResult(players, dealer);
	}

	private void handOutCards(final List<Player> players, final Dealer dealer, final Deck deck) {
		players.forEach(player -> player.pickCardOnFirstHandOut(deck));
		dealer.pickCardOnFirstHandOut(deck);
		outputHandOut(players, dealer);
	}

	private void outputHandOut(final List<Player> players, final Dealer dealer) {
		outputView.printHandOutIntroduce(convertPlayersToNames(players));

		final Participant dealerParticipant = dealer.getParticipant();
		outputView.printDealerHandOutResult(convertedDealerCardText(dealerParticipant));

		outputView.printPlayersCard(convertPlayersToEntries(players));
	}

	private List<String> convertPlayersToNames(final List<Player> players) {
		return players.stream().map(Player::getName).collect(Collectors.toList());
	}

	private String convertedDealerCardText(final Participant dealerParticipant) {
		final CardHand dealerHand = dealerParticipant.getCardHand();
		final Card dealerFirstCard = dealerHand.getCards().getFirst();
		return convertedCardText(dealerFirstCard);
	}

	private List<Map.Entry<String, List<String>>> convertPlayersToEntries(final List<Player> players) {
		return players.stream().map(this::convertPlayerToEntry).collect(Collectors.toList());
	}

	private Map.Entry<String, List<String>> convertPlayerToEntry(final Player player) {
		return new AbstractMap.SimpleEntry<>(player.getName(), convertParticipantCardText(player.getParticipant()));
	}

	private void outputPickCard(final List<Player> players, final Dealer dealer, Deck deck) {
		playersIfCanPickCard(players, deck);
		dealerIfCanPickCard(dealer, deck);
	}

	private void playersIfCanPickCard(final List<Player> players, final Deck deck) {
		players.forEach(player -> playerIfCanPickCard(deck, player));
	}

	private void playerIfCanPickCard(final Deck deck, final Player player) {
		final String name = player.getName();
		while (player.isPickCard() && inputView.readPlayerAnswer(name)) {
			player.pickCard(deck);
			outputView.printPlayerCards(name, convertParticipantCardText(player.getParticipant()));
		}
	}

	private void dealerIfCanPickCard(final Dealer dealer, final Deck deck) {
		while (dealer.isPickCard()) {
			dealer.pickCard(deck);
			outputView.printDealerPickCard();
		}
	}

	private void outputHandResult(final Dealer dealer, final List<Player> players) {
		outputDealerHandResult(dealer);
		outputPlayersHandResult(players);
	}

	private void outputDealerHandResult(final Dealer dealer) {
		final Participant participant = dealer.getParticipant();
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = participant.calculateAllScore();
		outputView.printDealerHandResult(convertedCards, score);
	}

	private List<String> convertParticipantCardText(final Participant dealerParticipant) {
		return dealerParticipant.getCardHand().getCards().stream().map(this::convertedCardText).toList();
	}

	private String convertedCardText(final Card dealerFirstCard) {
		final String cardNumberText = RankMessage.convertRankToMessage(dealerFirstCard.rank());
		final String cardEmblemText = dealerFirstCard.suit().getName();
		return cardNumberText + cardEmblemText;
	}

	private void outputPlayersHandResult(final List<Player> players) {
		players.forEach(this::outputPlayerHandResult);
	}

	private void outputPlayerHandResult(final Player player) {
		final Participant participant = player.getParticipant();
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = participant.calculateAllScore();
		outputView.printPlayerHandResult(player.getName(), convertedCards, score);
	}

	private void duel(final Dealer dealer, final List<Player> players) {
		players.forEach(dealer::startDuel);
	}

	private void outputDuelResult(final List<Player> players, final Dealer dealer) {
		final DuelHistory duelHistory = dealer.getParticipant().getDuelHistory();
		outputView.printBlackjackDuelResultIntroduce();
		outputView.printBlackjackDealerDuelResult(duelHistory.getWinCount(), duelHistory.getLoseCount());
		players.forEach(this::outputPlayersDuelResult);

	}

	private void outputPlayersDuelResult(final Player player) {
		final String name = player.getName();
		final DuelHistory duelHistory = player.getParticipant().getDuelHistory();
		final boolean isWin = duelHistory.getWinCount() == 1;
		outputView.printBlackjackPlayerDuelResult(name, isWin);
	}
}
