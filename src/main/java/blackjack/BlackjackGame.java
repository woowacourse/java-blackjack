package blackjack;

import blackjack.domain.HitOrStand;
import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Deck;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.HitResultDto;
import blackjack.domain.dto.WinDrawLoseDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private void initHit(Player dealer, Players players, Deck deck) {
        dealer.hit(deck.draw());
        dealer.hit(deck.draw());
        for (Player player : players.getPlayers()) {
            player.hit(deck.draw());
            player.hit(deck.draw());
        }
    }

    private Map<String, List<CardDto>> getCardStatus(Player dealer, Players players) {
        Map<String, List<CardDto>> cardStatus = new LinkedHashMap<>();
        addCardDto(cardStatus, dealer);
        List<Player> playerList = players.getPlayers();
        for (Player player : playerList) {
            addCardDto(cardStatus, player);
        }
        return cardStatus;
    }

    private void addCardDto(Map<String, List<CardDto>> cardStatus, Player player) {
        cardStatus.put(player.getName(), toListCardDto(player));
    }

    private void playersHit(Players players, Deck deck) {
        while (players.hasNext()) {
            playerHit(players, deck);
        }
    }

    private void playerHit(Players players, Deck deck) {
        HitOrStand flag = HitOrStand.valueOf(InputView.inputHitOrStand(players.getNowPlayer().getName()));
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
        OutputView.printPresentStatus(nowTurnPlayer.getName(), toListCardDto(nowTurnPlayer),
                nowTurnPlayer.getCards().calculateScore(),
                nowTurnPlayer.isBust());
    }

    private List<CardDto> toListCardDto(Player player) {
        return player.getViewCard().stream()
                .map(card -> new CardDto(card.getDenomination().getName(), card.getSuit().getName()))
                .collect(Collectors.toList());
    }

    private void dealerHit(Player dealer, Deck deck) {
        if (!(dealer instanceof Dealer)) {
            throw new RuntimeException("딜러의 구현체가 Dealer가 맞는지 확인.");
        }
        while (((Dealer) dealer).checkHitFlag()) {
            dealer.hit(deck.draw());
        }
    }

    private Map<String, HitResultDto> getHitResults(Player dealer, Players players) {
        Map<String, HitResultDto> hitResult = new LinkedHashMap<>();
        putHitResult(hitResult, dealer);
        for (Player player : players.getPlayers()) {
            putHitResult(hitResult, player);
        }
        return hitResult;
    }

    private void putHitResult(Map<String, HitResultDto> hitResult, Player player) {
        hitResult.put(player.getName(), new HitResultDto(toListCardDto(player), player.getCards().calculateScore()));
    }

    private List<WinDrawLoseDto> judgeWinDrawLose(Player dealer, Players players) {
        WinDrawLose.judge(dealer, players);
        return makeWinDrawLoseDto(dealer, players);
    }

    private List<WinDrawLoseDto> makeWinDrawLoseDto(Player dealer, Players players) {
        List<WinDrawLoseDto> winDrawLoseDtos = new ArrayList<>();
        winDrawLoseDtos.add(new WinDrawLoseDto(dealer.getName(), dealer.getWinDrawLoseString()));
        for (Player player : players.getPlayers()) {
            winDrawLoseDtos.add(new WinDrawLoseDto(player.getName(), player.getWinDrawLoseString()));
        }
        return winDrawLoseDtos;
    }
}
