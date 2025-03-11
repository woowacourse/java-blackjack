package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.duel.DuelHistory;
import domain.game.Blackjack;
import domain.paticipant.Dealer;
import domain.paticipant.Participant;
import domain.paticipant.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {
	private final InputView inputView;
	private final OutputView outputView;
	private final Blackjack blackjack;

	public BlackjackController(final InputView inputView, final OutputView outputView, final Blackjack blackjack) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.blackjack = blackjack;
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
		outputHandResult(players, dealer);
		duel(players, dealer);
		outputDuelResult(players, dealer);
	}

	private void handOutCards(final List<Player> players, final Dealer dealer, final Deck deck) {
		blackjack.initPickCard(dealer, players, deck);
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

	private Map<String, List<String>> convertPlayersToEntries(final List<Player> players) {
		return players.stream()
			.collect(Collectors.toMap(
				player -> player.getName(),
				player -> convertParticipantCardText(player.getParticipant())
			));
	}

	private void outputPickCard(final List<Player> players, final Dealer dealer, Deck deck) {
		playersIfCanPickCard(players, deck);
		dealerIfCanPickCard(dealer, deck);
	}

	private void playersIfCanPickCard(final List<Player> players, final Deck deck) {
		for (final Player player : players) {
			final String name = player.getName();
			while (blackjack.isPickCardByPlayer(player) && inputView.readPlayerAnswer(name)) {
				blackjack.pickCard(player, deck);
				outputView.printPlayerCards(name, convertParticipantCardText(player.getParticipant()));
			}
		}
	}

	private void dealerIfCanPickCard(final Dealer dealer, final Deck deck) {
		while (blackjack.isPickCardByDealer(dealer)) {
			blackjack.pickCard(dealer, deck);
			outputView.printDealerPickCard();
		}
	}

	private void outputHandResult(final List<Player> players, final Dealer dealer) {
		outputDealerHandResult(dealer);
		outputPlayersHandResult(players);
	}

	private void outputDealerHandResult(final Dealer dealer) {
		final Participant participant = dealer.getParticipant();
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = blackjack.calculateScore(participant);
		outputView.printDealerHandResult(convertedCards, score);
	}

	private List<String> convertParticipantCardText(final Participant dealerParticipant) {
		return dealerParticipant.getCardHand().getCards().stream()
			.map(this::convertedCardText)
			.toList();
	}

	private String convertedCardText(final Card dealerFirstCard) {
		final String rankText = RankMessage.convertRankToMessage(dealerFirstCard.rank());
		final String cardsuitText = dealerFirstCard.suit().getName();
		return rankText + cardsuitText;
	}

	private void outputPlayersHandResult(final List<Player> players) {
		for (final Player player : players) {
			final Participant participant = player.getParticipant();
			final List<String> convertedCards = convertParticipantCardText(participant);
			final int score = blackjack.calculateScore(participant);
			outputView.printPlayerHandResult(player.getName(), convertedCards, score);
		}
	}

	private void duel(final List<Player> players, final Dealer dealer) {
		for (final Player player : players) {
			blackjack.duelDealerVsPlayer(dealer, player);
		}
	}

	private void outputDuelResult(final List<Player> players, final Dealer dealer) {
		final DuelHistory duelHistory = dealer.getParticipant().getDuelHistory();
		outputView.printBlackjackDuelResultIntroduce();
		outputView.printBlackjackDealerDuelResult(duelHistory.getWinCount(), duelHistory.getLoseCount());
		outputPlayersDuelResult(players);

	}

	private void outputPlayersDuelResult(final List<Player> players) {
		for (final Player player : players) {
			final String name = player.getName();
			final DuelHistory duelHistory = player.getParticipant().getDuelHistory();
			final boolean isWin = duelHistory.getWinCount() == 1;
			outputView.printBlackjackPlayerDuelResult(name, isWin);
		}
	}
}
