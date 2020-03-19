package domain.result;

import domain.user.Dealer;
import domain.user.Player;

public interface MatchService {
    Result match(Player player, Dealer dealer);
}
