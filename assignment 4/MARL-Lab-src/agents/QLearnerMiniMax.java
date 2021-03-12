package agents;

public class QLearnerMiniMax implements Agent {


    private double Q[], alpha, alphadecay, temp, tempdecay, V[], pi[][], gamma;
    private int numberOfActions;

    public QLearnerMiniMax(int numberOfActions) {
        Q = new double[numberOfActions];
        V = new double[numberOfActions];
        pi = new double[numberOfActions][numberOfActions];
        for (int i=0; i<numberOfActions; i++) {
            Q[i] = 1;
            V[i] = 1;
            for (int j=0; j<numberOfActions; j++) {pi[j][i] = 1/numberOfActions;}
        }
        temp = 0.1;
        tempdecay = 1.0;
        alpha = 1.0;
        alphadecay = 1.0;
        gamma = 1.0;
    }

    public double actionProb(int index) {
        double sum = 0.0;
        for (double a : Q)
            sum += Math.exp(a/temp);
        return Math.exp(Q[index]/temp)/sum;
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
        update1(own, other, reward);
    }

    private void update1(int index, int nextindex, double reward) {
        Q[index] = (1-alpha) * Q[index] + alpha * (reward + gamma * V[nextindex]);
        double sum = 0;
        for (int i=0; i<numberOfActions; i++) { sum = sum + pi[index][selectAction()] * Q[selectAction()];}

        for (int i=0; i<numberOfActions; i++) {
            pi[index][i] = Math.max(pi[nextindex][i], Math.min(actionProb(i), sum));
            V[index] = Math.min(nextindex, sum);
        }
        alpha*=alphadecay;
    }

    @Override
    public double getQ(int i) {
        return Q[i];
    }

}
