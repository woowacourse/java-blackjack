package blackjack.game;

import blackjack.card.Deck;
import blackjack.money.BetMoney;
import blackjack.money.BetTable;
import blackjack.money.Money;
import blackjack.player.Dealer;
import blackjack.player.Participant;
import blackjack.player.Participants;
import blackjack.resultstate.MatchResult;
import blackjack.resultstate.Referee;
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
        Participants participants = createParticipants();
        Dealer dealer = new Dealer();
        BetTable betTable = createBetTable(participants);

        initGame(deck, dealer, participants);
        drawMoreForParticipants(deck, participants);
        drawMoreForDealer(deck, dealer);

        showCardsWithScore(dealer, participants);
        showPlayersProfit(betTable, dealer, participants);
    }

    private Participants createParticipants() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Participants participants = Participants.create(names);
        outputView.printNewLine();
        return participants;
    }

    private BetTable createBetTable(Participants participants) {
        BetTable betTable = new BetTable();
        for (String name : participants.getNames()) {
            placeParticipantBet(name, betTable);
        }
        return betTable;
    }

    private void placeParticipantBet(String name, BetTable betTable) {
        outputView.printBetMoneyRequest(name);
        int money = inputView.readBetMoney();
        betTable.placeBet(name, BetMoney.of(money));
    }

    private void initGame(Deck deck, Dealer dealer, Participants participants) {
        participants.drawCardsForAll(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        dealer.drawCards(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        outputView.printInitializeBlackJack(participants.getNames());
        showInitCard(dealer, participants);
    }

    private void showInitCard(Dealer dealer, Participants participants) {
        outputView.printDealerFirstCard(dealer.revealCardsOnFirstPhase());

        for (Participant participant : participants.getParticipants()) {
            outputView.printPlayerCards(participant.getName(), participant.getCards());
        }
        outputView.printNewLine();
    }

    private void drawMoreForParticipants(Deck deck, Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            drawMoreForParticipant(deck, participant);
        }
        outputView.printNewLine();
    }

    private void drawMoreForParticipant(Deck deck, Participant participant) {
        while (participant.hasDrawableScore() && isParticipantWantsToDrawMore(participant)) {
            participant.drawCard(deck.draw());
            outputView.printPlayerCards(participant.getName(), participant.getCards());
        }
    }

    private boolean isParticipantWantsToDrawMore(Participant participant) {
        outputView.printDrawMoreCardRequest(participant.getName());
        return inputView.isReadCommandYes();
    }

    private void drawMoreForDealer(Deck deck, Dealer dealer) {
        while (dealer.hasDrawableScore()) {
            dealer.drawCard(deck.draw());
            outputView.printDealerDrawCard();
            outputView.printNewLine();
        }
    }

    private void showCardsWithScore(Dealer dealer, Participants participants) {
        outputView.printDealerCardsWithScore(dealer.getCards(), dealer.getScore());
        for (Participant participant : participants.getParticipants()) {
            outputView.printPlayerCardsWithScore(participant.getName(), participant.getCards(), participant.getScore());
        }
        outputView.printNewLine();
    }

    private void showPlayersProfit(BetTable betTable, Dealer dealer, Participants participants) {
        outputView.printProfitMessage();
        showDealerProfit(betTable, dealer, participants);
        showParticipantsProfit(betTable, dealer, participants);
    }

    private void showDealerProfit(BetTable betTable, Dealer dealer, Participants participants) {
        Money dealerProfit = participants.getParticipants()
                .stream()
                .map(player -> calculateProfit(betTable, dealer, player))
                .reduce(Money.zero(), Money::add)
                .profitOnLose();
        outputView.printPlayerProfit(dealer.getName(), dealerProfit.getAmount());
    }

    private void showParticipantsProfit(BetTable betTable, Dealer dealer, Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            Money profit = calculateProfit(betTable, dealer, participant);
            outputView.printPlayerProfit(participant.getName(), profit.getAmount());
        }
    }

    private Money calculateProfit(BetTable betTable, Dealer dealer, Participant participant) {
        MatchResult result = Referee.judge(participant, dealer);
        return betTable.calculateProfitByName(participant.getName(), result);
    }
}
