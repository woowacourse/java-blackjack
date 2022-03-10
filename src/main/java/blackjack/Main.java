package blackjack;

import blackjack.domain.CardDto;
import blackjack.domain.Dealer;
import blackjack.domain.Gamer;
import blackjack.domain.HitOrStand;
import blackjack.domain.HitResultDto;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.WinDrawLose;
import blackjack.domain.WinDrawLoseDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Players players = Players.fromNames(InputView.inputPlayerName());
        Dealer dealer = Dealer.init();
        Map<String, List<CardDto>> cardStatus = new LinkedHashMap<>();
        addCardDto(cardStatus, dealer);
        List<Player> playerList = players.getPlayers();
        for (Player player : playerList) {
            addCardDto(cardStatus, player);
        }
        OutputView.printInitCard(cardStatus);

        for (Player player : playerList) {
            playerHit(player);
        }

        while (dealer.checkHitFlag()) {
            dealer.hit();
        }

        List<HitResultDto> hitResultDtos = new ArrayList<>();
        hitResultDtos.add(new HitResultDto(dealer.getName(), toListCardDto(dealer), dealer.getCards().calculateScore()));
        for (Player player : playerList) {
            hitResultDtos.add(new HitResultDto(player.getName(), toListCardDto(player), player.getCards().calculateScore()));
        }

        OutputView.printHitResult(hitResultDtos);

        WinDrawLose.judgeResult(dealer, players);
        List<WinDrawLoseDto> winDrawLoseDtos = new ArrayList<>();
        winDrawLoseDtos.add(new WinDrawLoseDto(dealer.getName(), dealer.getWinDrawLoseString()));
        for (Player player : playerList) {
            winDrawLoseDtos.add(new WinDrawLoseDto(player.getName(), player.getWinDrawLoseString()));
        }
        OutputView.printResult(winDrawLoseDtos);
    }

    private static void playerHit(Player player) {
        HitOrStand flag = HitOrStand.valueOf(InputView.inputHitOrStand(player.getName()));
        if (!flag.isValue()) {
            return;
        }
        player.hit();
        OutputView.printPresentStatus(player.getName(), toListCardDto(player));
        if (player.isBust()) {
            OutputView.printBustPlayer(player.getName(), player.getCards().calculateScore());
            return;
        }
        if (player.isBlackjack()) {
            OutputView.printBlackjackPlayer(player.getName());
            return;
        }
        playerHit(player);
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
