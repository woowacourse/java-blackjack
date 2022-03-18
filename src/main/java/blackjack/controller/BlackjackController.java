package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.CurrentCardsDto;
import blackjack.dto.ProfitDto;
import blackjack.dto.TotalProfitDto;
import blackjack.dto.TotalScoreDto;

import java.util.ArrayList;
import java.util.List;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;

public class BlackjackController {

    private static final int CARD_COUNT_OF_FIRST_DISTRIBUTE = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackController() {
        this.players = new Players(inputNames());
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void run() {
        try {
            betMoney();
            firstDistribute();
            hitOrStayForAllPlayers();
            addCardForDealerIfNeed();
            printTotalScore(computeTotalScore());
            printTotalProfit(computeTotalProfit());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage());
        }
    }

    private void betMoney() {
        players.getPlayers().forEach(player -> player.betMoney(inputBettingMoney(player.getName())));
    }

    private void firstDistribute() {
        for (int i = 0; i < CARD_COUNT_OF_FIRST_DISTRIBUTE; i++) {
            players.addForAllPlayers(deck);
            dealer.addCard(deck.draw());
        }
        printFirstDistribute(CurrentCardsDto.of(dealer), players.generateCurrentCardsDTO());
    }

    private void hitOrStayForAllPlayers() {
        players.getPlayers().forEach(this::hitOrStay);
    }

    private void hitOrStay(Player player) {
        while (player.isHittable() && isRequestHit(player.getName())) {
            player.addCard(deck.draw());
            printCurrentStatus(CurrentCardsDto.of(player));
        }
    }

    private void addCardForDealerIfNeed() {
        while (dealer.isHittable()) {
            dealer.addCard(deck.draw());
            printDealerAdded(dealer.getName(), dealer.getBound());
        }
    }

    private List<TotalScoreDto> computeTotalScore() {
        List<TotalScoreDto> totalScore = new ArrayList<>();
        totalScore.add(dealer.computeTotalScore());
        totalScore.addAll(players.computeTotalScore());
        return totalScore;
    }

    private TotalProfitDto computeTotalProfit() {
        List<ProfitDto> profitOfPlayers = players.computeTotalProfit(dealer);
        ProfitDto profitOrDealer = dealer.computeProfit(profitOfPlayers);
        return new TotalProfitDto(profitOrDealer, profitOfPlayers);
    }

}
