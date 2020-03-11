package model;

public interface Strategy {
    Card draw();

    static Card draw2(Symbol symbol, Type type){
        return new Card(symbol, type);
    }
}
