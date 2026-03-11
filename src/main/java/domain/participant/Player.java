package domain.participant;

import domain.BattingAmount;
import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Player {
    private final Participant participant;
    private final BattingAmount battingAmount;

    private Player(Participant participant, BattingAmount battingAmount) {
        this.participant = participant;
        this.battingAmount = battingAmount;
    }

    public static Player from(String name, int amount) {
        Participant participant = new Participant(new Name(name), new Hand());
        return new Player(participant, new BattingAmount(amount));
    }

    public void playTurn(Deck deck){
        participant.playTurn(deck);
    }

    public String getName(){
        return this.participant.getName();
    }

    public boolean isBust(){
        return participant.isBust();
    }

    public Participant getParticipant() {
        return participant;
    }

    public List<Card> getCards() {
        return this.participant.getCards();
    }

    public int getScore() {
        return participant.getScore();
    }

    public void initHand(Deck deck) {
        participant.initHand(deck);
    }
}
