package springbook.user.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionHandler implements InvocationHandler {
	private Object target;
	private PlatformTransactionManager transacntionManager;
	private String pattern;	

	public void setTarget(Object target) {
		this.target = target;
	}
	
	public void setTransacntionManager(PlatformTransactionManager transacntionManager) {
		this.transacntionManager = transacntionManager;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().startsWith(pattern)) {
			return invokeInTransaction(method, args);
		} else {
			return method.invoke(target, args);
		}
	}
	
	private Object invokeInTransaction(Method method, Object[] args) throws Throwable {
		TransactionStatus status = this.transacntionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			Object ret = method.invoke(target, args);
			this.transacntionManager.commit(status);
			return ret;
		} catch (InvocationTargetException e) {
			this.transacntionManager.rollback(status);
			throw e.getTargetException();
		}
	}

}
