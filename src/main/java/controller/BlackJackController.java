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

        Map<String, List<String>> dealOutResult = blackJack.dealOut();
        outputView.printDealOut(dealOutResult);

        proceedPlayerTurns(blackJack, participants.getPlayers());
        proceedDealerTurn(blackJack, participants.getDealer());

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

    private void proceedPlayerTurns(BlackJack blackJack, List<Player> players) {
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
}
