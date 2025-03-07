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
        cardManager.initialize(nicknames);
    }

    public List<Card> findCardsByPlayer(Player player) {
        return cardManager.findCardsByNickname(player.getNickname()).getCards();
    }

    public void distributeCards() {
        cardManager.distributeCards();
    }

    public void hit(Player player) {
        cardManager.addCardByNickname(player.getNickname());
    }

    public int drawDealerCards() {
        int count = 0;
        Player dealer = gameUserStorage.getDealer();
        while (GameRule.shouldDrawCardToDealer(cardManager.calculateSumByNickname(dealer.getNickname()))) {
            cardManager.addCardByNickname(gameUserStorage.getDealer().getNickname());
            count++;
        }
        return count;
    }

    public boolean isBustPlayer(Player player) {
        return GameRule.isBurst(cardManager.calculateSumByNickname(player.getNickname()));
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
        int dealerPoint = cardManager.calculateSumByNickname(dealer.getNickname());

        List<Player> allPlayers = gameUserStorage.getPlayers();
        return new PlayerWinningStatistics(allPlayers.stream()
                .map(player -> calculatePlayerWinningResult(player, dealerPoint))
                .toList());
    }

    private PlayerWinningResult calculatePlayerWinningResult(Player player, int dealerPoint) {

        int playerPoint = cardManager.calculateSumByNickname(player.getNickname());
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
        Cards cards = cardManager.findCardsByNickname(player.getNickname());
        int point = cardManager.calculateSumByNickname(player.getNickname());
        return new DrawnCardResult(player.getNickname(), cards.getCards(), point);
    }
}

