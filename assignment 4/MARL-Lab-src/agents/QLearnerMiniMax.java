package agents;

public class QLearnerMiniMax implements Agent {


    private double Q[], alpha, alphadecay, temp, tempdecay, V[], pi[][], gamma, target;
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
        temp = 0.2;
        tempdecay = 0.99;
        alpha = 0.01;
        alphadecay = 1.0;
        gamma = 0.8;
    }

    public double actionProb(int index) {
        double sum = 0;

        for (int i=0; i<numberOfActions; i++) { sum += pi[index][i] * Q[index]; }
        V[index] = sum;
        for (int i=0; i<numberOfActions; i++) { pi[index][i] = Math.max(pi[index][i], pi[index][i] * V[index]); }
        double max = 0;
        for (int i=0; i<numberOfActions-1; i++) { max = Math.max(pi[index][i], pi[index][i+1]); }
        return max;
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
