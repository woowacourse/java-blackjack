package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int INITIAL_DRAW_SIZE = 2;
    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private final Players players;
    private final Dealer dealer;

    public GameManager(List<String> playerNames, List<Integer> betAmounts, CardProvider provider) {
        this.provider = provider;
        this.players = new Players(createPlayers(playerNames, betAmounts));
        this.dealer = new Dealer(drawInitialCards());
    }

    private List<Player> createPlayers(List<String> playerNames, List<Integer> betAmounts) {
        validateNamesAndAmountsSize(playerNames, betAmounts);
        List<Player> createdPlayers = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            createdPlayers.add(new Player(playerNames.get(i), drawInitialCards(), betAmounts.get(i)));
        }
        return createdPlayers;
    }

    private void validateNamesAndAmountsSize(List<String> playerNames, List<Integer> betAmounts) {
        if (playerNames.size() != betAmounts.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어별 베팅 금액이 잘못 입력되었습니다.");
        }
    }

    private List<Card> drawInitialCards() {
        return this.provider.provideCards(INITIAL_DRAW_SIZE);
    }

    public Dealer findDealer() {
        return dealer;
    }

    public List<Player> findAllPlayers() {
        return players.findAllPlayers();
    }

    public void drawCard(Participant participant) {
        participant.drawCard(provider.provideCards(DEFAULT_DRAW_SIZE));
    }

    public Map<Player, Integer> calculateIncomes() {
        Map<Player, Integer> incomes = new HashMap<>();
        Map<Player, ResultStatus> gameResult = ResultStatus.judgeGameResult(players, dealer);
        for (Player player : gameResult.keySet()) {
            int income = player.calculateIncome(gameResult.get(player));
            incomes.put(player, income);
        }
        return incomes;
    }

    public boolean isPlayerBurst(Player player) {
        return player.isBurst();
    }

    public boolean isDealerHittable(Dealer dealer) {
        return dealer.isDealerHittable();
    }
}
