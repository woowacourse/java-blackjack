package model;

public class TempDeck implements Strategy{
    @Override
    public Card draw() {
        return new Card(Symbol.ACE, Type.CLUB);
    }

}
