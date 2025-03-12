package blackjack.domain;

import static blackjack.domain.Round.BLACK_JACK;
import static blackjack.view.WinningType.BLACKJACK_WIN;
import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.gambler.Gambler;
import blackjack.domain.gambler.Name;
import blackjack.view.WinningType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WinningDiscriminator {
    private final Gambler dealer;
    private final List<Gambler> players;

    public WinningDiscriminator(final Gambler dealer, final List<Gambler> players) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return players.stream()
                .collect(toMap(Gambler::getName, this::judgePlayerResult));
    }

    /**
     * TODO
     * 점수도 플레이어 내부에서 비교할 수 있지 않을까?
     */
    private WinningType judgePlayerResult(final Gambler player) {
        int playScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();
        if (playScore > BLACK_JACK) {
            return DEFEAT;
        }
        if (dealerScore > BLACK_JACK) {
            return WIN;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        if (playScore > dealerScore) {
            return WIN;
        }
        if (playScore < dealerScore) {
            return DEFEAT;
        }
        return DRAW;
    }
}
