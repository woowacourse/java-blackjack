package blackjack.service;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.dto.CardDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantResultDto;

public class DealerService {

    private final Deck deck;
    private final Dealer dealer;

    public DealerService(final Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer();
    }

    public void initCards() {
        dealer.initCards(deck);
    }

    public CardDto findFirstCard() {
        return CardDto.from(dealer.openFirstCard());
    }

    public DealerTurnResultDto progressTurn() {
        int count = 0;
        
        while (dealer.canDrawCard()) {
            dealer.hit(deck);
            count++;
        }
        return new DealerTurnResultDto(count);
    }

    public ParticipantResultDto getResult() {
        return ParticipantResultDto.from(dealer);
    }

    public int getScore() {
        return dealer.getScore();
    }
}
