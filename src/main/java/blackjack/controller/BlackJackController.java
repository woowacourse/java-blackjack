package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerGameResult;
import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckCreator;
import blackjack.domain.player.Hand;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Judge;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = initPlayers();
        CardDeck cardDeck = initCardDeck();
        Dealer dealer = initDealer();

        issueInitialCardsToPlayers(players, cardDeck);
        issueInitialCardsToDealer(cardDeck, dealer);
        outputView.printHandOutEvent(players, 2);
        players.getPlayers().forEach(outputView::printPlayerHand);

        //딜러 카드 보여주는 메서드 만들어야 함 (카드 한장만)
        Player tempDealer = new Player(new PlayerName("딜러"), dealer.getHand());
        outputView.printPlayerHand(tempDealer);

        completePlayersHand(players, cardDeck);
        completeDealerHand(dealer, cardDeck);

        int dealerPopCount = dealer.getHand().getCards().size() - 2;
        outputView.printDealerPopCount(16, dealerPopCount);

        printPlayersScore(players);
        printDealerGameResult(dealer, players);

        printPlayersGameResult(players, dealer);

    }

    private void printPlayersScore(Players players) {
        Judge judge = new Judge();
        players.getPlayers()
                .forEach(player -> outputView.printPlayerScore(player, judge.calculateBestScore(player.getHand())));
    }

    private void printPlayersGameResult(Players players, Dealer dealer) {
        Judge judge = new Judge();
        players.getPlayers()
                .forEach(player -> outputView.printPlayerGameResult(player, judge.isPlayerWin(dealer, player)));
    }

    private void printDealerGameResult(Dealer dealer, Players players) {
        Judge judge = new Judge();
        DealerGameResult dealerGameResult = judge.calculateDealerResult(dealer, players);
        outputView.printDealerGameResult(dealerGameResult);
    }

    private void completeDealerHand(Dealer dealer, CardDeck cardDeck) {
        Judge judge = new Judge();
        while (judge.canDealerHit(dealer)) {
            Card card = cardDeck.popCard();
            dealer.appendCard(card);
        }
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            completePlayerHand(player, cardDeck);
        }
    }

    private void completePlayerHand(Player player, CardDeck cardDeck) {
        Judge judge = new Judge();
        while (!judge.isBustedHand(player.getHand())) {
            String name = player.getName();
            InputMapper inputMapper = new InputMapper();
            DrawDecision drawDecision = inputMapper.mapToDrawDecision(inputView.readDrawPlan(name));

            if (drawDecision == DrawDecision.NO) {
                break;
            }

            Card card = cardDeck.popCard();
            player.appendCard(card);
            outputView.printPlayerHand(player);
        }
    }

    private CardDeck initCardDeck() {
        CardDeckCreator cardDeckCreator = new CardDeckCreator();
        return cardDeckCreator.create();
    }

    private Players initPlayers() {
        InputMapper inputMapper = new InputMapper();
        List<PlayerName> playerNames = inputMapper.mapToPlayers(inputView.readNames());
        return Players.from(playerNames);
    }

    private Dealer initDealer() {
        Hand hand = new Hand(new ArrayList<>());
        return new Dealer(hand);
    }

    private List<Card> issueInitialHand(CardDeck cardDeck) {
        return cardDeck.popCards(2);
    }

    private void addIssuedCardToHand(Hand hand, List<Card> cards) {
        cards.forEach(hand::append);
    }

    private void issueInitialCardsToPlayers(Players players, CardDeck cardDeck) {
        players.getPlayers().stream()
                .map(Player::getHand)
                .forEach(hand -> addIssuedCardToHand(hand, issueInitialHand(cardDeck)));
    }

    private void issueInitialCardsToDealer(CardDeck cardDeck, Dealer dealer) {
        List<Card> cards = issueInitialHand(cardDeck);
        addIssuedCardToHand(dealer.getHand(), cards);
    }
}
