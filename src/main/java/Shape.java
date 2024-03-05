import java.util.List;

public enum Shape {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER;

    public static List<Shape> giveShapes(){
        return List.of(values());
    }
}
