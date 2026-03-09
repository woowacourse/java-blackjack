package controller;

import java.util.List;
import java.util.Map;
import model.BlackJack;
import model.Rank;
import model.Suit;
import model.deck.DeckImpl;
import model.participant.Participant;
import model.Participants;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = setUpParticipants();
        BlackJack blackJack = BlackJack.of(participants, DeckImpl.of(Suit.values(), Rank.values()));

        Map<String, List<String>> dealOutResult = blackJack.dealOut();
        outputView.printDealOut(dealOutResult);

        blackJack.startPlayerTurn(this::askHit, this::afterHit);
        blackJack.startDealerTurn(this::afterDealerTurn);

        outputView.printHandsAndScore(participants);
        outputView.printResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResult());
    }

    private Participants setUpParticipants() {
        String rawNames = inputView.readPlayerNames();
        List<String> parsed = InputParser.parseName(rawNames);

        return Participants.of(parsed);
    }

    private Boolean askHit(String name) {
        return inputView.readHitOrNot(name);
    }

    private void afterHit(Participant participant) {
        outputView.printHands(participant);

        if (participant.isBust()) {
            outputView.printBustState(participant.getName(), participant.calculateScore());
        }
    }

    private void afterDealerTurn(Boolean draw) {
        outputView.printDealerDrawResult(draw);
    }
}
