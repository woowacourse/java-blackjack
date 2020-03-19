package blackjack.controller;

import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.result.MoneyChanger;
import blackjack.domain.result.MoneyResult;
import blackjack.domain.rule.BasicRule;
import blackjack.exceptions.InvalidMoneyException;
import blackjack.exceptions.InvalidPlayerException;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJack {

    private final Deck deck;
    private final Dealer dealer;
    private Participants participants;
    private MoneyChanger moneyChanger;

    private BlackJack() {
        this.deck = Deck.create();
        this.dealer = new Dealer();
    }

    public static void run() {
        new BlackJack().play();
    }

    private void play() {
        OutputView.nameInstruction();
        this.participants = getParticipants();
        this.moneyChanger = new MoneyChanger(participants);
        bettingPhase();
        initialPhase();
        userGamePhase();
        dealerGamePhase();
        endPhase();
    }

    private Participants getParticipants() {
        try {
            return new Participants(dealer, InputView.getInput());
        } catch (InvalidPlayerException e) {
            OutputView.printError(e.getMessage());
            return getParticipants();
        }
    }

    private void bettingPhase() {
        for (Participant participant : participants.getPlayers()) {
            betMoney(participant);
        }
    }

    private void betMoney(final Participant participant) {
        try {
            OutputView.moneyInstruction(participant);
            moneyChanger.receive(participant, InputView.getInput());
        } catch (InvalidMoneyException e) {
            OutputView.printError(e.getMessage());
            betMoney(participant);
        }
    }

    private void initialPhase() {
        OutputView.shareFirstPair(participants);
        participants.initialDraw(deck);
        OutputView.participantsStatus(participants);
    }

    private void userGamePhase() {
        dealerDrawsMore(participants.getDealer());
        playersDrawMore(participants.getPlayers());
    }

    private void dealerDrawsMore(final Participant participant) {
        participant.drawMoreCard(deck);
    }

    private void playersDrawMore(final List<Participant> players) {
        for (Participant player : players) {
            playersChooseToDraw((Player)player);
        }
    }

    private void playersChooseToDraw(final Player player) {
        boolean wantsMoreCard;
        do {
            OutputView.moreCardInstruction(player);
            wantsMoreCard = wantsToDrawMore(player);
            OutputView.participantStatus(player);
        } while (wantsMoreCard && !BasicRule.isBusted(player.score()));
    }

    private boolean wantsToDrawMore(final Player player) {
        final boolean wantsMoreCard;
        wantsMoreCard = InputView.yesOrNo();
        if (wantsMoreCard) {
            player.draw(deck);
        }
        return wantsMoreCard;
    }

    private void dealerGamePhase() {
        OutputView.moreCardInstruction(dealer);
    }

    private void endPhase() {
        OutputView.result(participants);
        OutputView.statistics(new MoneyResult(participants, moneyChanger));
    }
}
