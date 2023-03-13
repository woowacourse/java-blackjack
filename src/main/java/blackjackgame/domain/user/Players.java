package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    public Players(Names names, List<Bet> bets) {
        validateSize(names, bets);
        players = new ArrayList<>();
        for (int playerCount = 0; playerCount < bets.size(); playerCount++) {
            Name name = names.getNames().get(playerCount);
            Bet bet = bets.get(playerCount);

            players.add(new Player(name, bet));
        }
    }

    private void validateSize(Names names, List<Bet> bets) {
        if (names.size() != bets.size()) {
            throw new IllegalArgumentException("입력된 플레이어와 베팅 금액의 수가 일치하지 않습니다.");
        }
    }

    public void receiveCards(Cards cards, int cardCount) {
        for (Player player : players) {
            player.receiveCards(cards.drawCards(cardCount));
        }
    }

    public int calculateProfitSum() {
        int profitSum = 0;

        for (Player player : players) {
            profitSum += player.getProfitAmount();
        }

        return profitSum;
    }

    public Map<Player, List<Card>> getHandsByPlayer() {
        Map<Player, List<Card>> handsByPlayer = new LinkedHashMap<>();

        for (Player player : players) {
            handsByPlayer.put(player, player.getCards());
        }

        return handsByPlayer;
    }

    public Map<Player, Profit> getPlayerProfits() {
        Map<Player, Profit> profitByPlayer = new LinkedHashMap<>();
        for (Player player : players) {
            profitByPlayer.put(player, player.getProfit());
        }
        return profitByPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
