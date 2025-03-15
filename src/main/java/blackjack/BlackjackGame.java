package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.generator.ShuffleDeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.DealerWinningResult;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int DEALER_COUNT = 1;
    private static final int SPREAD_CARD_SIZE_PER_PLAYER = 2;
    private static final int SPREAD_SIZE = 1;

    private static final String YES = "y";
    private static final String NO = "n";

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final Deck deck = new Deck(new ShuffleDeckGenerator());
        final Dealer dealer = Dealer.createEmpty();
        final Players players = makePlayers();

        spreadInitialCards(dealer, players, deck);
        spreadExtraCards(dealer, players, deck);
        showParticipantScore(dealer, players);
        showWinningResult(dealer, players);
    }

    private void showParticipantScore(final Dealer dealer, final Players players) {
        resultView.makeParticipantsWithScoreMessage(Map.entry(dealer.getNickname(), dealer.showAllCards()),
                players.showTotalCards());
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseByComma(names);
        return new Players(parsedNames.stream()
                .map(Player::createEmpty)
                .toList());
    }

    private void spreadInitialCards(final Dealer dealer, final Players players, final Deck deck) {
        final int cardsCount = (DEALER_COUNT + players.getSize()) * SPREAD_CARD_SIZE_PER_PLAYER;
        final Hand hand = deck.spreadCards(cardsCount);
        dealer.receiveCards(hand.getPartialCards(0, SPREAD_CARD_SIZE_PER_PLAYER));
        players.receiveCards(hand.getPartialCards(SPREAD_CARD_SIZE_PER_PLAYER, hand.getSize()),
                SPREAD_CARD_SIZE_PER_PLAYER);

        resultView.printSpreadCard(players.getNames(), Map.entry(dealer.getNickname(), dealer.showInitialCards()),
                players.showTotalInitialCards());
    }

    private void spreadExtraCards(final Dealer dealer, final Players players, final Deck deck) {
        spreadExtraCardsToPlayer(players, deck);
        spreadExtraCardsToDealer(dealer, deck);
    }

    private void spreadExtraCardsToDealer(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            final Hand hand = deck.spreadCards(SPREAD_SIZE);
            dealer.receiveCards(new Hand(List.of(hand.getFirstCard())));
            resultView.printDealerExtraCard();
        }
    }

    private void spreadExtraCardsToPlayer(final Players players, final Deck deck) {
        Players availablePlayers = players.findHitAvailablePlayers();
        for (Gamer gamer : availablePlayers.getPlayers()) {
            while (gamer.canHit() && wantHit(gamer)) {
                final Hand hand = deck.spreadCards(SPREAD_SIZE);
                gamer.receiveCards(new Hand(List.of(hand.getFirstCard())));
                resultView.printParticipantTotalCards(gamer.getNickname(), gamer.showAllCards());
            }
        }
    }

    private boolean wantHit(final Gamer player) {
        String answer = inputView.askHit(player);
        if (isValidAnswer(answer)) {
            return answer.equals(YES);
        }
        resultView.showln("잘못된 응답입니다. 다시 입력해주세요.");
        return wantHit(player);
    }

    private boolean isValidAnswer(final String answer) {
        return answer.equals(YES) || answer.equals(NO);
    }

    private void showWinningResult(final Dealer dealer, final Players players) {
        final DealerWinningResult result = dealer.makeDealerWinningResult(players.calculateScores());
        resultView.showDealerWinningResult(result);
    }
}
