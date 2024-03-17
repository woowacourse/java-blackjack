package blackjack.model.result;

import blackjack.model.player.Name;
import blackjack.view.dto.PlayerEarning;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableMap;

public class BettingBoard {
    private static final String LIST_LENGTH_MISMATCH = "플레이어 이름 리스트와 배팅 금액 리스트의 크기는 일치해야 합니다.";
    private static final String PLAYER_NOT_FOUND = " 플레이어를 배팅 보드에서 찾을 수 없습니다.";

    private final Map<Name, BettingMoney> bettings;

    public BettingBoard(final List<Name> names, final List<BettingMoney> bettingMonies) {
        this.bettings = unmodifiableMap(prepareBettingBoard(names, bettingMonies));
    }

    private Map<Name, BettingMoney> prepareBettingBoard(final List<Name> names, final List<BettingMoney> bettingMonies) {
        if (names.size() != bettingMonies.size()) {
            throw new IllegalArgumentException(LIST_LENGTH_MISMATCH);
        }
        return IntStream.range(0, names.size())
                .boxed()
                .collect(Collectors.toMap(names::get, bettingMonies::get));
    }

    public PlayerEarning determineEarning(final Name playerName, final MatchResult matchResult) {
        BettingMoney bettingMoney = getBettingMoney(playerName);
        int earning = matchResult.calculateEarning(bettingMoney);
        return new PlayerEarning(playerName.toString(), earning);
    }

    private BettingMoney getBettingMoney(final Name playerName) {
        BettingMoney bettingMoney = bettings.get(playerName);
        if (bettingMoney == null) {
            throw new NoSuchElementException(playerName + PLAYER_NOT_FOUND);
        }
        return bettingMoney;
    }
}
