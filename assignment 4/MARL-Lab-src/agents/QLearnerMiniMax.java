package agents;

public class QLearnerMiniMax implements Agent {


    private double Q[], alpha, alphadecay, V[], pi[][], gamma, target;
    private int numberOfActions;

    public QLearnerMiniMax(int numberOfActions) {
        Q = new double[numberOfActions];
        V = new double[numberOfActions];
        pi = new double[numberOfActions][numberOfActions];
        for (int i=0; i<numberOfActions; i++) {
            Q[i] = 1;
            V[i] = 1;
            for (int j=0; j<numberOfActions; j++){ pi[i][j] = 1/numberOfActions;}
        }
        alpha = 0.01;
        alphadecay = 0.98;
        gamma = 0.01;
    }

    public double actionProb(int index) {
        double sum = 0;

        for (int i=0; i<numberOfActions-1; i++) { sum += Math.min(pi[index][i], pi[index][i+1]) * Q[index]; }
        V[index] = sum;
        for (int i=0; i<numberOfActions; i++) { pi[index][i] = Math.max(pi[index][i], V[index]); }
        double max = -1000000;
        for (int i=0; i<numberOfActions-1; i++) { max = Math.max(pi[index][i], pi[index][i+1]); }
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
        update(own, reward); }

    private void update(int index, double reward) {
        Q[index] = (1-alpha) * Q[index] + alpha * (reward + gamma * V[index]);
        alpha*=alphadecay;
    }

    @Override
    public double getQ(int i) {
        return Q[i];
    }

}
