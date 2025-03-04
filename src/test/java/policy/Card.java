package policy;

public class Card {
    String shape;
    String number;

    public Card(String shape, String number) {
        this.shape = validate(shape);
        this.number = number;
    }

    private String validate(String shape) {
        if(!shape.equals("스페이드")){
            throw new IllegalArgumentException("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
        }

        if(!shape.equals("다이아몬드")){
            throw new IllegalArgumentException("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
        }

        if(!shape.equals("하트")){
            throw new IllegalArgumentException("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
        }

        if(!shape.equals("클로버")){
            throw new IllegalArgumentException("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
        }
        return shape;
    }
}
