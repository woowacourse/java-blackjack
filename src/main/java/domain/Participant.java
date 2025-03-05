package domain;

public interface Participant {
    
    void receive(Card card);

    int getScore();
}
