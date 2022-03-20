package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.currentCards.CurrentCardsDto;
import blackjack.dto.currentCards.TotalCurrentCardsDto;
import blackjack.dto.profit.ProfitDto;
import blackjack.dto.profit.TotalProfitDto;
import blackjack.dto.score.TotalScoreDto;

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
            computeTotalScore();
            computeTotalProfit();
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
        printFirstDistribute(new TotalCurrentCardsDto(dealer, players));
    }

    private void hitOrStayForAllPlayers() {
        players.getPlayers().forEach(this::hitOrStay);
    }

    private void hitOrStay(Player player) {
        while (player.isHittable() && isRequestHit(player.getName())) {
            player.addCard(deck.draw());
            printCurrentStatus(CurrentCardsDto.from(player));
        }
    }

    private void addCardForDealerIfNeed() {
        while (dealer.isHittable()) {
            dealer.addCard(deck.draw());
            printDealerAdded(dealer.getName(), dealer.getBound());
        }
    }

    private void computeTotalScore() {
        dealer.computeAce();
        players.computeAceForAllPlayers();

        printTotalScore(new TotalScoreDto(dealer, players));
    }

    private void computeTotalProfit() {
        List<ProfitDto> profitOfPlayers = players.computeTotalProfit(dealer);
        ProfitDto profitOfDealer = dealer.computeProfit(profitOfPlayers);

        printTotalProfit(new TotalProfitDto(profitOfDealer, profitOfPlayers));
    }

}
