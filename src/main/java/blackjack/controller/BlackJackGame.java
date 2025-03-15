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

    public static BlackJackGame registerPlayers() {
        Deck deck = new DeckInitializer().generateDeck();
        Players players = Players.from(InputView.inputParticipant());
        Dealer dealer = new Dealer();
        return new BlackJackGame(deck, dealer, players);
    }

    private BlackJackGame(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void play() {
        initializeGame();
        OutputView.outputFirstCardDistributionResult(players, dealer);

        givePlayersCard();
        giveMoreDealerCard();
        BettingResult bettingResult = new BettingResult(GameResult.calculateGameResult(dealer, players));

        OutputView.outputFinalCardStatus(dealer, players);
        OutputView.outputFinalProfit(bettingResult.getBettingResult(), bettingResult.getDealerResult());
    }

    private void initializeGame() {
        players.getParticipants().forEach(p -> p.setBetting(InputView.inputBetting(p.getName())));
        players.getParticipants().forEach(p -> p.initialCard(deck.drawCard(), deck.drawCard()));
        dealer.putCard(deck.drawCard());
    }

    private void givePlayersCard() {
        Deque<Player> readyQueue = new LinkedList<>(players.getParticipants());
        while (!readyQueue.isEmpty()) {
            Player player = readyQueue.getFirst();
            processPlayerTurn(player, readyQueue);
        }
    }

    private void processPlayerTurn(Player player, Deque<Player> readyQueue) {
        HitOrStand hitOrStand = HitOrStand.from(InputView.inputHitOrStand(player.getName()));
        if (hitOrStand == HitOrStand.HIT) {
            handlePlayerHit(player, readyQueue);
        } else {
            readyQueue.poll();
        }
    }

    private void handlePlayerHit(Player player, Deque<Player> readyQueue) {
        player.putCard(deck.drawCard());
        OutputView.printPlayerCardStatus(player.getName(), player);

        if (player.isBust()) {
            OutputView.printParticipantBust(player.getName());
            readyQueue.poll();
        }
    }

    private void giveMoreDealerCard() {
        while (dealer.calculatePoint() <= ACE_THRESHOLD) {
            dealer.putCard(deck.drawCard());
            OutputView.outputDealerGetCard();
            OutputView.printPlayerCardStatus("딜러", dealer);
        }
        OutputView.outputDealerCardFinish();
    }
}
