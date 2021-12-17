import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


class Person()
{
	Integer money = 0;
	public loan(Integer amount)
	{
		money += amount;
	}

}


class Bank() 
{
	public loan(Integer amount)
	{
		money -= amount;
	}

	public static void simpleSubmit(String[] args, int POOL_SIZE) throws Exception {
		int THREAD_COUNT = 10;
		int STEP_COUNT = 10_000;

		var pool = Executors.newFixedThreadPool(POOL_SIZE);

		for (int i = 0; i < THREAD_COUNT; i++) {
			var threadIdx = i;
			pool.submit(() -> {
				for (int j = 0; j < STEP_COUNT; j++) {
					System.out.printf("Thread %d step %d%n", threadIdx, j);
				}
			});
		}

}

class Main
{

	public static void simpleSubmit(String[] args, int POOL_SIZE) throws Exception {
		int THREAD_COUNT = 10;
		int STEP_COUNT = 10_000;

		var pool = Executors.newFixedThreadPool(POOL_SIZE);

		for (int i = 0; i < THREAD_COUNT; i++) {
			var threadIdx = i;
			pool.submit(() -> {
				for (int i = 0; i < THREAD_COUNT; i++) {
					Person emberunk = new Person();
					pool.submit(() -> {
						for (int j = 0; j < STEP_COUNT; j++) {
							emberunk.loan(ThreadLocalRandom.nextInt());
						}
					});
				}

				}
			});
		}


	public static void main(String[] args) {
		Bank bank = new Bank();
		ArrayList<Person> people = new ArrayList<>();
		for(int idx=0; idx<10; idx++)
		{
			people.add(new Person());
		}
	}


}


