package view;

import common.PlayerDto;
import domain.PlayersResult;
import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final int FIRST_CARD_INDEX = 0;

    public static void printInitGamersState(PlayerDto dealerDto, Players players) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        for(int playerIndex=0; playerIndex < players.participantNumber(); playerIndex++){
            Player player = players.eachPlayer(playerIndex);
            playerDtos.add(new PlayerDto(player.getName(),player.getPlayingCards().getCards()));
        }
        String dealerName = dealerDto.getName();
        String playerNames = playerDtos.stream().map(PlayerDto::getName).collect(Collectors.joining(DELIMITER));
        System.out.println(String.format("%s와 %s에게 2장의 카드를 나누었습니다.",dealerName, playerNames));
        printInitDealerCard(dealerDto);
        for (PlayerDto playerDto : playerDtos) {
            printGamerCardsState(playerDto);
        }
    }

    public static void printAllPlayerCardsStateWithScore(Players players, Dealer dealer) {
        printEachPlayerCardsStateWithScore(PlayerDto.of(dealer), dealer.calculateScore());
        for(int playerIndex=0; playerIndex < players.participantNumber(); playerIndex++){
            Player player = players.eachPlayer(playerIndex);
            printEachPlayerCardsStateWithScore(PlayerDto.of(player), player.calculateScore());
        }

    }

    private static void printInitDealerCard(PlayerDto dealerDto) {
        Card card =  dealerDto.getCards().get(FIRST_CARD_INDEX);
        System.out.println(String.format("%s : %s%s"
                ,dealerDto.getName(),card.getSymbol().getWord(), card.getType().getPattern()));
    }

    public static void printGamerCardsState(PlayerDto playerDto) {
        String gamerCards = playerDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format("%s: %s", playerDto.getName(), gamerCards));
    }

    public static void printDealerHit() {
        System.out.println("딜러 16이하라 한 장의 카드를 더 받습니다.\n");
    }

    private static void printEachPlayerCardsStateWithScore(PlayerDto playerDto, int gamerScore) {
        String gamerCards = playerDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format("%s: %s - 결과: %d", playerDto.getName(), gamerCards, gamerScore));
    }

    public static void printGameResult(PlayersResult playersResult, Players players) {
        System.out.println("최종 수익");
        System.out.println(String.format("딜러 : %d",playersResult.dealerProfit()));
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + " : " + player.getPlayerBettingMoney());
        }
    }
}
