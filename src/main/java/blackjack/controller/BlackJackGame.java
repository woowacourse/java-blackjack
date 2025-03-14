package blackjack.controller;

import blackjack.model.game.Deck;
import blackjack.model.game.DeckInitializer;
import blackjack.model.game.GameResult;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Deque;
import java.util.LinkedList;

public class BlackJackGame {
    private static final int ACE_THRESHOLD = 16;
    private final Deck deck;
    private final Players players;
    private final Dealer dealer;


    public BlackJackGame(InputView inputView) {
        this.deck = new DeckInitializer().generateDeck();
        this.players = Parser.parseParticipants(inputView.inputParticipant());
        this.dealer = new Dealer();
    }

    public void play(InputView inputView, OutputView outputView) {
        initializeGame();
        outputView.outputFirstCardDistributionResult(players, dealer);

        giveParticipantsMoreCard(inputView, outputView);
        giveMoreDealerCard(outputView);

        GameResult gameResult = new GameResult(dealer, players);
        outputView.outputFinalCardStatus(dealer, players);
        outputView.outputFinalResult(gameResult.getWinLoseResult(), gameResult.getDealerWinCount(),
                gameResult.getDealerLoseCount());
    }

    private void initializeGame() {
        players.getParticipants().forEach(this::putTwoCard);
        putTwoCard(dealer);
    }

    private void putTwoCard(Participant participant) {
        participant.putCard(deck.drawCard());
        participant.putCard(deck.drawCard());
    }

    private void giveParticipantsMoreCard(InputView inputView, OutputView outputView) {
        Deque<Player> readyQueue = new LinkedList<>(players.getParticipants());
        while (!readyQueue.isEmpty()) {
            Player player = readyQueue.getFirst();
            boolean isReceive = Parser.parseCommand(inputView.inputCallOrStay(player.getName()));
            if (isReceive) {
                player.putCard(deck.drawCard());
                outputView.printPlayerCardStatus(player.getName(), player);
                if (player.isBust()) {
                    outputView.printParticipantBust(player.getName());
                    readyQueue.poll();
                }
            } else {
                readyQueue.poll();
            }
        }
    }

    private void giveMoreDealerCard(OutputView outputView) {
        while (dealer.calculatePoint() <= ACE_THRESHOLD) {
            dealer.putCard(deck.drawCard());
            outputView.outputDealerGetCard();
            outputView.printPlayerCardStatus("딜러", dealer);
        }
        outputView.outputDealerCardFinish();
    }
}