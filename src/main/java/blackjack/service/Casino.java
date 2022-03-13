package blackjack.service;

import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.OutputView;
import blackjack.vo.ParticipantVo;
import java.util.List;

public class Casino {

    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final Players players;

    public Casino(final List<String> playerNames) {
        this.cardFactory = CardFactory.create();
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
    }

    public void prepareParticipants() {
        dealer.prepareGame(cardFactory);
        players.prepareGame(cardFactory);

        OutputView.printInitResult(players.getNames());
        OutputView.printDealerFirstCard(dealer.openFirstCard());
        players.getValue().stream()
                .map(ParticipantVo::new)
                .forEach(OutputView::printPlayerCards);
    }

    public boolean isPlayerTurn() {
        return players.isDrawablePlayerExist();
    }

    public String findDrawablePlayerName() {
        return players.findHitPlayer().getName();
    }

    public void progressPlayerTurn(final String playerName, final Status status) {
        final Player player = players.findByName(playerName);
        if (status == Status.HIT) {
            player.hit(cardFactory);
        }
        if (status == Status.STAY) {
            player.stay();
        }

        OutputView.printPlayerCards(new ParticipantVo(player));
    }

    public void progressDealerTurn() {
        if (!dealer.canDrawCard()) {
            OutputView.printDealerNotDrawMessage();
            return;
        }

        do {
            dealer.hit(cardFactory);
            OutputView.printDealerDrawMessage();
        } while (dealer.canDrawCard());
    }

    public void endGame() {
        OutputView.breakLine();
        OutputView.printParticipantCards(new ParticipantVo(dealer));
        players.getValue().stream()
                .map(ParticipantVo::new)
                .forEach(OutputView::printParticipantCards);

        final RecordFactory factory = new RecordFactory(dealer.getScore(), players.getValue());
        OutputView.printRecord(factory.getDealerRecord(), factory.getAllPlayerRecord());
    }
}
