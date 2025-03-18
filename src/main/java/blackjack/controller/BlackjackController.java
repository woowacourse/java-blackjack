package blackjack.controller;

import java.util.List;

import blackjack.domain.BettingTable;
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

public class BlackjackController {

    // TODO: CARD 관련 상수들 분리
    public static final int STARTING_CARDS_SIZE = 2;

    private boolean finishGame = false;
    private Gamers gamers;
    private BettingTable bettingTable;
    private final Deck deck = Deck.generateFrom(new RandomCardStrategy());

    public void run() {
        setPlayers();
        betPlayers();
        drawStartingCards();
        if (!finishGame) {
            drawPlayerCards();
            drawDealerCards();
        }
        printResult();
        printProfit();
    }

    private void setPlayers() {
        List<String> names = InputView.readNames().names();
        Dealer dealer = new Dealer();
        bettingTable = BettingTable.start(dealer);
        gamers = Gamers.of(dealer, namesToPlayers(names));
    }

    private List<Player> namesToPlayers(List<String> names) {
        return names.stream()
            .map(Player::new)
            .toList();
    }

    private void betPlayers() {
        for (var player : gamers.getPlayers()) {
            bettingTable.betting(player, InputView.betting(player.getName()).bettingAmount());
        }
    }

    private void drawStartingCards() {
        drawStartingCards(gamers.getDealer());
        boolean dealerBlackjack = gamers.getDealer().isBlackjack();
        for (var player : gamers.getPlayers()) {
            drawStartingCards(player);
            if (player.isBlackjack() && !dealerBlackjack &&
                bettingTable.endGameIfBlackjack(player)) {
                finishGame = true;
            }
        }
        if (dealerBlackjack) {
            bettingTable.endGameIfBlackjack(gamers.getDealer());
            finishGame = true;
        }
        OutputView.printStartingCards(StartingCardsResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
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
            if (dealer.isBust()) {
                bettingTable.endGameIfDealerBust();
            }
        }
    }

    private void printResult() {
        OutputView.printRoundResult(RoundResultsResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
        OutputView.printFinalResult(FinalResultResponseDto.of(gamers.getDealer(), gamers.getPlayers()));
    }

    private void printProfit() {
        bettingTable.computeResult();
        OutputView.printProfit(ProfitResponseDto.of(bettingTable.getAllProfit()));
    }

    public void drawStartingCards(Gamer gamer) {
        drawCard(gamer, STARTING_CARDS_SIZE);
    }

    public void drawCard(Gamer gamer) {
        gamer.drawCard(deck);
    }

    private void drawCard(Gamer gamer, int count) {
        for (int i = 0; i < count; i++) {
            gamer.drawCard(deck);
        }
    }
}
