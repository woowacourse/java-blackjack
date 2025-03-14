package game;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import card.Card;
import card.CardHand;
import money.Money;
import money.WagerMoney;
import paticipant.Dealer;
import paticipant.Participant;
import paticipant.Player;
import view.InputView;
import view.OutputView;
import view.RankMessage;

public class BlackjackManager {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackManager(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		final Blackjack blackjack = Blackjack.from(inputView.readPlayerNames());
		final WagerMoney wagerMoney = new WagerMoney(inputView.readPlayersMoney(blackjack));
		handOutCards(blackjack);
		blackjack.pickCardPlayersIfNotBust(inputView::readPlayerAnswer);
		blackjack.pickCardDealerIfNotMax();
		outputDealerPickCard(blackjack);
		outputHandResult(blackjack);
		blackjack.duel();
		outputWagerResult(wagerMoney);
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
			.collect(Collectors.toMap(Player::getName,
				player -> convertParticipantCardText(player.getParticipant())));
	}

	private void outputHandResult(final Blackjack blackjack) {
		outputDealerHandResult(blackjack);
		outputPlayersHandResult(blackjack);
	}

	private void outputDealerHandResult(final Blackjack blackjack) {
		final Participant participant = blackjack.getDealer().getParticipant();
		final List<String> convertedCards = convertParticipantCardText(participant);
		final int score = blackjack.calculateScore(participant).value();
		outputView.printDealerHandResult(convertedCards, score);
	}

	private List<String> convertParticipantCardText(final Participant dealerParticipant) {
		return dealerParticipant.getCardHand().getCards().stream().map(this::convertedCardText).toList();
	}

	private String convertedCardText(final Card dealerFirstCard) {
		final String rankText = RankMessage.convertRankToMessage(dealerFirstCard.rank());
		final String cardSuitText = dealerFirstCard.suit().getName();
		return rankText + cardSuitText;
	}

	private void outputPlayersHandResult(final Blackjack blackjack) {
		for (final Player player : blackjack.getPlayers().getPlayers()) {
			final Participant participant = player.getParticipant();
			final List<String> convertedCards = convertParticipantCardText(participant);
			final int score = blackjack.calculateScore(participant).value();
			outputView.printPlayerHandResult(player.getName(), convertedCards, score);
		}
	}

	private void outputWagerResult(WagerMoney wagerMoney) {
		final Map<Player, Money> playerMoney = wagerMoney.calculateWagerResult();
		outputView.printWagerResult(playerMoney, calculateDealerWagerResult(playerMoney));
	}

	private Money calculateDealerWagerResult(final Map<Player, Money> playerWagerResult) {
		Money dealerMoney = new Money(0);
		for (final Money playerMoney : playerWagerResult.values()) {
			dealerMoney = dealerMoney.minus(playerMoney);
		}
		return dealerMoney;
	}
}
