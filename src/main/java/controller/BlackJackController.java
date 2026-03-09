package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import model.BlackJack;
import model.card.Card;
import model.card.Cards;
import model.card.Deck;
import model.card.Rank;
import model.card.Suit;
import model.card.RandomDeck;
import model.participant.Participant;
import model.participant.Participants;
import model.participant.Dealer;
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

        proceedDealOut(blackJack, participants.getPlayers());

        proceedPlayerTurns(blackJack, participants.getPlayers());

        proceedDealerTurn(blackJack, participants.getDealer());

        proceedFinalPhase(blackJack, participants);
    }

    private Participants setUpParticipants() {
        String rawNames = inputView.readPlayerNames();
        List<String> parsed = InputParser.parseName(rawNames);

        return Participants.from(parsed);
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

    private void proceedDealOut(BlackJack blackJack, List<Player> players) {
        Map<String, Cards> dealOutResult = blackJack.dealOut();
        String playerNames = players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(
                        ", ",
                        "",
                        ""
                ));

        outputView.printDealOut(playerNames);

        for (Entry<String, Cards> entry : dealOutResult.entrySet()) {
            String participantName = entry.getKey();
            List<Card> hands = entry.getValue().toList();

            List<String> result = hands.stream()
                    .map(card -> card.getSuit().getName() + card.getRank().getName())
                    .toList();

            outputView.printHands(participantName, result);
        }
    }

    private void proceedPlayerTurns(BlackJack blackJack, List<Player> players) {
        outputView.printNewLine();

        for (Player player : players) {
            while (player.canHit() && inputView.askHit(player.getName())) {
                blackJack.giveCardTo(player);

                List<Card> hands = player.open().toList();
                List<String> result = hands.stream()
                        .map(card -> card.getSuit().getName() + card.getRank().getName())
                        .toList();

                outputView.printHands(player.getName(), result);

                if (player.isBust()) {
                    outputView.printBustState(player.getName(), player.calculateScore());
                }
            }
        }
    }

    private void proceedDealerTurn(BlackJack blackJack, Dealer dealer) {
        boolean draw = dealer.needDraw();

        outputView.printDealerDrawResult(draw);

        if (draw) {
            blackJack.giveCardTo(dealer);

            if (dealer.isBust()) {
                outputView.printBustState(dealer.getName(), dealer.calculateScore());
            }
        }
    }

    private void proceedFinalPhase(BlackJack blackJack, Participants participants) {
        for (Participant participant : participants.toList()) {
            List<Card> hands = participant.open().toList();
            List<String> result = hands.stream()
                    .map(card -> card.getSuit().getName() + card.getRank().getName())
                    .toList();

            outputView.printHandsWithScore(participant.getName(), result, participant.calculateScore());
        }

        outputView.printFinalResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResult());
    }
}
