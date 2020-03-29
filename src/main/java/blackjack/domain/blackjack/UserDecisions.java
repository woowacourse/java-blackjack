package blackjack.domain.blackjack;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

import blackjack.domain.card.Card;
import blackjack.domain.exceptions.InvalidUserDecisionsException;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class UserDecisions {
	private final Function<Player, String> choice;
	private final BiConsumer<User, List<Card>> handStatus;
	private final Runnable dealerChoice;

	public UserDecisions(
		Function<Player, String> choice, BiConsumer<User, List<Card>> handStatus, Runnable dealerChoice) {
		validate(choice, handStatus, dealerChoice);
		this.choice = choice;
		this.handStatus = handStatus;
		this.dealerChoice = dealerChoice;
	}

	private void validate(Function<Player, String> choice, BiConsumer<User, List<Card>> handStatus,
		Runnable dealerChoice) {
		if (Objects.isNull(choice) || Objects.isNull(handStatus) || Objects.isNull(dealerChoice)) {
			throw new InvalidUserDecisionsException(InvalidUserDecisionsException.NULL);
		}
	}

	public boolean isHit(Player player) {
		return Choice.of(choice.apply(player))
			.isHit();
	}

	public void showHandStatus(Player player) {
		handStatus.accept(player, player.getHand());
	}

	public void showDealerHitStatus() {
		dealerChoice.run();
	}
}
