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

    public void run() {
        final var dealer = new Dealer();
        final var players = new Players(InputView.inputPlayerName());
        initCards(dealer, players);
        OutputView.printInitCardsResult(getNameWithCardsAllParticipant(dealer, players));
        hit(players, dealer);
        showCardsTotal(dealer, players);
        showGameResult(dealer, players);
    }

    private void initCards(final Dealer dealer, final Players players) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            dealer.drawCard(CardDeck.draw());
            players.drawCard();
        }
    }

    private Map<String, List<Card>> getNameWithCardsAllParticipant(final Dealer dealer, final Players players) {
        final Map<String, List<Card>> nameWithCardsAllParticipant = new LinkedHashMap<>(dealer.getNameWithCards());
        nameWithCardsAllParticipant.putAll(players.getCardsWithName());
        return nameWithCardsAllParticipant;
    }

    private void hit(final Players players, final Dealer dealer) {
        for (final Player player : players.getPlayers()) {
            catchHitPlayerException(player);
        }
        hitDealer(dealer);
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

    private void hitDealer(final Dealer dealer) {
        OutputView.printDealerHit(dealer.drawCard(CardDeck.draw()));
    }

    private void showCardsTotal(final Dealer dealer, final Players players) {
        dealer.stopRunning();
        OutputView.printCardsWithTotalScore(CardsWithTotalScoreDto.generateDtos(dealer, players));
    }

    private void showGameResult(final Dealer dealer, final Players players) {
        OutputView.printGameResultWithName(new DealerResultDto(dealer, players), PlayerResultDto.generateDtos(dealer, players));
    }
}
