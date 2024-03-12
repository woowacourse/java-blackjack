package blackjack.game;

import blackjack.card.Card;
import blackjack.card.Deck;
import blackjack.money.BetMoney;
import blackjack.money.PlayerBets;
import blackjack.participant.Participant;
import blackjack.participant.Participants;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {
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
        PlayerBets playerBets = createPlayerBets(participants);

        initGame(deck, dealer, participants);
        playersDrawMore(deck, participants);
        dealerDrawMore(deck, dealer);

        showResults(dealer, participants, playerBets);
    }

    private MatchResults createMatchResults(Participant dealer, Participants participants) {
        MatchResults matchResults = new MatchResults();
        for (Participant player : participants.getPlayers()) {
            matchResults.addResult(player.getName(), player, dealer);
        }
        return matchResults;
    }

    private PlayerBets createPlayerBets(Participants participants) {
        PlayerBets playerBets = new PlayerBets();
        for (String name : participants.getNames()) {
            outputView.printBetRequest(name);
            int amount = inputView.readBetMoney();
            playerBets.addPlayerBet(name, BetMoney.of(amount));
        }
        return playerBets;
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
        outputView.printDealerFirstCard(dealer.getFirstCard());

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
        if (!participant.isDrawable()) {
            return;
        }
        Command command = askPlayerToDrawMore(participant);
        if (command.isNo()) {
            participant.stand();
            return;
        }
        drawSingleCard(participant, deck);

        if (participant.isDrawable()) {
            playerDrawMore(deck, participant);
        }
    }

    private void drawSingleCard(Participant participant, Deck deck) {
        Card card = deck.draw();
        participant.drawCard(card);
        outputView.printPlayerCards(participant.getName(), participant.getCards());
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

    private void showResults(Participant dealer, Participants participants, PlayerBets playerBets) {
        MatchResults matchResults = createMatchResults(dealer, participants);
        showCardsWithScore(dealer, participants);
        showProfitResult(dealer, playerBets, matchResults);
    }

    private void showCardsWithScore(Participant dealer, Participants participants) {
        outputView.printDealerCardsWithScore(dealer.getCards(), dealer.getScore());
        for (Participant participant : participants.getPlayers()) {
            outputView.printPlayerCardsWithScore(participant.getName(), participant.getCards(), participant.getScore());
        }
        outputView.printNewLine();
    }

    private void showProfitResult(Participant dealer, PlayerBets playerBets, MatchResults matchResults) {
        outputView.printResultStart();
        showDealerProfit(dealer, playerBets, matchResults);
        showPlayersResult(playerBets, matchResults);
    }

    private void showDealerProfit(Participant dealer, PlayerBets playerBets, MatchResults matchResults) {
        int playersProfit = playerBets.stream()
                .mapToInt(matchResults::calculateProfitByBet)
                .sum();
        int dealerProfit = playersProfit * -1;

        outputView.printPlayerResult(dealer.getName(), dealerProfit);
    }

    private void showPlayersResult(PlayerBets playerBets, MatchResults matchResults) {
        playerBets.stream()
                .forEach(bet -> {
                            int profit = matchResults.calculateProfitByBet(bet);
                            outputView.printPlayerResult(bet.name(), profit);
                        }
                );
    }
}
