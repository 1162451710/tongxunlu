package bean;

/**
 * Created by zhanglun on 2017/9/12.
 */

public class COT {
    private String displayName;
    private long number;

    public COT(String displayName,long number){
        this.displayName=displayName;
        this.number=number;
    }

    public COT(){}
    public String getDisplayName(){return displayName;}
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public long getNumber() {
        return number;
    }

    public void setNumber(long number){
        this.number = number;
    }
    @Override
    public String toString(){
        return displayName + "\n" + number;
    }

}


