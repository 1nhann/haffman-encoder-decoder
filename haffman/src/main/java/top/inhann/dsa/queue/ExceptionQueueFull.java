package top.inhann.dsa.queue;

public class ExceptionQueueFull extends RuntimeException{
    public ExceptionQueueFull(String err){
        super(err);
    }
}