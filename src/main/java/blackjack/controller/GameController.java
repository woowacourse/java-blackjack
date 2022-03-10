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
        Dealer dealer = Dealer.of();
        Players players = generatePlayers();

        initGame(players, dealer);
        OutputView.printInitGameState(players, dealer);

        askPlayersOneMoreCard(players);
        addDealerOneMoreCard(dealer);

        OutputView.printCardAndPoint(players, dealer);
        Statistic.of(dealer, players).calculate();

        //게임 승패 출력
        printGameResult(players);
    }

    private Players generatePlayers() {
        List<Player> playerList = new ArrayList<>();
        String[] names = InputView.inputPlayerName();
        for (String name : names) {
            playerList.add(Player.of(Name.of(name)));
        }
        return Players.of(playerList);
    }

    private void initGame(Players players, Dealer dealer) {
        dealer.addCard(CardDeck.giveCard());
        dealer.addCard(CardDeck.giveCard());
        players.giveCard();
        players.giveCard();
    }

    private void askPlayersOneMoreCard(Players players) {
        for (Player player : players.getCardNeedPlayers()) {
            askOneMoreCardByPlayer(player);
        }
    }

    private void addDealerOneMoreCard(Dealer dealer) {
        if (dealer.isOneMoreCard()) {
            dealer.addCard(CardDeck.giveCard());
            OutputView.printDealerCardAdded();
        }
    }

    // indent 수정 및 CardDeck 관련 수정 필요
    private void askOneMoreCardByPlayer(Player player) {
        boolean isFirstQuestion = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if (isFirstQuestion) {
                    OutputView.printHumanCardState(player);
                }
                break;
            }
            player.addCard(CardDeck.giveCard());
            OutputView.printHumanCardState(player);
            isFirstQuestion = false;
        }
    }

    private void printGameResult(Players players) {
        OutputView.printResult(players);
    }
}
