package blackjack.controller;

import java.util.List;

import blackjack.domain.GameManager;
import blackjack.domain.GameRound;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.ProfitResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame implements GameManager {

    private Gamers gamers;
    private GameRound gameRound;
    private final Deck deck = Deck.generateFrom(new RandomCardStrategy());

    public void run() {
        setPlayers();
        betPlayers();
        drawPlayerCards();
        drawDealerCards();
        printResult();
        printProfit();
    }

    private void setPlayers() {
        List<String> names = InputView.readNames().names();
        Dealer dealer = new Dealer();
        gameRound = GameRound.start(dealer);
        gamers = Gamers.of(dealer,
            names.stream()
                .map(Player::new)
                .toList());
    }

    private void betPlayers() {
        for (var player : gamers.getPlayers()) {
            gameRound.betting(player, InputView.betting(player.getName()).bettingAmount());
        }
        OutputView.printStartingCards(drawStartingCards());
    }

    public StartingCardsResponseDto drawStartingCards() {
        drawStartingCards(gamers.getDealer());
        boolean dealerBlackjack = gamers.getDealer().isBlackjack();
        for (var player : gamers.getPlayers()) {
            drawStartingCards(player);
            if (player.isBlackjack() && !dealerBlackjack) {
                gameRound.endGameIfBlackjack(player);
            }
        }
        if (dealerBlackjack) {
            gameRound.endGameIfBlackjack(gamers.getDealer());
        }
        return StartingCardsResponseDto.of(gamers.getDealer(), gamers.getPlayers());
    }

    private void drawPlayerCards() {
        for (var player : gamers.getPlayers()) {
            drawCardsFor(player);
        }
    }

    private void drawCardsFor(Player player) {
        while (player.canReceiveAdditionalCards()
            && InputView.readAdditionalCardSelection(player.getName()).selection()) {
            drawCard(player);
            OutputView.printAdditionalCard(GamerDto.from(player));
        }
        if (!player.canReceiveAdditionalCards()) {
            OutputView.printBustNotice(player.getName());
        }
    }

    private void drawDealerCards() {
        Dealer dealer = gamers.getDealer();
        while (dealer.canReceiveAdditionalCards()) {
            drawCard(dealer);
            OutputView.printDealerDrawNotice();
            if (gameRound.endGameIfDealerBust()) {
                return;
            }
        }
    }

    private void printResult() {
        OutputView.printRoundResult(RoundResultsResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
        OutputView.printFinalResult(FinalResultResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
    }

    private void printProfit() {
        gameRound.computeResult();
        OutputView.printProfit(ProfitResponseDto.of(gameRound.getAllProfit()));
    }

    @Override
    public void drawStartingCards(Gamer gamer) {
        drawCard(gamer, STARTING_CARDS_SIZE);
    }

    @Override
    public void drawCard(Gamer gamer) {
        gamer.drawCard(deck);
    }

    private void drawCard(Gamer gamer, int count) {
        for (int i = 0; i < count; i++) {
            gamer.drawCard(deck);
        }
    }
}
