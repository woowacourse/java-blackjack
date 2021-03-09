package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Nickname;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.BlackJackResult;
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
        OutputView.distributeFirstTwoCard(players.toPlayersDto(), dealer.toParticipantDto());
    }

    private void playersTurn(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(cardDeck, player);
        }
    }

    private void eachPlayerTurn(CardDeck cardDeck, Player player) {
        while (player.canDraw() && askDrawCard(player)) {
            player.draw(cardDeck.drawCard());
            OutputView.showCards(player.toParticipantDto());
        }
    }

    private boolean askDrawCard(Player player) {
        try {
            OutputView.askOneMoreCard(player.toParticipantDto());
            boolean wantDraw = InputView.inputAnswer();
            showCurrentCardWhenStopDraw(player, wantDraw);
            return wantDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player);
        }
    }

    private void showCurrentCardWhenStopDraw(Player player, boolean wantDraw) {
        if (!wantDraw) {
            OutputView.showCards(player.toParticipantDto());
        }
    }

    private void dealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canDraw()) {
            dealer.draw(cardDeck.drawCard());
            OutputView.dealerReceiveOneCard();
        }
    }

    private void showResult(Players players, Dealer dealer) {
        OutputView.showAllCards(players.toPlayersDto(), dealer.toParticipantDto());
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        OutputView.showFinalResult(blackJackResult.toDealerResultDto(), blackJackResult.toPlayersResultDto());
    }
}
