package blackjack.controller;

import blackjack.model.game.BettingResult;
import blackjack.model.game.Deck;
import blackjack.model.game.DeckInitializer;
import blackjack.model.game.GameResult;
import blackjack.model.game.HitOrStand;
import blackjack.model.player.Dealer;
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
        this.players = Players.from(inputView.inputParticipant());
        this.dealer = new Dealer();
    }

    public void play(InputView inputView, OutputView outputView) {
        initializeGame(inputView);
        outputView.outputFirstCardDistributionResult(players, dealer);

        givePlayersCard(inputView, outputView);
        giveMoreDealerCard(outputView);
        BettingResult bettingResult = new BettingResult(GameResult.calculateGameResult(dealer, players));

        outputView.outputFinalCardStatus(dealer, players);
        outputView.outputFinalProfit(bettingResult.getBettingResult(), bettingResult.getDealerResult());
    }


    private void initializeGame(InputView inputView) {
        players.getParticipants().forEach(p -> p.setBetting(inputView.inputBetting(p.getName())));
        players.getParticipants().forEach(p -> p.initialCard(deck.drawCard(), deck.drawCard()));
        dealer.putCard(deck.drawCard());
    }


    private void givePlayersCard(InputView inputView, OutputView outputView) {
        Deque<Player> readyQueue = new LinkedList<>(players.getParticipants());
        while (!readyQueue.isEmpty()) {
            Player player = readyQueue.getFirst();
            processPlayerTurn(player, inputView, outputView, readyQueue);
        }
    }

    private void processPlayerTurn(Player player, InputView inputView, OutputView outputView,
                                   Deque<Player> readyQueue) {
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(player.getName()));
        if (hitOrStand == HitOrStand.HIT) {
            handlePlayerHit(player, outputView, readyQueue);
        } else {
            readyQueue.poll();
        }
    }

    private void handlePlayerHit(Player player, OutputView outputView, Deque<Player> readyQueue) {
        player.putCard(deck.drawCard());
        outputView.printPlayerCardStatus(player.getName(), player);

        if (player.isBust()) {
            outputView.printParticipantBust(player.getName());
            readyQueue.poll();
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