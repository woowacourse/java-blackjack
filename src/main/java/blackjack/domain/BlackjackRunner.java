package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomCardsGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.DealerResultsDto;
import blackjack.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class BlackjackRunner {

    public void run() {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer(deck.getInitCards());
        Players players = new Players(deck, InputView.getNames());

        OutputView.printStart(dealer, players.getValue());

        players.getValue().forEach(player -> playing(deck, player));
        drawDealer(deck, dealer);

        OutputView.printResult(dealer, players.getValue());
        OutputView.printGameResult(DealerResultsDto.of(players, dealer), createPlayerResult(players, dealer));
    }

    private void playing(Deck deck, Player player) {
        if (player.isDrawable()) {
            drawCard(deck, player);
            playing(deck, player);
        }
    }

    private void drawCard(Deck deck, Player player) {
        PlayCommand playCommand = InputView.getPlayCommand(player);

        if (playCommand.isYes()) {
            player.append(deck.draw());
            OutputView.printPlayerCard(player);
        }
    }

    private void drawDealer(Deck deck, Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.append(deck.draw());
            OutputView.printDealerDrawable();
        }
    }

    private List<PlayerResultDto> createPlayerResult(Players players, Dealer dealer) {
        return players.getValue()
                .stream()
                .map(player -> PlayerResultDto.of(player, dealer))
                .collect(toList());
    }
}
