import java.util.ArrayList;

public class Store {
    private ArrayList<PresentType> presents = new ArrayList<PresentType>();
    private static final int startAmount = 1000;
    int[] gifts = new int[PresentType.values().length]

    public Store()
    {
        for(int idx=0; idx<PresentType.values().length; idx++)
        {
            gifts[idx] = startAmount;
        }
    }

    public buyPresent(PresentType p, int amount )
    {
        synchronized()
    }

    @Override
    public String toString() {
        int idx=0;
        String ret = "";
        for(PresentType a : PresentType.values())
        {
            ret += (a.toString() + "pieces:" + gifts[idx++]); 
        }
        return ret;
    }
}
