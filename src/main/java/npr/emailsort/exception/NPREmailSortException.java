package npr.emailsort.exception;

public class NPREmailSortException extends RuntimeException{
    private int statusCode;

    public NPREmailSortException(){
        this.statusCode = 500;
    }

    public NPREmailSortException(int statusCode){
        this.statusCode = statusCode;
    }
}
