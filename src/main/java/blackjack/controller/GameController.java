package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Name;
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
        OutputView.printInitCards(players, dealer);

        // 카드 받을지 물어보는 기능
        for (Player player : players.getCardNeedPlayers()) {
            questionOneMoreCard(player);
        }

        // 딜러 카드 추가
        addCardToDealer(dealer);

        // 포인트 계산
        OutputView.printCardAndPoint(players, dealer);
        Statistic.of(dealer, players).calculate();

        //게임 승패 출력
        printGameResult(players);
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

    public void questionOneMoreCard(Player player) {
        boolean flag = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if (flag) {
                    OutputView.printHumanCardState(player);
                }
                break;
            }
            player.addCard(CardDeck.giveCard());
            OutputView.printHumanCardState(player);
            flag = false;
        }
    }
}