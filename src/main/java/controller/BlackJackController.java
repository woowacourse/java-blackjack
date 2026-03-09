package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import model.BlackJack;
import model.card.Card;
import model.card.Deck;
import model.card.Rank;
import model.card.Suit;
import model.card.RandomDeck;
import model.participant.Participant;
import model.Participants;
import model.participant.Player;
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
        Deck deck = setUpDeck();
        BlackJack blackJack = BlackJack.of(participants, deck);

        Map<String, List<String>> dealOutResult = blackJack.dealOut();
        outputView.printDealOut(dealOutResult);

        processPlayerTurns(blackJack, participants.getPlayers());
//        blackJack.startPlayerTurn(this::askHit, this::afterHit);
        blackJack.startDealerTurn(this::afterDealerTurn);

        outputView.printHandsAndScore(participants);
        outputView.printResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResult());
    }

    private Participants setUpParticipants() {
        String rawNames = inputView.readPlayerNames();
        List<String> parsed = InputParser.parseName(rawNames);

        return Participants.of(parsed);
    }

    private Deck setUpDeck() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(Card.of(suit, rank));
            }
        }

        Collections.shuffle(cards);

        return RandomDeck.from(cards);
    }

    private void processPlayerTurns(BlackJack blackJack, List<Player> players) {
        for (Player player : players) {
            while (player.canHit() && inputView.askHit(player.getName())) {
                blackJack.giveCardTo(player);
                outputView.printHands(player);

                if (player.isBust()) {
                    outputView.printBustState(player.getName(), player.calculateScore());
                }
            }
        }
    }

    private Boolean askHit(String name) {
        return inputView.askHit(name);
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
