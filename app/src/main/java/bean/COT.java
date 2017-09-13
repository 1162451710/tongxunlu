package bean;

/**
 * Created by zhanglun on 2017/9/12.
 */

public class COT {
    private String displayName;
    private String number;

    public COT(String displayName, String number){
        this.displayName=displayName;
        this.number=number;
    }

    public COT(){}
    public String getDisplayName(){return displayName;}
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }
    @Override
    public String toString(){
        return "displayName" + displayName + "\n" +"number"+ number;
    }

}


