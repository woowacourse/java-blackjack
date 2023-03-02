package controller;

import domain.deck.CardDeck;
import domain.game.BlackJackGame;
import domain.player.Name;
import domain.player.Participant;
import domain.player.State;
import org.jetbrains.annotations.NotNull;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final Map<String, State> COMMAND_STATE_MAP = new HashMap<>();

    static {
        COMMAND_STATE_MAP.put("y", State.HIT);
        COMMAND_STATE_MAP.put("n", State.STAY);
    }

    public void run() {
        final BlackJackGame blackJackGame = setUpGame();
        OutputView.printAfterFirstDeal(blackJackGame.dealer(), blackJackGame.participants());
        hitOrStayForParticipants(blackJackGame);
        hitOrStayForDealer(blackJackGame);
        OutputView.showGameStatistic(blackJackGame.statistic());
    }

    @NotNull
    private BlackJackGame setUpGame() {
        final List<Name> participantNames = createParticipantNames();
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
            final Participant canHitParticipant = blackJackGame.findCanHitParticipant();
            final State state = inputHitOrStay(canHitParticipant);
            canHitParticipant.changeState(state);
            blackJackGame.hitOrStayForParticipant(canHitParticipant);
            OutputView.showPlayerCardAreaState(canHitParticipant);
        }
    }

    private State inputHitOrStay(final Participant participant) {
        final String command = InputView.readMoreCard(participant);
        return mapOrThrowCommand(command);
    }

    private State mapOrThrowCommand(final String command) {
        final State state = COMMAND_STATE_MAP.getOrDefault(command, null);
        if (state == null) {
            throw new IllegalArgumentException("y 혹은 n 만을 입력해주세요");
        }
        return state;
    }

    private void hitOrStayForDealer(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerShouldMoreHit()) {
            OutputView.printDealerOneMoreCard();
            blackJackGame.hitForDealer();
        }
    }
}
