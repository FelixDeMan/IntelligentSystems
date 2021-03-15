package agents;

public class QLearnerNash implements Agent {

    private double Q[][], alpha, alphadecay, temp, tempdecay, gamma;
    private int numberOfActions;

    public QLearnerNash(int numberOfActions) {
        Q = new double[numberOfActions][numberOfActions];
        for (int i=0; i<numberOfActions; i++) {
            for (int j=0; j<numberOfActions; j++) Q[i][j] = 0; }
        temp = 0.2;
        tempdecay = 0.8;
        alpha = 0.01;
        alphadecay = 0.98;
        gamma = 0.01;
    }

    public double actionProb(int index) {
        double sum = 0;
        for(int i=0; i<numberOfActions; i++) {
            sum += actionProb(i);
        }
        return gamma * sum;
    }

    public int selectAction() {
        temp *= tempdecay;
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
        for (int i=0; i<numberOfActions; i++) { Q[index][i] = (1-alpha) * Q[index][i] + alpha * (reward + gamma * Q[index][i]); }
    }

    @Override
    public double getQ(int i) {
        return Math.max(Q[i][0], Q[i][1]);
    }

}
