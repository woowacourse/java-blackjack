package controller;

import domain.GameResult;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.CardsWithTotalScoreDto;
import dto.NameWithCardsDto;
import utils.GameResultConvertor;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BlackJackGameController {
    private static final int INIT_CARD_COUNT = 2;

    private Players players;
    private Dealer dealer;

    public void run() {
        initParticipants();
        hit();
        showCardsTotal();
        showGameResult();
    }

    private void initParticipants() {
        final List<String> names = InputView.inputPlayerName();
        dealer = new Dealer();
        players = new Players(names);
        initCards();
        OutputView.printInitCardsResult(new NameWithCardsDto(getNameWithCardsAllParticipant()));
    }

    private void initCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            dealer.drawCard();
            players.drawCard();
        }
    }

    private Map<String, List<Card>> getNameWithCardsAllParticipant() {
        final Map<String, List<Card>> nameWithCardsAllParticipant = new LinkedHashMap<>(dealer.getNameWithCards());
        nameWithCardsAllParticipant.putAll(players.getCardsWithName());
        return nameWithCardsAllParticipant;
    }

    private void hit() {
        for (final Player player : players.getPlayers()) {
            catchHitPlayerException(player);
        }
        hitDealer();
    }

    private void catchHitPlayerException(final Player player) {
        try {
            hitPlayer(player);
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
        }
    }

    private void hitPlayer(final Player player) {
        int printCount = 0;
        while (player.drawCard(inputPlayerHitResponse(player))) {
            OutputView.printCardsWithName(new NameWithCardsDto(player.getNameWithCards()));
            printCount++;
        }
        if (printCount == 0) {
            OutputView.printCardsWithName(new NameWithCardsDto(player.getNameWithCards()));
        }
    }

    private boolean inputPlayerHitResponse(final Player player) {
        return InputView.inputTryToHit(player.getName());
    }

    private void hitDealer() {
        OutputView.printDealerHit(dealer.drawCard());
    }

    private void showCardsTotal() {
        dealer.stopRunning();
        final Map<String, Integer> totalScoreWithName = new LinkedHashMap<>(dealer.getTotalScoreWithName());
        totalScoreWithName.putAll(players.getTotalScoreWithName());
        final CardsWithTotalScoreDto cardsWithTotalScore = new CardsWithTotalScoreDto(getNameWithCardsAllParticipant(), totalScoreWithName);
        OutputView.printCardsWithTotalScore(cardsWithTotalScore);
    }

    private void showGameResult() {
        final Map<String, GameResult> playersGameResults = players.calculateGameResult(dealer);
        final Map<String, List<GameResult>> dealerGameResult = dealer.getGameResultWithName(new ArrayList<>(playersGameResults.values()));
        final Map<String, List<String>> gameResult = convertGameResultToString(dealerGameResult, playersGameResults);
        OutputView.printGameResultWithName(gameResult);
    }

    private Map<String, List<String>> convertGameResultToString(final Map<String, List<GameResult>> dealerGameResult, final Map<String, GameResult> playersGameResults) {
        return GameResultConvertor.convertToString(dealerGameResult, playersGameResults);
    }
}
