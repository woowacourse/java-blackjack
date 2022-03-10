package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
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
        CardDeck cardDeck = CardDeckGenerator.generate();
        Dealer dealer = initDealer(cardDeck);
        Players players = initPlayers(cardDeck);
        OutputView.printInitGameState(players, dealer);

        askPlayersOneMoreCard(players, cardDeck);
        addDealerOneMoreCard(dealer, cardDeck);

        OutputView.printCardAndPoint(players, dealer);
        Statistic.of(dealer, players).calculate();

        //게임 승패 출력
        printGameResult(players);
    }

    private Dealer initDealer(CardDeck cardDeck) {
        Dealer dealer = Dealer.of();
        dealer.addCard(cardDeck.giveCard());
        dealer.addCard(cardDeck.giveCard());
        return dealer;
    }

    private Players initPlayers(CardDeck cardDeck) {
        Players players = generatePlayers();
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);
        return players;
    }

    private Players generatePlayers() {
        List<Player> playerList = new ArrayList<>();
        String[] names = InputView.inputPlayerName();
        for (String name : names) {
            playerList.add(Player.of(Name.of(name)));
        }
        return Players.of(playerList);
    }

    private void askPlayersOneMoreCard(Players players, CardDeck cardDeck) {
        for (Player player : players.getCardNeedPlayers()) {
            askOneMoreCardByPlayer(player, cardDeck);
        }
    }

    private void addDealerOneMoreCard(Dealer dealer, CardDeck cardDeck) {
        if (dealer.isOneMoreCard()) {
            dealer.addCard(cardDeck.giveCard());
            OutputView.printDealerCardAdded();
        }
    }

    // indent 수정 및 CardDeck 관련 수정 필요
    private void askOneMoreCardByPlayer(Player player, CardDeck cardDeck) {
        boolean isFirstQuestion = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if (isFirstQuestion) {
                    OutputView.printHumanCardState(player);
                }
                break;
            }
            player.addCard(cardDeck.giveCard());
            OutputView.printHumanCardState(player);
            isFirstQuestion = false;
        }
    }

    private void printGameResult(Players players) {
        OutputView.printResult(players);
    }
}
