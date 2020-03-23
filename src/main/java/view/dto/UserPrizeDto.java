package view.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.result.GameResult;
import domain.result.Prize;
import domain.user.Player;

public class UserPrizeDto {
	private final String userName;
	private final int prize;

	public UserPrizeDto(String userName, int prize) {
		this.userName = Objects.requireNonNull(userName);
		this.prize = prize;
	}

	public static List<UserPrizeDto> createAllUsersDto(GameResult gameResult) {
		Objects.requireNonNull(gameResult);
		return Stream.concat(
			Stream.of(calculateDealerPrize(gameResult)), calculatePlayerPrizes(gameResult).stream())
			.collect(Collectors.toList());
	}

	private static UserPrizeDto calculateDealerPrize(GameResult gameResult) {
		String dealerName = gameResult.getDealerName();
		Prize dealerPrize = gameResult.calculateDealerPrize();
		return new UserPrizeDto(dealerName, dealerPrize.getPrize());
	}

	private static List<UserPrizeDto> calculatePlayerPrizes(GameResult gameResult) {
		Map<Player, Prize> playerPrizeResult = gameResult.getPlayerPrizeResult();
		return playerPrizeResult.entrySet().stream()
			.map(entry -> new UserPrizeDto(entry.getKey().getName(), entry.getValue().getPrize()))
			.collect(Collectors.toList());
	}

	public String getName() {
		return userName;
	}

	public int getPrize() {
		return prize;
	}
}
