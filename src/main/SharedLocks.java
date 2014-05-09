package main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedLocks {
	public static final Lock movementThreadLock = new ReentrantLock();
}
