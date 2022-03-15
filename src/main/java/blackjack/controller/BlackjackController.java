package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.*;

import java.util.ArrayList;
import java.util.List;

import static blackjack.view.InputView.inputNames;
import static blackjack.view.InputView.isRequestHit;
import static blackjack.view.OutputView.*;

public class BlackjackController {

    private static final int CARD_COUNT_OF_FIRST_DISTRIBUTE = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackController() {
        players = new Players(inputNames());
        dealer = new Dealer();
        deck = new Deck();
    }

    public void run() {
        try {
            firstDistribute();
            hitOrStayForAllPlayers();
            addCardForDealerIfNeed();
            printTotalScore(computeTotalScore());
            printTotalResult(computeTotalResult());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage());
        }
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
            printDealerAdded(dealer.getName());
        }
    }

    private List<TotalScoreDto> computeTotalScore() {
        List<TotalScoreDto> totalScoreDtos = new ArrayList<>();
        totalScoreDtos.add(dealer.computeTotalScore());
        totalScoreDtos.addAll(players.computeTotalScore());
        return totalScoreDtos;
    }

    private TotalResultDto computeTotalResult() {
        List<PlayerResultDto> playersResult = players.computeResult(dealer.getScore());
        DealerResultDto dealerResult = dealer.computeResult(playersResult);
        return new TotalResultDto(playersResult, dealerResult);
    }

}
