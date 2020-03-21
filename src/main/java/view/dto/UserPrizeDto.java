package view.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.result.Prize;
import domain.user.Dealer;
import domain.user.Players;
import domain.user.User;

public class UserPrizeDto {
	private final User user;
	private final Prize prize;

	public UserPrizeDto(User user, Prize prize) {
		this.user = Objects.requireNonNull(user);
		this.prize = Objects.requireNonNull(prize);
	}

	public static List<UserPrizeDto> createAllUsersDto(Players players, Dealer dealer) {
		return Stream.concat(
			Stream.of(calculateDealerPrize(players, dealer)), calculatePlayerPrizes(players, dealer).stream())
			.collect(Collectors.toList());
	}

	private static UserPrizeDto calculateDealerPrize(Players players, Dealer dealer) {
		return players.getPlayers().stream()
			.map(player -> player.calculatePlayerPrize(dealer))
			.map(Prize::calculateDealerPrize)
			.collect(Collectors.collectingAndThen(Collectors.reducing(Prize.ZERO, Prize::sum),
				prize -> new UserPrizeDto(dealer, prize)));
	}

	private static List<UserPrizeDto> calculatePlayerPrizes(Players players, Dealer dealer) {
		return players.getPlayers().stream()
			.map(player -> new UserPrizeDto(player, player.calculatePlayerPrize(dealer)))
			.collect(Collectors.toList());
	}

	public String getName() {
		return user.getName();
	}

	public Prize getPrize() {
		return prize;
	}
}
