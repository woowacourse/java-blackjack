package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardShape;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.GameResult;
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
        Deck deck = new Deck(createCards());

        initialDeal(participants, deck);
        playersTurn(participants.getPlayers(), deck);
        dealerTurn(participants.getDealer(), deck);
        printResult(participants);
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();

        return new Participants(playerNames);
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        for (CardRank rank : CardRank.values()) {
            cards.addAll(createRankCards(rank));
        }

        Collections.shuffle(cards);

        return cards;
    }

    private List<Card> createRankCards(CardRank rank) {
        List<Card> cards = new ArrayList<>();

        for (CardShape shape : CardShape.values()) {
            cards.add(new Card(rank, shape));
        }

        return cards;
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

    private void printResult(Participants participants) {
        Map<Player, GameResult> playerGameResults = createPlayerGameResults(participants);
        Map<GameResult, Integer> dealerGameResult = createDealerGameResult(playerGameResults);

        outputView.printAllCardsWithScore(participants.getParticipants());
        outputView.printGameResult(playerGameResults, dealerGameResult);
    }

    private Map<Player, GameResult> createPlayerGameResults(Participants participants) {
        Map<Player, GameResult> playerGameResults = new LinkedHashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            GameResult gameResult = dealer.judge(player);
            playerGameResults.put(player, gameResult);
        }

        return playerGameResults;
    }

    private Map<GameResult, Integer> createDealerGameResult(Map<Player, GameResult> playerGameResults) {
        Map<GameResult, Integer> dealerGameResults = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            dealerGameResults.put(gameResult, 0);
        }

        for (Entry<Player, GameResult> entry : playerGameResults.entrySet()) {
            GameResult gameResult = entry.getValue().getOpposite();
            int current = dealerGameResults.get(gameResult);
            dealerGameResults.put(gameResult, current + 1);
        }

        return dealerGameResults;
    }
}
