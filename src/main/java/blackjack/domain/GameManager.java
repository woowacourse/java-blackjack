package blackjack.domain;

import blackjack.dto.DrawnCardResult;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final CardManager cardManager;
    private Players players;

    public GameManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public void registerPlayers(List<Nickname> nicknames) {
        nicknames.add(Nickname.createDealerNickname());
        players = new Players(nicknames);
        cardManager.initialize(nicknames);
    }

    public void distributeCards() {
        cardManager.distributeCards();
    }

    public void hit(Player player) {
        cardManager.addCardByNickname(player.getNickname());
    }

    public int drawDealerCards() {
        int count = 0;
        Player dealer = players.getDealer();
        while (GameRule.shouldDrawCardToDealer(cardManager.calculateSumByNickname(dealer.getNickname()))) {
            cardManager.addCardByNickname(players.getDealer().getNickname());
            count++;
        }
        return count;
    }

    public boolean isBustPlayer(Player player) {
        return GameRule.isBurst(cardManager.calculateSumByNickname(player.getNickname()));
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<DrawnCardResult> calculateDrawnCardResults() {
        List<DrawnCardResult> drawnCardResults = new ArrayList<>();

        Player dealer = players.getDealer();
        drawnCardResults.add(getDrawnCardResult(dealer));

        List<Player> allPlayers = players.getPlayers();
        allPlayers.stream()
                .map(this::getDrawnCardResult)
                .forEach(drawnCardResults::add);

        return drawnCardResults;
    }

    public List<PlayerWinningResult> calculateGameResult() {
        Player dealer = players.getDealer();
        int dealerPoint = cardManager.calculateSumByNickname(dealer.getNickname());

        List<Player> allPlayers = players.getPlayers();
        return allPlayers.stream()
                .map(player -> calculatePlayerWinningResult(player, dealerPoint))
                .toList();
    }

    private PlayerWinningResult calculatePlayerWinningResult(Player player, int dealerPoint) {
        if (cardManager.calculateSumByNickname(player.getNickname()) > dealerPoint) {
            return new PlayerWinningResult(player.getNickname(), GameResultType.WIN);
        }

        if (cardManager.calculateSumByNickname(player.getNickname()) < dealerPoint) {
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

