package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.GameResult;
import blackjack.domain.Name;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    public void run() {
        Players players = getPlayers();
        Dealer dealer = Dealer.of();
        // 카드 2장 지급
        giveTwoCards(players, dealer);
        // 지급 받은 카드 목록 출력
        printInitCards(players, dealer);

        // 딜러 카드 추가
        addCardToDealer(dealer);

        // 카드 받을지 물어보는 기능
        for (Player player : players.getCardNeedPlayers()) {
            questionOneMoreCard(player);
        }

        // 포인트 계산
        OutputView.printHumanCardPointState(dealer);
        for(Player player : players.getPlayers()){
            OutputView.printHumanCardPointState(player);
        }

        calculateStatistic(dealer, players);


    }
    private void calculateStatistic(Dealer dealer, Players players){
        int dealerPoint = dealer.getPoint();
        // 초과
        if (dealerPoint > 21) {
            int playerWinCount =0;
            for (Player player : players.getPlayers()) {
                int point = player.getPoint();
                if (point <= 21){
                    // 플레이어 승리
                    player.setResult(GameResult.WIN);
                    playerWinCount++;
                }
                // 플레이어 패배
                player.setResult(GameResult.LOSE);
            }
            if(playerWinCount == 0){
                // 플레이어 패배
                for (Player player : players.getPlayers()) {
                    player.setResult(GameResult.LOSE);
                }
            }
        }
        if(dealerPoint <= 21) {
            for (Player player : players.getPlayers()) {
                int point = player.getPoint();
                if(point > 21 || dealerPoint > point){
                    // 플레이어 패배
                    player.setResult(GameResult.LOSE);
                }
                if(dealerPoint == point){
                    // 무승부
                    player.setResult(GameResult.DRAW);
                }
                // 플레이어 승리
                player.setResult(GameResult.WIN);
            }
        }
    }
    private void addCardToDealer(Dealer dealer) {
        if (dealer.isOneMoreCard()) {
            dealer.addCard(CardDeck.giveCard());
            OutputView.printDealerCardAdded();
        }
    }

    private Players getPlayers() {
        List<Player> playerList = new ArrayList<>();
        String[] names = InputView.inputPlayerName();
        for (String name : names) {
            playerList.add(Player.of(Name.of(name)));
        }
        return Players.of(playerList);
    }


    private void giveTwoCards(Players players, Dealer dealer) {
        dealer.addCard(CardDeck.giveCard());
        dealer.addCard(CardDeck.giveCard());
        players.giveCard();
        players.giveCard();
    }

    private void printGameResult(Players players) {
        OutputView.printResult(players);
    }

    private void printInitCards(Players players, Dealer dealer) {
        OutputView.printInitCardState(players, dealer);
        OutputView.printHumanCardState(dealer);
        for(Player player : players.getPlayers()){
            OutputView.printHumanCardState(player);
        }
    }

    public void questionOneMoreCard(Player player) {
        boolean flag = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if(flag){
                    OutputView.printHumanCardState(player);
                }
                break;
            }
            player.addCard(CardDeck.giveCard());
            OutputView.printHumanCardState(player);
            flag = false;
        }
    }

//    public void questionOneMoreCard(Player player) {
//        while (player.isOneMoreCard()) {
//            if (InputView.inputOneMoreCard(player.getName())) {
//                player.addCard(CardDeck.giveCard());
//                continue;
//            }
//            player.toNoCardNeeded();
//        }
//    }
}