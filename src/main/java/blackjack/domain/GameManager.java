package blackjack.domain;

import blackjack.dto.HandState;
import blackjack.dto.HandsAfterDrawingCard;
import blackjack.dto.HandsBeforeDrawingCard;
import blackjack.dto.HiddenDealerHandState;
import blackjack.dto.PlayerWinningResult;
import blackjack.dto.PlayerWinningStatistics;
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

    public HandsBeforeDrawingCard getHandBeforeDrawCard() {
        HiddenDealerHandState dealerHandState = getHiddenDealerHandState();
        List<HandState> playerHandStates = getAllPlayerHandState();
        return new HandsBeforeDrawingCard(dealerHandState, playerHandStates);
    }

    public HandsAfterDrawingCard getHandAfterDrawCard() {
        Player dealer = gameUserStorage.getDealer();
        HandState dealerHandState = getHandState(dealer);
        List<HandState> playerHandStates = getAllPlayerHandState();
        return new HandsAfterDrawingCard(dealerHandState, playerHandStates);
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
        GameResultType resultType = GameResultType.parse(playerPoint, dealerPoint);
        return new PlayerWinningResult(player.getNickname(), resultType);
    }

    private HiddenDealerHandState getHiddenDealerHandState() {
        Player dealer = gameUserStorage.getDealer();
        List<Card> cards = cardManager.findCardsByNickname(dealer);
        return new HiddenDealerHandState(dealer.getNickname(), cards.getFirst());
    }

    private List<HandState> getAllPlayerHandState() {
        List<Player> allPlayers = gameUserStorage.getPlayers();
        return allPlayers.stream()
                .map(this::getHandState)
                .toList();
    }

    private HandState getHandState(Player player) {
        List<Card> cards = cardManager.findCardsByNickname(player);
        int point = cardManager.calculateSumByNickname(player);
        return new HandState(player.getNickname(), cards, point);
    }
}

