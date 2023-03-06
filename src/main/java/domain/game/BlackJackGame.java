package domain.game;

import domain.card.Card;
import domain.card.Cards;
import domain.user.Dealer;
import domain.user.DealerStatus;
import domain.user.Player;
import domain.user.PlayerStatus;
import domain.user.Players;
import domain.user.UserStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
    }

    public void drawDefaultCard() {
        drawPlayersDefaultCard();
        dealer.receiveCards(cards.drawTwoCards());
    }

    private void drawPlayersDefaultCard() {
        for (Player player : players.getPlayers()) {
            player.receiveCards(cards.drawTwoCards());
        }
    }

    public void drawOneMoreCard(Player player) {
        player.receiveCard(cards.drawCard());
    }

    public void drawDealerCardUntilSatisfyingCondition() {
        while (dealer.getStatus().equals(DealerStatus.UNDER_MIN_SCORE)) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public void judgeWinner() {
        for (Player player : players.getPlayers()) {
            judgeWinnerBetweenDealer(player);
        }
    }

    private void judgeWinnerBetweenDealer(Player player) {
        UserStatus playerStatus = player.getStatus();
        if (playerStatus.equals(PlayerStatus.BUST)) {
            dealer.win(player);
            return;
        }
        if (playerStatus.equals(PlayerStatus.NORMAL) && dealer.getStatus().equals(DealerStatus.BUST)) {
            player.win(dealer);
            return;
        }
        judgeWinnerByScore(player);
    }

    private void judgeWinnerByScore(Player player) {
        if (dealer.getScore() >= player.getScore()) {
            dealer.win(player);
            return;
        }
        player.win(dealer);
    }

    public Map<String, List<Card>> getSetUpResult() {
        Map<String, List<Card>> setUpResult = new LinkedHashMap<>();

        setUpResult.put(dealer.getName(), List.of(dealer.getFirstCard()));

        for (Player player : players.getPlayers()) {
            setUpResult.put(player.getName(), player.getCards());
        }

        return setUpResult;
    }

    public Map<String, Boolean> getPlayerFinalResult() {
        return players.getPlayerFinalResult();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
