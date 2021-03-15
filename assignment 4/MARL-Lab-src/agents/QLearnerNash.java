package agents;

public class QLearnerNash implements Agent {

    private double Q[][], alpha, alphadecay, gamma, nashQ[], reward, pi[][];
    private int numberOfActions;

    public QLearnerNash(int numberOfActions) {
        Q = new double[numberOfActions][numberOfActions];
        nashQ = new double[numberOfActions];
        pi = new double[numberOfActions][numberOfActions];
        for (int i=0; i<numberOfActions; i++) {
            nashQ[i] = 0;
            for (int j = 0; j < numberOfActions; j++) {
                Q[i][j] = 0;
                pi[i][j] = (1 / ((double) numberOfActions));
            }
            
        }

        alpha = 0.01;
        alphadecay = 0.98;
        gamma = 0.01;
        reward = 0;
    }

    public double actionProb(int index) {
        double mult = 0, max = -1000000;
        System.out.println(numberOfActions);
        for (int i=0; i<numberOfActions; i++) {
            System.out.println("test");
            System.out.println(Math.max(pi[index][i], pi[index][i+1]));

            max = Math.max(pi[index][i], pi[index][i+1]);
           // System.out.println(pi[index][i] + " " + pi[index][i+1]);
           // System.out.println(max);
        }
       // System.out.println(max);
        for (int i=0; i<numberOfActions; i++) {
            mult *= pi[index][i];
            pi[index][i] = Math.max(pi[index][i], nashQ[index]);
        }
        nashQ[index] = mult * max;
       // System.out.println(max);
        return max;
    }

    public int selectAction() {
        double target = Math.random();
        double collected = actionProb(0);
        int index = 1;
        while (collected < target && index<Q.length)
            collected += actionProb(index++);
        return index-1;
    }

    public void update(int own, int other, double reward) {
        update(own,reward);
    }

    private void update(int index, double reward) {
        for (int i=0; i<numberOfActions; i++) {
            Q[index][i] = (1-alpha) * Q[index][i] + alpha * (reward + gamma * nashQ[index]);
        }
        alpha*=alphadecay;
    }

    @Override
    public double getQ(int i) {
        return Math.max(Q[i][0], Q[i][1]);
    }

}
