package domain.game;

import domain.card.Card;
import domain.card.GameDeck;
import domain.card.Score;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    public static final Name dealerName = new Name("딜러");

    private final List<Player> players;
    private final Dealer dealer;
    private final GameDeck gameDeck;

    public BlackjackGame(List<Player> players, Dealer dealer, GameDeck gameDeck) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
        this.gameDeck = gameDeck;
    }

    public void drawOneMoreCardForPlayer(Player player) {
        player.receiveCard(gameDeck.drawCard());
    }

    public void drawCardUntilDealerFinished() {
        boolean flag = dealer.hasResult();
        while (!flag) {
            dealer.receiveCard(gameDeck.drawCard());
            flag = dealer.hasResult();
        }
    }

    public HashMap<Name, List<Card>> makeSetUpResult() {
        HashMap<Name, List<Card>> setUpResult = new LinkedHashMap<>();
        setUpResult.put(dealer.getName(), dealer.getOnlyFirstCard());
        players.forEach(player -> setUpResult.put(player.getName(), player.getCards()));
        return setUpResult;
    }

    public void doStay(Player player) {
        player.doStay();
    }

    public boolean hasReadyPlayer() {
        return players.stream()
                .anyMatch(player -> !player.hasResult());
    }

    public Player getReadyPlayer() {
        return players.stream()
                .filter(player -> !player.hasResult())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 게임이 미완료된 플레이어가 없습니다."));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Card> getCards(Name name) {
        if (dealerName.equals(name))
            return dealer.getCards();

        return findPlayerByName(name).getCards();
    }

    public Score getScore(Name name) {
        if (dealerName.equals(name))
            return dealer.getScore();

        return findPlayerByName(name).getScore();
    }

    public List<Name> getAllUserNames() {
        List<Name> allUserNames = new ArrayList<>(List.of(dealer.getName()));
        allUserNames.addAll(players.stream().map(User::getName).collect(Collectors.toList()));
        return allUserNames;
    }

    private Player findPlayerByName(Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 User 이름입니다."));
    }

    public Map<Name, Integer> calculatePlayerResult() {
        GameResult gameResult = new GameResult(getAllUserNames().subList(1, getAllUserNames().size()));
        gameResult.saveResults(dealer, players);
        return gameResult.getPlayerPrizes();
    }

    public int dealerDrawCount() {
        return dealer.drawCount();
    }
}
