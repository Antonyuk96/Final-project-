package services;

public class counter implements autocloseable{
    static int sum;
    {
        sum = 0;
    }

    public void add() {
        sum++;
    }
    public int getSum(){
        return sum;
    }

    @Override
    public void close() {
        System.out.println("Счётчик закрыт");
    }
}