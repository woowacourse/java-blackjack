package blackjack.domain;

import blackjack.dto.DrawnCardResult;
import blackjack.dto.PlayerWinningResult;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final CardManager cardManager;
    private final GameUserStorage gameUserStorage;

    public GameManager(CardManager cardManager, GameUserStorage gameUserStorage) {
        this.cardManager = cardManager;
        this.gameUserStorage = gameUserStorage;
    }

    public void registerPlayers(List<Nickname> nicknames) {
        gameUserStorage.initialize(nicknames);
    }

    public void distributeCards() {
        List<Player> players = gameUserStorage.getPlayers();
        players.forEach(player -> cardManager.addCardByNickname(player, 2));
        Player dealer = gameUserStorage.getDealer();
        cardManager.addCardByNickname(dealer, 2);
    }

    public List<Card> findCardsByPlayer(Player player) {
        return cardManager.findCardsByNickname(player);
    }

    public void hit(Player player) {
        cardManager.addCardByNickname(player, 1);
    }

    public int drawDealerCards() {
        int count = 0;
        Player dealer = gameUserStorage.getDealer();
        while (GameRule.shouldDrawCardToDealer(cardManager.calculateSumByNickname(dealer))) {
            cardManager.addCardByNickname(gameUserStorage.getDealer(), 1);
            count++;
        }
        return count;
    }

    public boolean isBustPlayer(Player player) {
        return GameRule.isBurst(cardManager.calculateSumByNickname(player));
    }

    public List<Player> getPlayers() {
        return gameUserStorage.getPlayers();
    }

    public List<DrawnCardResult> calculateDrawnCardResults() {
        List<DrawnCardResult> drawnCardResults = new ArrayList<>();

        Player dealer = gameUserStorage.getDealer();
        drawnCardResults.add(getDrawnCardResult(dealer));

        List<Player> allPlayers = gameUserStorage.getPlayers();
        allPlayers.stream()
                .map(this::getDrawnCardResult)
                .forEach(drawnCardResults::add);

        return drawnCardResults;
    }

    public PlayerWinningStatistics calculateGameResult() {
        Player dealer = gameUserStorage.getDealer();
        int dealerPoint = cardManager.calculateSumByNickname(dealer);

        List<Player> allPlayers = gameUserStorage.getPlayers();
        return new PlayerWinningStatistics(allPlayers.stream()
                .map(player -> calculatePlayerWinningResult(player, dealerPoint))
                .toList());
    }

    private PlayerWinningResult calculatePlayerWinningResult(Player player, int dealerPoint) {

        int playerPoint = cardManager.calculateSumByNickname(player);
        if (playerPoint > 21) {
            playerPoint = 0;
        }

        if (dealerPoint > 21) {
            dealerPoint = 0;
        }

        if (playerPoint > dealerPoint) {
            return new PlayerWinningResult(player.getNickname(), GameResultType.WIN);
        }

        if (playerPoint < dealerPoint) {
            return new PlayerWinningResult(player.getNickname(), GameResultType.LOSE);
        }

        return new PlayerWinningResult(player.getNickname(), GameResultType.DRAW);
    }

    private DrawnCardResult getDrawnCardResult(Player player) {
        List<Card> cards = cardManager.findCardsByNickname(player);
        int point = cardManager.calculateSumByNickname(player);
        return new DrawnCardResult(player.getNickname(), cards, point);
    }
}

