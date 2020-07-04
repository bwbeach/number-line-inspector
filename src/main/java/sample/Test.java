package sample;

public class Test {
    public double fractionToPercentage(double f) {
        if (f > 1.0) {
            throw new RuntimeException("too big");
        }
        return f * 100.0;
    }
}
