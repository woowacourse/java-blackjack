package blackjack.game;

import blackjack.card.Card;
import blackjack.card.Deck;
import blackjack.participant.Participant;
import blackjack.participant.Participants;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    public static final int BLACKJACK_MAX_SCORE = 21;
    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Deck deck = Deck.createShuffledFullDeck();
        Participant dealer = Participant.createAsDealer();

        Participants participants = createPlayers();
        initGame(deck, dealer, participants);
        playersDrawMore(deck, participants);
        dealerDrawMore(deck, dealer);

        showCardsWithScore(dealer, participants);
        showMatchResult(dealer, participants);
    }

    private Participants createPlayers() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Participants participants = new Participants(names);
        outputView.printNewLine();
        return participants;
    }

    private void initGame(Deck deck, Participant dealer, Participants participants) {
        participants.drawCardsForAll(deck, BLACKJACK_INIT_CARD_AMOUNT);
        dealer.drawCards(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        outputView.printInitializeBlackJack(participants.getNames());
        showInitCard(dealer, participants);
    }

    private void showInitCard(Participant dealer, Participants participants) {
        // TODO: exception check
        outputView.printDealerFirstCard(dealer.getCards().get(0));

        for (Participant participant : participants.getPlayers()) {
            outputView.printPlayerCards(participant.getName(), participant.getCards());
        }
        outputView.printNewLine();
    }

    private void playersDrawMore(Deck deck, Participants participants) {
        for (Participant participant : participants.getPlayers()) {
            playerDrawMore(deck, participant);
        }
        outputView.printNewLine();
    }

    private void playerDrawMore(Deck deck, Participant participant) {
        Command command = askPlayerToDrawMore(participant);
        if (command.isNo()) {
            return;
        }
        Card card = deck.draw();
        participant.drawCard(card);
        outputView.printPlayerCards(participant.getName(), participant.getCards());

        if (participant.isDrawable()) {
            playerDrawMore(deck, participant);
        }
    }

    private Command askPlayerToDrawMore(Participant participant) {
        outputView.printDrawMoreCardRequest(participant.getName());
        String input = inputView.readCommand();
        return Command.from(input);
    }

    private void dealerDrawMore(Deck deck, Participant dealer) {
        while (dealer.isDrawable()) {
            Card card = deck.draw();
            dealer.drawCard(card);
            outputView.printDealerDrawCard();
            outputView.printNewLine();
        }
    }

    private void showCardsWithScore(Participant dealer, Participants participants) {
        outputView.printDealerCardsWithScore(dealer.getCards(), dealer.getScore());
        for (Participant participant : participants.getPlayers()) {
            outputView.printPlayerCardsWithScore(participant.getName(), participant.getCards(), participant.getScore());
        }
        outputView.printNewLine();
    }

    private void showMatchResult(Participant dealer, Participants participants) {
        MatchResults matchResults = calculateMatchResults(dealer, participants);
        outputView.printResultStart();
        showDealerResult(matchResults);
        showPlayersResult(participants, matchResults);
    }

    private MatchResults calculateMatchResults(Participant dealer, Participants participants) {
        MatchResults matchResults = new MatchResults();
        for (Participant participant : participants.getPlayers()) {
            matchResults.addResult(participant.getName(), participant.getScore(), dealer.getScore());
        }
        return matchResults;
    }

    private void showDealerResult(MatchResults matchResults) {
        outputView.printDealerResult(
                matchResults.getResultCount(MatchResult.DEALER_WIN),
                matchResults.getResultCount(MatchResult.TIE),
                matchResults.getResultCount(MatchResult.PLAYER_NORMAL_WIN)
        );
    }

    private void showPlayersResult(Participants participants, MatchResults matchResults) {
        for (Participant participant : participants.getPlayers()) {
            String playerName = participant.getName();
            MatchResult result = matchResults.getResultByName(playerName);
            outputView.printPlayerResult(playerName, result);
        }
    }
}
