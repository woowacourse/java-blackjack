package domain;

public class Judge {
	private Judge() {
	}

	public static int calculateProfit(final Player player, final Dealer dealer) {
		if (isAnyoneBlackjack(player, dealer)) {
			return calculateBlackjack(player, dealer);
		}
		if (isAnyoneBurst(player, dealer)) {
			return calculateBurst(player);
		}

		return calculateUnder21(player, dealer);
	}

	private static boolean isAnyoneBlackjack(Player player, Dealer dealer) {
		return isBlackjack(player) || isBlackjack(dealer);
	}

	private static boolean isBlackjack(User user) {
		return user.getCards().size() == 2 && user.getScore() == 21;
	}

	private static int calculateBlackjack(Player player, Dealer dealer) {
		double newBet = player.getBet();

		if (isBothBlackjack(player, dealer)) {
			return distribute(newBet, 0);
		}
		if (isBlackjack(player)) {
			return distribute(newBet, 1.5);
		}

		return distribute(newBet, -1);
	}

	private static boolean isAnyoneBurst(Player player, Dealer dealer) {
		return isBurst(player) || isBurst(dealer);
	}

	private static boolean isBurst(User user) {
		return user.getScore() > 21;
	}

	private static int calculateBurst(Player player) {
		double newBet = player.getBet();

		if (isBurst(player)) {
			return distribute(newBet, -1);
		}

		return distribute(newBet, 1);
	}

	private static int calculateUnder21(Player player, Dealer dealer) {
		double newBet = player.getBet();

		if (isPlayerHigher(player, dealer)) {
			return distribute(newBet, 1);
		}
		if (isDealerHigher(player, dealer)) {
			return distribute(newBet, -1);
		}

		return distribute(newBet, 0);
	}

	private static boolean isPlayerHigher(Player player, Dealer dealer) {
		return player.getScore() > dealer.getScore();
	}

	private static boolean isDealerHigher(Player player, Dealer dealer) {
		return dealer.getScore() > player.getScore();
	}

	private static int distribute(double newBet, double ratio) {
		newBet *= ratio;
		return (int)newBet;
	}

	private static boolean isBothBlackjack(Player player, Dealer dealer) {
		return isBlackjack(player) && isBlackjack(dealer);
	}
}
