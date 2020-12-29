package top.inhann.dsa.queue;

public class ExceptionQueueEmpty extends RuntimeException{
    public ExceptionQueueEmpty(String err){
        super(err);
    }
}