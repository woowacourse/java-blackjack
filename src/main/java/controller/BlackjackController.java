package controller;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

import domain.Card;
import domain.CardHand;
import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final List<Player> players = inputPlayers();
        final Dealer dealer = new Dealer();
        final Deck deck = Deck.createShuffledDeck();
        handOutCards(players, dealer, deck);

    }

    private List<Player> inputPlayers() {
        final List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    private void handOutCards(final List<Player> players, final Dealer dealer, final Deck deck) {
        players.forEach(player -> {
            player.pickCard(deck);
            player.pickCard(deck);
        });
        dealer.pickCard(deck);
        dealer.pickCard(deck);
        outputHandOut(players, dealer);
    }

    private void outputHandOut(final List<Player> players, final Dealer dealer) {
        List<String> names = players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
        outputView.printHandOutIntroduce(names);

        Participant dealerParticipant = dealer.getParticipant();
        outputView.printDealerHandOutResult(getConvertedDealerCardText(dealerParticipant));

        players.stream()
            .map(player -> new AbstractMap.SimpleEntry<>(player.getName(),
                getConvertedPlayerCardText(player.getParticipant())))
            .forEach(player -> outputView.printPlayerCards(player.getKey(), player.getValue()));
    }

    private String getConvertedDealerCardText(Participant dealerParticipant) {
        CardHand dealerHand = dealerParticipant.getHand();
        Card dealerFirstCard = dealerHand.hand().getFirst();
        return getConvertedCardText(dealerFirstCard);
    }

    private String getConvertedCardText(Card dealerFirstCard) {
        String cardNumberText = CardNumberToTextConverter.convert(dealerFirstCard.number());
        String cardEmblemText = dealerFirstCard.emblem().getName();
        return cardNumberText + cardEmblemText;
    }

    private List<String> getConvertedPlayerCardText(Participant dealerParticipant) {
        CardHand hand = dealerParticipant.getHand();
        return hand.hand().stream().map(this::getConvertedCardText).toList();

    }
}
