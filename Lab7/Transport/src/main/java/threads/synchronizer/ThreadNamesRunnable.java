package threads.synchronizer;

public class ThreadNamesRunnable implements Runnable{

    private TransportSynchronizer transportSynchronizer;

    public ThreadNamesRunnable(TransportSynchronizer synchronizer) {
        this.transportSynchronizer = synchronizer;
    }

    @Override
    public void run() {
        while (transportSynchronizer.canPrintModel()){
            try {
                transportSynchronizer.printModel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
