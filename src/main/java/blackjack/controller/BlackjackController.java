package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.ResultStatus;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private static final String YES = "y";
    private static final String NO = "n";

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final Deck deck = new Deck(new CardRandomGenerator());
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(deck, participants);

        spreadInitialCards(blackjackGame);
        spreadExtraCards(blackjackGame);
        resultView.makeParticipantsWithScoreMessage(blackjackGame.showDealerCard(), blackjackGame.showPlayersCards());
        showWinningResult(blackjackGame);
    }

    private Participants makeParticipants() {
        final Dealer dealer = Dealer.createEmpty();
        final Players players = makePlayers();
        return new Participants(dealer, players);
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseByComma(names);
        return new Players(parsedNames.stream()
                .map(Player::createEmpty)
                .toList());
    }

    private void spreadInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.spreadInitialCards();
        resultView.printSpreadCard(blackjackGame.getPlayersNames(), blackjackGame.showInitialDealerCard(),
                blackjackGame.showInitialPlayersCards());
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame) {
        spreadPlayersExtraCards(blackjackGame);
        spreadDealerExtraCards(blackjackGame);
    }

    private void spreadPlayersExtraCards(final BlackjackGame blackjackGame) {
        Players players = blackjackGame.findExtraCardsAvailablePlayers();
        for (Gamer gamer : players.getPlayers()) {
            spreadExtraCards(blackjackGame, gamer);
        }
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame, final Gamer gamer) {
        while (gamer.canGetMoreCard() && isMoreCard(gamer)) {
            blackjackGame.spreadOneCardToPlayer(gamer);
            resultView.printParticipantTotalCards(gamer.getNickname(), gamer.showAllCards());
        }
    }

    private boolean isMoreCard(final Gamer player) {
        String answer = inputView.askMoreCard(player);
        if (isValidAnswer(answer)) {
            return answer.equals(YES);
        }
        resultView.showln("잘못된 응답입니다. 다시 입력해주세요.");
        return isMoreCard(player);
    }

    private boolean isValidAnswer(final String answer) {
        return answer.equals(YES) || answer.equals(NO);
    }

    private void spreadDealerExtraCards(final BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerMoreCard()) {
            blackjackGame.spreadOneCardToDealer();
            resultView.printDealerExtraCard();
        }
    }

    private void showWinningResult(final BlackjackGame blackjackGame) {
        final Map<String, ResultStatus> result = blackjackGame.calculateWinningResult();
        resultView.showWinningResult(result);
    }
}
