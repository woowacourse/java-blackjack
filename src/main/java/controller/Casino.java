package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import view.BlackJackGameCommand;
import view.InputView;
import view.ResultView;
import view.dto.participant.DealerDto;
import view.dto.participant.ParticipantDto;
import view.dto.participant.PlayerDto;
import view.dto.participant.PlayersDto;

public class Casino {

    private final InputView inputView;
    private final ResultView resultView;

    public Casino(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play(final BlackjackGame game) {
        Dealer dealer = new Dealer(createCards());
        dealer.shuffleCards();
        Players players = new Players(inputView.askPlayerNames());
        game.ready(dealer, players);
        proceed(dealer, players);
        result(game, dealer, players);
    }

    private void proceed(final Dealer dealer, final Players players) {
        resultView.printInitialCards(new DealerDto(dealer, dealer.peek()), new PlayersDto(players));
        players.forEach(player -> askAndDealMoreCard(dealer, player));
        if (dealer.canHit()) {
            resultView.printDealerCardMessage(new DealerDto(dealer));
            dealer.deal(dealer);
        }
    }

    private void result(final BlackjackGame game, final Dealer dealer, final Players players) {
        List<ParticipantDto> dealerAndPlayers = new ArrayList<>();
        dealerAndPlayers.add(new DealerDto(dealer));
        dealerAndPlayers.addAll(new PlayersDto(players).players());
        resultView.printResults(dealerAndPlayers, game.resultsOf(dealer, players));
    }

    private void askAndDealMoreCard(final Dealer dealer, final Player player) {
        if (player.isBust() || player.isBlackjack()) {
            return;
        }
        BlackJackGameCommand command = inputView.askMoreCard(new PlayerDto(player));
        if (command.yes()) {
            dealer.deal(player);
            resultView.printParticipantHand(new PlayerDto(player));
            askAndDealMoreCard(dealer, player);
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
