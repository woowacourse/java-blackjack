package blackjack;

import blackjack.domain.HitOrStand;
import blackjack.domain.WinDrawLose;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.HitResultDto;
import blackjack.domain.dto.WinDrawLoseDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Players players = Players.fromNames(InputView.inputPlayerName());
        List<Player> playerList = players.getPlayers();
        Dealer dealer = Dealer.init();
        OutputView.printInitCard(makeCardStatusDto(dealer, players));
        gamerHit(playerList, dealer);
        OutputView.printHitResult(makeHitResultDto(playerList, dealer));
        WinDrawLose.judgeResult(dealer, players);
        OutputView.printResult(makeWinDrawLoseDto(playerList, dealer));
    }

    private static List<WinDrawLoseDto> makeWinDrawLoseDto(List<Player> playerList, Dealer dealer) {
        List<WinDrawLoseDto> winDrawLoseDtos = new ArrayList<>();
        winDrawLoseDtos.add(new WinDrawLoseDto(dealer.getName(), dealer.getWinDrawLoseString()));
        for (Player player : playerList) {
            winDrawLoseDtos.add(new WinDrawLoseDto(player.getName(), player.getWinDrawLoseString()));
        }
        return winDrawLoseDtos;
    }

    private static List<HitResultDto> makeHitResultDto(List<Player> playerList, Dealer dealer) {
        List<HitResultDto> hitResultDtos = new ArrayList<>();
        hitResultDtos.add(
                new HitResultDto(dealer.getName(), toListCardDto(dealer), dealer.getCards().calculateScore()));
        for (Player player : playerList) {
            hitResultDtos.add(
                    new HitResultDto(player.getName(), toListCardDto(player), player.getCards().calculateScore()));
        }
        return hitResultDtos;
    }

    private static void gamerHit(List<Player> playerList, Dealer dealer) {
        for (Player player : playerList) {
            playerHit(player);
        }
        while (dealer.checkHitFlag()) {
            dealer.hit();
        }
    }

    private static Map<String, List<CardDto>> makeCardStatusDto(Dealer dealer, Players players) {
        Map<String, List<CardDto>> cardStatus = new LinkedHashMap<>();
        addCardDto(cardStatus, dealer);
        List<Player> playerList = players.getPlayers();
        for (Player player : playerList) {
            addCardDto(cardStatus, player);
        }

        return cardStatus;
    }

    private static void playerHit(Player player) {
        HitOrStand flag = HitOrStand.valueOf(InputView.inputHitOrStand(player.getName()));
        if (!flag.isValue()) {
            return;
        }
        player.hit();
        OutputView.printPresentStatus(player.getName(), toListCardDto(player));
        if (bustOrBlackJack(player)) {
            return;
        }
        playerHit(player);
    }

    private static boolean bustOrBlackJack(Player player) {
        if (player.isBust()) {
            OutputView.printBustPlayer(player.getName(), player.getCards().calculateScore());
            return true;
        }
        if (player.isBlackjack()) {
            OutputView.printBlackjackPlayer(player.getName());
            return true;
        }
        return false;
    }

    private static void addCardDto(Map<String, List<CardDto>> cardStatus, Gamer gamer) {
        cardStatus.put(gamer.getName(), toListCardDto(gamer));
    }

    private static List<CardDto> toListCardDto(Gamer gamer) {
        return gamer.getViewCard().stream()
                .map(card -> new CardDto(card.getDenomination().getName(), card.getSuit().getName()))
                .collect(Collectors.toList());
    }
}
