package controller;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.CardsWithTotalScoreDto;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import view.InputView;
import view.OutputView;

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
        OutputView.printInitCardsResult(getNameWithCardsAllParticipant());
    }

    private void initCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            dealer.drawCard(CardDeck.draw());
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
        while (player.drawCard(CardDeck.draw(), inputPlayerHitRequest(player))) {
            OutputView.printCardsWithName(player.getNameWithCards());
            printCount++;
        }
        if (printCount == 0) {
            OutputView.printCardsWithName(player.getNameWithCards());
        }
    }

    private boolean inputPlayerHitRequest(final Player player) {
        return InputView.inputTryToHit(player.getName());
    }

    private void hitDealer() {
        OutputView.printDealerHit(dealer.drawCard(CardDeck.draw()));
    }

    private void showCardsTotal() {
        dealer.stopRunning();
        OutputView.printCardsWithTotalScore(CardsWithTotalScoreDto.generateDtos(dealer, players));
    }

    private void showGameResult() {
        OutputView.printGameResultWithName(new DealerResultDto(dealer, players), PlayerResultDto.generateDtos(dealer, players));
    }
}
