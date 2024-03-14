package blackjack.game;

import blackjack.card.Deck;
import blackjack.money.BetMoney;
import blackjack.money.BetTable;
import blackjack.money.Money;
import blackjack.player.Dealer;
import blackjack.player.Participants;
import blackjack.player.Player;
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
        BetTable betTable = placeParticipantsBet(participants);

        initGame(deck, dealer, participants);
        drawMoreForPlayers(deck, participants);
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

    private BetTable placeParticipantsBet(Participants participants) {
        BetTable betTable = new BetTable();
        for (String name : participants.getNames()) {
            outputView.printBetMoneyRequest(name);
            int money = inputView.readBetMoney();
            betTable.placeBet(name, BetMoney.of(money));
        }
        return betTable;
    }

    private void initGame(Deck deck, Dealer dealer, Participants participants) {
        participants.drawCardsForAll(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        dealer.drawCards(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        outputView.printInitializeBlackJack(participants.getNames());
        showInitCard(dealer, participants);
    }

    private void showInitCard(Dealer dealer, Participants participants) {
        outputView.printDealerFirstCard(dealer.revealCardsOnFirstPhase());

        for (Player player : participants.getPlayers()) {
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
        outputView.printNewLine();
    }

    private void drawMoreForPlayers(Deck deck, Participants participants) {
        for (Player player : participants.getPlayers()) {
            drawMoreForParticipant(deck, player);
        }
        outputView.printNewLine();
    }

    private void drawMoreForParticipant(Deck deck, Player player) {
        while (player.hasDrawableScore() && isParticipantWantsToDrawMore(player)) {
            player.drawCard(deck.draw());
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
    }

    private boolean isParticipantWantsToDrawMore(Player player) {
        outputView.printDrawMoreCardRequest(player.getName());
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
        for (Player player : participants.getPlayers()) {
            outputView.printPlayerCardsWithScore(player.getName(), player.getCards(), player.getScore());
        }
        outputView.printNewLine();
    }

    private void showPlayersProfit(BetTable betTable, Dealer dealer, Participants participants) {
        outputView.printProfitMessage();
        showDealerProfit(betTable, participants, dealer);
        showParticipantsProfit(betTable, dealer, participants);
    }

    private void showDealerProfit(BetTable betTable, Participants participants, Dealer dealer) {
        Money dealerProfit = participants.getPlayers().stream()
                .map(player -> calculateProfit(betTable, dealer, player))
                .reduce(Money.zero(), Money::add)
                .profitOnLose();
        outputView.printPlayerProfit(dealer.getName(), dealerProfit.getAmount());
    }

    private void showParticipantsProfit(BetTable betTable, Dealer dealer, Participants participants) {
        for (Player player : participants.getPlayers()) {
            Money profit = calculateProfit(betTable, dealer, player);
            outputView.printPlayerProfit(player.getName(), profit.getAmount());
        }
    }

    private Money calculateProfit(BetTable betTable, Dealer dealer, Player player) {
        MatchResult result = Referee.judge(player, dealer);
        return betTable.calculateProfitByName(player.getName(), result);
    }
}
