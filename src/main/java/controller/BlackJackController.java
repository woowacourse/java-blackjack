package controller;

import common.Logger;
import domain.deck.CardDeck;
import domain.game.BlackJackGame;
import domain.player.HitState;
import domain.player.Name;
import domain.player.Player;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final Map<String, HitState> COMMAND_STATE_MAP = new HashMap<>();

    static {
        COMMAND_STATE_MAP.put("y", HitState.HIT);
        COMMAND_STATE_MAP.put("n", HitState.STAY);
    }

    public void run() {
        final BlackJackGame blackJackGame = setUpGame();
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
        final String command = InputView.readMoreCard(player);
        return mapCommandToStateOrElseThrow(command);
    }

    private HitState mapCommandToStateOrElseThrow(final String command) {
        final HitState hitState = COMMAND_STATE_MAP.getOrDefault(command, null);
        if (hitState == null) {
            throw new IllegalArgumentException("y 혹은 n 만을 입력해주세요");
        }
        return hitState;
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
