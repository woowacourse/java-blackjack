package controller;

import domain.deck.CardDeck;
import domain.game.BlackJackGame;
import domain.player.HitState;
import domain.player.Name;
import domain.player.Player;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {
        final BlackJackGame blackJackGame = withExceptionHandle(this::setUpGame);
        OutputView.printAfterFirstDeal(blackJackGame.dealer(), blackJackGame.participants());
        hitOrStayForParticipants(blackJackGame);
        hitOrStayForDealer(blackJackGame);
        OutputView.showGameStatistic(blackJackGame.statistic());
    }

    private BlackJackGame setUpGame() {
        final List<Name> participantNames = withExceptionHandle(this::createParticipantNames);
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        return BlackJackGame.defaultSetting(cardDeck, participantNames);
    }

    private List<Name> createParticipantNames() {
        return InputView.readParticipantsName()
                .stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void hitOrStayForParticipants(final BlackJackGame blackJackGame) {
        while (blackJackGame.existCanHitParticipant()) {
            final Player canHitPlayer = blackJackGame.findCanHitParticipant();
            final HitState hitState = withExceptionHandle(() -> inputHitOrStay(canHitPlayer));
            canHitPlayer.changeState(hitState);
            blackJackGame.hitOrStayForParticipant(canHitPlayer);
            OutputView.showPlayerCardAreaState(canHitPlayer);
        }
    }

    private HitState inputHitOrStay(final Player player) {
        if (withExceptionHandle(() -> InputView.readWantHit(player))) {
            return HitState.HIT;
        }
        return HitState.STAY;
    }

    private void hitOrStayForDealer(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerShouldMoreHit()) {
            OutputView.printDealerOneMoreCard();
            blackJackGame.hitForDealer();
        }
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
