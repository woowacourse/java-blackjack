package controller;

import domain.BlackjackGame;
import domain.Card;
import domain.Decision;
import domain.RandomShuffleStrategy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.initialize(inputView.readNames(), new RandomShuffleStrategy());
        outputView.printParticipantsInitialState(toCardNames(blackjackGame.getParticipantsCards()));
        while (blackjackGame.hasDrawablePlayer()) {
            String currentPlayerName = blackjackGame.nextDrawablePlayer();
            Decision decision = Decision.from(inputView.readDecision(currentPlayerName));
            if (decision.equals(Decision.HIT)) {
                blackjackGame.handOutCardToNextPlayer();
            }
            if (decision.equals(Decision.STAND)) {
                blackjackGame.standCurrentPlayer();
            }
            outputView.printParticipantCards(currentPlayerName,
                    toCardNames(blackjackGame.getParticipantCards(currentPlayerName)));
        }
        while (blackjackGame.isDealerDrawable()) {
            blackjackGame.handOutCardToDealer();
            outputView.printDealerHandOutInfo();
        }
        outputView.printParticipantsResults(toCardNames(blackjackGame.getParticipantsCards()), blackjackGame.scores());
        outputView.printGameOutcomes(blackjackGame.getPlayersOutcome());
    }

    public List<String> toCardNames(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<String, List<String>> toCardNames(Map<String, List<Card>> participantsCards) {
        return participantsCards.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, e -> toCardNames(e.getValue())
                        , (d1, d2) -> d1, LinkedHashMap::new));
    }
}

