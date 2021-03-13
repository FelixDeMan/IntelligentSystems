package agents;

public class QLearnerNash implements Agent {

    private double Q[], alpha, alphadecay, temp, tempdecay;

    public QLearnerNash(int numberOfActions) {
        Q = new double[numberOfActions];
        for (int i=0; i<numberOfActions; i++)
            Q[i] = 0;
        temp = 0.1;
        tempdecay = 1.0;
        alpha = 0.01;
        alphadecay = 1.0;
    }

    public double actionProb(int index) {
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
    }

    @Override
    public double getQ(int i) {
        return Q[i];
    }

}
