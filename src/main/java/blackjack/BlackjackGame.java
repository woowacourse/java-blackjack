package blackjack;

import blackjack.domain.BlackjackResult;
import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    public static final int INIT_CARD_SIZE = 2;
    public static final int BLACK_JACK_SCORE = 21;

    public void run() {
        Deck deck = new Deck();
        Players players = Players.fromNames(InputView.inputPlayerName(),
                (player) -> HitFlag.fromCommand(InputView.inputHitOrStand(player.getName())));
        players.initHit(deck, INIT_CARD_SIZE);
        OutputView.printInitCard(getCardStatus(players));
        players.playersHit(deck, OutputView::printPresentStatus);
        OutputView.printCardResults(getCardResults(players));
        OutputView.printResult(BlackjackResult.match(players.findDealer(), players.getGuests()).getResultMap());
    }

    private Map<String, Cards> getCardStatus(Players players) {
        return players.getPlayers()
                .stream()
                .collect(Collectors.toMap(Player::getName, Player::getShowCards));
    }

    private Map<String, Cards> getCardResults(Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getCards());
        }
        return result;
    }
}
