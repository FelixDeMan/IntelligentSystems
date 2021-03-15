import java.util.List;


public class SmartWolf implements Wolf {
    private int sizePack;
    private boolean firstRound = false;
    private boolean wolfGang = false;

    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        int[] mymove = new int[2];
        if (!firstRound) {
            sizePack = wolvesSight.size();
            firstRound = true;
        }

        if (preysSight.size() < 1) { //if no prey in sight, move diagonally
            System.out.println("NO target");
            mymove[0] = 1;
            mymove[1] = 1;
        }
        else {
            System.out.println("Target locked");

            if (preysSight.get(0)[0] > 0) {
                mymove[0] = -1;
            } else {
                mymove[0] = 1;
            }
            if (preysSight.get(0)[1] > 0) {
                mymove[1] = -1;
            } else {
                mymove[1] = 1;
            }
        }


            return mymove;

        }


    @Override
    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        return 0;
    }
}
