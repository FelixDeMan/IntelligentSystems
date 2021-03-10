import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SmarterWolf implements Wolf {
    private int sizePack;
    private boolean firstRound = false;
    private boolean wolfGang = false;

    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        int[] mymove = new int[2];
        if (!firstRound){
            sizePack = wolvesSight.size();
            firstRound = true;
        }
        if (!wolfGang){
            if(Math.abs(wolvesSight.get(1)[0]) + Math.abs(wolvesSight.get(1)[1]) <= 5){
                System.out.println("Gang gang");
                wolfGang = true;
            }
            if(wolvesSight.get(1)[0] > 0){
                mymove[0] = -1;
            }
            else{
                mymove[0] = 1;
            }

            if(wolvesSight.get(1)[0] > 0){
                mymove[1] = -1;
            }
            else{
                mymove[1] = 1;
            }
        }
        if (wolfGang){


            if(preysSight.size() < 1){ //if no prey in sight, move diagonally
                System.out.println("NO target");
                mymove[0] = 1;
                mymove[1] = 1;
            }
            else{
                System.out.println("Target locked");

                if(preysSight.get(0)[0] > 0){
                    mymove[0] = -1;
                }
                else {
                    mymove[0] = 1;
                }
                if(preysSight.get(0)[1] > 0){
                    mymove[1] = -1;
                }
                else {
                    mymove[1] = 1;
                }

            }
            if(Math.abs(wolvesSight.get(1)[0]) + Math.abs(wolvesSight.get(1)[1])> 6){
                System.out.println("Other wolf in chase");
                if (wolvesSight.get(1)[0] > 0){
                    mymove[0] = -1;
                }
                else{
                    mymove[0] = 1;
                }

                if(wolvesSight.get(1)[0] > 0){
                    mymove[1] = 1;
                }
                else{
                    mymove[1] = -1;
                }
            }
        }
        //System.out.println(wolvesSight.get(0)[0] +" "+ wolvesSight.get(0)[1]);

        //Random r = new Random();

        //mymove[0] = r.nextInt(3)-1;
        //mymove[1] = r.nextInt(3)-1;
        return mymove;

    }

    @Override
    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        return 0;
    }
}
