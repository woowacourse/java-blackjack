package service;

import domain.MatchResult;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.GameResultDto;

import java.util.Map;

public class GameManager {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public GameManager(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initHands(Deck deck) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.receive(deck.drawCard());
        }

        players.initialHands(deck, INITIAL_CARD_COUNT);
    }

    public GameResultDto calculateResults() {
        Map<Player, MatchResult> playersResult = players.calculateResult(dealer);
        Map<MatchResult, Integer> dealerResult = dealer.calculateResult(playersResult);

        return new GameResultDto(dealerResult, playersResult);
    }
}
