package blackjack.factory;

import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.PlayerHand;
import blackjack.domain.Players;
import blackjack.domain.Wallet;
import java.util.List;
import java.util.stream.IntStream;

public final class PlayersFactory {

    private PlayersFactory() {
    }

    public static Players generate(List<String> playerNames, List<Integer> moneyList) {
        validate(playerNames, moneyList);

        List<Player> players = IntStream.range(0, playerNames.size())
                .mapToObj(i -> toPlayer(playerNames.get(i), moneyList.get(i)))
                .toList();

        return Players.from(players);
    }

    private static void validate(List<String> playerName, List<Integer> moneyList) {
        if (playerName.size() != moneyList.size()) {
            throw new IllegalArgumentException("플레이어 이름과 돈 입력의 개수는 일치해야 합니다.");
        }
    }

    private static Player toPlayer(String name, int money) {
        return new Player(name, new PlayerHand(new Hand(), Wallet.bet(money)));
    }
}
