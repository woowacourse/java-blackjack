package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        // 블랙잭 게임 구성하기
        final CardManager cardManager = new CardManager(new CardRandomGenerator());
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(cardManager, participants);

        // 초기 분배하기
        blackjackGame.spreadInitialCards();
        resultView.printSpreadCard(participants);

        // 플레이어마다 카드 나눠주기
        spreadPlayersExtraCards(blackjackGame);
        spreadDealerExtraCards(blackjackGame);
    }

    private void spreadDealerExtraCards(final BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerMoreCard()) {
            blackjackGame.spreadOneCardToDealer();
            resultView.printDealerExtraCard();
        }
    }

    private void spreadPlayersExtraCards(final BlackjackGame blackjackGame) {
        for (int index = 0; index < blackjackGame.getPlayerSize(); index++) {
            final Player player = blackjackGame.getPlayer(index);
            while (blackjackGame.canPlayerMoreCard(index)) {
                if (!isMoreCard(player)) {
                    break;
                }
                blackjackGame.spreadOneCardToPlayer(index);
                resultView.printParticipantTotalCards(player);
            }
        }
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer(new ArrayList<>());
        final Players players = makePlayers();
        return new Participants(dealer, players);
    }

    private boolean isMoreCard(final Player player) {
        return inputView.askMoreCard(player).equals("y");
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseComma(names);
        return new Players(parsedNames.stream()
                .map(name -> new Player(name, new ArrayList<>()))
                .toList());
    }
}
