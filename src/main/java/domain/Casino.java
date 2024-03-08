package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import view.BlackJackGameCommand;
import view.InputView;
import view.ResultView;
import view.dto.GameResultDto;
import view.dto.DealerDto;
import view.dto.ParticipantDto;
import view.dto.PlayerDto;
import view.dto.PlayersDto;

public class Casino {


    private final InputView inputView;
    private final ResultView resultView;

    public Casino(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play(final BlackjackGame game) {
        Dealer dealer = new Dealer(createCards());
        dealer.shuffle();
        Players players = new Players(inputView.askPlayerNames());
        game.ready(dealer, players);
        resultView.printInitialCards(new DealerDto(dealer, dealer.peek()), new PlayersDto(players));
        players.forEach(player -> askMoreCardAndDeal(dealer, player));
        if (dealer.doesGetCard()) {
            resultView.printDealerCardMessage(new DealerDto(dealer));
            dealer.deal(dealer);
        }
        DealerDto dealerDto = new DealerDto(dealer);
        PlayersDto playersDto = new PlayersDto(players);
        List<ParticipantDto> dealerAndPlayers = new ArrayList<>();
        dealerAndPlayers.add(dealerDto);
        dealerAndPlayers.addAll(playersDto.dtos());
        GameResultDto gameResultDto = game.resultsOf(dealer, players);
        resultView.printResult(dealerAndPlayers, gameResultDto);
    }

    private void askMoreCardAndDeal(final Dealer dealer, final Player player) {
        if (player.isBust() || player.isBlackjack()) {
            return;
        }
        BlackJackGameCommand command = inputView.askMoreCard(new PlayerDto(player));
        if (command.yes()) {
            dealer.deal(player);
            resultView.printParticipantHand(new PlayerDto(player));
            askMoreCardAndDeal(dealer, player);
        }
    }

    private Cards createCards() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(this::addCard)
                .toList();
        return new Cards(cards);
    }

    private Stream<Card> addCard(final CardShape cardShape) {
        return EnumSet.allOf(CardNumber.class)
                .stream()
                .map(cardNumber -> new Card(cardShape, cardNumber));
    }
}
