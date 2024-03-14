package blackjack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Judgement;
import blackjack.domain.JudgementResult;
import blackjack.domain.Money;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = createParticipants();
        Deck deck = Deck.createShuffledDeck();

        initialDeal(participants, deck);
        playersTurn(participants.getPlayers(), deck);
        dealerTurn(participants.getDealer(), deck);
        showCard(participants);
        showProfit(participants);
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();
        List<Money> playersMoney = playerNames.stream()
                .map(this::createPlayerMoney)
                .toList();

        return new Participants(playerNames, playersMoney);
    }

    private Money createPlayerMoney(String playerName) {
        double playerMoney = inputView.readPlayerMoney(playerName);

        return new Money(playerMoney);
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(deck.draw());
            participant.hit(deck.draw());
        }

        outputView.printInitialDeal(participants.getDealer(), participants.getPlayers());
    }

    private void playersTurn(List<Player> players, Deck deck) {
        players.forEach(player -> playerTurn(player, deck));
    }

    private void playerTurn(Player player, Deck deck) {
        if (!player.isPlayable()) {
            return;
        }

        boolean wannaHit = inputView.readCommand(player.getName());
        if (wannaHit) {
            player.hit(deck.draw());
            outputView.printCards(player);
            playerTurn(player, deck);
            return;
        }

        outputView.printCards(player);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isPlayable()) {
            dealer.hit(deck.draw());
            outputView.printDealerHitMessage();
        }
    }

    private void showCard(Participants participants) {
        outputView.printAllCardsWithScore(participants.getParticipants());
    }

    private void showProfit(Participants participants) {
        Map<String, Double> resultMap = new LinkedHashMap<>();
        Judgement judgement = new Judgement();
        Dealer dealer = participants.getDealer();
        double dealerProfit = 0;
        resultMap.put(dealer.getName(), dealerProfit);

        for (Player player : participants.getPlayers()) {
            JudgementResult result = judgement.judge(dealer, player);
            double playerProfit = player.calculateProfit(result).getAmount();
            dealerProfit -= playerProfit;
            resultMap.put(player.getName(), playerProfit);
        }

        resultMap.put(dealer.getName(), dealerProfit);

        outputView.printProfit(resultMap);
    }
}
