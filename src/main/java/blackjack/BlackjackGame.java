package blackjack;

import blackjack.domain.HitFlag;
import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    public void run() {
        Player dealer = new Dealer();
        Deck deck = new Deck();
        Players players = Players.fromNames(InputView.inputPlayerName());
        initHit(dealer, players, deck);
        OutputView.printInitCard(getCardStatus(dealer, players));
        playersHit(players, deck);
        dealerHit(dealer, deck);
        OutputView.printHitResult(getHitResults(dealer, players));
        OutputView.printResult(judgeWinDrawLose(dealer, players));
    }

    private List<Player> judgeWinDrawLose(Player dealer, Players players) {
        WinDrawLose.judge(dealer, players);
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(dealer);
        allPlayers.addAll(players.getPlayers());
        return allPlayers;
    }

    private Map<String, Cards> getHitResults(Player dealer, Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        result.put(dealer.getName(), dealer.getCards());
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getCards());
        }
        return result;
    }

    private Map<String, Cards> getCardStatus(Player dealer, Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        result.put(dealer.getName(), dealer.getShowCards());
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getShowCards());
        }
        return result;
    }

    private void initHit(Player dealer, Players players, Deck deck) {
        dealer.hit(deck.draw());
        dealer.hit(deck.draw());
        for (Player player : players.getPlayers()) {
            player.hit(deck.draw());
            player.hit(deck.draw());
        }
    }

    private void playersHit(Players players, Deck deck) {
        while (players.hasNext()) {
            playerHit(players, deck);
        }
    }

    private void playerHit(Players players, Deck deck) {
        HitFlag flag = HitFlag.commandOf(InputView.inputHitOrStand(players.getNowPlayer().getName()));
        if (flag.isStand()) {
            players.next();
            return;
        }
        hitAndOutputResult(players, deck);
    }

    private void hitAndOutputResult(Players players, Deck deck) {
        Player nowTurnPlayer = players.getNowPlayer();
        nowTurnPlayer.hit(deck.draw());
        if (nowTurnPlayer.isBust()) {
            players.next();
        }
        OutputView.printPresentStatus(nowTurnPlayer);
    }

    private void dealerHit(Player dealer, Deck deck) {
        if (!(dealer instanceof Dealer)) {
            throw new RuntimeException("딜러의 구현체가 Dealer가 맞는지 확인.");
        }
        while (((Dealer) dealer).checkHitFlag()) {
            dealer.hit(deck.draw());
        }
    }
}
