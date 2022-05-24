package threads.synchronizer;

public class ThreadPricesRunnable implements Runnable{

    private TransportSynchronizer transportSynchronizer;

    public ThreadPricesRunnable(TransportSynchronizer synchronizer) {
        this.transportSynchronizer = synchronizer;
    }

    @Override
    public void run() {
        while (transportSynchronizer.canPrintPrice()){
            try {
                transportSynchronizer.printPrice();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
