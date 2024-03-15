package blackjack;

import blackjack.domain.Judge;
import blackjack.domain.bet.BettingBank;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.result.PlayerGameResult;
import blackjack.domain.result.PlayerProfitResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Players players = initPlayers(cardDeck);
        BettingBank bettingBank = inputView.readBetInformation(players);
        Dealer dealer = new Dealer(Hand.createHandFrom(cardDeck));
        Judge judge = new Judge();
        printPlayersInformation(players, dealer);

        completePlayersHand(players, cardDeck);
        dealer.completeHand(cardDeck);
        outputView.printDealerPopCount(Dealer.HIT_THRESHOLD, dealer.countDraw());

        printParticipantScore(dealer);
        printPlayersScore(players);

        PlayerGameResult playerGameResult = judge.calculatePlayerGameResult(dealer, players);
        PlayerProfitResult playerProfitResult = bettingBank.calculateProfitResult(playerGameResult);
        Profit profit = playerProfitResult.calculateDealerProfit();
        outputView.printProfitResults(playerProfitResult, profit);
    }

    private Players initPlayers(CardDeck cardDeck) {
        List<PlayerName> playerNames = inputView.readNames();
        return new Players(playerNames.stream()
                .map(playerName -> new Player(playerName, Hand.createHandFrom(cardDeck)))
                .toList());
    }

    private void printPlayersInformation(Players players, Dealer dealer) {
        outputView.printHandOutEvent(players, Hand.INITIAL_HAND_SIZE);
        outputView.printDealerInitialHand(dealer);
        players.getPlayers().forEach(outputView::printPlayerHand);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> completePlayerHand(player, cardDeck));
    }

    private void completePlayerHand(Player participant, CardDeck cardDeck) {
        while (participant.canHit() && inputView.readDrawDecision(participant.getName()).isYes()) {
            participant.appendCard(cardDeck.popCard());
            outputView.printPlayerHand(participant);
        }
    }

    private void printPlayersScore(Players players) {
        players.getPlayers().forEach(this::printParticipantScore);
    }

    private void printParticipantScore(Participant participant) {
        outputView.printParticipantScore(participant, participant.calculateHandScore());
    }
}
