package domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

public class GameResult {
	private static final String INCLUDING_NULL_PLAYERS_DEALER_EXCEPTION_MESSAGE = "플레이어들과 딜러는 null이 될 수 없습니다.";
	private final Map<Player, Prize> playerPrizeResult;
	private final Dealer dealer;

	public GameResult(Players players, Dealer dealer) {
		validateNull(players, dealer);
		this.playerPrizeResult = initPlayersPrize(players, dealer);
		this.dealer = dealer;
	}

	private void validateNull(Players players, Dealer dealer) {
		if (Objects.isNull(players) || Objects.isNull(dealer)) {
			throw new IllegalArgumentException(INCLUDING_NULL_PLAYERS_DEALER_EXCEPTION_MESSAGE);
		}
	}

	private Map<Player, Prize> initPlayersPrize(Players players, Dealer dealer) {
		return players.getPlayers().stream()
			.collect(collectingAndThen(toMap(Function.identity(),
				player -> player.calculatePlayerPrize(dealer),
				(prize1, prize2) -> prize2,
				LinkedHashMap::new), Collections::unmodifiableMap)
			);
	}

	public String getDealerName() {
		return dealer.getName();
	}

	public Prize calculateDealerPrize() {
		return playerPrizeResult.values().stream()
			.map(Prize::calculateDealerPrize)
			.reduce(Prize.ZERO, Prize::sum);
	}

	public Map<Player, Prize> getPlayerPrizeResult() {
		return playerPrizeResult;
	}
}
