package controller;

import java.util.List;
import java.util.Map;
import model.BlackJack;
import model.Rank;
import model.Suit;
import model.deck.DeckImpl;
import model.participant.Dealer;
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
        String rawNames = inputView.readPlayerNames();
        List<String> parsed = InputParser.parseName(rawNames);

        Participants participants = Participants.of(parsed);
        BlackJack blackJack = BlackJack.of(participants, DeckImpl.of(Suit.VALUES, Rank.VALUES));

        Map<String, List<String>> dealOutResult = blackJack.dealOut();
        outputView.printDealOut(dealOutResult);

        for (Participant participant : participants) {
            if (participant instanceof Dealer) {
                continue;
            }

            if (participant.calculateScore() == 21) {
                continue;
            }

            String input;
            do {
                input = inputView.readHitOrNot(participant.getName());
                if (input.equals("n")) {
                    outputView.printHands(participant);
                    break;
                }
                blackJack.hit(participant);
                if (participant.isBust()) {
                    outputView.printBustState(participant.getName(), participant.calculateScore());
                    outputView.printHands(participant);
                    break;
                }
                outputView.printHands(participant);

            } while (input.equals("y"));
        }

        Participant dealer = participants.getDealer();
        if (dealer.dealerNeedDraw()) {
            blackJack.hit(dealer);
            outputView.printDealerDraw();
        }

        outputView.printHandsAndScore(participants);
        outputView.printResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResult());
    }
}
