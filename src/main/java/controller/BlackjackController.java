package controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardHand;
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

	public BlackjackController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		final Blackjack blackjack = Blackjack.from(inputView.readPlayerNames());
		handOutCards(blackjack);
		blackjack.pickCardPlayersIfNotBust(inputView::readPlayerAnswer);
		blackjack.pickCardDealerIfNotMax();
		outputDealerPickCard(blackjack);
		outputHandResult(blackjack);
		blackjack.duel();
		outputDuelResult(blackjack);
	}

	private void outputDealerPickCard(final Blackjack blackjack) {
		final int dealerCardCount =
			blackjack.getDealer().getParticipant().getCardHand().getCards().size() - Blackjack.INIT_PICK_CARD_COUNT;
		for (int i = 0; i < dealerCardCount; i++) {
			outputView.printDealerPickCard();
		}
	}

	private void handOutCards(final Blackjack blackjack) {
		blackjack.initPickCard();
		outputHandOut(blackjack.getPlayers().getPlayers(), blackjack.getDealer());
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
			.collect(Collectors.toMap(player -> player.getName(),
				player -> convertParticipantCardText(player.getParticipant())));
	}

	private void outputHandResult(final Blackjack blackjack) {
		outputDealerHandResult(blackjack);
		outputPlayersHandResult(blackjack);
	}

	private void outputDealerHandResult(final Blackjack blackjack) {
		final Participant participant = blackjack.getDealer().getParticipant();
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = blackjack.calculateScore(participant);
		outputView.printDealerHandResult(convertedCards, score);
	}

	private List<String> convertParticipantCardText(final Participant dealerParticipant) {
		return dealerParticipant.getCardHand().getCards().stream().map(this::convertedCardText).toList();
	}

	private String convertedCardText(final Card dealerFirstCard) {
		final String rankText = RankMessage.convertRankToMessage(dealerFirstCard.rank());
		final String cardsuitText = dealerFirstCard.suit().getName();
		return rankText + cardsuitText;
	}

	private void outputPlayersHandResult(final Blackjack blackjack) {
		for (final Player player : blackjack.getPlayers().getPlayers()) {
			final Participant participant = player.getParticipant();
			final List<String> convertedCards = convertParticipantCardText(participant);
			final int score = blackjack.calculateScore(participant);
			outputView.printPlayerHandResult(player.getName(), convertedCards, score);
		}
	}

	private void outputDuelResult(final Blackjack blackjack) {
		final DuelHistory duelHistory = blackjack.getDealer().getParticipant().getDuelHistory();
		outputView.printBlackjackDuelResultIntroduce();
		outputView.printBlackjackDealerDuelResult(duelHistory.getWinCount(), duelHistory.getLoseCount());
		outputPlayersDuelResult(blackjack.getPlayers().getPlayers());
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
