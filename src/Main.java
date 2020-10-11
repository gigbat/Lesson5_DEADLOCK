public class Main {
    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;

        MyThreadA myThreadA = new MyThreadA();
        MyThreadB myThreadB = new MyThreadB();

        myThreadA.resourceA = resourceA;
        myThreadB.resourceB = resourceB;

        myThreadA.start();
        myThreadB.start();
    }
}

class MyThreadA extends Thread {
    ResourceA resourceA;
    @Override
    public void run() {
        System.out.println(resourceA.getI());
    }
}

class MyThreadB extends Thread {
    ResourceB resourceB;
    @Override
    public void run() {
        System.out.println(resourceB.getI());
    }
}

class ResourceA {
    ResourceB resourceB;

    public synchronized int getI() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resourceB.returnI();
    }

    public synchronized int returnI() {
        return 1;
    }
}

class ResourceB {
    ResourceA resourceA;
    public synchronized int getI() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resourceA.returnI();
    }

    public synchronized int returnI() {
        return 2;
    }
}
