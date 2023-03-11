package controller;

import domain.card.CardDeck;
import domain.game.BlackJackGame;
import domain.player.BettingMoney;
import domain.player.Gambler;
import domain.player.HitState;
import domain.player.Name;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class BlackJackController {

    public void run() {
        final BlackJackGame blackJackGame = setUpGame();
        hitOrStayForGamblers(blackJackGame);
        hitOrStayForDealer(blackJackGame);
        statistic(blackJackGame);
    }

    private BlackJackGame setUpGame() {
        final List<Name> gamblerNames = withExceptionHandle(this::createGamblerNames);
        final Map<Name, BettingMoney> gamblerBettingMoneyMap = betting(gamblerNames);
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final BlackJackGame blackJackGame = BlackJackGame.defaultSetting(cardDeck, gamblerBettingMoneyMap);
        OutputView.printAfterFirstDeal(blackJackGame.dealer(), blackJackGame.gamblers());
        return blackJackGame;
    }

    private List<Name> createGamblerNames() {
        return InputView.readParticipantsName()
                .stream()
                .map(Name::of)
                .collect(toList());
    }

    private Map<Name, BettingMoney> betting(final List<Name> gamblerNames) {
        return gamblerNames.stream()
                .collect(toMap(
                        identity(),
                        name -> withExceptionHandle(() -> BettingMoney.of(InputView.readBettingMoney(name))),
                        (a, b) -> b,
                        LinkedHashMap::new
                ));
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
        while (blackJackGame.hitForDealerWhenShouldMoreHit()) {
            OutputView.printDealerOneMoreCard();
        }
    }

    private void statistic(final BlackJackGame blackJackGame) {
        OutputView.showGameStatistic(
                new GameStatisticResponse(
                        blackJackGame.dealer(),
                        blackJackGame.gamblers(),
                        blackJackGame.revenue()
                )
        );
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
