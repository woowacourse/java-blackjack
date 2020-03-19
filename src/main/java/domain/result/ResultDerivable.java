package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;

public interface ResultDerivable {
    Result derivePlayerResult(Player player, Dealer dealer);

    Result deriveDealerResult(List<Result> playerResults);
}
