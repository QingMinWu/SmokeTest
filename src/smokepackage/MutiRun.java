package smokepackage;


class MutiRun extends Thread {

	 

    public MutiRun() {

 

    }

 

    public MutiRun(String name) {

        this.name = name;

    }

 

    public void run() {

        for (int i = 0; i < 5; i++) {

            System.out.println(name + "ÔËÐÐ     " + i);

        }

    }

 

    public static void main(String[] args) {

    	MutiRun h1=new MutiRun("A");

    	MutiRun h2=new MutiRun("B");

        h1.start();

        h2.start();

    }

 

    private String name;

}