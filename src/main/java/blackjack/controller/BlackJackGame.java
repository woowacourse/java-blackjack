package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.*;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.DealerResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayersResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    public void start() {
        try {
            Players players = registerPlayers();
            Dealer dealer = new Dealer();
            CardDeck cardDeck = cardDeckSetting();
            distributeCards(players, dealer, cardDeck);
            playersTurn(players, cardDeck);
            dealerTurn(dealer, cardDeck);
            showResult(players, dealer);
        } catch (IllegalStateException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private Players registerPlayers() {
        try {
            OutputView.enterPlayersName();
            List<String> allPlayersName = InputView.inputName();
            return new Players(generatePlayers(allPlayersName));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return registerPlayers();
        }
    }

    private List<Player> generatePlayers(List<String> allPlayersName) {
        return allPlayersName.stream()
                .map(Nickname::new)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private CardDeck cardDeckSetting() {
        Cards deck = new Cards(Card.values());
        CardDeck cardDeck = new CardDeck(deck);
        cardDeck.shuffleCard();
        return cardDeck;
    }

    private void distributeCards(Players players, Dealer dealer, CardDeck cardDeck) {
        dealer.firstDraw(cardDeck.drawCard(), cardDeck.drawCard());
        players.eachPlayerFirstDraw(cardDeck);
        OutputView.distributeFirstTwoCard(toPlayersDto(players), toParticipantDto(dealer));
    }

    private List<ParticipantDto> toPlayersDto(Players players) {
        return players.getPlayers().stream()
                .map(this::toParticipantDto)
                .collect(Collectors.toList());
    }

    private ParticipantDto toParticipantDto(Participant participant) {
        Cards cards = participant.getCurrentCards();
        return new ParticipantDto(participant.getName(), cards, cards.calculateScore());
    }

    private void playersTurn(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(cardDeck, player);
        }
    }

    private void eachPlayerTurn(CardDeck cardDeck, Player player) {
        while (player.canDraw() && askDrawCard(player)) {
            player.draw(cardDeck.drawCard());
            OutputView.showCards(toParticipantDto(player));
        }
    }

    private boolean askDrawCard(Player player) {
        try {
            OutputView.askOneMoreCard(toParticipantDto(player));
            boolean wantDraw = InputView.inputAnswer();
            playerWantStopDraw(player, wantDraw);
            return wantDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player);
        }
    }

    private void playerWantStopDraw(Player player, boolean wantDraw) {
        if (!wantDraw) {
            OutputView.showCards(toParticipantDto(player));
            player.stay();
        }
    }

    private void dealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canDraw()) {
            dealer.draw(cardDeck.drawCard());
            OutputView.dealerReceiveOneCard();
        }
        if (!dealer.isBust() && dealer.isHit()) {
            dealer.stay();
        }
    }

    private void showResult(Players players, Dealer dealer) {
        OutputView.showAllCards(toPlayersDto(players), toParticipantDto(dealer));
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        OutputView.showFinalResult(toDealerResultDto(blackJackResult), toPlayersResultDto(players, dealer));
    }

    private DealerResultDto toDealerResultDto(BlackJackResult blackJackResult) {
        return new DealerResultDto(blackJackResult.getDealerResult());
    }

    private PlayersResultDto toPlayersResultDto(Players players, Dealer dealer) {
        return new PlayersResultDto(players.verifyResultByCompareScore(dealer));
    }
}
