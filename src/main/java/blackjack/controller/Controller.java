package blackjack.controller;

import blackjack.dto.ProfitsDto;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.model.Profits;
import blackjack.model.Referee;
import blackjack.util.Splitter;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static final int INITIAL_DEAL_REPEAT = 2;
    public static final int ONE_REPEAT = 1;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Deck deck = new Deck();
        Referee referee = new Referee();
        Dealer dealer = new Dealer();
        Players players = createPlayer();

        deck.shuffle();
        dealFirstTwoCards(dealer, players, deck);
        outputView.printFirstCardStatus(dealer, players);

        turnToPlayers(players, deck);
        turnToDealer(dealer, deck);
        outputView.printScoreResult(dealer, players);

        Profits profits = new Profits(players.judgeAll(dealer, referee));
        ProfitsDto profitsDto = profits.toDto();
        outputView.printGameResult(profitsDto);
    }

    private Players createPlayer() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = Splitter.split(inputView.getName()).stream().toList();
        for (String name : playerNames) {
            players.add(new Player(name, inputView.getBettingAmount(name)));
        }
        return new Players(players);
    }

    private void dealFirstTwoCards(Dealer dealer, Players players, Deck deck) {
        receiveCardToParticipant(dealer, deck, INITIAL_DEAL_REPEAT);
        players.giveTwoCards(deck);
    }

    private void turnToPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            turnToOnePlayer(deck, player);
        }
    }

    private void turnToOnePlayer(Deck deck, Player player) {
        while (player.canReceive()) {
            if (inputView.getReceiveCard(player)) {
                return;
            }
            player.receiveCard(deck.giveCard());
            outputView.printPlayerCardStatus(player, player.getHandCards());
        }
    }

    private void turnToDealer(Dealer dealer, Deck deck) {
        while (dealer.canReceive()) {
            outputView.printDealerReceiveCard();
            receiveCardToParticipant(dealer, deck, ONE_REPEAT);
        }
    }

    private void receiveCardToParticipant(Participant participant, Deck deck, int repeat) {
        for (int i = 0; i < repeat; i++) {
            participant.receiveCard(deck.giveCard());
        }
    }
}
