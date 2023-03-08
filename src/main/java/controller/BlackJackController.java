package controller;

import domain.card.CardDeck;
import domain.game.BlackJackGame;
import domain.player.Gambler;
import domain.player.HitState;
import domain.player.Name;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {
        final BlackJackGame blackJackGame = withExceptionHandle(this::setUpGame);
        hitOrStayForGamblers(blackJackGame);
        hitOrStayForDealer(blackJackGame);
        statistic(blackJackGame);
    }

    private BlackJackGame setUpGame() {
        final List<Name> gamblerNames = withExceptionHandle(this::createGamblerNames);
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final BlackJackGame blackJackGame = BlackJackGame.defaultSetting(cardDeck, gamblerNames);
        OutputView.printAfterFirstDeal(blackJackGame.dealer(), blackJackGame.gamblers());
        return blackJackGame;
    }

    private List<Name> createGamblerNames() {
        return InputView.readParticipantsName()
                .stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void hitOrStayForGamblers(final BlackJackGame blackJackGame) {
        while (blackJackGame.existCanHitGambler()) {
            final Gambler canHitGambler = blackJackGame.findCanHitGambler();
            final HitState hitState = withExceptionHandle(() -> inputHitOrStay(canHitGambler));
            blackJackGame.hitOrStayForGambler(canHitGambler, hitState);
            OutputView.showGamblerCardAreaState(canHitGambler);
        }
    }

    private HitState inputHitOrStay(final Gambler gambler) {
        return HitState.hitWhenBooleanIsTrue(InputView.readWantHit(gambler));
    }

    private void hitOrStayForDealer(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerShouldMoreHit()) {
            OutputView.printDealerOneMoreCard();
            blackJackGame.hitForDealer();
        }
    }

    private void statistic(final BlackJackGame blackJackGame) {
        OutputView.showGameStatistic(new GameStatisticResponse(
                blackJackGame.dealer(),
                blackJackGame.gamblers(),
                blackJackGame.statistic()));
    }

    private <T> T withExceptionHandle(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            Logger.error(e.getMessage());
            return withExceptionHandle(supplier);
        }
    }
}
