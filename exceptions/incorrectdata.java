package exceptions;

public class incorrectdata extends RuntimeException{
    public incorrectdata (String msg) {
        super(msg);
    }
}